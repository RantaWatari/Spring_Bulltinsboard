package com.tuyano.springboot;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="userdata")
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;
	
	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 200, nullable = true)
	private String text;

	@Column()
	private LocalDateTime time;
	
	@Column()
	private String stringTime;




	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	// LocalDateTime型のtime
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	// String型のtime
	public String getStringTime() {
		return stringTime;
	}
	public void setStringTime(String stringTime) {
		this.stringTime = stringTime;
	}

}