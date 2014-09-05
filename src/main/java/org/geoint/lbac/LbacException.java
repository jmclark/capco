package org.geoint.lbac;

/**
 * Base exception type for the CAPCO classification library.
 */
public abstract class LbacException extends Exception {

    public LbacException() {
    }

    public LbacException(String message) {
        super(message);
    }

    public LbacException(String message, Throwable cause) {
        super(message, cause);
    }

    public LbacException(Throwable cause) {
        super(cause);
    }

}
