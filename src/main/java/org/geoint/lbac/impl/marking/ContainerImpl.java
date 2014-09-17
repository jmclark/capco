package org.geoint.lbac.impl.marking;

import java.util.HashMap;
import java.util.Map;
import org.geoint.lbac.marking.ComponentContainer;
import org.geoint.lbac.marking.SecurityComponent;
import org.geoint.lbac.policy.ContainerPolicy;

/**
 *
 * @param <C>
 */
public class ContainerImpl<C extends SecurityComponent>
        implements ComponentContainer<C> {

    private final ContainerPolicy policy;
    private final String portion;
    private final String banner;
    private final Map<String, C> components; //key=path

    private ContainerImpl(ContainerPolicy policy,
            String portion, String banner, C... components) {
        this.policy = policy;
        this.portion = portion;
        this.banner = banner;
        this.components = new HashMap<>();
        for (C c : components) {
            this.components.put(c.getPath(), c);
        }
    }

    public static <C extends SecurityComponent> ContainerImpl<C> 
        (ContainerPolicy policy, C... components) {
        
    }
        
    
    @Override
    public ContainerPolicy getPolicy() {
        return policy;
    }

    @Override
    public String getPath() {
        return policy.getPath();
    }

    @Override
    public String getName() {
        return policy.getContainerName();
    }

    @Override
    public String getPortion() {
        return portion;
    }

    @Override
    public String getBanner() {
        return banner;
    }

    @Override
    public C[] getComponents() {
        return (C[]) components.values().toArray();
    }

    @Override
    public C getComponent(String path) {
        return components.get(path);
    }

}
