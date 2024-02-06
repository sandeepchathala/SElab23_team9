package com.nitconf.model;

//import java.sql.Date;
//import java.util.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Paper {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private String link;
  //@Lob
  //private byte[] paperdata;
  private String tags;
  private int status;
  private long authorid;
  public long getAuthorid() {
		return authorid;
	}
	public void setAuthorid(long author_id) {
		this.authorid = author_id;
	}
	private LocalDate uploadeddate;
  
	public LocalDate getUploadeddate() {
		return uploadeddate;
	}
	public void setUploadeddate(LocalDate uploadeddate) {
		this.uploadeddate = uploadeddate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
  
  
}
