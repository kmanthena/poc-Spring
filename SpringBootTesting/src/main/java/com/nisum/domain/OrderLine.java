package com.nisum.domain;

import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

public class OrderLine {

	@Id
	private String strOrderLineNo;

	private String strItemId;
	private String strQuantity;

	@ManyToOne
	public OrderHeader orderHeader;
	
	public String getStrOrderLineNo() {
		return strOrderLineNo;
	}
	public void setStrOrderLineNo(String strOrderLineNo) {
		this.strOrderLineNo = strOrderLineNo;
	}
	
	public OrderHeader getOrder() {
		return orderHeader;
	}
	public void setOrder(OrderHeader orderHeader) {
		this.orderHeader = orderHeader;
	}

	public String getStrItemId() {
		return strItemId;
	}
	public void setStrItemId(String strItemId) {
		this.strItemId = strItemId;
	}
	public String getStrQuantity() {
		return strQuantity;
	}
	public void setStrQuantity(String strQuantity) {
		this.strQuantity = strQuantity;
	}

}
