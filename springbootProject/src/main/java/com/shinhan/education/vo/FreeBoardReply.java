package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter@ToString(exclude = "board")
@Entity
@Table(name="tbl_free_Replies")
@EqualsAndHashCode(of="rno")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FreeBoardReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rno;
	private String reply;
	private String replyer;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	
	//연관관계 설정n:1
	//FK: 킬람이 board_bnofh 로 생성된다
	@ManyToOne
	FreeBoard board;
}
