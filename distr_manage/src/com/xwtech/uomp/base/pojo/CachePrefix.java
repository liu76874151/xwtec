package com.xwtech.uomp.base.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CachePrefix implements Serializable {

    private String cachKey;

    public String getCachKey() {
        return cachKey;
    }

    public void setCachKey(String cachKey) {
        this.cachKey = cachKey;
    }

}
