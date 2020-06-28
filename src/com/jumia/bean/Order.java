package com.jumia.bean;

import java.sql.Date;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.jumia.util.OrderStatus;

@XmlRootElement(name="order")
public class Order {
	private int orderId;
	private String number;
	private Date orderedOn;
	private String orderedBy;
	private OrderStatus status;
	private Set<Product> cart;

	public Order() {
		super();
	}

	public Order(int orderId, String number, Date orderedOn, String orderedBy,
			OrderStatus status, Set<Product> cart) {
		super();
		this.orderId = orderId;
		this.number = number;
		this.orderedOn = orderedOn;
		this.orderedBy = orderedBy;
		this.status = status;
		this.cart = cart;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(Date orderedOn) {
		this.orderedOn = orderedOn;
	}

	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Set<Product> getCart() {
		return cart;
	}

	public void setCart(Set<Product> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "\nOrder [orderId=" + orderId + ", number=" + number + ", orderedOn=" + orderedOn + ", orderedBy="
				+ orderedBy + ", status=" + status + ", cart=" + cart + "]";
	}
}
