package com.rrx.jdb;

import com.rrx.jdb.cmd.ArgCollections;
import com.rrx.jdb.utils.net.HttpUtils;

/**
 * com.rrx.jdb.Main
 * 
 * @author 刘文超
 * @date 2016年8月31日-下午2:02:37
 * @version v1.0
 * @desc java xxx -arg1 value1 -arg2 value2
 */
public class Main {
	private static final String sArgPrefix = "-";

	private static final String sArgInput = "input";

	//-input
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			i = parseArg(args, i);
		}
		if (!ArgCollections.getInstance().isArgValid()) {
			System.err.println("args invalid!!");
			return ;
		}
		String tUrl = ArgCollections.getInstance().getInputArg().getArgValue(); 
		System.out.println("input: " + tUrl);
		String tHtmlContent = HttpUtils.getHtml("http://mobile.zol.com.cn/manu_list.html");
		System.out.println(tHtmlContent);
		System.out.println("success!!");
	}

	private static int parseArg(String[] args, int i) {
		String arg = args[i];
		if (!arg.startsWith(sArgPrefix)) {
			System.err.println("arg["+i+"]: "+arg+", isn't starts with \""+sArgPrefix+"\"");
			return i;
		}

		if (args.length <= i+1) {
			System.err.println("args's length: "+args.length+" <= (i+1):"+(i+1));
			return i;
		}
		
		String argName = arg.substring(1);
		if (argName.startsWith(sArgPrefix)) {
			System.err.println("argName: "+argName+" invalid!");
			return i;
		}
		
		String argValue = args[++i];

		boolean succeed = false;
		if (argName.equals(sArgInput)) {
			succeed = ArgCollections.getInstance().setArgInput(argValue);
		} 
		if (!succeed) {
			System.err.println("argName: "+argName+" invalid!");
		}
		return i;
	}
}
