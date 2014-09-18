
package org.geoint.lbac.cache;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Explicitly identifies a class that is NOT cachable.
 * 
 * Unlike {@link Cacheable}, annotating a type with DoNotCache does not 
 * infer its thread-safe status.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DoNotCache {

}
