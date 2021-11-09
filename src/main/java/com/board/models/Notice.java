package com.board.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "notices")
public class Notice {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@NotNull
	private Long notice_id;
	
	@NotNull
	@Size( min = 5, max = 400)
	private String title;
	
	@NotNull
	@Size( min = 5, max = 5000)
	private String description;
	
	
	@Size( min = 2, max = 500)
	private String img;
	

	@Size( min = 2, max = 500)
	private String img2;
	
	
	@Size(max = 500)
	private String link;
	
	@NotNull
	@Size( min = 2, max = 10)
	private String importance;
//---------------------------------------------
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date created_at;
	
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updated_at;
	
    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updated_at = new Date();
    }
//------------------------------------------------
	
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
			String img2, String link, String importance,
			List<User> users) {
		this.notice_id = notice_id;
		this.title = title;
		this.description = description;
		this.img = img;
		this.img2 = img2;
		this.link = link;
		this.importance = importance;
		this.users = users;
	}

// Without ID
	public Notice(String title,
			String description,  String img,
			String img2, String link, String importance,
			List<User> users) {
		this.title = title;
		this.description = description;
		this.img = img;
		this.img2 = img2;
		this.link = link;
		this.importance = importance;
		this.users = users;
	}
	
// Only 1 IMG
	
		public Notice(String title,
				String description,  String img,String link, String importance,
				List<User> users) {
			this.title = title;
			this.description = description;
			this.img = img;
			this.link = link;
			this.importance = importance;
			this.users = users;
		}

		
		
// Only 1 IMG (With ID)
		
			public Notice(Long notice_id,String title,
					String description,  String img,String link, String importance,
					List<User> users) {
				this.notice_id = notice_id;
				this.title = title;
				this.description = description;
				this.img = img;
				this.link = link;
				this.importance = importance;
				this.users = users;
			}
			
		
// NO IMG
		
			public Notice(String title,
					String description,String link, String importance,
					List<User> users) {
				this.title = title;
				this.description = description;
				this.link = link;
				this.importance = importance;
				this.users = users;
			}
			
// NO IMG (With ID)
	
		public Notice(Long notice_id, String title,
				String description,String link, String importance,
				List<User> users) {
			this.notice_id = notice_id;
			this.title = title;
			this.description = description;
			this.link = link;
			this.importance = importance;
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

		public String getImportance() {
			return importance;
		}

		public void setImportance(String importance) {
			this.importance = importance;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public Date getUpdated_at() {
			return updated_at;
		}

		public void setUpdated_at(Date updated_at) {
			this.updated_at = updated_at;
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
