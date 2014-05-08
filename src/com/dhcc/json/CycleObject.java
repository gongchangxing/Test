package com.dhcc.json;


public class CycleObject {

	private String memberId;  
    private String sex;  
    private CycleObject me = this;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return 返回  {@link #me}。
	 */
	public CycleObject getMe() {
		return me;
	}
	/**
	 * @param me 要设置的 {@link #me}。
	 */
	public void setMe(CycleObject me) {
		this.me = me;
	}
    
	

}
