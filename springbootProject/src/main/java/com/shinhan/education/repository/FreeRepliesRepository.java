package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.FreeBoard;
import com.shinhan.education.vo.FreeBoardReply;

//findAll,findById...
public interface FreeRepliesRepository extends CrudRepository<FreeBoardReply, Long>{
	//보드찾기
	List<FreeBoardReply> findByBoard(FreeBoard board);
	
}
