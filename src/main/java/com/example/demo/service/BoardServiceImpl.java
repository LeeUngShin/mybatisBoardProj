package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mappers.BoardMapper;
import com.example.demo.paging.Criteria;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		if(params.getIdx() == null) {  // 게시글 작성
			queryResult = boardMapper.insertBoard(params);  // 성공시 queryResult : 1
		}
		else {  // 게시글 수정
			queryResult = boardMapper.updateBoard(params);  // 성공시 queryResult : 1
		}
		return (queryResult == 1) ? true : false;
	}

	@Override
	public BoardDTO getBoardDetail(Long idx) {
		
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		
		return board;
	}

	@Override
	public boolean deleteBoard(Long idx) {
		int queryResult = 0;
		BoardDTO board = boardMapper.selectBoardDetail(idx);  // 해당 게시글 번호에 해당하는 게시글
		if(board != null && board.getDeleteYn().equals("N")) {  // 게시글이 존재하고 삭제되지 않은 상태이면
			queryResult = boardMapper.deleteBoard(idx);  // 삭제 성공시 qeuryResult : 1
		}
		return queryResult==1 ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList(BoardDTO params) {
		
		List<BoardDTO> boardList = new ArrayList<>();
		int boardTotalCount = boardMapper.selectBoardTotalCount(params);
		if(boardTotalCount > 0) {  // 게시글이 하나 이상이면
			boardList = boardMapper.selectBoardList(params);
		}
		
		return boardList;
	}
	
}
