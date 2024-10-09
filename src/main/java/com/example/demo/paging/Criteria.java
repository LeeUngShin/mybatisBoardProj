package com.example.demo.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Criteria {
	
	private int currentPageNo;  // 현재 페이지번호
	private int recordsPerPage;  // 한 페이지당 보여줄 개수
	private int pageSize;  // 하단에 있는 선택 페이지 개수
	private String searchKeyword;  // 검색어
	private String searchType;  // 검색유형
	
	public Criteria() {  // Criteria 객체 생성시 초기화
		 this.currentPageNo = 1;
		 this.recordsPerPage = 10;
		 this.pageSize = 10;
	}
	
	public int getStartPage() {  // MySQL에서 LIMIT 구문의 앞부분에 사용되는 메서드
		return (currentPageNo - 1) * recordsPerPage; 
	}
	
	public String makeQueryString(int pageNo) {
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage)
				.queryParam("pageSize", pageSize)
				.queryParam("searchType", searchType)
				.queryParam("searchKeyword", searchKeyword)
				.build()
				.encode();
		
		return uriComponents.toUriString();
				
	}
}