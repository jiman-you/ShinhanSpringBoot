package com.shinhan.education.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor
@Entity
@Table(name="tbl_freeboards")
@EqualsAndHashCode(of= {"bno","title"})
@Builder
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	//연관관계설정:1:n
	//mappedBy
	@JsonIgnore//jackson이 json만들때 무시
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "board")
	List<FreeBoardReply> replies;
}
