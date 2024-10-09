package com.example.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardDTO;
import com.example.demo.paging.Criteria;

@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDTO boardDTO);
    public BoardDTO selectBoardDetail(Long idx);
    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(Long idx);
    public List<BoardDTO> selectBoardList(BoardDTO params);
    public int selectBoardTotalCount(BoardDTO params);
}
