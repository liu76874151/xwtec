package com.xwtech.uomp.base.pojo.market;



public class MarketLogBean {
    private String pkid;

    private String marketFirstPkid;

    private String marketSecondPkid;

    private String firstCode;

    private String secondCode;

    private String city;

    private String caseLevel;

    private String type;

    private String optUser;

    private String optTime;

    private String flag;

  
    public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}

	public String getMarketFirstPkid() {
		return marketFirstPkid;
	}

	public void setMarketFirstPkid(String marketFirstPkid) {
		this.marketFirstPkid = marketFirstPkid;
	}

	public String getMarketSecondPkid() {
		return marketSecondPkid;
	}

	public void setMarketSecondPkid(String marketSecondPkid) {
		this.marketSecondPkid = marketSecondPkid;
	}

	public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode == null ? null : firstCode.trim();
    }

    public String getSecondCode() {
        return secondCode;
    }

    public void setSecondCode(String secondCode) {
        this.secondCode = secondCode == null ? null : secondCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCaseLevel() {
        return caseLevel;
    }

    public void setCaseLevel(String caseLevel) {
        this.caseLevel = caseLevel == null ? null : caseLevel.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOptUser() {
        return optUser;
    }

    public void setOptUser(String optUser) {
        this.optUser = optUser == null ? null : optUser.trim();
    }

    public String getOptTime() {
        return optTime;
    }

    public void setOptTime(String optTime) {
        this.optTime = optTime == null ? null : optTime.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

	@Override
	public String toString() {
		return "MarketLogBean [caseLevel=" + caseLevel + ", city=" + city
				+ ", firstCode=" + firstCode + ", flag=" + flag
				+ ", marketFirstPkid=" + marketFirstPkid
				+ ", marketSecondPkid=" + marketSecondPkid + ", optTime="
				+ optTime + ", optUser=" + optUser + ", pkid=" + pkid
				+ ", secondCode=" + secondCode + ", type=" + type + "]";
	}
    
    
    
}