package com.shinhan.education.vo3;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@AllArgsConstructor
@Builder
@ToString
public class PageVO {//페이지 이동을 위한 페이지정보들
	
	private static final int DEFAULT_SIZE = 10;
	private static final int DEFAULT_MAX_SIZE = 50;	
	private int page;
	private int size;	
	//검색조건처리위해 field추가
	private String keyword;
	private String type;		
	public PageVO() {
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	public void setSize(int size) {
		this.size = size<DEFAULT_SIZE || size > DEFAULT_MAX_SIZE?
				DEFAULT_SIZE:size;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pageable makePageable(int direction, String ...prop) {
		Sort.Direction dir = direction==0?Sort.Direction.DESC:Sort.Direction.ASC;
		return PageRequest.of(this.page-1, this.size, dir, prop);
	}
}
