package org.bflow.toolbox.hive.libs.aprogu.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class LogWriter {
	
	public static <TClass> ILogWriter createInstance(Class<TClass> cls) {
		return new LogWriterImpl<TClass>(cls);
	}

	static class LogWriterImpl<TClass> implements ILogWriter {
		
		private final Log fLog;

		public LogWriterImpl(Class<TClass> cls) {
			fLog = LogFactory.getLog(cls);
		}
		
		@Override
		public void error(String message) {
			errorFormat(message, new Object[0]);
		}
		
		@Override
		public void errorFormat(String messageFormat, Object... args) {
			if (!fLog.isErrorEnabled()) return;
			String message = String.format(messageFormat, args);
			fLog.error(message);
		}
	}
}
