package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;

public final class StaticLoggerBinder implements LoggerFactoryBinder
{
	public static String REQUESTED_API_VERSION = "2.0";
	private static final StaticLoggerBinder SINGLETON = new StaticLoggerBinder();

	private final ILoggerFactory factory;

	/** */
	private StaticLoggerBinder() {
		factory = new JLogLoggerFactory();
	}

	/**
	 * Gets the singleton instance of this static logger binder.
	 *
	 * @return Static logger binder instance
	 */
	public static StaticLoggerBinder getSingleton() {
		return SINGLETON;
	}

	@Override
	public ILoggerFactory getLoggerFactory() {
		return factory;
	}

	@Override
	public String getLoggerFactoryClassStr() {
		return factory.getClass().getName();
	}

}
