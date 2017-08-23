package com.nisum.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="order")
public class Order {

	private String strOrderNo;

	private String strFirstName;
	private String strLastName;
	private String strShipZipCode;
	private String strPaymentInfo;
	
	private List<OrderLine> lOrderLine; 

	public String getStrOrderNo() {
		return strOrderNo;
	}
	public void setStrOrderNo(String strOrderNo) {
		this.strOrderNo = strOrderNo;
	}
	public String getStrFirstName() {
		return strFirstName;
	}
	public void setStrFirstName(String strFirstName) {
		this.strFirstName = strFirstName;
	}
	public String getStrLastName() {
		return strLastName;
	}
	public void setStrLastName(String strLastName) {
		this.strLastName = strLastName;
	}
	public String getStrShipZipCode() {
		return strShipZipCode;
	}
	public void setStrShipZipCode(String strShipZipCode) {
		this.strShipZipCode = strShipZipCode;
	}
	public String getStrPaymentInfo() {
		return strPaymentInfo;
	}
	public void setStrPaymentInfo(String strPaymentInfo) {
		this.strPaymentInfo = strPaymentInfo;
	}
	public List<OrderLine> getlOrderLine() {
		return lOrderLine;
	}
	public void setlOrderLine(List<OrderLine> lOrderLine) {
		this.lOrderLine = lOrderLine;
	}
	
}
