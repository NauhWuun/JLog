package org.Jlog;

import java.util.concurrent.locks.ReentrantLock;

class Mutex
{
    private ReentrantLock mutexHandler;

	public Mutex() {
		mutexHandler = new ReentrantLock();
	}
	
	public void lock() {
		if (mutexHandler.tryLock())
			mutexHandler.lock();
	}
	
	public void unlock() {
		if (mutexHandler.isLocked())
			mutexHandler.unlock();
	}

	public boolean isLock() {
		return mutexHandler.isLocked();
	}
}
