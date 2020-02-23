package org.jlog.slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.ILoggerFactory;

public final class JLogLoggerFactory implements ILoggerFactory
{
	private final ConcurrentMap<String, JLog> loggers;

	public JLogLoggerFactory() {
		loggers = new ConcurrentHashMap<String, JLog>();
	}

	@Override
	public JLog getLogger(final String name) {
		JLog logger = loggers.get(name);
		if (logger == null) {
			JLog newLogger = new JLog(name);
			JLog existingLogger = loggers.putIfAbsent(name, newLogger);
			return existingLogger == null ? newLogger : existingLogger;
		} else {
			return logger;
		}
	}

}
