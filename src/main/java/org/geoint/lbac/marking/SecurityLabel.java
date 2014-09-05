
package org.geoint.lbac.marking;

/**
 *
 */
public interface SecurityLabel {

    /**
     * Report the portion-marking formatted version of the label.
     * 
     * @return 
     */
    String getPortion();
    
    /**
     * Return the banner formatter version of the label.
     * @return 
     */
    String getBanner();
}
