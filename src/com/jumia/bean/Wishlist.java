package com.jumia.bean;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wishlist")
public class Wishlist {
	private int WishListId;
	private String wishedBy;
	private Set<Product> wishes;

	public Wishlist() {
		super();
	}

	public Wishlist(int wishListId, String wishedBy, Set<Product> wishes) {
		super();
		WishListId = wishListId;
		this.wishedBy = wishedBy;
		this.wishes = wishes;
	}

	public int getWishListId() {
		return WishListId;
	}

	public void setWishListId(int wishListId) {
		WishListId = wishListId;
	}

	public String getWishedBy() {
		return wishedBy;
	}

	public void setWishedBy(String wishedBy) {
		this.wishedBy = wishedBy;
	}

	public Set<Product> getWishes() {
		return wishes;
	}

	public void setWishes(Set<Product> wishes) {
		this.wishes = wishes;
	}

	@Override
	public String toString() {
		return "Wishlist [WishListId=" + WishListId + ", wishedBy=" + wishedBy + ", wishes=" + wishes + "]";
	}
}
