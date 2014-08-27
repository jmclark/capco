package org.geoint.capco.impl.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.geoint.capco.CapcoException;
import org.geoint.capco.marking.component.CountryComponent;
import org.geoint.capco.marking.SecurityMarking;

/**
 * Supports the rules associated with countries/aliases in CAPCO.
 *
 * @param <C>
 */
public class CountryComponentPolicy<C extends CountryComponent>
        implements ComponentPolicy<C> {

    private final Map<String, C> components = new HashMap<>(); //key=code
    private final SecurityMarkingComponentExtractor<C> extractor;

    //indicies
    private final Map<String, Set<String>> countries = new HashMap<>(); //key=country, value=list of assoc aliases
    private final Map<String, String[]> aliasIndex = new HashMap<>(); //key=alias, value=countries

    //locks
    protected final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(false);
    protected final Lock readLock = rwl.readLock();
    protected final Lock writeLock = rwl.writeLock();

    public CountryComponentPolicy(SecurityMarkingComponentExtractor<C> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void addComponent(C c) throws CapcoException {
        writeLock.lock();
        try {
            final String code = c.getCountry().getCode();

            if (components.containsKey(code)) {
                return;
            }
            components.put(code, c);

            final String[] alias = c.getCountry().getAlias();

            if (alias != null) {
                //this is a country alias
                aliasIndex.put(code, alias);

                //add the country reverse index
                for (String a : alias) {
                    getCountryAssocAliases(a).add(code);
                }
            } else {
                //is not an alias
                //creates the reference in the index if not already there
                getCountryAssocAliases(code);
            }
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public boolean isComponentString(String component) {
        readLock.lock();
        try {
            return countries.containsKey(component) || aliasIndex.containsKey(component);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public C getComponent(String component) {
        readLock.lock();
        try {
            return components.get(component);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public C[] getComponents() {
        readLock.lock();
        try {
            return (C[]) components.values().toArray();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Figures out which countries/aliases could still be selected based on the
     * current counties/aliases already selected.
     *
     * @param marking
     * @return
     */
    @Override
    public C[] getAvailable(SecurityMarking marking) {
        final C[] assigned = extractor.extract(marking);
        Arrays.sort(assigned);
        List<C> available = new ArrayList<>();

        readLock.lock();
        try {
            componentLoop:
            for (C c : components.values()) {
                if (Arrays.binarySearch(assigned, c) < 0) {
                //component isn't already assigned

                    //check if the component is a member of an assigned alias
                    for (String alias : getCountryAssocAliases(c.getCountry().getCode())) {
                        if (Arrays.binarySearch(assigned, components.get(alias)) >= 0) {
                            //alias is assigned
                            continue componentLoop; //don't add the component
                        }
                    }

                    available.add(c); //not already assigned or member of an assigned alias
                }
            }
        } finally {
            readLock.unlock();
        }

        return (C[]) available.toArray();
    }


    /*
     * returns the associated aliases of a country, creating the index if it
     * doesn't already exist
     */
    private Set<String> getCountryAssocAliases(String code) {
        if (!countries.containsKey(code)) {
            countries.put(code, new HashSet<String>());
        }
        return countries.get(code);
    }

}
