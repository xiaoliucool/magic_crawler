package com.rrx.jdb.utils;

import java.io.*;

/**
 * com.rrh.jdb.log.LogFileParser
 * @author 张鹏超
 */
public class FileUtils {
	
	/**
	 * 删除目录及内部所有文件
	 * @param dirFile 根目录
	 * @return
	 */
	public static boolean removeAllFiles(File dirFile){
		if (dirFile == null || !dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }

        File[] list = dirFile.listFiles();
        if (list == null || list.length <= 0) {
            dirFile.delete();
            return true;
        }

        for (File file : list) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
            	removeAllFiles(file);
            }
        }
        return dirFile.delete();
	}
	
	public static String readFirstLineFromFile(File target) {
        FileInputStream tFis = null;
        InputStreamReader tIsr = null;
        BufferedReader tBr = null;
        try {
            tFis = new FileInputStream(target);
            tIsr = new InputStreamReader(tFis);
            tBr = new BufferedReader(tIsr);
            return tBr.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeCloseable(tBr);
            closeCloseable(tIsr);
            closeCloseable(tFis);
        }
    }

	public static void writeToFile(String content, boolean newLine, boolean append, File tAvailableSizeFile) {
        FileOutputStream tFos = null;
        OutputStreamWriter tOsw = null;
        BufferedWriter tBw = null;
        try {
            tFos = new FileOutputStream(tAvailableSizeFile, append);
            tOsw = new OutputStreamWriter(tFos);
            tBw = new BufferedWriter(tOsw);
            tBw.write(content);
            if (newLine) {
                tBw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeCloseable(tBw);
            closeCloseable(tOsw);
            closeCloseable(tFos);
        }
    }

	public static int readIntFromFileOnFirstLine(File target) {
		if (!target.exists()) {
			return 0;
		}
		int tSize = 0;
		String content = FileUtils.readFirstLineFromFile(target);
		if (!StringUtils.isEmpty(content)) {
			try {
				tSize = Integer.parseInt(content);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tSize;
	}

	public interface FileOperation {
		void accept(File f);
	}

	public static void traverseDir(File dirFile, FileOperation op, FileOperation rootDirOp) {
		if (dirFile == null || !dirFile.exists()) {
			return;
		}
		
		if (!dirFile.isDirectory()) {
			if (rootDirOp != null) {
				rootDirOp.accept(dirFile);
			}
			return;
		}

		File[] list = dirFile.listFiles();
		if (list == null || list.length <= 0) {
			if (rootDirOp != null) {
				rootDirOp.accept(dirFile);
			}
			return;
		}

		for (File file : list) {
			if (file.isFile()) {
				if (op != null) {
					op.accept(file);
				}
			} else if (file.isDirectory()) {
				traverseDir(file, op, rootDirOp);
			}
		}
		if (rootDirOp != null) {
			rootDirOp.accept(dirFile);
		}
	}

	public static boolean isValidDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			System.err.println("dir ("+path+") doesn't exist");
			return false;
		}

		if (!dir.isDirectory()) {
			System.err.println("("+path+") isn't directory");
			return false;
		}
		return true;
	}

	public static boolean isValidFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.err.println("bean ("+path+") doesn't exist");
			return false;
		}

		if (!file.isFile()) {
			System.err.println("("+path+") isn't bean");
			return false;
		}

		return true;
	}

	public static void closeCloseable(Closeable target) {
		try {
			if (target != null) {
				target.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
