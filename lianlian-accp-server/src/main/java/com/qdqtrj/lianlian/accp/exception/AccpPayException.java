package com.qdqtrj.lianlian.accp.exception;

/**
 * Accp支付平台异常
 *
 * @author yinbin
 * @version 1.0
 * @date 2021/3/16
 */
public class AccpPayException extends Exception {

    private static final long serialVersionUID = 4259830914189350738L;

    public AccpPayException() {
        super();
    }

    public AccpPayException(String str) {
        super("Accp支付平台异常, " + str);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A {@code null} value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     * @since 1.4
     */
    public AccpPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccpPayException(Exception e) {
        super(e);
    }
}
