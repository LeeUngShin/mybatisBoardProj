package com.example.demo.service;

import com.example.demo.domain.InputTestDTO;

public interface inputTestService {
	
	public boolean insertInputTest(InputTestDTO input);
	public InputTestDTO selectInputTest(Long idx);
}
