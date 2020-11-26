package com.lib.demo.vo;

import com.lib.demo.bean.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends User {
	private Integer limit;
	private Integer page;
}
