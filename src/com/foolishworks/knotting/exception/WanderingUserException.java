package com.foolishworks.knotting.exception;

import java.io.Serializable;

public class WanderingUserException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 6865920910129567426L;
	
	private String exceptionMsg;
	private String errCode;

	public WanderingUserException(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
		this.errCode="100";
	}

	public String getExceptionMsg(){
		return this.exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public WanderingUserException(String errCode,String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
		this.errCode = errCode;
	}



}