package com.lib.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lib.demo.bean.Board;
import com.lib.demo.utils.ResultObj;
import com.lib.demo.vo.BoardVo;

public interface BoardService extends IService<Board> {

	public ResultObj insertNotice(Board board);

	public IPage<Board> selectNotice(BoardVo boardVo);

	public ResultObj updateNotice(Board board);

	public ResultObj deleteNotice(Integer id);
}
