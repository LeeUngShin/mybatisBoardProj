package com.example.demo.domain;

import java.time.LocalDateTime;

import com.example.demo.paging.Criteria;
import com.example.demo.paging.PaginationInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonDTO extends Criteria{
	
	private PaginationInfo paginationInfo;  // 페이징 정보
	private String deleteYn;  // 삭제 여부
	private LocalDateTime insertTime;  // 등록일
	private LocalDateTime updateTime;  // 수정일
	private LocalDateTime deleteTime;  // 삭제일
}
