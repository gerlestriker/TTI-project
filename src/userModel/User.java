package userModel;

public class User {
	
	/**
	 * Cette classe est un User, rassemble toutes les fonctions communes aux ... utilisateurs !
	 * 
	 * @author Germain Belacel	et Edouard Lamoine
	 * @version 06/2016
	 * 
	 */
	
	
	protected String login;
	protected String surname;
	protected String firstname;
	protected String pwd;
	protected int id;
	protected String type;
	
	public User(String login, String surname, String firstname, String pwd, int id, String type) {
		this.login = login;
		this.surname = surname;
		this.firstname = firstname;
		this.pwd = pwd;
		this.id = id;
		this.type = type;
	}

	public String getLogin(){
		return this.login;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public String getFirstname(){
		return this.firstname;
	}
	
	public String getPwd(){
		return this.pwd;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String toString(){
		return this.type+" "+this.login+" "+this.surname+" "+this.firstname+" "+this.pwd+" "+this.id;
	}
}
