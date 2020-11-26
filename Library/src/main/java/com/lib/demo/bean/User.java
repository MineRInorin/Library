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
 * @author ibm5
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;

    private String name;

    private String pwd;

    private Integer age;

    private Date birthday;

    private Long tel;

    private Integer roleId;

    private Integer sex;

    private String info;

    private String address;

    private String email;
   
    private String img;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
