package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.FreeBoard;
import com.shinhan.education.vo.FreeBoardReply;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long>{

	//보드번호~이상 패이징추가
	List<FreeBoard> findByBnoGreaterThan(Long bno, Pageable paging);
}
