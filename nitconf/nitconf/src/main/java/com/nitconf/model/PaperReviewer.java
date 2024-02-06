package com.nitconf.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class PaperReviewer {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;
    private float rating;
    private LocalDate assigneddate;
    public LocalDate getAssigneddate() {
		return assigneddate;
	}
	public void setAssigneddate(LocalDate assigneddate) {
		this.assigneddate = assigneddate;
	}
	private Long paper_id;
    private long reviewer_id;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Long getPaper_id() {
		return paper_id;
	}
	public void setPaper_id(Long paper_id) {
		this.paper_id = paper_id;
	}
	public long getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(long reviewer_id) {
		this.reviewer_id = reviewer_id;
	}
	
    
}
