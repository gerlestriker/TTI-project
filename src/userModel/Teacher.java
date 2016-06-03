package userModel;

public class Teacher extends User {
	
	/**
	 * ..... vraiment besoin d'une doc pour sa ?
	 */
	public Teacher(String login, String surname, String firstname, String pwd, int id) {
		super(login, surname, firstname, pwd, id, "Teacher");
	}

}
