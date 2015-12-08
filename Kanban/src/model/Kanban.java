package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbconnection.DBConnection;


public class Kanban {
	
	String name;
	String description;
	static List<KanbanBoard> boardList = new ArrayList<KanbanBoard>(); //list of projects
	static DBConnection db = new DBConnection();
	static Map<String, User> userMap = new HashMap<String, User>();
	
	static void addKanbonBoard (String name, String description) {
		boardList.add(new KanbanBoard(name, description));
		
	}
	
	public static void showUsers () {
		userMap = db.selectUsers();
		for (Map.Entry<String, User> entry : userMap.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().getInfo());
		}
	}
	
	public static void addUser (String name, String phone) {
		int flag = 0;
		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			if (entry.getKey().equals(name)) {
		    	System.out.println("NIE DODANO UZYTKOWNIKA, PODANY LOGIN JEST JU� ZAJ�TY!!! WYBIERZ INNY");
		    	flag = 1;
		    }
		}
		if (flag == 0) {
		    	db.insertUsers(name, phone);
				userMap = db.selectUsers();
		}
		
	}
	
	public static void deleteUser (String name) {
		if (userMap.get(name).projectsNumber == 0) {
			db.deleteUsers(name);
		} else {
			System.out.println("NIE MO�NA USUN�� U�YTKOWNIKA PONIEWA� JEST PRZYPISANY DO ZADA�!!!!");
		}
		
	}
	
	public static void updateUser (String oldName, String newName, String newPhone) {
		int oldProjectsNumber = userMap.get(oldName).projectsNumber;
		int flag = 0;
		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			if (entry.getKey().equals(newName)) {
		    	System.out.println("NIE ZAKTUALIZOWANO, PODANY LOGIN JEST JU� ZAJ�TY!!! WYBIERZ INNY");
		        flag = 1;
			}
		}
		if (flag == 0) {
			db.deleteUsers(oldName);
			db.updateUsers(newName, newPhone, oldProjectsNumber);
			userMap = db.selectUsers();
		}
	}

	public static void main(String[] args) {
		userMap = db.selectUsers();
		
		//showUsers();
		//System.out.println();
		//addUser("Ula", "777888777");
		
		//updateUser("Ula", "Ewa", "123123123");
		//updateUser("Ula", "Urszula", "123123123");
		showUsers();
		
		
		//new kanban board created
		addKanbonBoard("Rozk�ad jazdy", "oto opis kanban boarda rozk�ad jazdy");
		
		//dodajemy kilka kolumn
		boardList.get(0).column.add(new Column("To Do"));
		boardList.get(0).column.add(new Column("Development"));
		boardList.get(0).column.add(new Column("Tests"));
		boardList.get(0).column.add(new Column("Completed"));
		
		//3 new tasks added
		boardList.get(0).column.get(0).task.add(new Task("Interfejs u�ytkownika", 
				"stworzy� przejrzysty, graficzny interfejs u�ytkownika"));		// adding some data
		boardList.get(0).column.get(0).task.add(new Task("Zadanie2", 
				"opis2", 3));
		boardList.get(0).column.get(1).task.add(new Task("Zadanie3", 
				"opis3", 2));
		
		//owners assigned to the tasks
		boardList.get(0).column.get(0).task.get(0).owner.add(userMap.get("Adam"));
		userMap.get("Adam").projectsNumber++;
		boardList.get(0).column.get(0).task.get(0).owner.add(userMap.get("Arek"));
		userMap.get("Arek").projectsNumber++;
		boardList.get(0).column.get(0).task.get(1).owner.add(userMap.get("Adam"));
		userMap.get("Adam").projectsNumber++;
		boardList.get(0).column.get(1).task.get(0).owner.add(userMap.get("Adam"));
		userMap.get("Adam").projectsNumber++;
		
		//deadline added
		boardList.get(0).column.get(1).task.get(0).setDeadline("2015.12.12");
		
		//new coment added
		boardList.get(0).column.get(1).task.get(0).comment.add
		(new Comment("Si� robi", "Bu�ka z mas�em :)", userMap.get("Arek")));
		
		System.out.println(boardList.get(0).getInfo());  //writing in the console
				
		
		db.closeConnection();
	}

}
