package com.shinhan.education.vo2;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.shinhan.education.vo.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class EnumTypeVO {

		@Id
		private String mid;
		private String mpass;
		private String mname;
		@ElementCollection(targetClass = MemberRole.class, fetch = FetchType.EAGER)
		@CollectionTable(name="tbl_enum_mroles",
		joinColumns = @JoinColumn(name="mid"))
		@Enumerated(EnumType.STRING)
		Set<MemberRole> mrole;
		
}
//한명의 member가 여러 개의 권한을 가지고있다.//tmㅅmldm
