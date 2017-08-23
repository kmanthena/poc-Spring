package com.nisum.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="order")
public class OrderHeader {

	@Id
	private String strOrderNo;

	private String strFirstName;
	private String strLastName;
	private String strShipZipCode;
	private String strPaymentInfo;
	
	
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

	
	public String toString (OrderHeader order) {
		String strOutput =  "OrderNo : "+order.getStrOrderNo()+", FirstName :"+order.getStrFirstName()+", LastName : "
		+order.getStrLastName()+", ZipCode : "+order.getStrShipZipCode()+", PaymentInfo : "+order.getStrPaymentInfo() +" \n\n\n";
		
		return strOutput;
	}
	
}
