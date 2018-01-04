package com.feicuiedu.www;

public class RunHandler implements Runnable {

	@Override
	public void run() {
		while(true){
			BaseManager manager = ManagerFactory.getManager(ManagerFactory.MY_MANAGER);
			manager.logic();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
