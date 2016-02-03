package modelLayer;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private double obligation;

	public Person(String name) {
		setName(name);
		this.obligation = 0;
	}

	public Person(String name, double obligation) {
		setName(name);
		setObligation(obligation);
	}
	public String getName(){
		return name;
	}
	public double getObligation(){
		return obligation;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setObligation(double obligation){
		this.obligation = obligation;
	}
	public String toString(){
		return "Name: " + name  + ", Obligation = " + obligation;
	}
}
