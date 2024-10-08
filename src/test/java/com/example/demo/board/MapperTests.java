package com.example.demo.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mappers.BoardMapper;

@SpringBootTest
public class MapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test  // 게시글 자성
	public void testOfInsert() {
		BoardDTO board = new BoardDTO();
		board.setTitle("테스트제목");
		board.setWriter("테스트작성자");
		board.setContent("테스트내용");
		boardMapper.insertBoard(board);
	}
	
	@Test  // 게시글 조회
	public void testOfSelectDetail() {
		
		BoardDTO board = boardMapper.selectBoardDetail((long) 13);
		System.out.println(board);
	}
	
	@Test  // 게시글 수정
	public void testOfUpdate() {
		BoardDTO board = new BoardDTO();
		board.setTitle("수정테스테제목");
		board.setWriter("수정테스트작성자");
		board.setContent("수정테스트내용");
		board.setIdx((long) 13);
		int resultCnt = boardMapper.updateBoard(board);
		if(resultCnt==1) {
			BoardDTO updateBoard = boardMapper.selectBoardDetail((long) 13);
			System.out.println(updateBoard);
		}
	}
	
	@Test  // 게시글 삭제
	public void testOfDelete() {
		int resultCnt = boardMapper.deleteBoard((long) 13);
		if(resultCnt==1) {
			BoardDTO deleteBoard = boardMapper.selectBoardDetail((long) 13);
			System.out.println(deleteBoard);
		}
	}
	
	@Test  // 게시글 목록 조회
	public void testOfList() {
		
		int boardCnt = boardMapper.selectBoardTotalCount();
		
		if(boardCnt > 0){
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if(!boardList.isEmpty()) {
				for(BoardDTO board : boardList) {
					System.out.println(board);
				}
			}
		}
	}
}
