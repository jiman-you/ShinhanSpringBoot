package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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


@Getter
@Setter
@ToString
@Entity//jpa가 관리
@Table(name = "t_boards")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long bno;
		
		@NonNull //생성 시 반드시 값이 있어야한다 (lom)
		@Column(nullable=false)
		private String title;
		
		@Column(length = 100)
		private String content;
		private String writer;
		
		@CreationTimestamp //insert시 시각이입력
		private Timestamp regdate;
		@UpdateTimestamp //update시 수정 시각이 입력
		private Timestamp updatedate;
}
