package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

@SpringBootTest 
@Log
class BoardTest {
	
	//Logger Log = LoggerFactory.getLogger(BoardTest.class);
	
	@Autowired
	BoardRepository brepo;
	
	@Test//동적으로 sql만들기
	public void dynamicSQLTest() {
		String title = "제목9"; //where title like '%제목9%'
		Long bno = 50L; //where bno>50
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoardVO board  = QBoardVO.boardVO;
		
		builder.and(board.title.like("%"+title+"%"));
		builder.and(board.bno.gt(bno));
		builder.and(board.writer.eq("작성자7"));
		
		System.out.println(builder);
		
		//findALL()=> CrudRepository
		//findAll(predicate) => QuerydslpredicateExecutor에서 제공
		List<BoardVO>blist =  (List<BoardVO>)brepo.findAll(builder);
		blist.forEach(b->{
			log.info(b+"");
		});
		
	}
	
	//@Test
	public void salpe10() {
		List<BoardVO> blist = brepo.findByTitle6("수정","내용");
		blist.forEach(board->{
			log.info(board+"");
		});
	}

	//@Test
	public void salpe9() {
		List<Object[]> blist = brepo.findByTitle5("수정","내용");
		blist.forEach(arr->{
			log.info(Arrays.toString(arr));
		});
	}
	//@Test
	public void salple8() {
		List<BoardVO> blist = brepo.findByTitle4("수정","내용");
		blist.forEach(board->{
			log.info(board+"");
		});
	}
	
	//@Test
	public void salple7() {
		List<BoardVO> blist = brepo.findByTitle3("수정","내용");
		blist.forEach(board->{
			log.info(board+"");
		});
	}
	
	//@Test
	public void salple6() {
		List<BoardVO> blist = brepo.findByTitle2("수정","내용");
		blist.forEach(board->{
			log.info(board+"");
		});
	}
	
	//@Test//title로 조회 ,sort desc , paging=>page,size 몇번째페이지인데 사이즈가 몇개있어 
	public void sample5() {
		//Sort sort = Sort.by("bno").descending();
		Sort sort = Sort.by(Sort.Direction.DESC, new String [] {"Writer","bno"});
		
		Pageable paging = PageRequest.of(1, 5,sort );;//몇페이지를 나타내고 한페이지에 몇개씩있어
		
		
		 Page<BoardVO> result = brepo.findByBnoGreaterThan(80L,paging);
			log.info("페이지당 건수"+result.getSize());
			log.info("페이지 총 수"+result.getTotalElements());
			log.info("페이지 전체 건수"+result.getTotalElements());
			log.info("다음 페이지 정보"+result.nextPageable());

			List<BoardVO> blist = result.getContent();
		 blist.forEach(board->{
			log.info(board.toString());
		});
		
	}
	
//	@Test//title로 조회 ,sort desc , paging=>page,size 몇번째페이지인데 사이즈가 몇개있어 
	public void sample4() {
		//Sort sort = Sort.by("bno").descending();
		Sort sort = Sort.by(Sort.Direction.DESC, new String [] {"Writer","bno"});
	
		Pageable paging = PageRequest.of(1, 5,sort );;//몇페이지를 나타내고 한페이지에 몇개씩있어
		List<BoardVO> blist = brepo.findByTitleContaining("제목",paging);
		blist.forEach(board->{
			log.info(board.toString());
		});
		
	}
	
	//@Test//title로 조회 ,sort desc , paging=>page,size 몇번째페이지인데 사이즈가 몇개있어 
	public void sample3() {
		Pageable paging = PageRequest.of(1, 5);//몇페이지를 나타내고 한페이지에 몇개씩있어
		List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목",paging);
		blist.forEach(board->{
			log.info(board.toString());
		});
		
	}
	
	//@Test//e대소문자 구분없이
		public void sample2() {
			List<BoardVO> blist = brepo.findByTitleContainingOrderByTitleDesc("제목");
			blist.forEach(board->{
				log.info(board.toString());
			 });
			
		}
	
