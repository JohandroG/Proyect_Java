package com.board.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "users")
public class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
	private Long user_id;
	
	@NotNull
	@Size( min = 5, max = 100)
	private String fullname;

	@NotNull
	@Size( min = 5, max = 100)
	private String username;
	
	@NotNull
	@Size( min = 5, max = 200)
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "associations",
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "notice_id")
	        )
	private List<Notice> notices;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "associations",
	        joinColumns = @JoinColumn(name = "user_id"),
	        inverseJoinColumns = @JoinColumn(name = "code_id")
	        )
	private List<Code> codes;
	
	public User() {}

	public User(Long user_id, String fullname,
			String username, String password,
			List<Notice> notices, List<Code> codes) {
		this.user_id = user_id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.notices = notices;
		this.codes = codes;
	}
	
	
	public User(String fullname,
			String username, String password,
			List<Notice> notices, List<Code> codes) {
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.notices = notices;
		this.codes = codes;
	}
	
	public User(String fullname,
			String username, String password) {
		this.fullname = fullname;
		this.username = username;
		this.password = password;
	}

//--------------------------------------------------------------------------------------------
	
	
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Notice> getNotices() {
		return notices;
	}

	public void setNotices(List<Notice> notices) {
		this.notices = notices;
	}

	public List<Code> getCodes() {
		return codes;
	}

	public void setCodes(List<Code> codes) {
		this.codes = codes;
	}
	
	
	
	
	

}
