/**
 * 
 */
package com.kodbook.dto;

/**
 * 
 */
public class SuccessResponse {
    
    private String message;
    private Object data;
    /*
     * 
     */
    public SuccessResponse() {
	// TODO Auto-generated constructor stub
    }
    public SuccessResponse(String message, Object data) {
	super();
	this.message = message;
	this.data = data;
    }
    
    public SuccessResponse(String message) {
	super();
	this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
	return "SuccessResponse [message=" + message + ", data=" + data + "]";
    }
    
}
