package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo.FreeBoardReply;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@SpringBootTest
public class WebBoardRepositoryTest {

	@Autowired
	WebBoardRepository boardRepo;
	@Autowired
	WebReplyRepository replyRepo;
	
	//특정보드댓글(reply에서 시작)
	//@Test
	void ReplySelectbyboard2() {
	WebBoard board = boardRepo.findById(144L).get();
	List<WebReply> replyList = replyRepo.findByBoardOrderByRnoDesc(board);
	replyList.forEach(reply->{
		System.out.println(reply);
	});
	}


	
	//특정 보드 댓글(board에서 시작)
	//@Test
	void ReplySelectbyboard() {
		boardRepo.findById(144L).ifPresent(board->{
			List<WebReply> replyList=board.getReplies();
			for(WebReply reply :replyList) {
				System.out.println(reply);
			}
		});
	}
	
	//모든보드 조회하기
	//@Test
	void BoardSelectAll() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	//5개의 board에 댓글 10개달기
	@Test
	void ReplyInsert() {
		Long[] arr = {144L, 157L, 163L, 183L};
		Arrays.stream(arr).forEach(index -> {
			boardRepo.findById(index).ifPresent(board ->{
				IntStream.rangeClosed(1,10).forEach(i->{
					WebReply reply = WebReply.builder()
							.replyText("댓글"+i)
							.replyer("user"+(i%3))
							.board(board)
							.build();
					replyRepo.save(reply);
				});
			});
		});
	}
	//board에 insert100
	@Test
	void BoardInsert() {
		IntStream.rangeClosed(100, 101).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("board"+i)
					.writer("user"+(i%10))
					.content("정말재밋는내용"+i)
					.build();
			boardRepo.save(board);
		});
	
			
	}
	
}
