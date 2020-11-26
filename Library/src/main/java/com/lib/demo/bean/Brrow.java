package com.lib.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.concurrent.CountDownLatch;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ibm5
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Brrow extends Model<Brrow> {

    private static final long serialVersionUID = 1L;

    private Integer retCheck;

    /**
     * 续借check
     */
    private Integer addCheck;

    private Integer brrCheck;

    private String bookName;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uId;

    private Integer bookId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp brrowDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp returnDate;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp expectDate;

    @TableField(exist = false)
    private String author;
    
    @TableField(exist = false)
    private String country;
    
    @TableField(exist = false)
    private String theme;
    
    @TableField(exist = false)
    private Integer brrCount;
    
    @TableField(exist = false)
    private Integer value;
    
    @TableField(exist = false)
    private String  name;

    
    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
