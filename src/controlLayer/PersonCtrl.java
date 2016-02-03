package controlLayer;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

import modelLayer.Person;
import modelLayer.PersonCollection;

public class PersonCtrl {

	private PersonCollection personCollection;
	private ArrayList<Person> persons;
	

	public PersonCtrl() {
		personCollection = new PersonCollection();
	
		setPersons(personCollection.getPersons());
	}

	/**
	 * 
	 * @param name the name of the person who want to add
	 * @param obligation the amount of money which this person owe you
	 * @return 1 if the person is created successfully or return 2 if it is not 
	 */
	public int createPerson(String name, double obligation) {
		
		if(getPerson(name) == null){
			Person p = new Person(name, obligation);
			getPersons().add(p);
			storeinFile();
			return 1;
		}else{
			return 2;
		}
		
			}
	/**
	 * 
	 * @param name the name of the Person 
	 * @return object of type Person searched by name
	 */
	public Person getPerson(String name) {
		for (Person p : getPersons()) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param name the name of the person
	 * remove object of type Person searched by name 
	 */
	
	public void removePerson(String name){
		getPersons().remove(getPerson(name));
		storeinFile();
	}
	/**
	 * 
	 * @param name the name of the Person 
	 * @return obligation the amount of money which particular person searched by name owe
	 */
	public double getPersonObligation(String name) {
		Person p = getPerson(name);
		return p.getObligation();
	}
	/**
	 * 
	 * @param name the name of the Person 
	 * @param obligation the amount of money which particular person  owe
	 */
	public void setObligation(String name,double obligation){
		getPerson(name).setObligation(obligation);
		storeinFile();
	}
	/**
	 * add sum to obligation of particular Person's object and store it to a file
	 * @param name the name of the Person 
	 * @param sum the amount of money which wanted to add to obligation of particular person  
	 */
	public void addSum(String name, double sum){
		Double newSum = getPersonObligation(name);
		newSum += sum;
		newSum = Math.round(newSum* 100.0) / 100.0;
		setObligation(name, newSum);
		
		storeinFile();

	}
	/**
	 * subtract sum from obligation of particular Person's object and store it to a file
	 * @param name the name of the Person 
	 * @param sum the amount of money which wanted to subtract from obligation of particular person 
	 */
	public void sbutractObligation(String name, double sum){
		Double newSum = getPersonObligation(name);
		newSum -= sum;
		setObligation(name, newSum);
		System.out.println(getPerson(name));
		storeinFile();
	}
	/**
	 * store all objects of type Person to the file persons.ser
	 */
	public void storeinFile() {
	
		try {
			FileOutputStream fo = new FileOutputStream("persons.ser");
			ObjectOutputStream output = new ObjectOutputStream(fo);
			for (Person p : getPersons()) {
				output.writeObject(p);
			}
			output.close();
			fo.close();
		}catch(EOFException ex){
		
		} catch (FileNotFoundException ex) {
			System.out.printf("the file %s  was not found");
			

		} catch (IOException ex) {
			System.out.println("IO exception occured");
		}
		
	}

	public LinkedList<Person> getPersonsLinkedList() {
		LinkedList<Person> temporary = new LinkedList<Person>(getPersons());
		
		return temporary ;
	}
	public ArrayList<Person> getPersonsCollection() {
		ArrayList<Person> temporary = new ArrayList<Person>(persons);
		
		return temporary ;
	}
	
	private ArrayList<Person> getPersons() {
		
		
		return persons ;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}
	public void removePerson(int index){
		persons.remove(index);
		storeinFile();
	}
	private Person getPerson(int index){
		return persons.get(index);
	}
	/**
	 * 
	 * @param newName the new name of the person
	 * @param index the position in which it is stored in the arrayList
	 * @return true if the changes are made successfully and false otherwise
	 */
	public boolean setPersonName(String newName, int index){
		if(getPerson(newName)== null){
			getPerson(index).setName(newName);
			storeinFile();
			return true;
		}else{
			return false;
		}
	}
	public String getPersonName(int index){
		return getPerson(index).getName();
	}
	public double getPersonObligation(int index) {
		
		return getPerson(index).getObligation();
	}
	public void setObligation(int index,double obligation){
		getPerson(index).setObligation(obligation);
		storeinFile();
	}
	
public static void main(String[]args){
	PersonCtrl person = new PersonCtrl();
	System.out.println(person.getPersons());
	
}
	
}
