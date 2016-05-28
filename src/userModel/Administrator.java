package userModel;

import java.util.ArrayList;

public class Administrator extends User {

	public Administrator(String login, String surname, String firstname, String pwd, int id) {
		super(login,surname,firstname,pwd,id, "Administrator");
	}

	public Administrator addAdmin(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Administrator(newlogin,surname,firstname,pwd,ID);
	}
	
	public Teacher addTeacher(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Teacher(newlogin,surname,firstname,pwd,ID);
	}
	
	public Student addStudent(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Student(newlogin,surname,firstname,pwd,ID);
	}

	public ArrayList<User> removeUser(String userLogin,ArrayList<User> ListUsers,ArrayList<Group> ListGroup) {
		for(int i=0;i<ListUsers.size();i++){
			if(ListUsers.get(i).getLogin().equals(userLogin)){
				if(ListUsers.get(i).getType().equals("Student")){
					for(int j=0;j<ListGroup.size();j++){
						if(ListGroup.get(j).getGroupId()==((Student) ListUsers.get(i)).getGroup())
							ListGroup.get(j).removeStudent(userLogin);
					}
				}
					
				ListUsers.remove(i);
				return ListUsers;
			}
		}
		return ListUsers;
	}
	
	public ArrayList<Group> addGroup(int iD, ArrayList<Group> ListGroup){
		ListGroup.add(new Group(iD));
		return ListGroup;
	}
	
	public boolean associateStudToGroup(String studentLogin, int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
		for(int j=0;j<ListGroup.size();j++){
			if(ListGroup.get(j).getGroupId()==groupId){
				for(int i=0;i<ListUsers.size();i++){	
					if(ListUsers.get(i).getLogin().equals(studentLogin)){
						((Student) ListUsers.get(i)).setGroup(groupId);
						ListGroup.get(j).addStudent(((Student) ListUsers.get(i)));
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean removeGroup(int groupId,ArrayList<Group> ListGroup,ArrayList<User> ListUsers){
		for(int j=0;j<ListGroup.size();j++){
			if(ListGroup.get(j).getGroupId()==groupId){
				for(int i=0;i<ListUsers.size();i++){	
					if(ListUsers.get(i).getType().equals("Student")){
						if(((Student) ListUsers.get(i)).getGroup()==groupId)
							((Student) ListUsers.get(i)).setGroup(-1);
					}
				}
				ListGroup.remove(j);
				return true;
			}
		}
		return false;
	}
}
