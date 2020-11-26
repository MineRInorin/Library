package com.lib.demo.constate;

public interface SysConstate {
	 String USER_LOGIN_ERROR_MSG = "用户名或密码不正确";
	    /**
	     * 用户类型
	     */
	    Integer USER_TYPE_SUPER = 0;
	    Integer USER_TYPE_NORMAL= 1;

	    /**
	     * 操作状态
	     */
	    String LOGIN_SUCCESS="登陆成功";
	    String LOGIN_ERROR="登陆失败,请检查账号密码";
	    
	    String ADD_SUCCESS="添加成功";
	    String ADD_ERROR="添加失败";

	    String UPDATE_SUCCESS="更新成功";
	    String UPDATE_ERROR="更新失败";

	    String DELETE_SUCCESS="删除成功";
	    String DELETE_ERROR="删除失败";

	    String RESET_SUCCESS="重置成功";
	    String RESET_ERROR="重置失败";
	    String RETURN_SUCCESS = "归还成功";
	    String RETURN_ERROR = "归还失败";

	    Integer CODE_SUCCESS=0; //操作成功
	    Integer CODE_ERROR=-1;//失败
	    /**
	     * 公用常量
	     */
	    Integer CODE_ZERO = 0;
	    Integer CODE_ONE = 1;
	    Integer CODE_TWO = 2;
	    Integer CODE_THREE = 3;
	    /**
	     * 默认密码配置
	     */
	    String USER_DEFAULT_PWD = "123456";

	    /**
	     * 默认图片地址
	     */
	    Object DEFAULT_CAR_IMG = "images/defaultcarimage.jpg";


	    /**
	     * 归还状态
	     */
	    Integer RENT_BACK_FALSE = 1;
	    Integer RENT_BACK_TRUE = 2;

	    String USER_CODE_ERROR_MSG = "验证码错误";
	    String FILE_UPLOAD_TEMP = "_temp";
	    

}
