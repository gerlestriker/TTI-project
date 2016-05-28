package userModel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * Cette classe gére la base de données d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */


public class UserDB {
	
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	private Element racine;
	private org.jdom2.Document document;
	private ArrayList<User> ListUsers;
	private ArrayList<Group> ListGroups;
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * !!!!!!!!!!!! PENSEZ À AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS PERMETTRA DE CHARGER LA BASE DE DONNÉES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public UserDB(String file){
		this.setFile(file);
		this.ListUsers = new ArrayList<User>();
		this.ListGroups = new ArrayList<Group>();
		this.loadDB();
	}
	
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public String getFile() {
		return file;
	}
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public void setFile(String file) {
		this.file = file;
	}
	
	private boolean contentUsers(String type){
		if(type.equals("Administrators") || type.equals("Students") || type.equals("Teachers"))
			return true;
		else return false;
	}
	
	public boolean loadDB(){
		SAXBuilder sxb = new SAXBuilder();
		try{
			this.document = sxb.build(new File(this.file));
			}catch(Exception e){}
		if (document!=null){
			this.racine = document.getRootElement();
			List<Element> listUsers = racine.getChildren();
			Iterator<Element> iUsers = listUsers.iterator();
			while(iUsers.hasNext()){
				Element courantI = (Element)iUsers.next();
				List<Element> listU = courantI.getChildren();
				Iterator<Element> i= listU.iterator();
				if(courantI.getName().equals("Groups")){
					while(i.hasNext()){
						Element courantGroup = (Element)i.next();
						this.ListGroups.add(new Group(Integer.valueOf(courantGroup.getChildText("Id"))));
					}
				}
				if(contentUsers(courantI.getName())){
					while(i.hasNext()){
						Element courantUser = (Element)i.next();
						if(courantUser.getName().equals("Administrator")){
							this.addUsertoDB(new Administrator(courantUser.getChildText("login"), 
													courantUser.getChildText("surname"), 
													courantUser.getChildText("firstname"), 
													courantUser.getChildText("pwd"), 
													Integer.valueOf(courantUser.getChildText("Id"))));
						}
						if(courantUser.getName().equals("Teacher")){
							this.addUsertoDB(new Teacher(courantUser.getChildText("login"), 
													courantUser.getChildText("surname"), 
													courantUser.getChildText("firstname"), 
													courantUser.getChildText("pwd"), 
													Integer.valueOf(courantUser.getChildText("Id"))));
						}
						if(courantUser.getName().equals("Student")){
							Student student = new Student(courantUser.getChildText("login"), 
															courantUser.getChildText("surname"), 
															courantUser.getChildText("firstname"), 
															courantUser.getChildText("pwd"), 
															Integer.valueOf(courantUser.getChildText("Id")),
															Integer.valueOf(courantUser.getChildText("groupId")));
							this.addUsertoDB(student);
							for(int j=0;j<this.ListGroups.size();j++){
								if(this.ListGroups.get(j).getGroupId()==student.getGroup())
									this.ListGroups.get(j).addStudent(student);
							}
						}
					}
				}	
			}
		}
		
		else{
			createNewBase();
			return false;
		}
		return true;
	}
	
	private void createNewBase(){
		this.racine = new Element("UserDB");
		this.document = new Document(racine);
		this.addUsertoDB(new Administrator("su", "su", "su", "su", 0));
	}
	
	private void saveUsertoDB(User user){
		Element TypeUser= racine.getChild(user.getType()+"s");
		if(TypeUser==null){
			racine.addContent(new Element(user.getType()+"s"));
			TypeUser= racine.getChild(user.getType()+"s");
		}
		Element User = new Element(user.getType());
		User.addContent(new Element("login").setText(user.getLogin()));
		User.addContent(new Element("firstname").setText(user.getFirstname()));
		User.addContent(new Element("surname").setText(user.getSurname()));
		User.addContent(new Element("pwd").setText(user.getPwd()));
		User.addContent(new Element("Id").setText(String.valueOf(user.getId())));
		if(user.getType().equals("Student"))
			User.addContent(new Element("groupId").setText(String.valueOf((((Student) user).getGroup() ))  )  )  ;
		TypeUser.addContent(User);
	}
	
	private void saveGrouptoDB(Group group){
		Element TypeUser= racine.getChild("Groups");
		if(TypeUser==null){
			racine.addContent(new Element("Groups"));
			TypeUser= racine.getChild("Groups");
		}
		Element Group = new Element("Group");
		Group.addContent(new Element("Id").setText(String.valueOf(group.getGroupId())));
		TypeUser.addContent(Group);
	}
	
	public boolean save(){
		this.racine = new Element("UserDB");
		this.document = new Document(racine);
		for(int i=0;i<this.ListGroups.size();i++){
			this.saveGrouptoDB(this.ListGroups.get(i));
		}
		for(int i=0; i<this.ListUsers.size();i++){
			this.saveUsertoDB(this.ListUsers.get(i));
		}

		try
		{
			XMLOutputter out= new XMLOutputter(Format.getPrettyFormat());
			out.output(document, new FileOutputStream(file));
		}catch (java.io.IOException e){ return false;}
		return true;
	}
	
