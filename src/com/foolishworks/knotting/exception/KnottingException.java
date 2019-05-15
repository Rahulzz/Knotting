package com.foolishworks.knotting.exception;

import java.io.Serializable;

public class KnottingException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -2267898410112610226L;

	private String exceptionMsg;
	private String errCode;

	public KnottingException(String exceptionMsg) {
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

	public KnottingException(String errCode,String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
		this.errCode = errCode;
	}



}