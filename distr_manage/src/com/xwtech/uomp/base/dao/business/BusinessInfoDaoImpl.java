package com.xwtech.uomp.base.dao.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwtech.uomp.base.dao.BaseDAO;
import com.xwtech.uomp.base.pojo.business.BusinessInfoBean;

/**
 * 业务信息持久化
 * This class is used for ...   
 * @author unique  
 * @version   
 *       1.0, 2014-1-20 下午03:23:57
 */
@Repository("businessInfoDao")
public class BusinessInfoDaoImpl extends BaseDAO implements IBusinessInfoDao {
    
    public void sortBusinessInfo(List<BusinessInfoBean> list){
	List<Object[]> param = new ArrayList<Object[]>();
	String sql = "update t_busi_info set f_xh=? where f_busi_num=? and F_CHANNEL_NUM=?";
	Object[] objects = null;
	for (int i = 0; i < list.size(); i++) {
	    objects = new Object[3];
	    BusinessInfoBean bean = list.get(i);
	    objects[0] = i;
	    objects[1] = bean.getBusiNum();
	    objects[2] = bean.getChannelNum();
	    param.add(objects);
	}
	update(sql, param);
    }
    
    public void sortBaseBusinessInfo(List<BusinessInfoBean> list){
	List<Object[]> param = new ArrayList<Object[]>();
	String sql = "update t_busi_baseinfo set f_xh=? where f_busi_num=?";
	Object[] objects = null;
	for (int i = 0; i < list.size(); i++) {
	    objects = new Object[2];
	    BusinessInfoBean bean = list.get(i);
	    objects[0] = i;
	    objects[1] = bean.getBusiNum();
	    param.add(objects);
	}
	update(sql, param);
    }
}
