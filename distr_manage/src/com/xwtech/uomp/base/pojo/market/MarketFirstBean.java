package com.xwtech.uomp.base.pojo.market;


public class MarketFirstBean {
    /*
     * 一级营销方案主键标识
     */
    private String marketFirstPkid;
    /*
     *  一级营销方案标 识
     */
    private String marketFirstCode;
    /*
     * BOSS一级方案名称
     */
    private String marketFirstName;
    /*
     * 对外名称
     */
    private String viewName;
    /*
     * 网厅小图片
     */
    private String imgDir;
    /*
     * 展示FLASH
     */
    private String flashDir;
    /*
     * 活动对象
     */
    private String toObject;
    /*
     * 活动说明
     */
    private String activityComment;
    /*
     * 开始时间
     */
    private String beginTime;
    /*
     * 结束时间
     */
    private String endTime;
    /*
     * 是否FLASH展示
     */
    private String isFlashView;
    /*
     * 是否在营销方案办理列表展示
     */
    private String isListView;
    /*
     * 展示模板标识
     */
    private String viewTemplateId;
    /*
     * 方案状态：0-无效，1-有效。
     */
    private String state;
    /*
     * 创建时间
     */
    private String createTime;
    /*
     * 城市
     */
    private String city;
    /*
     * 城市cityId
     */
    private String cityId;
    private String isDT;


	/*
     * 审核状态：0-待审核，1-审核通过，2-审核不通过。
     */
    private String verifyState;
    /*
     * 配置工号
     */
    private String cfgUserId;
    /*
     * 一级方案活动类型：0-普通优惠活动，1-家庭宽带，2-家庭电话
     */
    private String marketFirstType;
    /*
     * 是否手机商城营销方案：0-不是，1-是
     */
    private String mobileMallType;
    /*
     * 手机商城地址
     */
    private String mobileMallUrl;
    /*
     * 手机商城详情地址
     */
    private String detailUrl;
    /*
     * 渠道：0-无权限，1-有权限，第1位代表网厅,第2位代表网村组服站,第3位代表校园e站
     */
    private String tChannal;
    /*
     * 营销案类型：0-一般营销案，1-宽带营销案
     */
    private String type;
    /*
     * 网厅营销案展示顺序
     */
    private String marketOrder;
    /*
     * 掌厅营销案展示顺序
     */
    private String ztMarketOrder;
    /*
     * 网厅大图片
     */
    private String bigImgDir;
    /*
     * 礼品内容
     */
    private String prize;
    /*
     * 品牌
     */
    private String isInBrand;
    /*
     * 营销案图片
     */
    private String yxImgDir;
    /*
     * 宽带类型 ，0无意义，type字段一定为0，1开发宽带，2续费宽带
     */
    private String bandType;
    /*
     * 4网厅数据 ，5掌厅数据，6短厅数据
     */
    private String channalData;
    /*
     * 宽带营销案图片,一般营销案无数据
     */
    private String broadbandImage;
    /*
     * 温馨提示
     */
    private String sweetPrompt;
    private String specialFlag;
    
    /*
     * 关联二级预约营销案
     */
    private String relateSecondList;
    
	/*
	 * 列表展示操作按钮
	 */
    private String linkOper;
    /*
     * 是否是统一营销案：0：不是；1：是
     */
    private String unityFlag;

   private String proVerifyState;
   
   private String localVerifyState;
   
   private String proOper;
   
   private String localOper;
   
   private String testOnlineState;

   private String testOper;
   
   private MarketAuditBean marketAuditBean;
   
   private String areaName;
   private String ztMarketShowChannel;
   
   public String getZtMarketShowChannel() {
	return ztMarketShowChannel;
}

public void setZtMarketShowChannel(String ztMarketShowChannel) {
	this.ztMarketShowChannel = ztMarketShowChannel;
}

	/*
     *模板內容 
     */
    private String marketTemplateContent;

   

