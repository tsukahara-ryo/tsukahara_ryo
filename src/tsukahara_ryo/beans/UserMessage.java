package tsukahara_ryo.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int messagesId;
	private String subject;
	private String cotegory;
	private int user_id;
	private String text;
	private String name;
	private Date insertDate;
	public int getMessagesid() {
		return messagesId;
	}
	public void setMessagesId(int messagesId) {
		this.messagesId = messagesId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCotegory() {
		return cotegory;
	}
	public void setCotegory(String cotegory) {
		this.cotegory = cotegory;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}