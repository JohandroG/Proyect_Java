package com.board.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hostcode")
public class Code {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
	private Long code_id;
	
	@NotNull
	@Size( min = 5, max = 20)
	private String hcode;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "associations",
	        joinColumns = @JoinColumn(name = "code_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	        )
	private List<User> users;

	
	public Code() {}
	
	public Code(Long code_id, String hcode, List<User> users) {
		this.code_id = code_id;
		this.hcode = hcode;
		this.users = users;
	}
	
	public Code(Long code_id, String hcode) {
		this.code_id = code_id;
		this.hcode = hcode;
	}
	
	public Code(String hcode, List<User> users) {
		this.hcode = hcode;
		this.users = users;
	}

//------------------------------------------------------------------------------------------
	
	public Long getCode_id() {
		return code_id;
	}

	public void setCode_id(Long code_id) {
		this.code_id = code_id;
	}

	public String getHcode() {
		return hcode;
	}

	public void setHcode(String hcode) {
		this.hcode = hcode;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	
	
	
	
}
