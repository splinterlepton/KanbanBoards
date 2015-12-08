package model;
import java.util.ArrayList;
import java.util.List;

public class Column {

	public String name;
	private String buffer1;  //used in getInfo
	
	List<Task> task = new ArrayList<Task>();
	
	public Column (String name) {
		this.name = name;
	}
	
//	for (Task i: task) {
//		System.out.println(i.getInfo());
//	}
	
	public String getInfo () {
		buffer1 = "<<<<<< "+name+" >>>>>>";
		for (Task i: task) {
			buffer1 = buffer1 + "\n\n" + i.getInfo();
		}
		return buffer1;
	}
}
