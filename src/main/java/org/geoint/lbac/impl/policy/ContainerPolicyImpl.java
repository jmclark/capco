package org.geoint.lbac.impl.policy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import org.geoint.lbac.impl.marking.ContainerImpl;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.policy.ComponentPolicy;
import org.geoint.lbac.policy.ContainerPolicy;
import org.geoint.lbac.sort.AlphabeticalPortionComparator;

/**
 *
 *
 * @param <P> the policy type of the container security components
 * @param <C> the type of the contained security components
 */
public class ContainerPolicyImpl<P extends ComponentPolicy, C extends SecurityComponent>
        implements ContainerPolicy<P, C> {

    private final String path;
    private final String containerName;
    private final String portionPrefix;
    private final String bannerPrefix;
    private final String componentSeparator;
    private final Comparator<C> portionComparator;
    private final Comparator<C> bannerComparator;
    private final ComponentPolicy[] componentPolicies;

    public static final Comparator<? extends SecurityComponent> DEFAULT_PORTION_COMPARATOR
            = new AlphabeticalPortionComparator();
    public static final Comparator<? extends SecurityComponent> DEFAULT_BANNER_COMPARATOR
            = new AlphabeticalPortionComparator();

    private ContainerPolicyImpl(String path, String containerName,
            String portionPrefix, String bannerPrefix, String componentSeparator,
            Comparator<C> portionComparator, Comparator<C> bannerComparator,
            ComponentPolicy... componentPolicies) {
        this.path = path;
        this.containerName = containerName;
        this.portionPrefix = portionPrefix;
        this.bannerPrefix = bannerPrefix;
        this.componentSeparator = componentSeparator;
        this.portionComparator = portionComparator;
        this.bannerComparator = bannerComparator;
        this.componentPolicies = componentPolicies;
    }

    public static ContainerPolicyBuilder<?, ?> create(String path, String name) {
        return new ContainerPolicyBuilder(path, name);
    }

    @Override
    public String getContainerName() {
        return containerName;
    }

    @Override
    public String getPortionPrefix() {
        return portionPrefix;
    }

    @Override
    public String getBannerPrefix() {
        return bannerPrefix;
    }

    @Override
    public String getComponentSeparator() {
        return componentSeparator;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public Comparator<C> getPortionComparator() {
        return portionComparator;
    }

    @Override
    public Comparator<C> getBannerComparator() {
        return bannerComparator;
    }

    @Override
    public ContainerImpl<P, C> getComponent() {
        return ContainerImpl.instance(this);
    }

    @Override
    public Iterator iterator() {
        return Arrays.asList(componentPolicies).iterator();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.path);
        hash = 17 * hash + Objects.hashCode(this.containerName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContainerPolicyImpl<?, ?> other = (ContainerPolicyImpl<?, ?>) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (!Objects.equals(this.containerName, other.containerName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "container: " + containerName;
    }

    public static class ContainerPolicyBuilder<P extends ComponentPolicy, C extends SecurityComponent> {

        private final String path;
        private final String name;
        private String portionPrefix;
        private String bannerPrefix;
        private String componentSeparator;
        private Comparator<C> portionComparator;
        private Comparator<C> bannerComparator;
        private final Set<P> componentPolicies = new HashSet<>();

        public ContainerPolicyBuilder(String path, String name) {
            this.path = path;
            this.name = name;
        }

        public ContainerPolicyBuilder withPortionPrefix(String prefix) {
            this.portionPrefix = prefix;
            return this;
        }

        public ContainerPolicyBuilder withBannerPrefix(String prefix) {
            this.bannerPrefix = prefix;
            return this;
        }

        public ContainerPolicyBuilder withComponentSeparator(String separator) {
            this.componentSeparator = separator;
            return this;
        }

        public ContainerPolicyBuilder withComparators(Comparator<C> portion, 
                Comparator<C> banner) {
            this.portionComparator = portion;
            this.bannerComparator = banner;
            return this;
        }

        public ContainerPolicyBuilder add(P component) {
            this.componentPolicies.add(component);
            return this;
        }

        public ContainerPolicyImpl<P, C> build() {
            return new ContainerPolicyImpl(
                    this.path,
                    this.name,
                    (this.portionPrefix != null) ? this.portionPrefix : DEFAULT_PORTION_PREFIX,
                    (this.bannerPrefix != null) ? this.bannerPrefix : DEFAULT_BANNER_PREFIX,
                    (this.componentSeparator != null) ? this.componentSeparator : DEFAULT_SEPARATOR,
                    (this.portionComparator != null) ? this.portionComparator : DEFAULT_PORTION_COMPARATOR,
                    (this.bannerComparator != null) ? this.bannerComparator : DEFAULT_BANNER_COMPARATOR,
                    this.componentPolicies.toArray(new ComponentPolicy[this.componentPolicies.size()])
            );
        }
    }
}
