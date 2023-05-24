package com.shinhan.education.vo2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="tbl_child1")
@IdClass(MultiKeyA.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultiKeyADTO {
	@Id
	private Integer id1;
	@Id 
	private Integer id2;
	private String userName;
	private String address;
}
