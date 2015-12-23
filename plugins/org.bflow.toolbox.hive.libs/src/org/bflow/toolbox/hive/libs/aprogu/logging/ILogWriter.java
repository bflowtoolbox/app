package org.bflow.toolbox.hive.libs.aprogu.logging;

public interface ILogWriter {
	
	void error(String message);
	
	void errorFormat(String messageFormat, Object...args);

}
