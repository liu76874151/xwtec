package com.xwtech.uomp.base.constants;

public interface SystemCodeConstants {
    /**
     * *成功编码   以1开头 6位 如：100001****
     */
    // 操作成功！
    String OPERATE_SUCCEED = "100001";
    // 您已经安全退出系统！谢谢使用！
    String LOGOUT_SYSTEM_INFO = "100002";
    // 查询信息操作成功！
    String QUERY_INFO_SUCCEED = "100003";
    // 新增记录信息操作成功！
    String CREATE_INFO_SUCCEED = "100004";
    // 保存新增记录信息操作成功！
    String INSERT_INFO_SUCCEED = "100005";
    // 修改记录信息操作成功！
    String EDIT_INFO_SUCCEED = "100006";
    // 保存修改记录信息操作成功！
    String UPDATE_INFO_SUCCEED = "100007";
    // 删除选择的记录信息操作成功！
    String DELETE_INFO_SUCCEED = "100008";
    // 清除缓存成功
    String DELETE_CACHE_SUCCEED = "100009";

    /****错误编码 以-1开头 6位 如：-100001*****/
    /**
     * 登录失败结果 begin*
     */
    // 操作失败！
    String OPERATE_FAILED = "-100001";
    // 登录超时或尚未登录系统，请重新登录系统！
    String NOT_LOGIN = "-100002";
    // 您没权限执行该操作！
    String NOT_OPERATOR = "-100003";
    // 登录系统验证码不正确！请重新输入验证码！
    String VERIFICATION_CODE_ERROR = "-100004";
    // 登录帐号或登陆密码为空！请重新输入！
    String LOGIN_PWD_EMPTY = "-100005";
    // 登录密码不正确！请重新输入！
    String LOGIN_PWD_ERROR = "-100006";
    // 登录帐号不存在！请重新输入！
    String NOT_USER = "-100007";
    // 管理员无效！
    String USER_STATE = "-100008";
    /**登录失败结果 end**/

    /**
     * 权限校验结果 begin*
     */
    // 查询模块信息出错！
    String QUERY_FUNC_ERROR = "-100009";
    // 您访问的模块不存在！
    String NOT_FUNC = "-100010";
    // 您没权限访问该页面！
    String NOT_ACCESS_PAGE = "-100011";
    // 您访问的URL不存在！
    String URL_IS_NULL = "-100012";
    // 不存在该页面模块
    String NOT_PAGE_FUNC = "-100013";
    // 您访问的业务处理模块不存在！
    String NOT_OPER_FUNC = "-100014";
    /**
     * 权限校验结果 end*
     */

    // 查询信息操作失败！请检查提交数据的有效性，再重新操作！
    String QUERY_INFO_FAILED = "-100015";
    // 查询信息时，系统出错，请检查数据的有效性或请与管理员联系！
    String QUERY_INFO_ERROR = "-100016";
    // 新增记录信息操作失败！请检查提交数据的有效性，再重新操作！
    String CREATE_INFO_FAILED = "-100017";
    // 新增记录信息操作时，系统出错，请检查数据的有效性或请与管理员联系！
    String CREATE_INFO_ERROR = "-100018";
    // 保存新增记录信息操作失败！请检查提交数据的有效性，再重新操作！
    String INSERT_INFO_FAILED = "-100019";
    // 保存新增记录信息时，系统出错，请检查数据的有效性或请与管理员联系！
    String INSERT_INFO_ERROR = "-100020";
    // 修改记录信息操作失败！请检查提交数据的有效性或该数据已不存在，再重新操作！
    String EDIT_INFO_FAILED = "-100021";
    // 修改记录信息时，系统出错，请检查数据的有效性或请与管理员联系！
    String EDIT_INFO_ERROR = "-100022";
    // 保存修改记录信息操作失败！请检查提交数据的有效性或该纪录已不存在，再重新操作！
    String UPDATE_INFO_FAILED = "-100023";
    // 保存修改记录信息时，系统出错，请检查数据的有效性或请与管理员联系！
    String UPDATE_INFO_ERROR = "-100024";
    // 删除选择的记录信息操作失败！请检查提交数据的有效性或该纪录已不存在，再重新操作！
    String DELETE_INFO_FAILED = "-100025";
    // 删除选择的记录信息时，系统出错，请检查数据的有效性或请与管理员联系！
    String DELETE_INFO_ERROR = "-100026";
    
    //禁止删除，请检查数据是否正在被使用或包含下级数据！
    String DELETE_INFO_FORBID = "-1000261";
    
    // 显示信息详细资料操作失败！请检查提交数据的有效性，再重新操作！
    String VIEW_INFO_FAILED = "-100027";
    // 显示信息详细资料操作时，系统出错，请检查数据的有效性或请与管理员联系！
    String VIEW_INFO_ERR0R = "-100028";
    // 提交内容中存在必填项为空,请返回重新填写，再提交！
    String CHECK_FIELD_EMPTY = "-100029";
    // 判断信息是否存在时候，系统出错，请检查提交数据的有效性！
    String CHECK_EXIST_ERROR = "-100030";
    // 该用户组下存在用户，不可删除！请删除所有用户后再删除！
    String CHECK_EXIT_MEMBER = "-100031";
    // 该信息已经系统中存在了，请返回重新设置后提交或查询列表！
    String CHECK_FIELD_EXIST = "-100032";
    // 未查询到需清除的缓存
    String CACHE_NOT_FIND = "-100033";
    //清除缓存失败
    String DELETE_CACHE_FAILED = "-100034";
    //旧密码不正确请重新输入！
    String CHECK_OLDPASSWORD_ERROR = "-100035";
    //修改登录密码成功！
    String UPDATE_PASSWORD_SUCCESS = "-100036";
    //修改登录密码失败 !
    String UPDATE_PASSWORD_FAILED = "-100037";

    //"修改登录密码时，新密码不能与系统默认密码相同！";
    String UPDATE_DEFALUTPWD_FAILED = "-100038";
    //"该模块下含有子目录，请先删除子模块"
    String FUN_HAS_NEXT = "-100039";
    //该功能已经存在，请重新操作
    // String FUN_HAS_EXIT = "-100040";
    //子系统编码已经存在，请重新输入
    String SYSNUM_HAS_EXIT = "-100041";
}
