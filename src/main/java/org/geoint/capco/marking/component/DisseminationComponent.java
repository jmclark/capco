package org.geoint.capco.marking.component;

/**
 *
 */
public class DisseminationComponent implements MarkingComponent {

    private final AccmComponent[] accm;
    private final RelToComponent[] relTo;
    private final DisplayToComponent[] displayTo;
    private final StandardMarkingComponent[] dissem;
    
    @Override
    public String asPortion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String asBanner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(MarkingComponent o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
