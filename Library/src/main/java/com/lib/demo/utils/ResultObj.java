package com.lib.demo.utils;

import com.lib.demo.constate.SysConstate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObj {
	 private Integer code=0;
	 private String msg;

	    /**
	     * 添加成功
	     */
	    public static final ResultObj ADD_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.ADD_SUCCESS);
	    /**
	     * 添加失败
	     */
	    public static final ResultObj ADD_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.ADD_ERROR);
	    /**
	     * 更新成功
	     */
	    public static final ResultObj UPDATE_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.UPDATE_SUCCESS);
	    /**
	     * 更新失败
	     */
	    public static final ResultObj UPDATE_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.UPDATE_ERROR);
	    /**
	     * 删除成功
	     */
	    public static final ResultObj DELETE_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.DELETE_SUCCESS);
	    /**
	     * 删除失败
	     */
	    public static final ResultObj DELETE_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.DELETE_ERROR);

	    /**
	     * 重置成功
	     */
	    public static final ResultObj RESET_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.RESET_SUCCESS);
	    /**
	     * 重置失败
	     */
	    public static final ResultObj RESET_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.RESET_ERROR);
	    /**
	     *登陆成功
	     */
	    public static final ResultObj LOGIN_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.LOGIN_SUCCESS);
	    /**
	     * 登陆失败
	     */
	    public static final ResultObj LOGIN_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.LOGIN_ERROR);
	    /*归还成功
	    */
	    public static final ResultObj RETURN_SUCCESS=new ResultObj(SysConstate.CODE_SUCCESS, SysConstate.RETURN_SUCCESS);
	    /**
	     * 归还失败
	     */
	    public static final ResultObj RETURN_ERROR=new ResultObj(SysConstate.CODE_ERROR, SysConstate.RETURN_ERROR);
	  





}
