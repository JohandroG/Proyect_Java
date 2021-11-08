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
@Table(name = "notices")
public class Notice {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
	private Long notice_id;
	
	@NotNull
	@Size( min = 5, max = 500)
	private String title;
	
	@NotNull
	@Size( min = 5, max = 2000)
	private String description;
	
	@NotNull
	@Size( min = 5, max = 200)
	private String img;
	
	@NotNull
	@Size( min = 5, max = 200)
	private String img2;
	
	@NotNull
	@Size( min = 5, max = 700)
	private String link;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	        name = "associations",
	        joinColumns = @JoinColumn(name = "notice_id"), 
	        inverseJoinColumns = @JoinColumn(name = "user_id")
	        )
	private List<User> users;

	public Notice() {}
	
// Full Version
	public Notice(Long notice_id, String title,
			String description,  String img,
			String img2, String link,
			List<User> users) {
		this.notice_id = notice_id;
		this.title = title;
		this.description = description;
		this.img = img;
		this.img2 = img2;
		this.link = link;
		this.users = users;
	}

// Without ID
	public Notice(String title,
			String description,  String img,
			String img2, String link,
			List<User> users) {
		this.title = title;
		this.description = description;
		this.img = img;
		this.img2 = img2;
		this.link = link;
		this.users = users;
	}
	
// Only 1 IMG
	
		public Notice(String title,
				String description,  String img,String link,
				List<User> users) {
			this.title = title;
			this.description = description;
			this.img = img;
			this.link = link;
			this.users = users;
		}

//--------------------------------------------------------------------------------------------
		
		public Long getNotice_id() {
			return notice_id;
		}

		public void setNotice_id(Long notice_id) {
			this.notice_id = notice_id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getImg() {
			return img;
		}

		public void setImg(String img) {
			this.img = img;
		}

		public String getImg2() {
			return img2;
		}

		public void setImg2(String img2) {
			this.img2 = img2;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		
	
	
	
}
