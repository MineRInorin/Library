package com.lib.demo.service.impl;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lib.demo.bean.Board;
import com.lib.demo.mapper.BoardMapper;
import com.lib.demo.service.BoardService;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BoardVo;

@Service
@Transactional
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board> implements BoardService {

	@Autowired
	BoardMapper boardMapper;

	@Override
	public ResultObj insertNotice(Board board) {
		// TODO Auto-generated method stub
//		long currentTimeMillis = System.currentTimeMillis();
		Date date = new Date(System.currentTimeMillis());
		board.setDate(new Timestamp(System.currentTimeMillis()));

		if (board.getNotice() == null || board.getNotice() == "") {
			return ResultObj.ADD_ERROR;
		}
		return save(board) == true ? ResultObj.ADD_SUCCESS : ResultObj.ADD_ERROR;
	}

	@Override
	public IPage<Board> selectNotice(BoardVo boardVo) {
		// TODO Auto-generated method stub
		IPage<Board> page = new Page<Board>(boardVo.getPage(), boardVo.getLimit());
		QueryWrapper<Board> wrapper = new QueryWrapper<Board>();
		wrapper.eq(boardVo.getId()!=null, "id", boardVo.getId());
		wrapper.orderByDesc("id");
		return boardMapper.selectPage(page, wrapper);
	}

	@Override
	public ResultObj updateNotice(Board board) {
		// TODO Auto-generated method stub
		return updateById(board) ? ResultObj.UPDATE_SUCCESS : ResultObj.UPDATE_ERROR;
	}

	@Override
	public ResultObj deleteNotice(Integer id) {
		return boardMapper.deleteById(id) == 1 ? ResultObj.DELETE_SUCCESS : ResultObj.DELETE_ERROR;
	}

}
