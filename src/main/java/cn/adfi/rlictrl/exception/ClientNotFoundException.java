package cn.adfi.rlictrl.exception;

public class ClientNotFoundException extends Exception {
	private static final long serialVersionUID = -3836510793528202481L;
	
	public ClientNotFoundException(String message){
		super(message);
	}
}
