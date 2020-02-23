package org.slf4j.impl;

import org.slf4j.spi.MDCAdapter;

public final class StaticMDCBinder
{
	/**
	 * Singleton instance of this static MDC binder.
	 */
	public static final StaticMDCBinder SINGLETON = new StaticMDCBinder();

	private MDCAdapter adapter;

	/** */
	private StaticMDCBinder() {
		this.adapter = new JLogMdcAdapter();
	}

	/**
	 * Gets the MDC adapter implementation.
	 * 
	 * @return Instance of MDC adapter
	 */
	public MDCAdapter getMDCA() {
		return adapter;
	}

	/**
	 * Gets the fully-qualified MDC adapter class name.
	 * 
	 * @return Fully-qualified class name of MDC adapter
	 */
	public String getMDCAdapterClassStr() {
		return adapter.getClass().getName();
	}

}
