package com.shinhan.education.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Builder@NoArgsConstructor@AllArgsConstructor
@Table(name = "tbl_pdsfiles")
@EqualsAndHashCode(of = "fno")
public class PDSFile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fno;
	private String pdsfilename;
}
