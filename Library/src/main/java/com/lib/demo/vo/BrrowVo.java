package com.lib.demo.vo;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lib.demo.bean.Brrow;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrrowVo extends Brrow {
		private Integer limit;
		private Integer page;
		 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Timestamp startDate;
		 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
		private Timestamp endDate;
}
