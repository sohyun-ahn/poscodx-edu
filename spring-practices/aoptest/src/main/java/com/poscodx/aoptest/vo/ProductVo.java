package com.poscodx.aoptest.vo;

public class ProductVo {
	String name;

	public ProductVo(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ProductVo [name=" + name + "]";
	}
}
