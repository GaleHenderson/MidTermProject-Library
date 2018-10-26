

public class Book {
	private String title;
	private String author;
	private boolean status;
	private String dueDate;
	
	
	public String getTitle() {
		return title;
	}
	public Book() {
		super();
		this.title = title;
		this.author = author;
		this.status = status;
		this.dueDate = dueDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {

		
		return title + " ," + " Author:  " + author;
		//return title + "," + author + "," + status + "," + dueDate;
	}
	
	
}
