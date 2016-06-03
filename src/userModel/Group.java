package userModel;

import java.util.ArrayList;

public class Group {
	
	/**
	 * Cette classe est un groupe
	 * 
	 * @author Germain Belacel	et Edouard Lamoine
	 * @version 06/2016
	 * 
	 */
	
	protected int groupId;
	protected ArrayList<Student> ListStudent;
	/**
	 * Constructeur
	 */
	public Group(int Id) {
		this.groupId = Id;
		this.ListStudent = new ArrayList<Student>();
	}
	/**
	 * Getter de groupId
	 */
	public int getGroupId(){
		return groupId;
	}
	/**
	 * Nom explicite
	 */
	public boolean addStudent(Student student){
		this.ListStudent.add(student);
		return true;
	}
	/**
	 * Nom explicite
	 */
	public String toString(){
		String s="Group "+String.valueOf(this.groupId)+" | Etudiant : ";
		for(int i=0;i<this.ListStudent.size();i++){
			s+=this.ListStudent.get(i).getLogin()+" ";
		}
		return s;
	}
	/**
	 * Nom explicite
	 */
	public void removeStudent(String studentLogin){
		for(int i=0;i<this.ListStudent.size();i++){
			if(this.ListStudent.get(i).getLogin().equals(studentLogin))
				this.ListStudent.remove(i);
		}
	}
}
