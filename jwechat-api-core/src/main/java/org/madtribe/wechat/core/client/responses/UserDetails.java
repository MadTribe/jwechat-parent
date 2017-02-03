package org.madtribe.wechat.core.client.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {"subscribe":1,
 * "openid":"or4wgt3dwZQEXXyQxTGJBUlgYOJY",
 * "nickname":"Paul Smout",
 * "sex":0,
 * "language":"zh_CN",
 * "city":"",
 * "province":"",
 * "country":"",
 * "headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/libKeCufzUGJO6icoYV6lMggceG4pn38cWX9jZTuHWmtU4EK9WHFbAQWblrHDQ1eLkMsSfYO8qJj3DGQk1P6K9cadG3T0LaVHM\/0",
 * "subscribe_time":1486018936,
 * "remark":"",
 * "groupid":0,
 * "tagid_list":[]}
 * @author paulsmout
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails {
	
	/**
	 * 1 subscribed 0 not subscribed
	 * If not subscribed then only openid is available.
	 */
	@JsonProperty("subscribe")
    private Integer subscribe;
    
    /**
     * Open id
     */
	@JsonProperty("openid")
    private String openid;
    
    /**
     * nickname 
     */
	@JsonProperty("nickname")
    private String nickname;
    
    /**
     * 0 undefined, 1 male, 2 female
     */
	@JsonProperty("sex")
    private Integer sex; 
    
    /**
     * zh_CN, zh_TW，en
     */
	@JsonProperty("language")
    private String language;
    
    /**
     * city
     */
	@JsonProperty("city")
    private String city;
    
    /**
     * province
     */
	@JsonProperty("province")
    private String province;
    
    /**
     * country
     */
	@JsonProperty("country")
    private String country;
    
    /**
     * headimgurl, 
     */
	@JsonProperty("headimgurl")
    private String headImgUrl;
    
	@JsonProperty("subscribe_time")
    private Long subscribeTime;
	
	@JsonProperty("unionId")
    private String unionid;
	
	@JsonProperty("remark")
    private String remark;
	
	@JsonProperty("groupid")
    private Integer groupId;
	
	@JsonProperty("tagid_list")
	private List<String> tagIdList;

	public List<String> getTagIdList() {
		return tagIdList;
	}

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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public String getUnionid() {
		return unionid;
	}

	public String getRemark() {
		return remark;
	}

	public Integer getGroupId() {
		return groupId;
	}

	@Override
	public String toString() {
		return "UserDetails [subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname + ", sex=" + sex
				+ ", language=" + language + ", city=" + city + ", province=" + province + ", country=" + country
				+ ", headImgUrl=" + headImgUrl + ", subscribeTime=" + subscribeTime + ", unionid=" + unionid
				+ ", remark=" + remark + ", groupId=" + groupId + "]";
	}
	
	

}
