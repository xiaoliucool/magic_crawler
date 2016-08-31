package com.rrx.jdb.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * com.rrx.jdb.utils.FileTraverseUtils
 * @author 刘文超
 * @date 2016年8月30日-下午1:46:54
 * @version v1.0
 * @desc
 */
public class FileTraverseUtils {
	/**
	 * 阻塞队列，供多线程使用
	 */
	public static volatile LinkedBlockingQueue<File> sLBQueue = new LinkedBlockingQueue<>();
	/**
	 * 线程池，多个生产者
	 */
	private static final ExecutorService executor = Executors.newFixedThreadPool(4);
	
	/**
	 * 获取某个路径下的文件列表
	 * @param fileDir 文件夹路径
	 */
	public static void readFileList(String fileDir){
		traverseFileTree(fileDir);
		executor.shutdown();
	}

	/**
	 * 递归遍历指定文件目录，文件处理交给线程池
	 * 
	 * @param filePath 文件路径
	 */
	public static void traverseFileTree(String filePath) {
		if (null == filePath || 0 == filePath.length()) {
			return;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}
		if (file.isFile()) {
			executor.execute(new Producer(file));
		} else {
			File[] files = file.listFiles(new LogNameFilter(".log"));
			for (int i = 0; i < files.length; i++) {
				traverseFileTree(files[i].getAbsolutePath());
			}
		}
	}
	 /**
	  * @author 刘文超
	  * @date 2016年8月30日-下午2:25:46
	  * @version v1.0
	  * @desc 过滤所有的以.log格式的文件
	  */
	static class LogNameFilter implements FilenameFilter {
		private String type;

		public LogNameFilter(String type) {
			this.type = type;
		}

		@Override
		public boolean accept(File dir, String name) {
			if (null == dir || !dir.exists() || null == name || 0 == name.length()) {
				return false;
			}
			if (dir.isDirectory()||name.endsWith(type)) {
				return true;
			}
			return false;
		}

	}

	static class Producer implements Runnable {
		private File mFile;

		public Producer(File file) {
			this.mFile = file;
		}

		@Override
		public void run() {
			// traverseFileTree(mFilepath);
			try {
				sLBQueue.put(mFile);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	static class Consumer implements Runnable{
		
		@Override
		public void run() {
			try {
				while(true){
					File tFile = sLBQueue.take();
					System.out.println("fileName: " + tFile.getName());
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public static void main(String[] args) {
		readFileList("d:\\traverse");
		if (sLBQueue.size() == 0) {
			System.out.println("empty...");
			return;
		}
		Consumer tConsumer = new Consumer();
		new Thread(tConsumer).start();
	}
}