	//@Test
	public void sample1() {
		long rowCount = brepo.count();
		log.info(rowCount+"건");
		boolean result =  brepo.existsById(20L);
		log.info(result?"존재한다":"존재하지않는다");
	}
	
	//@Test//e대소문자 구분없이
	public void retrieve8() {
		List<BoardVO> blist = brepo.findByContentIgnoreCase("aBc");
		blist.forEach(board->{
			 System.out.println("대소문자 구분없이"+board);
		 });
		
	}
	
	//@Test//아이디 사이값 소트
	public void retrieve7() {
		List<BoardVO> blist = brepo.findByBnoBetweenOrderByBnoDesc((long) 80, (long) 95);
		blist.forEach(board->{
			if(board.getBno()%2==0) {
				board.setContent("ABC");
				brepo.save(board);
			}else {
				board.setContent("abc");
				brepo.save(board);
			}
		});
	}

	//@Test//포함하는 문자면서 bno보다 큰거
	public void retrieve6() {
		List<BoardVO> blist = brepo.findByTitleContainingAndBnoGreaterThan("제목", (long) 95);
		blist.forEach(board->{
			 System.out.println("제목이 들어가고 95보다 큰"+board);
		 });
	}
	
	//@Test//끝나는문자로 찾기
	public void retrieve5() {
		List<BoardVO> blist = brepo.findByTitleEndingWith("정");
		blist.forEach(board->{
			 System.out.println("정으로 끝나는"+board);
		 });
	}
	
	//@Test//시작하는 문자로 찾기
	public void retrieve4() {
		List<BoardVO> blist = brepo.findByTitleStartingWith("제목");
		blist.forEach(board->{
			 System.out.println("제목으로 시작하는"+board);
		 });
	}
	
	//@Test//들어있는 문자 찾기
	public void retrieve3() {
		List<BoardVO> blist = brepo.findByTitleContaining("수");
		blist.forEach(board->{
			 System.out.println("수가 들어있는 "+board);
		 });
	}
	
	//@Test//같은 문자 찾기
	public void retrieve2() {
		 List<BoardVO> blist =brepo.findByTitleAndWriter("제목수정", "admin");
		 blist.forEach(board->{
			 System.out.println(board);
		 });
	}
	
	//@Test
	public void retrieve1() {
		 List<BoardVO> blist =brepo.findByTitle("제목수정");
		 blist.forEach(board->{
			 System.out.println(board);
		 });
	}
	
	//@Test
	public void test6() {
		//ifPresetnt - 있다면
		BoardVO board= brepo.findById(11L).orElse(null);
		if(board != null) {
			board.setTitle("제목수정");
			board.setContent("내용수정");
			board.setWriter("admin");
			brepo.save(board);//수정도 세이브 이미 있는 데이터는 update
		}
	}

	
	//@Test
	public void test5() {
		//ifPresetnt - 있다면
	 	BoardVO board= brepo.findById(10L).orElse(null);
	 	System.out.println(board);
	}
	
	
	//@Test
	public void test4() {
		brepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	
	
	
	//@Test
	public void test3() {
		
		//1부터 100까지 돌기
		IntStream.range(1, 100).forEach(i->{
			BoardVO board= BoardVO.builder()
									.title("게시글 제목"+i)
									.content("게시글..."+i)
									.writer("작성자"+(i%10))
									.build();
			brepo.save(board); //save 함수에 의해서 insert가 된다.
		});
	}
	
	//@Test
	void test2() {
		CarVO car1 = CarVO.builder()
							.model("B모델")
							.price(1000)
							.build();
		
		log.info(car1.toString());
	}
	
	//@Test
	void test1(){
		CarVO car1=new CarVO();
		car1.setModel("모델");
		car1.setPrice(100);
		
		CarVO car2=new CarVO();
		car2.setModel("모델");
		car2.setPrice(1000);
		
		log.info(car1.toString());
		log.info("같은가?"+car1.equals(car2));
	}
			
	@Test
	void contextLoads() {
	
	}

}
