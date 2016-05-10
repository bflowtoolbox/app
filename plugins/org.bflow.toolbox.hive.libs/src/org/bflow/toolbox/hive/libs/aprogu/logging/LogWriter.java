package org.bflow.toolbox.hive.libs.aprogu.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Provides methods to create instances of {@link ILogWriter}.
 * 
 * @author Arian Storch<arian.storch@bflow.org>
 * @since 2016-08-02
 * 
 */
public abstract class LogWriter {
	
	/**
	 * Creates an instance of {@link ILogWriter} for the given class.
	 * 
	 * @param cls
	 *            Class the logger is used by
	 * @return Newly created instance of {@link ILogWriter}
	 */
	public static <TClass> ILogWriter createInstance(Class<TClass> cls) {
		return new LogWriterImpl<TClass>(cls);
	}

	/**
	 * Anonymous implementation of {@link ILogWriter}.
	 * 
	 * @author Arian Storch<arian.storch@bflow.org>
	 * 
	 * @param <TClass>
	 *            Type of class the log writer is applied to
	 */
	static class LogWriterImpl<TClass> implements ILogWriter {
		
		private final Log fLog;

		/**
		 * Creates a new instance using the given class.
		 * 
		 * @param cls
		 *            Class the log writer is applied to
		 */
		public LogWriterImpl(Class<TClass> cls) {
			fLog = LogFactory.getLog(cls);
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.bflow.toolbox.hive.libs.aprogu.logging.ILogWriter#error(java.lang.String)
		 */
		@Override
		public void error(String message) {
			errorFormat(message, new Object[0]);
		}
		
		/*
		 * (non-Javadoc)
		 * @see org.bflow.toolbox.hive.libs.aprogu.logging.ILogWriter#errorFormat(java.lang.String, java.lang.Object[])
		 */
		@Override
		public void errorFormat(String messageFormat, Object... args) {
			if (!fLog.isErrorEnabled()) return;
			String message = String.format(messageFormat, args);
			fLog.error(message);
		}
	}
}
