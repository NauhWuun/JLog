package org.NauhWuun.Jlog;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Java SingleTon Pattern<p>
 *     @Initialization on demand holder IDiom<p>
 *     	   @Double checking<p>
 *         @Thread Safe<p>
 *	       @Vars Initialize (Lazy/Early initialization)<p>
 *	       @OnlyOne Single Object<p>
 *		   @Null exception For Object<p>
 *		   @Runtime exception<p>
 *		   @DeConstructure<p>
 *		   @Serialization<p>
 *		   @Many ClassLoaders<p>
 *		   @Cloning<p>
 *		   @Combination QueueObject<p>
 */
public class SingleTon<T> implements Serializable 
{
	@SuppressWarnings("rawtypes")
	private static ConcurrentHashMap<String, SingleTon> map_instance = new ConcurrentHashMap<String, SingleTon>();

	private static final long serialVersionUID = -540841140899265232L;

	T newT;

	private SingleTon() {
	}

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T GetInstance(final Class cls) throws NullPointerException, RuntimeException {
		T _internal_instance = null;

		SingleTon hash_instance = map_instance.get(cls.getName());

        if (hash_instance == null) {
        	try {
        		synchronized(SingleTon.class) {
        			if (hash_instance == null) {
        				_internal_instance = (T)cls.getDeclaredConstructor().newInstance();

        				hash_instance = new SingleTon();
        				hash_instance.newT = _internal_instance;

        				map_instance.put(cls.getName(), hash_instance);
        			}
        		}
        	} catch (final Exception e) {
        		e.printStackTrace();
        	}
        }

        return (T) hash_instance.newT;
    }

	@SuppressWarnings("rawtypes")
	public static final ConcurrentHashMap<String, SingleTon> DeConstrcorInterface() {
		return map_instance;
	}
}
