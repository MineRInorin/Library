package com.lib.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lib.demo.bean.Board;
import com.lib.demo.service.BoardService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BoardVo;

@RestController
@RequestMapping("//board")
public class BoardController {

	@Autowired
	BoardService boardService;

	@PostMapping("insert")
	public ResultObj insertNotice(@RequestBody Board board) {
		return boardService.insertNotice(board);
	}

	@PostMapping("select")
	public IPage<Board> selectNotice(@RequestBody BoardVo boardVo) {
		return boardService.selectNotice(boardVo);
	}

	@PutMapping("update")
	public ResultObj updateNotice(@RequestBody Board board) {
		return boardService.updateNotice(board);
	}

	@DeleteMapping("delete/{id}")
	public ResultObj deleteNotice(@PathVariable("id") Integer id) {
		return boardService.deleteNotice(id);
	}
}
