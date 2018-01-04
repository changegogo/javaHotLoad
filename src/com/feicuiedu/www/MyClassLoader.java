package com.feicuiedu.www;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	// 要加载的java类的classPath路径
	private String classPath;
	
	
	public MyClassLoader(String classPath) {
		super(ClassLoader.getSystemClassLoader());
		this.classPath = classPath;
	}


	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = this.loadClassData(name);
		return this.defineClass(name,data, 0, data.length);
	}


	private byte[] loadClassData(String name) {
		FileInputStream is = null;
		ByteArrayOutputStream bos = null;
		try {
			name = name.replace(".", "//");
			is = new FileInputStream(new File(this.classPath+name+".class"));
			bos = new ByteArrayOutputStream();
			int b = 0;
			while((b = is.read()) != -1){
				bos.write(b);
			}
			return bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
