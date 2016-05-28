package userModel;

public class Teacher extends User {

	public Teacher(String login, String surname, String firstname, String pwd, int id) {
		super(login, surname, firstname, pwd, id, "Teacher");
	}

}
