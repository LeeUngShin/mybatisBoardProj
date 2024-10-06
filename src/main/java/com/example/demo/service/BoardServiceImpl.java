package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardDTO;
<<<<<<< HEAD
import com.example.demo.mapper.BoardMapper;
=======
import com.example.demo.mappers.BoardMapper;
>>>>>>> cbfc996 (a)

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		if(params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		}
		else {
			queryResult = boardMapper.updateBoard(params);
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
		BoardDTO board = boardMapper.selectBoardDetail(idx);
		if(board != null && board.getDeleteYn().equals("N")) {
			queryResult = boardMapper.deleteBoard(idx);
		}
		return queryResult==1 ? true : false;
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		List<BoardDTO> boardList = new ArrayList<>();
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		
		return boardList;
	}
	
}
