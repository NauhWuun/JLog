/*
MIT License

Copyright (c) 2020 ZC

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package core;

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
