package com.leonardocardozo.notesappbackend.entities.enums;

public enum ContributorPermission {

	CONTRIBUTOR_READ(0),
	CONTRIBUTOR_WRITE(1);
	
	private int code;

	private ContributorPermission(int code) {
		this.code = code;
	}
		
	
	public int getCode() {
		return code;
	}
	
	
	public static ContributorPermission valueOf(int code) {
        for (var value : ContributorPermission.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid general permission code!");
    }
}
