
package org.geoint.lbac.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Explicitly identifies a class as cachable.  
 * 
 * A Cachable object infers it's thread-safe (in other words, a Cacheable 
 * object MUST be thread-safe).
 * 
 * @see DoNotCache
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cacheable {

}
