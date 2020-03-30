package br.com.vivo.waynemobile.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Line {
	
	@NotNull		
	@Min(value = 1)
	@Max(value = 999)
	private int countryCode;
	
	@NotNull
	@Min(value = 1)
	@Max(value = 999)
	private int areaCode;
	
	@NotNull
	private Long number;
	
	@NotNull
	private Long planId;
	


	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	

}
