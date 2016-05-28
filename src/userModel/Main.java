package userModel;

import userController.UserController;
/**
 * Cette classe permet de tester les fonctions du contrôleur d'utilisateurs.
 * Elle crée une base de données de 6 utilisateurs et les sauvegarde dans le fichier "usersDB.xml". 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe pouvant être modifiée suivant vos besoins

public class Main {
	/**
	 * Fonction principale 
	 * 
	 * @param args
	 * 			Les arguments donnés en entrée du programme.
	 * 
	 */
	public static void main(String[] args) {
		final String file="usersDB.xml";
		UserController UC=new UserController(file);
		/*UC.addAdmin("su","KF",0001,"Kevin", "Flynn",  "@tron");
		UC.addAdmin("KF","KR",0002,"Keanu", "Reeves",  "redpill");
		UC.addTeacher("su","GS",1001,"Grand", "Schtroumpf",  "salsepareille");
		UC.addTeacher("GS","MF",1002,"Morgan", "Freeman",  "iknowall");
		UC.addStudent("su","BS",2001,"Buffy", "Summers",  "stake");
		UC.addStudent("su","NL",2002,"Nicolas", "Lepetit",  "prout");
		
		UC.addGroup("su", 2);
		UC.associateStudToGroup("su", "BS", 2);
		System.out.println(UC.getUserClass("BS", "stake"));
		System.out.println(UC.getUserName("BS"));
		System.out.println(UC.getStudentGroup("BS"));*/
		String[] tabUsers = UC.usersToString();
		for(int i=0;i<tabUsers.length;i++)
			System.out.println(tabUsers[i]);
		UC.saveDB();
	}
}
