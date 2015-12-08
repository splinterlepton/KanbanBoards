package model;

import java.util.HashMap;
import java.util.Map;

import dbconnection.DBConnection;


public class Kanban {
	
	static Map<String, KanbanBoard> boardList = new HashMap<String, KanbanBoard>(); //list of projects
	static DBConnection db = new DBConnection();
	static Map<String, User> userMap = new HashMap<String, User>();
	
	static Map<String, User> test = new HashMap<String, User>();

	
	static void addKanbonBoard (String name, String description) {
		boardList.put(name, new KanbanBoard(name, description));
	}
	
	static void updateKanbonBoard (String name, String newName, String newDescription) {
		boardList.get(name).setComment(newDescription);
		boardList.get(name).setName(newName);
	}
	
	static void deleteKanbonBoard (String name) {
		boardList.remove(name);
	}
	
	static void viewKanbanBoardsList () {
		for (Map.Entry<String, KanbanBoard> entry : boardList.entrySet()) {
		    System.out.println(entry.getValue().getList());
		}
	}
	
	static void viewKanbanBoard (String name) {
		System.out.println(boardList.get(name).getInfo());
	}
	
	static void addColumn (String kanbanBoardName, String columnName) {
		boardList.get(kanbanBoardName).column.put(columnName, new Column(columnName));
	}
	
	static void updateColumn (String kanbanBoardName, String columnName, String newColumnName) {
		boardList.get(kanbanBoardName).column.get(columnName).setName(newColumnName);;
	}
	
	static void deleteColumn (String kanbanBoardName, String columnName) {
		boardList.get(kanbanBoardName).column.remove(columnName);
	}
	
	static void addTask (String kanbanBoardName, String columnName, String taskName, String desc) {
		boardList.get(kanbanBoardName).column.get(columnName).task.put(taskName, new Task(taskName, desc));
	}
	
	static void deleteTask (String kanbanBoardName, String columnName, String taskName) {
		boardList.get(kanbanBoardName).column.get(columnName).task.remove(taskName);
	}
	
	static void moveTask (String kanbanBoardName, String taskName, String oldColumnName, String newColumnName) {
		boardList.get(kanbanBoardName).column.get(newColumnName).task.put(taskName, 
				boardList.get(kanbanBoardName).column.get(oldColumnName).task.get(taskName));
		boardList.get(kanbanBoardName).column.get(oldColumnName).task.remove(taskName);
	}
	
	static void updateTaskName (String kanbanBoardName, String columnName, String oldTaskName, String newTaskName) {
		boardList.get(kanbanBoardName).column.get(columnName).task.put(newTaskName, 
				boardList.get(kanbanBoardName).column.get(columnName).task.get(oldTaskName));
		boardList.get(kanbanBoardName).column.get(columnName).task.remove(oldTaskName);
	}

	static void updateTaskDesc (String kanbanBoardName, String columnName, String taskName, String newDesc) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).setDesc(newDesc);
	}
	
	static void updateTaskStoryPoints (String kanbanBoardName, String columnName, String taskName, int newStoryPoints) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).setStoryPoints(newStoryPoints);
	}
	
	static void updateTaskDate (String kanbanBoardName, String columnName, String taskName, String newDateyyyyMMdd) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).setDeadline(newDateyyyyMMdd);
	}

	static void addComment (String kanbanBoardName, String columnName, String taskName, String title, String text, String owner) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).
		comment.put(title, new Comment(title, text, userMap.get(owner)));
	}
	
	static void deleteComment (String kanbanBoardName, String columnName, String taskName, String title) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).
		comment.remove(title);
	}
	
	static void addTaskUser (String kanbanBoardName, String columnName, String taskName, String userName) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).
		owner.put(userName, userMap.get(userName));
		userMap.get(userName).plusProjectsNumber();
	}
	
	static void deleteTaskUser (String kanbanBoardName, String columnName, String taskName, String userName) {
		boardList.get(kanbanBoardName).column.get(columnName).task.get(taskName).
		owner.remove(userName);
		userMap.get(userName).minusProjectsNumber();
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
		
		//System.out.println();
		//addUser("Ula", "777888777");
		
		//updateUser("Ula", "Ewa", "123123123");
		//updateUser("Ula", "Urszula", "123123123");
		showUsers();
		
		System.out.println("--------------");
		
		//new kanban board created
		addKanbonBoard("Rozk�ad jazdy", "stworzenie interaktywnego rozk�adu jazdy kolejek SKM");
		addKanbonBoard("Przewodnik po ZOO", "stworzenie przewodnika po ZOO na urz�dzenia mobilne");
		addKanbonBoard("System AVR", "stworzenie systemu 'automated voice response'");
		
		viewKanbanBoardsList();
		System.out.println("-------------");
		
		updateKanbonBoard("Przewodnik po ZOO", "ZOO", "przewodnik po ZOO na urz�dzenia mobilne");
		deleteKanbonBoard("System AVR");
		
		viewKanbanBoardsList();
		System.out.println("-------------");
		
		
		addColumn("Rozk�ad jazdy", "ToDo");
		addColumn("Rozk�ad jazdy", "Development");
		addColumn("Rozk�ad jazdy", "Tests");
		addColumn("Rozk�ad jazdy", "Completed");
		
		addTask("Rozk�ad jazdy", "Development", "GUI", "Stworzy� przejrzysty, graficzny interfejs u�ytkownika");
		addTask("Rozk�ad jazdy", "Development", "Zadanie2", "Opis2");
		addTask("Rozk�ad jazdy", "Tests", "Zadanie3", "Opis3");
		addTask("Rozk�ad jazdy", "ToDo", "Zadanie4", "Opis4");
		
		updateTaskStoryPoints("Rozk�ad jazdy", "Tests", "Zadanie3", 3);
		updateTaskDate("Rozk�ad jazdy", "Tests", "Zadanie3", "20151216");
		
		addTaskUser("Rozk�ad jazdy", "Development", "GUI", "Adam");
		addTaskUser("Rozk�ad jazdy", "Development", "Zadanie2", "Arek");
		addTaskUser("Rozk�ad jazdy", "Development", "Zadanie2", "Piotrek");
		addTaskUser("Rozk�ad jazdy", "Tests", "Zadanie3", "Ewa");
		
		addComment("Rozk�ad jazdy", "Tests", "Zadanie3", "Si� robi", "bu�ka z mas�em", "Ewa");
		
		viewKanbanBoard("Rozk�ad jazdy");
		
		
		db.closeConnection();
	}

}
