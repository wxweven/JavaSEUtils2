package com.wxweven.collection;

import java.util.List;

public class Person {

	Certificate code;//身份证对象
	private String name;//name of the person
	private double cash;//cash of the person

	List<Car> cars;
	
	/**
	 * 从人p那里买车car
	 * @param p 卖车的人
	 * @param car 要卖的车
	 * @return 是否购买成功
	 */
	boolean buyCar(Person p, Car car) {
		/**
		 * 不能买自己的车
		 * 检查卖主是否是自己，如果是自己，则购买失败
		 */
		if(this.equals(p)) {
			System.out.println("不能买自己的车");
			return false;
		}

		/**
		 * 检查卖主是否有car这辆车
		 */
		if(p.getCars().contains(car)) {
			
			/**
			 * 检查买车的钱是否够用，如果够用，买车成功，并减去相应的车钱
			 * 如果钱不够用,则失败
			 */
			if(this.getCash() >= car.getCost()) {
				this.setCash(getCash()-car.getCost());
				this.cars.add(car);
				p.cars.remove(car);
				p.setCash(getCash()+car.getCost());
				System.out.println("买车成功");
				return true;
			} else {
				System.out.println("钱不够用，买车失败");
				
			}
		} else {
			System.out.println("没有要买的车，买车失败");
			return false;
		}
		
		return false;
	}

	public boolean buyCars(List<Car> carList) {
		return false;
		
		
	}
	
	public boolean sellCars(Person p) {
		return false;
	}
	
	public double getCarsMoney() {
		double sum = 0; 
		
		if(this.cars.isEmpty()) {
			sum = 0;
		} else {
			for(Car car : this.getCars()) {
				sum += car.getCost();
			}
		}
		return sum;
	}
	
	public void addCar(Car car) {
		this.cars.add(car);
	}
	
	/**
	 * setters and getters
	 * @param carList
	 */
	public List<Car> getCars() {
		return cars;
	}
	
	public void setCars(List<Car> carList) {
		cars = carList;
	}
	
	public double getCash() {
		return cash;
	}
	
	public void setCash(double cash) {
		this.cash = cash;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Person other = (Person) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [code=" + code + ", name=" + name + "]";
	}

	

}
