package com.rrx.jdb.cmd;

/**
 * com.rrx.jdb.cmd.Arg
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午1:42:48
 * @version v1.0
 * @desc 命令行参数,记录两个信息:第一、命令行参数值;第二、命令行参数依赖项。
 */
public abstract class Arg {
	protected String mArgValue;
	protected Arg mDependencyArg;

	public String getArgValue() {
		return this.mArgValue;
	}

	public void setArgValue(String value) {
		this.mArgValue = value;
	}

	public void setArgDependency(Arg dependency) {
		this.mDependencyArg = dependency;
	}
	/**
	 * 检查是否是可用参数
	 * @return
	 */
	public abstract boolean checkValid();
	/**
	 * 获取参数的名字
	 * @return
	 */
	public abstract String getArgName();

	public Arg getDependencyArg() {
		return mDependencyArg;
	}
}
