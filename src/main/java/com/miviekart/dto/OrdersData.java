package com.miviekart.dto;

import java.sql.Timestamp;
import java.util.List;

import com.miviekart.model.Customer;
import com.miviekart.model.OrderItem;

public class OrdersData {

	private int orderId;
	private Timestamp timestamp;
	private Customer customer;
	private List<OrderItem> orderItems;
	
	public OrdersData() {
		super();
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	

	// public int getOrderId() {
	// 	return orderId;
	// }

	// public void setOrderId(int orderId) {
	// 	this.orderId = orderId;
	// }

	// public Timestamp getTimestamp() {
	// 	return timestamp;
	// }

	// public void setTimestamp(Timestamp timestamp) {
	// 	this.timestamp = timestamp;
	// }

	// public Customer getCustomer() {
	// 	return customer;
	// }

	// public void setCustomer(Customer customer) {
	// 	this.customer = customer;
	// }

	// public List<OrderItem> getOrderItems() {
	// 	return orderItems;
	// }

	// public void setOrderItems(List<OrderItem> orderItems) {
	// 	this.orderItems = orderItems;
	// }

}
