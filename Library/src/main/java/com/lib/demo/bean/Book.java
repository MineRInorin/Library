package com.lib.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String author;

    private Integer total;

    private String type;

    private String country;

    private String length;

    private String theme;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
