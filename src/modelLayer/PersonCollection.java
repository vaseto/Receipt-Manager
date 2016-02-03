package modelLayer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersonCollection {
	private ArrayList<Person> persons;
	
	public PersonCollection(){
		
		persons = new ArrayList<Person>();
		gettingObject();
	}
	/**
	 * 
	 * @return persons the collection which store objects of class Person
	 */
	public ArrayList<Person> getPersons(){
		return persons;
	}
	public void setPersons(ArrayList<Person> persons){
		this.persons = persons;
	}
	
	/**
	 *  get all objects from the file "person.ser"
	 */
	private  void gettingObject(){
		FileInputStream fi = null ;
		ObjectInputStream input = null ;
			try {
				fi = new FileInputStream("persons.ser");
				 input = new ObjectInputStream(fi);

				while (true) {
					Person p = (Person) input.readObject();
					persons.add(p);
				
					
				}
				

			} catch (ClassNotFoundException ex) {
				System.out.println("the exception is not found");
				
			} catch (EOFException ex) {
				
			} catch (FileNotFoundException ex) {
			
			} catch (IOException ex) {
				System.out.println("IO exceptoin");
			
			}

		
	}
	
}
