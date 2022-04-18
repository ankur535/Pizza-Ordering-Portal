package com.accenture.lkm.business.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class PizzaOrderBean {
	
	private Integer orderId;
	@NotEmpty
	@Size(min = 3,max = 30,message = "Customer Name should be between {2} and {1}")
	private String customerName;
	@NotEmpty
	@Size(min = 10,max = 10,message = "Contact Number should be 10 digits")
	private String contactNumber;
	@NotNull
	private Integer pizzaId;
	
	private Double bill;
	@NotNull
	@Range(min = 1,message = "Minimum {1} quantity is required")
	private Integer numberOfPiecesOrdered;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Integer getPizzaId() {
		return pizzaId;
	}
	public void setPizzaId(Integer pizzaId) {
		this.pizzaId = pizzaId;
	}
	public Double getBill() {
		return bill;
	}
	public void setBill(Double bill) {
		this.bill = bill;
	}
	public Integer getNumberOfPiecesOrdered() {
		return numberOfPiecesOrdered;
	}
	public void setNumberOfPiecesOrdered(Integer numberOfPiecesOrdered) {
		this.numberOfPiecesOrdered = numberOfPiecesOrdered;
	}
	@Override
	public String toString() {
		return "PizzaOrderBean [orderId=" + orderId + ", customerName=" + customerName + ", contactNumber="
				+ contactNumber + ", pizzaId=" + pizzaId + ", bill=" + bill + ", numberOfPiecesOrdered="
				+ numberOfPiecesOrdered + "]";
	}
	

}




