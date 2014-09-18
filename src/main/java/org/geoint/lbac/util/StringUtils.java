
package org.geoint.lbac.util;

/**
 *
 */
public class StringUtils {

     public static String join(String[] s, String glue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append(s);
            if (i < s.length) {
                sb.append(glue);
            }
        }
        return sb.toString();
    }
     
}
