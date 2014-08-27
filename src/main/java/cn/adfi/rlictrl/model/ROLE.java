package cn.adfi.rlictrl.model;

public enum ROLE {
	SUPER(1,"SUPER"),
	ADMIN(2,"ADMIN"),
	USER(3,"USER");
	
	private final byte id;
	private final String name;
	
	private ROLE(int id, String name) {
		this.id = (byte) id;
		this.name = name;
	}
	
	/**
	 * @return the id
	 */
	public byte getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