	/*
    * 短厅用指令内容
    */
    private String instructionContent;
    /*
     * 短厅用扩展码
     */
    private String spreadCode;
    
    /**
	 * 短厅审核状态
	 */
	private String dtProVerifyState;
	private String dtLocalVerifyState;
	private String dtVerifyState;
	
	/**
	 * 网厅审核状态
	 */
	private String wtProVerifyState;
	private String wtLocalVerifyState;
	private String wtVerifyState;
	
	/**
	 * 短厅测试状态
	 */
	private String dtTestOnlineState;
	/**
	 * 网厅测试状态
	 */
	private String wtTestOnlineState;
	/**
	 * 网厅是否列表展示
	 */
	private String wtIsListView;
	private String allProSign;

    public String getAllProSign() {
		return allProSign;
	}

	public void setAllProSign(String allProSign) {
		this.allProSign = allProSign;
	}

	public String getZtMarketOrder() {
		return ztMarketOrder;
	}

	public void setZtMarketOrder(String ztMarketOrder) {
		this.ztMarketOrder = ztMarketOrder;
	}

	/**
	 * @return the wtIsListView
	 */
	public String getWtIsListView() {
		return wtIsListView;
	}

	/**
	 * @param wtIsListView the wtIsListView to set
	 */
	public void setWtIsListView(String wtIsListView) {
		this.wtIsListView = wtIsListView;
	}

	/**
	 * @return the dtTestOnlineState
	 */
	public String getDtTestOnlineState() {
		return dtTestOnlineState;
	}

	/**
	 * @param dtTestOnlineState the dtTestOnlineState to set
	 */
	public void setDtTestOnlineState(String dtTestOnlineState) {
		this.dtTestOnlineState = dtTestOnlineState;
	}

	/**
	 * @return the wtTestOnlineState
	 */
	public String getWtTestOnlineState() {
		return wtTestOnlineState;
	}

	/**
	 * @param wtTestOnlineState the wtTestOnlineState to set
	 */
	public void setWtTestOnlineState(String wtTestOnlineState) {
		this.wtTestOnlineState = wtTestOnlineState;
	}

	/**
	 * @return the dtProVerifyState
	 */
	public String getDtProVerifyState() {
		return dtProVerifyState;
	}

	/**
	 * @param dtProVerifyState the dtProVerifyState to set
	 */
	public void setDtProVerifyState(String dtProVerifyState) {
		this.dtProVerifyState = dtProVerifyState;
	}

	/**
	 * @return the dtLocalVerifyState
	 */
	public String getDtLocalVerifyState() {
		return dtLocalVerifyState;
	}

	/**
	 * @param dtLocalVerifyState the dtLocalVerifyState to set
	 */
	public void setDtLocalVerifyState(String dtLocalVerifyState) {
		this.dtLocalVerifyState = dtLocalVerifyState;
	}

	/**
	 * @return the dtVerifyState
	 */
	public String getDtVerifyState() {
		return dtVerifyState;
	}

	/**
	 * @param dtVerifyState the dtVerifyState to set
	 */
	public void setDtVerifyState(String dtVerifyState) {
		this.dtVerifyState = dtVerifyState;
	}

	/**
	 * @return the wtProVerifyState
	 */
	public String getWtProVerifyState() {
		return wtProVerifyState;
	}

	/**
	 * @param wtProVerifyState the wtProVerifyState to set
	 */
	public void setWtProVerifyState(String wtProVerifyState) {
		this.wtProVerifyState = wtProVerifyState;
	}

	/**
	 * @return the wtLocalVerifyState
	 */
	public String getWtLocalVerifyState() {
		return wtLocalVerifyState;
	}

	/**
	 * @param wtLocalVerifyState the wtLocalVerifyState to set
	 */
	public void setWtLocalVerifyState(String wtLocalVerifyState) {
		this.wtLocalVerifyState = wtLocalVerifyState;
	}

