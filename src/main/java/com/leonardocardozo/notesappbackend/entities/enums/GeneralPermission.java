package com.leonardocardozo.notesappbackend.entities.enums;

public enum GeneralPermission {
	
	AUTHOR_RW(0),
	PUBLIC_R(1),
	PUBLIC_RW(2);
	
	private int code;

	private GeneralPermission(int code) {
		this.code = code;
	}
		
	
	public int getCode() {
		return code;
	}
	
	
	public static GeneralPermission valueOf(int code) {
        for (var value : GeneralPermission.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid general permission code!");
    }
}
