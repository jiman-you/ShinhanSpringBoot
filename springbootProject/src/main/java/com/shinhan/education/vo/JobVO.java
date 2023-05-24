package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@Entity@Table(name="t_jobs")
@Builder@AllArgsConstructor@NoArgsConstructor

public class JobVO {
	@Id
	private String jobId;
	
	@Column(unique = true, nullable=false,length=35)
	private String jobTitle;
	private int maxSalary;
	private int minSalary;
	
	@CreationTimestamp//입력시
	private Timestamp regDate;
	@UpdateTimestamp//입력시 수정시
	private Timestamp updateDate;
}
