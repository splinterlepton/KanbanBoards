import java.util.ArrayList;
import java.util.List;

public class Kanban {

	public static void main(String[] args) {
		
		List<KanbanBoard> boardList = new ArrayList<KanbanBoard>(); //list of projects
		
		boardList.add(new KanbanBoard("Rozk�ad jazdy", 
				"stworzenie interaktywnego rozk�adu jazdy kolejek SKM na urz�dzenia mobine")); //adding a new project (new KanbanBoard)
		boardList.get(0).column.add(new Column("To Do"));	//setting project columns
		boardList.get(0).column.add(new Column("Development"));
		boardList.get(0).column.add(new Column("Tests"));
		boardList.get(0).column.add(new Column("Completed"));
		
		boardList.get(0).column.get(0).task.add(new Task("Interfejs u�ytkownika", 
				"stworzy� przejrzysty, graficzny interfejs u�ytkownika", "brak komentarza"));		// adding some data
		boardList.get(0).column.get(0).task.add(new Task("Zadanie2", 
				"opis2", "komentarz2"));
		boardList.get(0).column.get(1).task.add(new Task("Zadanie3", 
				"opis3", "komentarz3"));
		
		System.out.println(boardList.get(0).getInfo());  //writing in the console
		
		
		//hashmap nazwa - nazwa projektu, warto�� - board
	}

}
