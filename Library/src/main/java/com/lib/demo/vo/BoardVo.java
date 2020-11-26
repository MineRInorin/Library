package com.lib.demo.vo;

import com.lib.demo.bean.Board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVo extends Board {
	private Integer limit;
	private Integer page;
}
