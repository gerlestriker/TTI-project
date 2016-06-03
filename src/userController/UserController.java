package userController;

import userModel.UserDB;
/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données utilisateurs que vous allez créer.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez implémenter.
 * 
 * @author Germain Belacel	et Edouard Lamoine
 * @version 06/2016
 * 
 */


public class UserController implements IUserController
{
	
	/**
	 * Contient une instance de base de données d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;
	
	
	/**
	 * Constructeur de controleur d'utilisateurs créant la base de données d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de données d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	public String getUserName(String userLogin) {
		return userDB.getUserName(userLogin);
	}

	public String getUserClass(String userLogin, String userPwd) {
		return userDB.getUserClass(userLogin, userPwd);
	}

	public int getStudentGroup(String studentLogin) {
		return userDB.getStudentGroup(studentLogin);
	}

	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,String pwd) {
		if(userDB.addAdmin(adminLogin, newAdminlogin, adminID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}

	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,String surname, String pwd) {
		if(userDB.addTeacher(adminLogin, newteacherLogin, teacherID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}

	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,String surname, String pwd) {
		if(userDB.addStudent(adminLogin, newStudentLogin, studentID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}

	public boolean removeUser(String adminLogin, String userLogin) {
		if(userDB.removeUser(adminLogin, userLogin))
			return this.saveDB();
		return false;
	}

	public boolean addGroup(String adminLogin, int groupId) {
		if(userDB.addGroup(adminLogin, groupId))
			return this.saveDB();
		return false;
	}

	public boolean removeGroup(String adminLogin, int groupId) {
		if(userDB.removeGroup(adminLogin, groupId))
			return this.saveDB();
		return false;
	}

	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		if(userDB.associateStudToGroup(adminLogin, studentLogin, groupId))
			return this.saveDB();
		return false;
	}

	public String[] usersToString() {
		return userDB.usersToString();
	}

	public String[] usersLoginToString() {
		return userDB.usersLoginToString();
	}

	public String[] studentsLoginToString() {
		return userDB.studentsLoginToString();
	}

	public String[] groupsIdToString() {
		return userDB.groupsIdToString();
	}

	public String[] groupsToString() {
		return userDB.groupsToString();
	}

	public boolean loadDB() {
		return userDB.loadDB();
	}


	public boolean saveDB() {
		return userDB.save();
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
	

}