	/**
	 * @return the wtVerifyState
	 */
	public String getWtVerifyState() {
		return wtVerifyState;
	}

	/**
	 * @param wtVerifyState the wtVerifyState to set
	 */
	public void setWtVerifyState(String wtVerifyState) {
		this.wtVerifyState = wtVerifyState;
	}

	public String getIsDT() {
		return isDT;
	}

	public void setIsDT(String isDT) {
		this.isDT = isDT;
	}

	public String getInstructionContent() {
		return instructionContent;
	}

	public void setInstructionContent(String instructionContent) {
		this.instructionContent = instructionContent;
	}

	public String getSpreadCode() {
		return spreadCode;
	}

	public void setSpreadCode(String spreadCode) {
		this.spreadCode = spreadCode;
	}
	public String getMarketTemplateContent() {
		return marketTemplateContent;
	}

	public void setMarketTemplateContent(String marketTemplateContent) {
		this.marketTemplateContent = marketTemplateContent;
	}
	/**
 * @return the areaName
 */
public String getAreaName() {
	return areaName;
}

/**
 * @param areaName the areaName to set
 */
public void setAreaName(String areaName) {
	this.areaName = areaName;
}

	/**
 * @return the marketAuditBean
 */
public MarketAuditBean getMarketAuditBean() {
	return marketAuditBean;
}

/**
 * @param marketAuditBean the marketAuditBean to set
 */
public void setMarketAuditBean(MarketAuditBean marketAuditBean) {
	this.marketAuditBean = marketAuditBean;
}

	/**
 * @return the testOper
 */
public String getTestOper() {
	return testOper;
}

/**
 * @param testOper the testOper to set
 */
public void setTestOper(String testOper) {
	this.testOper = testOper;
}

	/**
 * @return the testOnlineState
 */
public String getTestOnlineState() {
	return testOnlineState;
}

/**
 * @param testOnlineState the testOnlineState to set
 */
public void setTestOnlineState(String testOnlineState) {
	this.testOnlineState = testOnlineState;
}

	/**
 * @return the proOper
 */
public String getProOper() {
	return proOper;
}

/**
 * @param proOper the proOper to set
 */
public void setProOper(String proOper) {
	this.proOper = proOper;
}

/**
 * @return the localOper
 */
public String getLocalOper() {
	return localOper;
}

/**
 * @param localOper the localOper to set
 */
public void setLocalOper(String localOper) {
	this.localOper = localOper;
}

	/**
 * @return the proVerifyState
 */
public String getProVerifyState() {
	return proVerifyState;
}

/**
 * @param proVerifyState the proVerifyState to set
 */
public void setProVerifyState(String proVerifyState) {
	this.proVerifyState = proVerifyState;
}

/**
 * @return the localVerifyState
 */
public String getLocalVerifyState() {
	return localVerifyState;
}

/**
 * @param localVerifyState the localVerifyState to set
 */
public void setLocalVerifyState(String localVerifyState) {
	this.localVerifyState = localVerifyState;
}

	public String getUnityFlag() {
		return unityFlag;
	}

	public void setUnityFlag(String unityFlag) {
		this.unityFlag = unityFlag;
	}

	public String getLinkOper() {
		return linkOper;
	}

	public void setLinkOper(String linkOper) {
		this.linkOper = linkOper;
	}

	public String getRelateSecondList() {
		return relateSecondList;
	}

	public void setRelateSecondList(String relateSecondList) {
		this.relateSecondList = relateSecondList;
	}

