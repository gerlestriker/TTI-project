package userModel;

import java.util.ArrayList;

public class Administrator extends User {

	/**
	 * Cette classe est un utilisateur de type Administrateur, elle permet de gérer les différentes fonctions accessible aux admins
	 * 
	 * @author Germain Belacel	et Edouard Lamoine
	 * @version 06/2016
	 * 
	 */
	/**
	 * 
	 * Constructeur de Administrator
	 * @see UserController
	 */
	public Administrator(String login, String surname, String firstname, String pwd, int id) {
		super(login,surname,firstname,pwd,id, "Administrator");
	}

	/**
	 * Creer un admin
	 * @see UserController
	 * @return
	 * 		Le nouvel Admin
	 */
	public Administrator addAdmin(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Administrator(newlogin,surname,firstname,pwd,ID);
	}
	
	/**
	 * Creer un prof
	 * @see UserController
	 * @return
	 * 		Le nouveau prof
	 */
	
	public Teacher addTeacher(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Teacher(newlogin,surname,firstname,pwd,ID);
	}
	/**
	 * Creer un etudiant
	 * @see UserController
	 * @return
	 * 		Le nouvel étudiant
	 */
	public Student addStudent(String newlogin, String firstname, String surname, String pwd, int ID){
		return new Student(newlogin,surname,firstname,pwd,ID);
	}

	/**
	 * Supprime un utilisateur de la list fourni en param
	 * @return
	 * 		La liste des utilisateurs avec l'utilisateur supprimé, inutile en soit 
	 */
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
	/**
	 * Nom explicite
	 */
	public ArrayList<Group> addGroup(int iD, ArrayList<Group> ListGroup){
		ListGroup.add(new Group(iD));
		return ListGroup;
	}
	/**
	 * Nom explicite
	 */
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
	/**
	 * Nom explicite
	 */
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
