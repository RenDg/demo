package com.yz.vo;

public class AjaxJson {

	private boolean success;
	private String msg;
	
	public AjaxJson() {
		super();
	}
	
	public AjaxJson(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "AjaxJson [success=" + success + ", msg=" + msg + "]";
	}
}
