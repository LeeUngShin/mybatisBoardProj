package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.InputTestDTO;
import com.example.demo.service.inputTestService;

@Controller
public class InputTestController {

	@Autowired
	private inputTestService inputTestService;
	
	@GetMapping("/test/input")
	public String inputTestForm(@RequestParam(value="idx", required = false) Long idx, Model model) {
		
		InputTestDTO inputTestDTO = new InputTestDTO();
		model.addAttribute("inputTestDTO", inputTestDTO);
		
		return "inputTestForm";
	}
	
	@PostMapping("/test/inputRegister")
	public String inputTestRegister(InputTestDTO inputTestDTO, Model model) {
		System.out.println("받은 인풋디티오 : " + inputTestDTO);
		boolean isRegistered = inputTestService.insertInputTest(inputTestDTO);
		
		return "inputResult";
	}
	
	@GetMapping("/test/inputInfo")
	public String inputInfo(@RequestParam(value="idx", required = false) Long idx, Model model) {
		
		InputTestDTO inputTestDTO = inputTestService.selectInputTest(idx);
		model.addAttribute("inputTestDTO", inputTestDTO);
		
		return "inputTestForm";
	}
}
