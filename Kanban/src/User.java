
public class User {
	public String name;
	public String phone;
	
	public User (String name, String phone) {
		this.name = name;
		this.phone = phone;
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
    
    public String getInfo () {
    	return "I AM USER "+name+" CALL ME MABYE: "+phone;
    }

}
