package com.shinhan.education.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.BooleanBuilder;
import com.shinhan.education.repository.BoardRepository;
import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.vo.BoardVO;
import com.shinhan.education.vo.CarVO;
import com.shinhan.education.vo.QBoardVO;

import lombok.extern.java.Log;

@Log
@RestController  //controller+ ResponseBody
						//response.getWriter().append("jsp/servlet")
public class samlpleController {

	@Autowired
	BoardRepository brepo;
	@Autowired
	PDSBoardRepository boardRepo;
	
	@Transactional
	@GetMapping("/monday")
	void test7() {
		boardRepo.updateFile(4L,"풍경사진");
	}
	
	
	
	@GetMapping("/sunday")
	public List<BoardVO> dynamicSQLTest() {
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
		
		return blist;
	}
	
	@GetMapping("/friday")
	public Map<String, Object> sample1() {
		long rowCount = brepo.count();
		log.info(rowCount+"건");
		
		
		boolean result =  brepo.existsById(20L);
		log.info(result?"존재한다":"존재하지않는다");
		
		Map<String, Object> map = new HashMap<>();
		map.put("aa", rowCount+"건");
		map.put("bb", result?"존재한다":"존재하지않는다");
		map.put("data", brepo.findById(65L).orElse(null));
	
		return map;
	}
	
	
	@GetMapping("/cartest2")
	public List<CarVO> test4() { //jacson이 java객체를 json으로 만들어서 return
		List<CarVO> carlist = new ArrayList<>();
		
		IntStream.rangeClosed(1,10).forEach(index->{
			CarVO car1 = CarVO.builder()
					.model("BMWzxc----"+index)
					.price(1000)
					.build();
			carlist.add(car1);
		});
		
		return carlist;
	}
	@GetMapping("/cartest")
	public CarVO test3() { //jacson이 java객체를 json으로 만들어서 return
		CarVO car1 = CarVO.builder()
				.model("BMW")
				.price(1000)
				.build();
		return car1;
	}
	
	@GetMapping("/sample1")
	public String test1() {
		return "Spring열공";
	}
	@GetMapping("/sample2")
	public String test2() {
		return "Spring열공---화잍ㅇ";
	}
}
