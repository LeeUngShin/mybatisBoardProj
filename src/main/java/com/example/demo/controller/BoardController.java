package com.example.demo.controller;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> cbfc996 (a)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.BoardDTO;
import com.example.demo.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping (value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value="idx", required = false) Long idx, 
									Model model) {
		
		if(idx == null) {
			model.addAttribute("board", new BoardDTO());
		}
		else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}
		return "board/write";
		// return "board/layout/basic";
	}
	
	@PostMapping(value="/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			System.out.println("게시글 입력한 후 받은 파라미터 : " + params);
			boolean isResgistered = boardService.registerBoard(params);
			if(isResgistered == false) {
				
			}
		}catch(DataAccessException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list.do";
	}
<<<<<<< HEAD
=======
	
	@GetMapping(value="/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		for(BoardDTO board : boardList) {
			System.out.println(board);
		}
		model.addAttribute("boardList", boardList);
		return "board/list";
		
	}
>>>>>>> cbfc996 (a)
}