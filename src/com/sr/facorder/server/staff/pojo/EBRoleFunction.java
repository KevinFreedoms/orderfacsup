package com.sr.facorder.server.staff.pojo;

public class EBRoleFunction {
	private String functionName;
	private String operation;
	private String ref2;
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getRef2() {
		return ref2;
	}
	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}
	@Override
	public String toString() {
		return "EBRoleFunction [functionName=" + functionName + ", operation=" + operation + ", ref2=" + ref2 + "]";
	}
	
}
