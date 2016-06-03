package userModel;

public class Student extends User {
	
	/**
	 * Cette classe est un utilisateur de type Administrateur, elle permet de gérer les différentes fonctions accessible aux admins
	 * 
	 * @author Germain Belacel	et Edouard Lamoine
	 * @version 06/2016
	 * 
	 */
	
	protected int groupId;
	
	/**
	 * Constructeur de Student
	 * @see UserController
	 */
	public Student(String login, String surname, String firstname, String pwd, int id) {
		super(login, surname, firstname, pwd, id, "Student");
		this.groupId = -1;
	}
	/**
	 * Constructeur de Student
	 * @see UserController
	 */
	public Student(String login, String surname, String firstname, String pwd, int id,int groupId) {
		super(login, surname, firstname, pwd, id, "Student");
		this.groupId = groupId;
	}
	/**
	 * Nom explicite
	 */
	public int getGroup(){
		return groupId;
	}
	/**
	 * Nom explicite
	 */
	public void setGroup(int groupId){
		this.groupId=groupId;
	}
	/**
	 * Nom explicite
	 */
	public String toString(){
		return super.toString()+" "+this.groupId;
	}
}
