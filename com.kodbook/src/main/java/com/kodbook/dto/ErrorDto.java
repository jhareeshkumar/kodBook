/**
 * 
 */
package com.kodbook.dto;

/**
 * 
 */
public class ErrorDto {
    
    private String message;
    /**
     * 
     */
    public ErrorDto() {
	// TODO Auto-generated constructor stub
    }
    public ErrorDto(String message) {
	super();
	this.message = message;
    }
    @Override
    public String toString() {
	return "ErrorDto [message=" + message + "]";
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    

}
