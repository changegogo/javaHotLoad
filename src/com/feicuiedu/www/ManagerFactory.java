package com.feicuiedu.www;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载manager的工厂
 * */
public class ManagerFactory {
	// 记录热加载类的加载信息
	private static final Map<String, LoadInfo> loadTimeMap = new HashMap<String, LoadInfo>();
	// class文件的根目录
	private static final String CLASS_PATH = "D:/workspaceweb1/classLoader/bin/";
	// 实现热加载的类的全名称（包名+类名）
	public static final String MY_MANAGER = "com.feicuiedu.www.MyManager";
	
	public static BaseManager getManager(String className){
		File loadFile = new File(CLASS_PATH+className.replaceAll("\\.", "/")+".class");
		// 最后一次修改的时间
		long lastModified = loadFile.lastModified();
		System.out.println("lastModified-->"+lastModified);
		if(loadTimeMap.get(className)==null){
			System.out.println("null");
			loadClass(className, lastModified);
		}else if(loadTimeMap.get(className).getLoadTime() != lastModified){
			System.out.println("!= lastModified");
			loadClass(className, lastModified);
		}
		return loadTimeMap.get(className).getManager();
	}

	private static void loadClass(String className, long lastModified) {
		// 创建类的加载器
		MyClassLoader myClassLoader = new MyClassLoader(CLASS_PATH);
		Class<?> loadClass = null;
		try {
			loadClass = myClassLoader.findClass(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseManager manager = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(lastModified);
		loadInfo.setManager(manager);
		loadTimeMap.put(className, loadInfo);
	}
	// 以反射的方式创建BaseManger的子类对象
	private static BaseManager newInstance(Class<?> loadClass) {
		try {
			return (BaseManager)loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}












