package com.bds.portal.util;

import org.apache.log4j.Logger;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {
	private static Logger LOGGER = Logger.getLogger(ThreadPools.class);

	private final static Map<Class<?>, ThreadPoolExecutor> MAP = new Hashtable<Class<?>, ThreadPoolExecutor>();
	private final static Object lock = new Object();

	public static ExecutorService getThreadPool(Object key, int thread) {
		Class<?> k = key.getClass();
		ThreadPoolExecutor pool = null;
		synchronized (lock) {
			pool = MAP.get(k);
			if (pool == null) {
				pool = new ThreadPoolExecutor(thread, thread, 1L, TimeUnit.MILLISECONDS,
						new ArrayBlockingQueue<Runnable>(1), new ThreadPoolExecutor.DiscardPolicy());
				MAP.put(k, pool);
			}
		}
		return pool;
	}
	
	public static ExecutorService getThreadPool(String key, int thread) {
		Class<?> k = key.getClass();
		ThreadPoolExecutor pool = null;
		synchronized (lock) {
			pool = MAP.get(k);
			if (pool == null) {
				pool = new ThreadPoolExecutor(thread, thread, 1L, TimeUnit.MILLISECONDS,
						new ArrayBlockingQueue<Runnable>(100), new ThreadPoolExecutor.DiscardPolicy());
				MAP.put(k, pool);
			}
		}
		return pool;
	}
}
