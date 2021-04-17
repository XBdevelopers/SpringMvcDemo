package com.example.login.session.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TODO_TABLE")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Todo {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	@Column(name = "TODO_ID", updatable = false, nullable = false)
	private int todoId;
	
	@Column(name = "USER_ID", updatable = false, nullable = false)
	private String userId;
	
	@Column(name = "DESC")
	private String desc;
	
	@Column(name = "TARGET_DATE")
	private Date targetDate;
	
	@Column(name = "IS_DONE")
	private boolean isDone;

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}
