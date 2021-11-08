package com.board.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "associations")
public class Association {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
	private Long as_id;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="notice_id")
	private Notice notice;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="code_id")
	private Code code;
	
	
	public Association (){}

//---------------------------------------------Getters y Setters----------------------------------
	public Long getAs_id() {
		return as_id;
	}


	public void setAs_id(Long as_id) {
		this.as_id = as_id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Notice getNotice() {
		return notice;
	}


	public void setNotice(Notice notice) {
		this.notice = notice;
	}


	public Code getCode() {
		return code;
	}


	public void setCode(Code code) {
		this.code = code;
	}
	
	
	
	
}
