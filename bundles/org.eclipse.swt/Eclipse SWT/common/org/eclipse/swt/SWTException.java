package org.eclipse.swt;

/*
 * Copyright (c) 2000, 2002 IBM Corp.  All rights reserved.
 * This file is made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */

/**
 * This runtime exception is thrown whenever a recoverable error
 * occurs internally in SWT. The message text and error code 
 * provide a further description of the problem. The exception
 * has a <code>throwable</code> field which holds the underlying
 * exception that caused the problem (if this information is
 * available (i.e. it may be null)).
 * <p>
 * SWTExceptions are thrown when something fails internally,
 * but SWT is left in a known stable state (eg. a widget call
 * was made from a non-u/i thread, or there is failure while
 * reading an Image because the source file was corrupt).
 * </p>
 *
 * @see SWTError
 */

public class SWTException extends RuntimeException {
	public int code;
	public Throwable throwable;
	
/**
 * Constructs a new instance of this class with its 
 * walkback filled in. The error code is set to an
 * unspecified value.
 */
public SWTException () {
	this (SWT.ERROR_UNSPECIFIED);
}

/**
 * Constructs a new instance of this class with its 
 * walkback and message filled in. The error code is
 * set to an unspecified value.
 *
 * @param message the detail message for the exception
 */
public SWTException (String message) {
	this (SWT.ERROR_UNSPECIFIED, message);
}

/**
 * Constructs a new instance of this class with its 
 * walkback and error code filled in.
 *
 * @param code the SWT error code
 */
public SWTException (int code) {
	this (code, SWT.findErrorText (code));
}

/**
 * Constructs a new instance of this class with its 
 * walkback, error code and message filled in.
 *
 * @param code the SWT error code
 * @param message the detail message for the exception
 */
public SWTException (int code, String message) {
	super (message);
	this.code = code;
}

/**
 *  Returns the string describing this SWTException object.
 *  <p>
 *  It is combined with the message string of the Throwable
 *  which caused this SWTException (if this information is available).
 *  </p>
 *  @return the error message string of this SWTException object
 */
public String getMessage() {
	if (throwable == null)
		return super.getMessage();
	else
		return super.getMessage() + " (" + throwable.toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
}

/**
 * Outputs a printable representation of this exception's
 * walkback on the standard error stream.
 * <p>
 * Note: printStackTrace(PrintStream) and printStackTrace(PrintWriter)
 * are not provided in order to maintain compatibility with CLDC.
 * </p>
 */
public void printStackTrace() {
	super.printStackTrace();
	if (throwable != null) {
		System.err.println("*** Stack trace of contained exception ***"); //$NON-NLS-1$
		throwable.printStackTrace();
	}
}

}


