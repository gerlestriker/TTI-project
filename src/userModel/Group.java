package userModel;

import java.util.ArrayList;

public class Group {
	
	protected int groupId;
	protected ArrayList<Student> ListStudent;
	
	public Group(int Id) {
		this.groupId = Id;
		this.ListStudent = new ArrayList<Student>();
	}
	
	public int getGroupId(){
		return groupId;
	}
	
	public boolean addStudent(Student student){
		this.ListStudent.add(student);
		return true;
	}
	
	public String toString(){
		String s="Group "+String.valueOf(this.groupId)+" | Etudiant : ";
		for(int i=0;i<this.ListStudent.size();i++){
			s+=this.ListStudent.get(i).getLogin()+" ";
		}
		return s;
	}
	
	public void removeStudent(String studentLogin){
		for(int i=0;i<this.ListStudent.size();i++){
			if(this.ListStudent.get(i).getLogin().equals(studentLogin))
				this.ListStudent.remove(i);
		}
	}
}
