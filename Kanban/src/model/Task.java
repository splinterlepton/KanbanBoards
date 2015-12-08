package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import java.util.Date;

public class Task {

	public String name;
	public String desc;
    private int storyPoints = 0;
    
	List<User> owner = new ArrayList<User>();
	List<Comment> comment = new ArrayList<Comment>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	Date date = null;


	
	public Task (String name) {
		this.name = name;
	}
	
	public Task (String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public Task (String name, String desc, int storyPoints) {
		this.name = name;
		this.desc = desc;
		this.storyPoints = storyPoints;
	}
	
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setDesc(String spec) {
        this.desc = spec;
    }
    
    public String getDesc() {
        return desc;
    }

    public int getStoryPoints() {
        return storyPoints;
    }
    
    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

	public void setDeadline (String stringDate) {
		try {
		    date = sdf.parse(stringDate);
		} catch (ParseException pe) {
		    System.out.println("Wrong data format!");
		}
	}
	
	
    
	public String getInfo() {
		String buffer = "TASK NAME: " + name + ", DESCRIPTION: " + desc + "\nOWNER(S):";
		for (int i=0; i<owner.size(); i++) {
			if (i>0) {
				buffer += " i";
			}
			buffer = buffer + " " + owner.get(i).name;
		}
		buffer = buffer + " STORY POINTS: " + storyPoints + "\n" + "DEADLINE: " + date;
		for (Comment i: comment) {
			buffer = buffer + "\n" + i.getInfo();
		}
		return buffer;
	}
}
