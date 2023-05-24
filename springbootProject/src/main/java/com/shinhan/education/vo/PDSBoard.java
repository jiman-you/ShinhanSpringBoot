package com.shinhan.education.vo;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString//(exclude = "files2")//exclude 이 칼럼은 안보여줘도대
@Entity
@Builder@NoArgsConstructor@AllArgsConstructor
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid") // pid만 같으면 같다
public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;
	// cascade:영속성전이 PDSBoard변경시 PDSFile변경)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 즉시로딩
	// JPA는 default로 지연로딩을 사용한다.(PDSBoard조회시 PDSFile 조인하지않음)
	// 1)fetch = FetchType.LAZY ....PDSFile과 연결불가, @Query로 해결
	// 2)fetch = FetchType.EAGER....PDSFile과 연결가능
	//이거하고 레이지 꼭 기억하기
	//이거==내가올때너도와라 레이지==귀찮으면집에잇어라
	@JoinColumn(name = "pdsno") // PDSFile칼럼에 생성 ----join칼럼을 붙이지 않으면 중간 테이블이 생김.
	private List<PDSFile> files2;//files2 변수는 칼럼으로 생성되지 않음
	//List<PDSFile>객체를 받으면 pdsno로 조인을 해서 fetch = FetchType.EAGER 때문에 즉시 로딩되어 pdsfile쪽에 pdsno 업데이트
}
