package org.jlog.slf4j;

import org.slf4j.spi.MDCAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class JLogMdcAdapter implements MDCAdapter
{
	public JLogMdcAdapter() {
	}

	@Override
	public void put(final String key, final String value) {
		ThreadContext.put(key, value);
	}

	@Override
	public String get(final String key) {
		return ThreadContext.get(key);
	}

	@Override
	public void remove(final String key) {
		ThreadContext.remove(key);
	}

	@Override
	public void clear() {
		ThreadContext.clear();
	}

	@Override
	public Map<String, String> getCopyOfContextMap() {
		return new HashMap<String, String>(ThreadContext.getMapping());
	}

	@Override
	public void setContextMap(final Map<String, String> contextMap) {
		ThreadContext.clear();
		for (Entry<String, String> entry : contextMap.entrySet()) {
			ThreadContext.put(entry.getKey(), entry.getValue());
		}
	}
}