	public String getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}

	public String getSweetPrompt() {
		return sweetPrompt;
	}

	public void setSweetPrompt(String sweetPrompt) {
		this.sweetPrompt = sweetPrompt;
	}



    public String getMarketFirstCode() {
        return marketFirstCode;
    }

    public void setMarketFirstCode(String marketFirstCode) {
        this.marketFirstCode = marketFirstCode == null ? null : marketFirstCode.trim();
    }

    public String getMarketFirstName() {
        return marketFirstName;
    }

    public void setMarketFirstName(String marketFirstName) {
        this.marketFirstName = marketFirstName == null ? null : marketFirstName.trim();
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName == null ? null : viewName.trim();
    }

    public String getImgDir() {
        return imgDir;
    }

    public void setImgDir(String imgDir) {
        this.imgDir = imgDir == null ? null : imgDir.trim();
    }

    public String getFlashDir() {
        return flashDir;
    }

    public void setFlashDir(String flashDir) {
        this.flashDir = flashDir == null ? null : flashDir.trim();
    }

    public String getToObject() {
        return toObject;
    }

    public void setToObject(String toObject) {
        this.toObject = toObject == null ? null : toObject.trim();
    }

    public String getActivityComment() {
        return activityComment;
    }

    public void setActivityComment(String activityComment) {
        this.activityComment = activityComment == null ? null : activityComment.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getIsFlashView() {
        return isFlashView;
    }

    public void setIsFlashView(String isFlashView) {
        this.isFlashView = isFlashView == null ? null : isFlashView.trim();
    }

    public String getIsListView() {
        return isListView;
    }

    public void setIsListView(String isListView) {
        this.isListView = isListView == null ? null : isListView.trim();
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState == null ? null : verifyState.trim();
    }



    public String getMarketFirstType() {
        return marketFirstType;
    }

    public void setMarketFirstType(String marketFirstType) {
        this.marketFirstType = marketFirstType == null ? null : marketFirstType.trim();
    }

    public String getMobileMallType() {
        return mobileMallType;
    }

    public void setMobileMallType(String mobileMallType) {
        this.mobileMallType = mobileMallType == null ? null : mobileMallType.trim();
    }

    public String getMobileMallUrl() {
        return mobileMallUrl;
    }

    public void setMobileMallUrl(String mobileMallUrl) {
        this.mobileMallUrl = mobileMallUrl == null ? null : mobileMallUrl.trim();
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl == null ? null : detailUrl.trim();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }



    public String getBigImgDir() {
        return bigImgDir;
    }

    public void setBigImgDir(String bigImgDir) {
        this.bigImgDir = bigImgDir == null ? null : bigImgDir.trim();
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize == null ? null : prize.trim();
    }

    public String getIsInBrand() {
        return isInBrand;
    }

    public void setIsInBrand(String isInBrand) {
        this.isInBrand = isInBrand == null ? null : isInBrand.trim();
    }

    public String getYxImgDir() {
        return yxImgDir;
    }

    public void setYxImgDir(String yxImgDir) {
        this.yxImgDir = yxImgDir == null ? null : yxImgDir.trim();
    }

    public String getBandType() {
        return bandType;
    }

    public void setBandType(String bandType) {
        this.bandType = bandType == null ? null : bandType.trim();
    }

    public String getBroadbandImage() {
        return broadbandImage;
    }

    public void setBroadbandImage(String broadbandImage) {
        this.broadbandImage = broadbandImage == null ? null : broadbandImage.trim();
    }


	public void setChannalData(String channalData) {
		this.channalData = channalData;
	}

	public String getChannalData() {
		return channalData;
	}

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getViewTemplateId() {
		return viewTemplateId;
	}

	public void setViewTemplateId(String viewTemplateId) {
		this.viewTemplateId = viewTemplateId;
	}

	public String getCfgUserId() {
		return cfgUserId;
	}

	public void setCfgUserId(String cfgUserId) {
		this.cfgUserId = cfgUserId;
	}

	public String getTChannal() {
		return tChannal;
	}

	public void setTChannal(String tChannal) {
		this.tChannal = tChannal;
	}

	public String getMarketOrder() {
		return marketOrder;
	}

	public void setMarketOrder(String marketOrder) {
		this.marketOrder = marketOrder;
	}

    public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}