package com.liqian.cms.exception;

public class CMSRuntimeException extends Exception {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4991958587242467150L;
	
	
	//提供无参构造
	public CMSRuntimeException() {
		
	}
	
	//提供一个有参数的构造方法   参数:可以传入异常信息
	public CMSRuntimeException(String message) {
		super(message);//调用Exception的有参数的构造方法
	}

}
