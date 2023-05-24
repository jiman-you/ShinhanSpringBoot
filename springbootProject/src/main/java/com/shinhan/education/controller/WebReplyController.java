package com.shinhan.education.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@RestController
@RequestMapping("/replies")
public class WebReplyController {//댓글 컨트롤러 RestFul방식
	
	@Autowired
	WebReplyRepository replyRepo;
	@Autowired
	WebBoardRepository boardRepo;
	
	//return의 공통된 부분을 하나의 함수로 묶어버림
	private ResponseEntity<List<WebReply>> makeReturn(Long bno, HttpStatus status){
		
		WebBoard board =  WebBoard.builder().bno(bno).title("").build();
		List<WebReply> replies =  replyRepo.findByBoardOrderByRnoDesc(board);
		
		return new ResponseEntity<>(replies,status) ; //값도 주고 상태 값도 준다.
	}
	
	//보드에 대한 댓글조회
	@GetMapping("/{bno}")
	public ResponseEntity<List<WebReply>> selectAllReply(@PathVariable("bno") Long bno) {
		return makeReturn(bno, HttpStatus.OK);
	}
	
	//댓글추가
	//@RequestBody 바디부분에 값을 받았을 때
	//@PathVariable 주소부분에 값을 넣어야할때
	@PostMapping("/{bno}")
	public ResponseEntity<List<WebReply>> insert(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
		
		WebBoard board =  boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		
		return makeReturn(bno, HttpStatus.CREATED);
	}

	//댓글 수정
	@PutMapping("/{bno}")
	public ResponseEntity<List<WebReply>> updateReply(@PathVariable("bno") Long bno, @RequestBody WebReply reply) {
		
		WebBoard board =  boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		return makeReturn(bno, HttpStatus.OK);
	}
	
	//댓글 삭제
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<WebReply>> deleteReply(@PathVariable("bno") Long bno, @PathVariable("rno") Long rno) {

		replyRepo.deleteById(rno);
		return makeReturn(bno, HttpStatus.OK);
	}
	
}
