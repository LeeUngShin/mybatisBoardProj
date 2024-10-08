package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.InputTestDTO;
import com.example.demo.mappers.InputTestMapper;

@Service
public class inputTestServiceImpl implements inputTestService{
	
	@Autowired
	private InputTestMapper inputTestMapper;

	@Override
	public boolean insertInputTest(InputTestDTO input) {
		
		int queryResult = 0;
		
		String hobbiesStr = input.getHobbiesAsString();
		System.out.println("취미 문자열 : " + hobbiesStr);
		input.setHobbiesString(hobbiesStr);
		queryResult = inputTestMapper.insertInputTest(input);
		if(queryResult == 1) return true;
			
		return false;
	}

	@Override
	public InputTestDTO selectInputTest(Long idx) {
		
		InputTestDTO inputTestDTO = inputTestMapper.selectInputTest(idx);
		System.out.println("db에서 가져온 dto : " + inputTestDTO);
		if(inputTestDTO != null && inputTestDTO.getHobbiesString()!=null) {
	        List<String> hobbiesList = Arrays.asList(inputTestDTO.getHobbiesString().split(","));
	        inputTestDTO.setHobbies(hobbiesList);		}
		
		return inputTestDTO;
	}
	
	

}
