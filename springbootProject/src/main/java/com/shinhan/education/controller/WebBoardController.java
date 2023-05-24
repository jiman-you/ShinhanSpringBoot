package com.shinhan.education.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.PageMarker;
import com.shinhan.education.vo3.PageVO;
import com.shinhan.education.vo3.WebBoard;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/webboard")
public class WebBoardController {//보드에 관한 mvc2모델 frontController 패턴

	@Autowired
	WebBoardRepository boardRepo;
	@Autowired
	WebReplyRepository replyRepo;
	
	
	@ApiOperation(value="게시판 등록화면",notes="게시판 등록화면")
	@PostMapping("/register.do")
	public String registerPost(WebBoard board , RedirectAttributes attr) {
		WebBoard newboard = boardRepo.save(board);
		if(newboard!=null) {
			attr.addFlashAttribute("msg","Insert OK");
		}else {
			attr.addFlashAttribute("msg","Insert Fail");
		}
		
		return "redirect:list.do";
	}
	
	@GetMapping("/register.do")
	public void registerGet() {
		
	}
	
	@GetMapping("/delete.do")
	public String deleteBoard(WebBoard board,PageVO pageVO , RedirectAttributes attr) {
		boardRepo.deleteById(board.getBno());
		//addFlashAttribute: 새로고침하면 사라짐 -- 1회성
		//addAttribute: 새로고침해도 유지
		attr.addFlashAttribute("msg","Delete OK");
		attr.addAttribute("page",pageVO.getPage());
		attr.addAttribute("size",pageVO.getSize());
		attr.addAttribute("type",pageVO.getType());
		attr.addAttribute("keyword",pageVO.getKeyword());
		return "redirect:list.do";
	}
	
	@PostMapping("/modify.do")
	public String updatePost(WebBoard board ,Model model,PageVO pageVO, RedirectAttributes attr) {
			WebBoard savedBoard= boardRepo.save(board);//수정
			
			if(savedBoard!=null) {
				attr.addFlashAttribute("msg","Update OK");
			}else {
				attr.addFlashAttribute("msg","Update Fail");
			}
			attr.addAttribute("bno",board.getBno());//보드에 관한 정보 넘기기
			attr.addAttribute("page",pageVO.getPage());
			attr.addAttribute("size",pageVO.getSize());
			attr.addAttribute("type",pageVO.getType());
			attr.addAttribute("keyword",pageVO.getKeyword());
			return "redirect:view.do";
	}
	
	@GetMapping("/modify.do")
	public void updateOrDelete(Long bno,Model model,PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board",board);//보드에 관한 정보 넘기기
			model.addAttribute("pageVO",pageVO);//페이지에 관한 정보 넘기기
		});
	}
	
	@GetMapping("/view.do")
	public void selectById(Long bno,Model model,PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board",board);//보드에 관한 정보 넘기기
			model.addAttribute("pageVO",pageVO);//페이지에 관한 정보 넘기기
		});
	}
	
	@GetMapping("/list.do")
	public void selectAll(PageVO pageVO, Model model, HttpServletRequest request) {
		if(pageVO==null) {
			pageVO = new PageVO();	
			pageVO.setPage(1);
		}
		
		
		Predicate pre = boardRepo.makePredicate(pageVO.getType(),
							pageVO.getKeyword());
		
		Pageable paging = pageVO.makePageable(pageVO.getPage(), "bno");
		Page<WebBoard> result= boardRepo.findAll(pre, paging);
		
		PageMarker<WebBoard> pageMaker = new PageMarker<>(result,pageVO.getSize());
		model.addAttribute("blist", pageMaker);
	
	}
	
}
