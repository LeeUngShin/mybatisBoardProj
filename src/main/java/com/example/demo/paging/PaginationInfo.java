package com.example.demo.paging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationInfo {
	
	private Criteria criteria;  // 페이지 번호 계산에 필요한 정보
	private int totalRecordCount;  // 전체 데이터 개수
	private int totalPageCount;  // 전체 페이지 개수
	private int firstPage;  // 하단에 있는 선택페이지 개수 중 첫페이지
	private int lastPage;  // 하단에 있는 선택페이지 개수 중 마지막페이지
	private int firstRecordIndex;  // 현재페이지에 보여줄 데이터행 중 첫번째행
	private int lastRecordIndex;  // 현재페이지에 보여줄 데이터행 중 마지막행
	private boolean hasPreviousPage;  // 이전페이지 존재 여부
	private boolean hasNextPage;  // 다음페이지 존재 여부
	
	public PaginationInfo(Criteria criteria) {  // 잘못된 값이 들어왔을 때 대비해 기본값 지정, 페이징 번호 계산
		if(criteria.getCurrentPageNo() < 1) {  // 현재페이지가 1보다 작으면 1로 처리
			criteria.setCurrentPageNo(1);
		}	
		if(criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100)
			criteria.setRecordsPerPage(10);
		if(criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
			criteria.setPageSize(10);
		}
		this.criteria = criteria;
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;  // 파라미터로 넘어온 전체 데이터 개수
		
		if(totalRecordCount > 0) {  // 데이터가 존재하면(1개 이상) 페이지 번호 계산 메서드(calculator()) 호출
			calculation();
		}
	}
	
	public void calculation() {
		
		// 전체 페이지 수, 현재 페이지번호가 전체페이지 수보다 크면 현재 페이지번호에 마지막 페이지번호를 넣음
		totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
		if(criteria.getCurrentPageNo() > totalPageCount) {
			criteria.setCurrentPageNo(totalPageCount);
		}
		
		// 하단에 있는 선택페이지 중 첫 페이지 번호
		firstPage = ((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize() + 1;
		
		// 하단에 있느 선택페이지 중 마지막 페이지 번호(이 번호가 전체 페이지 수보다 크면 전체페이지 수를 저장)
		lastPage = firstPage + criteria.getPageSize() -1;
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}
		
		// 현재 페이지에 보여줄 데이터들 중 첫번째 행
		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();
		
		// 현재 페이지에 보여줄 데이터들 중 마지막 행
		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();
		
		// 이전 페이지 존재여부 (<< 버튼이 활성화 되야하는지)
		hasPreviousPage = firstPage != 1;
		
		// 다음 페이지 존재여부 (>> 버튼이 활성화 되야하는지)
		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
		
		
		
	}
}