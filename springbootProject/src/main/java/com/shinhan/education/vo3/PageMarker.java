package com.shinhan.education.vo3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMarker<T> {
//pageVO의 정보를 받아 페이지를 생성하는데 기여
	private Page<T> result;
	private Pageable prevPage; //이전으로 이동하는데 필요한 정보를 가짐
	private Pageable nextPage;
	private Pageable currentPage;
	private int currentPageNum;  //화면에 보이는 1부터 시작하는 페이지번호
	private int totalPageNum;
	private List<Pageable> pageList;
	
	public PageMarker(Page<T> result,int pageSize) {
		this.result = result;
		this.currentPage = result.getPageable();
		this.currentPageNum = currentPage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages();
		this.pageList = new ArrayList<Pageable>();
		calcPage(pageSize);
	}
	public void calcPage(int cnt) {
		int tempEndNum = (int)(Math.ceil(currentPageNum/cnt*1.0)*cnt);
		int startNum = tempEndNum - (cnt-1);
		Pageable startPage = this.currentPage;
		for(int i = startNum; i<this.currentPageNum; i++) {
			startPage = startPage.previousOrFirst();
		}
		this.prevPage = startPage.getPageNumber()<=0?null:startPage.previousOrFirst();
		log.info("tempEndNum:" + tempEndNum);
		log.info("totalPageNum:" + totalPageNum);
		if(this.totalPageNum<tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.nextPage = null;
		}
		
		for(int i = 1; i<=totalPageNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
		}
		this.nextPage = startPage.getPageNumber()+1 < totalPageNum?startPage:null;
	}
}
