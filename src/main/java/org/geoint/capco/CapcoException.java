package org.geoint.capco;

/**
 * Base exception type for the CAPCO classification library.
 */
public abstract class CapcoException extends Exception {

    public CapcoException() {
    }

    public CapcoException(String message) {
        super(message);
    }

    public CapcoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CapcoException(Throwable cause) {
        super(cause);
    }

}
