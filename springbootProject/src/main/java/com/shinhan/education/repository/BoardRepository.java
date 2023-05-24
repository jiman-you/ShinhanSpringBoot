package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shinhan.education.vo.BoardVO;

//interface를 설계...CRUD작업을 위해
//구현은 JPA가 한다
//1. 기본은 findAll(), findByIdㄴ은 추가해주어야한다 findBy~~
//2.JPQL 사용 : @Query ---nativaQuery=true
//3.QueryDSL사용하여 동적 sql만든다
public interface BoardRepository extends CrudRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO> {
	//조건 조회를 추가
	public List<BoardVO> findByTitle(String title);
	public List<BoardVO> findByContent(String con);
	public List<BoardVO> findByWriter(String write);
	public List<BoardVO> findByTitleAndWriter(String title,String write);
	
	public List<BoardVO> findByTitleContaining(String title);//where title like '%?%'
	public List<BoardVO> findByTitleStartingWith(String title);//where title like '?%'
	public List<BoardVO> findByTitleEndingWith(String title);//where title like '%?'
	
	//title like '%?%' and bno >?
	public List<BoardVO> findByTitleContainingAndBnoGreaterThan(String title,Long id);//where title like '%?%'
	
	public List<BoardVO> findByBnoBetweenOrderByBnoDesc(Long bno1, Long bno2);
	
	public List<BoardVO> findByContentIgnoreCase(String contetn); //where upper(Content)=? or dupper(content)?

	//where Title like? order by title desc
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title);
	
	//title로 조회 ,sort desc , paging=>page,size 몇번째페이지인데 사이즈가 몇개있어 
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title,Pageable paging);
	public List<BoardVO> findByTitleContaining(String title,Pageable paging);
	public Page<BoardVO> findByBnoGreaterThan(Long bno,Pageable paging);
	
	//JPQL(JPL Query Language)...*지원 안됨
	@Query("select b from BoardVO b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle2(String title,String content);
	
	@Query("select b from BoardVO b where b.title like %:tt%"
			+ " and b.content like %:cc% order by b.bno desc")
	public List<BoardVO> findByTitle3(@Param("tt") String title,@Param("cc") String content);

	@Query("select b from #{#entityName} b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<BoardVO> findByTitle4(String title,String content);

	@Query("select b.title, b.content,b.writer from #{#entityName} b where b.title like %?1%"
			+ "and b.content like %?2% order by b.bno desc")
	public List<Object[]> findByTitle5(String title,String content);
	
	//직접sql입력 남용하지말자
	@Query(value="select * from t_boards b where b.title like '%'||?1||'%'"
			+ "and b.content like '%'||?2||'%' order by b.bno desc", nativeQuery = true)
	public List<BoardVO> findByTitle6(String title,String content);
}