	public boolean addUsertoDB(User user){
		this.ListUsers.add(user);
		return true;
	}
	
	private boolean isAlreadyInDB(String login){
		for(int i=0;i<this.ListUsers.size();i++){
			if(this.ListUsers.get(i).getLogin().equals(login))
				return true;
		}
		return false;
	}
	
	public boolean addAdmin(String adminLogin, String newlogin, int ID, String firstname, String surname,String pwd){
		if(isAlreadyInDB(newlogin))
			return false;
		
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator"))))
					return this.addUsertoDB(((Administrator) this.ListUsers.get(i)).addAdmin(newlogin,surname,firstname,pwd,ID));
		}
			
		return false;
	}
	
	public boolean addTeacher(String adminLogin, String newlogin, int ID, String firstname, String surname,String pwd){
		if(isAlreadyInDB(newlogin))
			return false;
		
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator"))))
					return this.addUsertoDB(((Administrator) this.ListUsers.get(i)).addTeacher(newlogin,surname,firstname,pwd,ID));
		}
			
		return false;
	}
	
	public boolean addStudent(String adminLogin, String newlogin, int ID, String firstname, String surname,String pwd){
		if(isAlreadyInDB(newlogin))
			return false;
		
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator"))))
					return this.addUsertoDB(((Administrator) this.ListUsers.get(i)).addStudent(newlogin,surname,firstname,pwd,ID));
		}
			
		return false;
	}
	
	public boolean removeUser(String adminLogin, String userLogin){
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator")))){
					((Administrator) this.ListUsers.get(i)).removeUser(userLogin,this.ListUsers,this.ListGroups);
					return true;
			}
		}
		return false;
	}
	
	public boolean addGroup(String adminLogin, int groupId){
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator")))){
					for(int j=0;j<this.ListGroups.size();j++){
						if(this.ListGroups.get(j).getGroupId()==groupId)
						return false;
					}
					((Administrator) this.ListUsers.get(i)).addGroup(groupId, ListGroups);
					return true;
			}
		}
		return false;
	}

	public  boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId){
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator"))))
					return ((Administrator) this.ListUsers.get(i)).associateStudToGroup(studentLogin, groupId, ListGroups, ListUsers);
		}
		return false;
	}
	
	public boolean removeGroup(String adminLogin, int groupId){
		for(int i=0;i<this.ListUsers.size();i++){
			if((this.ListUsers.get(i).getLogin().equals(adminLogin) && (this.ListUsers.get(i).getType().equals("Administrator"))))
					return ((Administrator) this.ListUsers.get(i)).removeGroup(groupId, ListGroups, ListUsers);
		}
		return false;
	}

	public String getUserClass(String userLogin, String userPwd){
		for(int i=0;i<this.ListUsers.size();i++){
			User user=this.ListUsers.get(i);
			if((user.getLogin().equals(userLogin)) && user.getPwd().equals(userPwd))
				return user.getType();
		}
		return "";
	}
	
	public String getUserName(String userLogin){
		for(int i=0;i<this.ListUsers.size();i++){
			User user=this.ListUsers.get(i);
			if((user.getLogin().equals(userLogin)))
				return user.getSurname()+" "+user.getFirstname();
		}
		return null;
	}
	
	public int getStudentGroup(String studentLogin){
		for(int i=0;i<this.ListUsers.size();i++){
			User user=this.ListUsers.get(i);
			if(user.getType().equals("Student")){
				if((user.getLogin().equals(studentLogin)))
					return ((Student) user).getGroup();
			}
		}
		return -1;
	}
	
	public String[] usersToString(){
		String[] tabUser= new String[this.ListUsers.size()];
		for(int i=0;i<this.ListUsers.size();i++){
			tabUser[i]=this.ListUsers.get(i).toString();
		}
		return tabUser;
	}
	
	public String[] usersLoginToString(){
		String[] tabUser= new String[this.ListUsers.size()];
		for(int i=0;i<this.ListUsers.size();i++){
			tabUser[i]=this.ListUsers.get(i).getLogin();
		}
		return tabUser;
	}
	
	public String[] studentsLoginToString(){
		ArrayList<String> ArraylistUsers=new ArrayList<String>();
		for(int i=0;i<this.ListUsers.size();i++){
			if(this.ListUsers.get(i).getType().equals("Student"))
				ArraylistUsers.add(this.ListUsers.get(i).getLogin());
		}
		String tab[] = new String[ArraylistUsers.size()];
		for(int i=0;i<ArraylistUsers.size();i++){
			tab[i]=ArraylistUsers.get(i);
		}
		return tab;
	}
	
	public String[] groupsIdToString() {
		String[] tabGroups= new String[this.ListGroups.size()];
		for(int i=0;i<this.ListGroups.size();i++){
			tabGroups[i]=String.valueOf(this.ListGroups.get(i).getGroupId());
		}
		return tabGroups;
	}
	
	public String[] groupsToString(){
		String[] tabGroups= new String[this.ListGroups.size()];
		for(int i=0;i<this.ListGroups.size();i++){
			tabGroups[i]=this.ListGroups.get(i).toString();
		}
		return tabGroups;
	}
}	
