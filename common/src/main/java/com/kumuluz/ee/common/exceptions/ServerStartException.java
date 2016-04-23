package com.kumuluz.ee.common.exceptions;

/**
 *
 *
 * Server Start Exception
 *
 * Created by dewmal on 4/23/16.
 */
public class ServerStartException extends Exception {

    public ServerStartException() {
    }

    public ServerStartException(String message) {
        super(message);
    }

    public ServerStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerStartException(Throwable cause) {
        super(cause);
    }

    public ServerStartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
