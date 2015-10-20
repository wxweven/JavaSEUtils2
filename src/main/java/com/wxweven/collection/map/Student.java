package com.wxweven.collection.map;

import java.util.HashMap;

public class Student implements Comparable<Student> {

	private String name;
	private HashMap<String, Double> score;
	private double avg;

	public Student(String name) {
		this.name = name;
		this.score = new HashMap<String, Double>();
	}

	public void addScore(String sub, double score) {
		this.score.put(sub, score);
	}

	public double getAvg() {

		if (this.score.size() == 0)
			return 0;

		double sum = 0;
		for (String str : this.score.keySet()) {
			sum += this.score.get(str);
		}

		this.avg = sum / this.score.size();

		return this.avg;
	}

	public int compareTo(Student o) {
		if (this.getAvg() > o.getAvg())
			return 1;
		else if (this.getAvg() < o.getAvg())
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", avg=" + avg + "]";
	}
}
