package com.jumia.bean;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="products")
public class MapWrapper {
	private Map<Product, Integer> map;

	public MapWrapper() {
		// TODO Auto-generated constructor stub
	}

	public MapWrapper(Map<Product, Integer> map) {
		super();
		this.map = map;
	}

	public Map<Product, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "MapWrapper [map=" + map + "]";
	}
}
