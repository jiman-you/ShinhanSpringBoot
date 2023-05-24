package com.shinhan.education.controller;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.shinhan.education.repository.FreeBoardRepository;
import com.shinhan.education.repository.FreeRepliesRepository;
@Controller
public class FreeBoardController {
	
	@Autowired
	FreeBoardRepository boardRepo;
	@Autowired
	FreeRepliesRepository replyRepo;
	
	@GetMapping("/freeboard/eltest")
	public void freeboard3(Model dataScope, HttpSession session) {
		dataScope.addAttribute("boardList",boardRepo.findAll());
		dataScope.addAttribute("now",new Date());
		dataScope.addAttribute("price",1234568996);
		dataScope.addAttribute("title","This is  a just sample");
		dataScope.addAttribute("option",Arrays.asList("apple","banana","cocoa"));
		session.setAttribute("userName", "user1");
	}
	
	@GetMapping("/freeboard/selectAll")
	public String freeboard2(Model dataScope) {
		dataScope.addAttribute("boardList",boardRepo.findAll());
		return "freeboard/list";
	}
	
	@GetMapping("/freeboard/detail")
	//@RequestParam("bno")은 생략 가능
	public String freeboard1(@RequestParam Long bno, Model model) {
		boardRepo.findById(bno).ifPresent(board -> {
			model.addAttribute("board",board);
		});
		return "freeboard/detail";
	}
	
	@GetMapping("/freeboard/detail1")
	//@RequestParam("bno")은 생략 가능
	public String detail(Model model) {
		boardRepo.findById(1L).ifPresent(board -> {
			model.addAttribute("board",board);
		});
		return "freeboard/detail";
	}
	
	@GetMapping("/aa/firstzone3")
	public void test3(Model model) {
		model.addAttribute("greeting", "BYE");
		model.addAttribute("company", "우리은행");
	}
	
	@GetMapping("/firstzone2")
	public String test2(Model model) {
		model.addAttribute("greeting", "OK");
		model.addAttribute("company", "국민은행");
		return "firstzone1"; //templates/firstzone1.html (로 forward 됨.)
	}
	
	@GetMapping("/firstzone1")
	public void test1(Model model) {
		model.addAttribute("greeting", "하이");
		model.addAttribute("company", "신한금융");
	}
}

