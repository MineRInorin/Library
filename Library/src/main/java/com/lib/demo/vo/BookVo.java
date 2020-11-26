package com.lib.demo.vo;

import com.lib.demo.bean.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookVo extends Book{
	private Integer limit;
	private Integer page;

}
