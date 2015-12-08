package model;

public class User {
	public String name;
	public String phone;
	public int projectsNumber;
	
	public User (String name, String phone, int projectsNumber) {
		this.name = name;
		this.phone = phone;
		this.projectsNumber = projectsNumber;
	}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String name) {
        this.phone = name;
    }
    
    public int getProjectsNumber() {
        return projectsNumber;
    }

    public void setProjectsNumber(int projectsNumber) {
        this.projectsNumber = projectsNumber;
    }
    
    public void plusProjectsNumber() {
        projectsNumber++;
    }
    
    public void minusProjectsNumber() {
        projectsNumber--;
    }
   
    public String getInfo () {
    	return "I AM USER "+name+", I'VE GOT "+projectsNumber+" PROJECTS, CALL ME MABYE: "+phone;
    }

}
