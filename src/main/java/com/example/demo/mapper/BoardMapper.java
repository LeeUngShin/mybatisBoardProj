package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.BoardDTO;

@Mapper
public interface BoardMapper {

    public int insertBoard(BoardDTO boardDTO);
    public BoardDTO selectBoardDetail(Long idx);
    public int updateBoard(BoardDTO boardDTO);
    public int deleteBoard(Long idx);
    public List<BoardDTO> selectBoardList();
    public int selectBoardTotalCount();

}
