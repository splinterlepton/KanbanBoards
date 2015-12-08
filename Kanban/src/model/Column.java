package model;
import java.util.HashMap;
import java.util.Map;

public class Column {

	public String name;
	private String buffer1;  //used in getInfo
	
	Map<String, Task> task = new HashMap<String, Task>();
	
	public Column (String name) {
		this.name = name;
	}
	
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
	
	public String getInfo () {
		buffer1 = "<<<<<< "+name+" >>>>>>";
		for (Map.Entry<String, Task> entry : task.entrySet()) {
		    buffer1 = buffer1 + "\n\n" + entry.getValue().getInfo();
		}		
		return buffer1;
	}
}
