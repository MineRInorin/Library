package com.lib.demo.bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ibm5
 * @since 2020-11-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Board extends Model<Board> {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	private Timestamp date;

	private String notice;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
