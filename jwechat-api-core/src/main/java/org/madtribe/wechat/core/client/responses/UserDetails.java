package org.madtribe.wechat.core.client.responses;

public class UserDetails {
	
	/**
	 * 1 subscribed 0 not subscribed
	 * If not subscribed then only openid is available.
	 */
    private int subscribe;
    
    /**
     * Open id
     */
    private String openid;
    
    /**
     * nickname 
     */
    private String nickname;
    
    /**
     * 0 undefined, 1 male, 2 female
     */
    private Integer sex; 
    
    /**
     * zh_CN, zh_TW，en
     */
    private String language;
    
    /**
     * city
     */
    private String city;
    
    /**
     * province
     */
    private String province;
    
    /**
     * country
     */
    private String country;
    
    /**
     * headimgurl, 
     */
    private String headimgurl;
    
    private Long subscribe_time;
    private String unionid;
    private String remark;
    private Integer groupid;
	public int getSubscribe() {
		return subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public String getNickname() {
		return nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public String getLanguage() {
		return language;
	}
	public String getCity() {
		return city;
	}
	public String getProvince() {
		return province;
	}
	public String getCountry() {
		return country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public Long getSubscribe_time() {
		return subscribe_time;
	}
	public String getUnionid() {
		return unionid;
	}
	public String getRemark() {
		return remark;
	}
	public Integer getGroupid() {
		return groupid;
	}


}
