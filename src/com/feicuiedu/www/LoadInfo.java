package com.feicuiedu.www;
/**
 * 封装加载类的信息
 * */
public class LoadInfo {
	// 记录要加载类的时间戳
	private long loadTime;
	private BaseManager manager;
	public LoadInfo(long loadTime) {
		super();
		this.loadTime = loadTime;
	}
	public long getLoadTime() {
		return loadTime;
	}
	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}
	public BaseManager getManager() {
		return manager;
	}
	public void setManager(BaseManager manager) {
		this.manager = manager;
	}
	
	
}
