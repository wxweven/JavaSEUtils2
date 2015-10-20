package com.wxweven.collection;

public class Car {

	private String ID;
	private double cost;
	private String color;
	private Person owner;
	
	public Car(String iD, double cost, String color, Person owner) {
		ID = iD;
		this.cost = cost;
		this.color = color;
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Car [ID=" + ID + ", cost=" + cost + ", color=" + color
				+ ", owner=" + owner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		Car other = (Car) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

	public String getID() {
		return ID;
	}

	public double getCost() {
		return cost;
	}

	public String getColor() {
		return color;
	}


	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
}
