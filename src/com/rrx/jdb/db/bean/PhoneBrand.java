package com.rrx.jdb.db.bean;

import com.rrx.jdb.db.annotation.Column;
import com.rrx.jdb.db.annotation.Table;

/**
 * com.rrx.jdb.bean.PhoneBrand
 * 
 * @author 刘文超
 * @date 2016年8月30日-下午2:30:56
 * @version v1.0
 * @desc 手机品牌实体类
 */
@Table(name = "_brand")
public class PhoneBrand {
	/**
	 * 手机的ID
	 */
	@Column(name = "_id")
	private String id;
	/**
	 * 手机中文名
	 */
	@Column(name = "_cnname")
	private String phoneNameInChinese;
	/**
	 * 手机对应的连接地址url
	 */
	@Column(name = "_url")
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNameInChinese() {
		return phoneNameInChinese;
	}

	public void setPhoneNameInChinese(String phoneNameInChinese) {
		this.phoneNameInChinese = phoneNameInChinese;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "手机名：" + phoneNameInChinese + "，手机ID：" + id + "，手机对应的url：" + url;
	}
}
