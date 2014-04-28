package com.xwtech.uomp.base.service.boss.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwtech.uomp.base.dao.boss.BossMarketSecondMapper;
import com.xwtech.uomp.base.pojo.Page;
import com.xwtech.uomp.base.pojo.boss.BossMarketSecondBean;
import com.xwtech.uomp.base.pojo.boss.ecp.CMarketingPlandt;
import com.xwtech.uomp.base.pojo.brand.BrandBean;
import com.xwtech.uomp.base.service.boss.IBossMarketSecondService;
import com.xwtech.uomp.base.service.brand.IBrandService;
import com.xwtech.uomp.base.service.brand.IBrandSortService;
/**
 * @author zhangel 
 */
@Service("bossMarketSecondService")
public class BossMarketSecondServiceImpl implements IBossMarketSecondService {

	@Autowired
	BossMarketSecondMapper bossMarketSecondMapper;
	@Autowired
	IBrandService brandService;
	@Autowired
	IBrandSortService brandSortService;
	
	public List<BossMarketSecondBean> queryBossMarketSecondList(
			Map<String, String> map) {
		return bossMarketSecondMapper.queryBossMarketSecondList(map);
	}
	
	public Page queryBossCodeList(Map<String, String> paramMap) {
		List<Map<String, Object>>  list=bossMarketSecondMapper.queryBossCodeList(paramMap);
		Map<String, Object> map=new HashMap<String, Object>();
		String brandNums;
		String brandNums1[];
		for (int i = 0; i < list.size(); i++) {
			brandNums=(String) ((Map)list.get(i)).get("brandId");
			if(StringUtils.isNotBlank(brandNums)){
				brandNums1=brandNums.split("\\,");
				map.put("bossCodes", brandNums1);
			}
			List<BrandBean>  brandList=brandService.queryBrandNameById(map);
			brandNums="";
			for (BrandBean BrandBean:brandList) {
				brandNums+=BrandBean.getBrandName()+",";
			}
			if(brandNums.length()>0){
				brandNums=brandNums.substring(0,brandNums.length()-1);
			}
			((Map)list.get(i)).put("brandId", brandNums);
		}
		int count=bossMarketSecondMapper.queryBossCodeListCount(paramMap);
		Page page=new Page();
		page.setRecords(list);
		page.setTotalRecord(count);
		return page;
	}
	
	public boolean checkIsIn(Map<String, String> param) {
	    
	    BossMarketSecondBean bean = bossMarketSecondMapper.queryOneBossMarketSecondBean(param);
	    if (bean != null) {
		return true;
	    }
	    return false;
	}
	
	public boolean insert(boolean isIn, CMarketingPlandt dt, String cityId,
		CMarketingPlandt dtallinfo) {
	    
	    BossMarketSecondBean bean = new BossMarketSecondBean();
	    bean.setMarketSecondCode(dt.marketingplan_plan_id);
	    bean.setMarketFirstCode(dt.marketingplan_type_id);
	    bean.setMarketSecondName(dt.marketingplan_name);
	    bean.setStartTime(dt.marketingplan_start_date);
	    bean.setEndTime(dt.marketingplan_end_date);
	    bean.setCityId(cityId);
	
	    //String brandId = brandSortService.queryBrandId(dt.marketingplan_permit_brand_ids);
	    bean.setBrandId("1,2,3,");
	    int account_amount=0;
	    if (dtallinfo.bossmms_special_account_amount!=null && !dtallinfo.bossmms_special_account_amount.trim().equals("")){
		account_amount=Integer.parseInt(dtallinfo.bossmms_special_account_amount.trim());
	    }
	    int credit_amount=0;  
	    if (dtallinfo.creditrelease_credit_amount!=null && !dtallinfo.creditrelease_credit_amount.trim().equals("")){
		credit_amount=Integer.parseInt(dtallinfo.creditrelease_credit_amount.trim());
	    }
	    int cash_amount=0;  
	    if (dt.marketingplan_cash_amount!=null && !dt.marketingplan_cash_amount.trim().equals("")){
		cash_amount=Integer.parseInt(dt.marketingplan_cash_amount.trim());
	    }
	    int mount=credit_amount+account_amount+cash_amount;
	    bean.setMoneyVal(new BigDecimal(mount/100));
	    if (isIn==false){
		bossMarketSecondMapper.insert(bean);
		return true;
	    }else if(isIn==true){
		bossMarketSecondMapper.updateBossMarketSecondBean(bean);
		return true;
	    }
	    return false;
	}

}
