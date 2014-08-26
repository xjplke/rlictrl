package cn.adfi.rlictrl.model;

public enum ClientStatus {
	TRIAL(0,"Trial"),
	TRIAL_EXPIRED(1,"Trial expired"),
	CONTRACT(2,"Contract"),
	CONTRACT_EXPIRED(3,"Contract expired"),
	BAN(100,"Ban");
	
	
	private final byte id;
	private final String name;
	private ClientStatus(int id, String name) {
		this.id = (byte) id;
		this.name = name;
	}
	public byte getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String toString(){
		return this.name;
	}
	
}	
