package com.lib.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Date;

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

    private Date brrowDate;

    private Date returnDate;

    private Date expectDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
