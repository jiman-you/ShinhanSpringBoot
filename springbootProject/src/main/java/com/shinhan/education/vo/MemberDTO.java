package com.shinhan.education.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tbl_members")
public class MemberDTO {
    @Id
	String mid;
	String mpassword;
	String mname;
	
	
	@Enumerated(EnumType.STRING)//열거형 타입중 문자만 저장
	MemberRole mrole;
	
}

