package com.wxweven.collection;

public class Certificate {
	
	private final int ID;

	public Certificate(int iD) {
		ID = iD;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Certificate other = (Certificate) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ID + "";
	}
	
}
