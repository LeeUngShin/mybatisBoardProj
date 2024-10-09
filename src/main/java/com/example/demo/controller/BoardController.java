package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.constant.Method;
import com.example.demo.domain.BoardDTO;
import com.example.demo.paging.Criteria;
import com.example.demo.service.BoardService;
import com.example.demo.utils.UiUtils;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController extends UiUtils{
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping (value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value="idx", required = false) Long idx, 
									Model model) {
		System.out.println("작성폼 열 때 : " + idx);
		if(idx == null) {  // 새 게시글 작성
			model.addAttribute("board", new BoardDTO());
		}
		else {  // 기존 게시글
			BoardDTO board = boardService.getBoardDetail(idx);  // 게시글 정보 가져오기
			if(board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}
		return "board/write";
		// return "board/layout/basic";
	}
	
	@PostMapping(value="/board/register.do")
	public String registerBoard(final BoardDTO params, Model model) {
		try {
			System.out.println("게시글 입력한 후 받은 파라미터 : " + params);
			boolean isResgistered = boardService.registerBoard(params);
			if(isResgistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
			}
		}catch(DataAccessException e) {
			e.printStackTrace();
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
		}catch(Exception e) {
			e.printStackTrace();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 등록이 완료되었습니다", "/board/list.do", Method.GET, null, model);
	}

	@GetMapping(value="/board/list.do")
	public String openBoardList(@ModelAttribute("params") BoardDTO params,
								HttpServletRequest request, Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		for(BoardDTO board : boardList) {
			System.out.println(board);
		}
		System.out.println("params : " + params.getPaginationInfo());
		model.addAttribute("requestURI", request.getRequestURI());
		model.addAttribute("boardList", boardList);
		return "board/list";		
	}
	
	@GetMapping(value="/board/view.do")
	public String openBoardDetail(@RequestParam(value="idx", required = false) Long idx, Model model){
		
		if(idx==null) {
			return "redirect:/board/list.do";
		}
		BoardDTO board = boardService.getBoardDetail(idx);
		if(board==null|| board.getDeleteYn().equals("Y")) {
			return "redircet:/board/list.do";
		}
		model.addAttribute("board", board);
		return "board/view";
	}
	
	@PostMapping(value="/board/delete.do")
	public String deleteBoard(@ModelAttribute("params") BoardDTO params, @RequestParam(value="idx", required=false) Long idx, Model model) {
		System.out.println("params : " + params);
		if(idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
		}
		
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if(isDeleted == false) {
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다", "/board/list.do", Method.GET, null, model);
				
			}
		}catch(DataAccessException e) {
			e.printStackTrace();
			return showMessageWithRedirect("데이터베이스 처리과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
		}catch(Exception e) {
			e.printStackTrace();
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 삭제가 완료되었습니다..", "/board/list.do", Method.GET, null, model);
	}
}