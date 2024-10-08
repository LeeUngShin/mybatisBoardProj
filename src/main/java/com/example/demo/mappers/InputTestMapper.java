package com.example.demo.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.InputTestDTO;

@Mapper
public interface InputTestMapper {
	
	public int insertInputTest(InputTestDTO input);
	public InputTestDTO selectInputTest(Long idx);

}
