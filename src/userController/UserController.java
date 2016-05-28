package userController;

import userModel.UserDB;
/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données utilisateurs que vous allez créer.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
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
		return userDB.addAdmin(adminLogin, newAdminlogin, adminID, firstname, surname, pwd);
	}

	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,String surname, String pwd) {
		return userDB.addTeacher(adminLogin, newteacherLogin, teacherID, firstname, surname, pwd);
	}

	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,String surname, String pwd) {
		return userDB.addStudent(adminLogin, newStudentLogin, studentID, firstname, surname, pwd);
	}

	public boolean removeUser(String adminLogin, String userLogin) {
		return userDB.removeUser(adminLogin, userLogin);
	}

	public boolean addGroup(String adminLogin, int groupId) {
		return userDB.addGroup(adminLogin, groupId);
	}

	public boolean removeGroup(String adminLogin, int groupId) {
		return userDB.removeGroup(adminLogin, groupId);
	}

	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		return userDB.associateStudToGroup(adminLogin, studentLogin, groupId);
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

