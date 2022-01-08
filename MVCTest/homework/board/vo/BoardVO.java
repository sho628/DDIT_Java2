package board.vo;

import java.util.Date;

public class BoardVO {
	private String No;
	private String title;
	private String name;
	private String date;
	private String content;
	
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "BoardVO [No=" + No + ", title=" + title + ", name=" + name + ", date=" + date + ", content=" + content
				+ "]";
	}
	
	
	
}
