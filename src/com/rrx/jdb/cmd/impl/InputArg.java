package com.rrx.jdb.cmd.impl;

import com.rrx.jdb.cmd.Arg;

/**
 * com.rrx.jdb.cmd.impl.InputArg
 * @author 刘文超
 * @date 2016年8月31日-下午1:44:20
 * @version v1.0
 * @desc -input参数项
 */
public class InputArg extends Arg {
	/**
	 * 参数项的名字
	 */
    private static final String sArgName = "input";

    public boolean checkValid() {
        if (!mArgValue.startsWith("https://")) {
            System.err.println("-input invalid");
            return false;
        }
        return true;
    }

    @Override
    public String getArgName() {
        return sArgName;
    }
}
