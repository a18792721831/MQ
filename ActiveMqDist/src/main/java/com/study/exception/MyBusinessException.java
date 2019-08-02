package com.study.exception;

/**
 * @author jiayq
 */
public class MyBusinessException extends RuntimeException {

    private static final long serialVersionUID = 6605135472168286906L;

    public MyBusinessException() {
        super();
    }

    public MyBusinessException(String msg) {
        super(msg);
    }

    public MyBusinessException(Throwable e) {
        super(e);
    }

    public MyBusinessException(String msg, Throwable e) {
        super(msg, e);
    }
}
