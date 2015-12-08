package model;
import java.util.HashMap;
import java.util.Map;

public class KanbanBoard {
	
	public String name;
	public String desc;
	private String buffer2; //used in getInfo
	
	Map<String, Column> column = new HashMap<String, Column>();
	
	public KanbanBoard (String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getComment() {
        return desc;
    }

    public void setComment(String desc) {
        this.desc = desc;
    }
    
	public String getInfo () {
		buffer2 = "<<<<<<<<<<<<<<< KANBAN BOARD "+name+">>>>>>>>>>>>>>\n";
		buffer2 = buffer2 + desc;
		for (Map.Entry<String, Column> entry : column.entrySet()) {
		    buffer2 = buffer2 + "\n\n" + entry.getValue().getInfo();
		}
		return buffer2;
	}
	
	public String getList () {
		return "KANBAN BOARD: "+name+" DESCRIPTION: "+desc+"\n";
	}

}
