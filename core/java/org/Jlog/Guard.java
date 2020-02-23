package org.Jlog;

class Guard
{
	private Mutex _lock;
	
	public Guard(final Guard g) {
		_lock = g._lock;
    }

    public Guard(Mutex m)  {
        this._lock = m;
		m.lock();
	}
	
    public void unGuard() {
        if (_lock.isLock()) 
			_lock.unlock();
    }
}
