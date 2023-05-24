package com.shinhan.education;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;
import com.shinhan.education.repository.MultiKeyARepository;
import com.shinhan.education.vo.FreeBoard;
import com.shinhan.education.vo.FreeBoardReply;
import com.shinhan.education.vo2.MultiKeyADTO;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class OneToManyToOneTest {

	@Autowired
	FreeBoardRepository boardRepo;
	@Autowired
	FreeRepliesRepository replyRepo;
	@Autowired
	MultiKeyARepository MRepo;
	
	//@Test
	void test8() {
		MultiKeyADTO musing = MultiKeyADTO.builder()
				.id1(10)
				.id2(10)
				.userName("지만이")
				.address("홍대")
				.build();
		MRepo.save(musing);
		MultiKeyADTO musing2 = new MultiKeyADTO();
				musing.setId1(1);
				musing.setId2(2);
				musing.setUserName("wlaksdl");
				musing.setAddress("hongdae");
				
		MRepo.save(musing2);
	}
	
	
	//댓글에 board번호 100번 삭제..댓글도 삭제된다
	//@Test
	void test7() {
		boardRepo.deleteById(100L);
	}
	
	
	//board번호가 100이상인 board조회..paging추가
	//@Test
	void test6() {
		Pageable paging = PageRequest.of(0, 3,Sort.Direction.ASC,"bno");
		List<FreeBoard> blist = boardRepo.findByBnoGreaterThan(99L, paging);
		blist.forEach(board->{
			System.out.println(board+"==>"+board.getReplies().size());
		});
	}
	
	//board번호가 15번인 댓글만보기
	//@Test
		void test5() {
		boardRepo.findById(15L).ifPresent(board->{
			replyRepo.findByBoard(board).forEach(reply->{
				System.out.println(reply + "==>"+reply.getBoard());
			});
		});
		
		
			
		}
	
	//댓글모두조회(n:1이영)
	//@Test
	void test4() {
		replyRepo.findAll().forEach(reply->{
			System.out.println(reply.getReply()+"-->"+reply.getBoard().getWriter());
		});
	}
	
	//보드조회(1:n이용)
	//@Test
	void test3() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
			//board번호와 댓글 갯수
			System.out.println(board.getBno()+"==>"+board.getReplies().size());
		});
	}
	
	//15번 board댓글 insert10개
	@Test
	void test2() {
		Long[] arr = {1L, 10L, 50L, 100L};
		Arrays.stream(arr).forEach(index -> {
			boardRepo.findById(index).ifPresent(board ->{
				IntStream.rangeClosed(1,10).forEach(i->{
					FreeBoardReply reply = FreeBoardReply.builder()
							.reply("화요일" + i)
							.replyer("user" + (i%2))
							.board(board)
							.build();
					replyRepo.save(reply);
				});
			});
		});
	}

	
	//board를 100개만들어보자
	//@Test
	void test1() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			FreeBoard board = FreeBoard.builder()
					.title("게시글 제목"+i)
					.writer("user"+(i%10))
					.content("게시글 0--내용---")
					.build();
			boardRepo.save(board);
		});
	}
}
