package com.rrx.jdb.cmd;

import com.rrx.jdb.cmd.impl.InputArg;

/**
 * com.rrx.jdb.cmd.ArgCollections
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午1:37:19
 * @version v1.0
 * @desc 命令行参数集合
 */
public class ArgCollections {
	private InputArg mInputArg;
	
	public boolean setArgInput(String content) {
		if (mInputArg != null) {
			System.err.println("duplicate input arg. prev(" + mInputArg.getArgValue() + ") curr(" + content + ")");
			return false;
		}

		mInputArg = new InputArg();
		mInputArg.setArgValue(content);
		return true;
	}
	
	public Arg getInputArg() {
		return mInputArg;
	}

	/**
	 * 判断参数是否可用
	 * @return
	 */
	public boolean isArgValid() {
		if (!checkInputArgs()) {
			return false;
		}
		return true;
	}
	/**
	 * 检查输入参数
	 * @return
	 */
	private boolean checkInputArgs() {
		if (mInputArg != null) {
			if (mInputArg.checkValid()) {
				return true;
			}
		}
		System.err.println("-input must occur!!");
		return false;
	}
	
	public static ArgCollections getInstance() {
		return ArgCollectionsLocker.sInstance;
	}

	private ArgCollections() {

	}

	private static class ArgCollectionsLocker {
		private static ArgCollections sInstance = new ArgCollections();
	}
}
