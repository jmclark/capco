package org.geoint.lbac.cache;

/**
 * Thrown when an object is attempted to be cached, but caching is not supported
 * for an object of this type.
 *
 * @see DoNotCache
 */
public class NotCachableException extends RuntimeException {

    public NotCachableException() {
    }

    public NotCachableException(String message) {
        super(message);
    }

    public NotCachableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCachableException(Throwable cause) {
        super(cause);
    }

}
