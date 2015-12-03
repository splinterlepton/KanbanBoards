public class Comment {
	public String title;
	public String text;
	User owner;
	
	Comment (String title, String text, User owner) {
		this.title = title;
		this.text = text;
		this.owner = owner;
	}
	
	public String getInfo () {
		return "COMMENT: " + title + " : " + text + " WRITTEN BY " + owner.name;
	}

}
