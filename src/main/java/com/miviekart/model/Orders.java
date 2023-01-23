package com.miviekart.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Orders {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private Timestamp timestamp;
    @OneToOne
    private Customer customer;
    @OneToMany
    private List<OrderItem> orderItems;
    
	public Orders() {
		super();
	}

	public Orders(int orderId, Timestamp timestamp, Customer customer, List<OrderItem> orderItems) {
		super();
		this.orderId = orderId;
		this.timestamp = timestamp;
		this.customer = customer;
		this.orderItems = orderItems;
	}

	public Orders(Timestamp timestamp, Customer customer, List<OrderItem> orderItems) {
		super();
		this.timestamp = timestamp;
		this.customer = customer;
		this.orderItems = orderItems;
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

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", timestamp=" + timestamp + ", customer=" + customer + ", orderItems="
				+ orderItems + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}
    

	
    
    

}
