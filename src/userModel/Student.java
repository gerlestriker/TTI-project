package userModel;

public class Student extends User {

	protected int groupId;
	public Student(String login, String surname, String firstname, String pwd, int id) {
		super(login, surname, firstname, pwd, id, "Student");
		this.groupId = -1;
	}
	
	public Student(String login, String surname, String firstname, String pwd, int id,int groupId) {
		super(login, surname, firstname, pwd, id, "Student");
		this.groupId = groupId;
	}

	public int getGroup(){
		return groupId;
	}
	
	public void setGroup(int groupId){
		this.groupId=groupId;
	}
	
	public String toString(){
		return super.toString()+" "+this.groupId;
	}
}
