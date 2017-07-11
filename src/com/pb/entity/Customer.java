package com.pb.entity;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

	// Fields

	private CustomerId id;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** full constructor */
	public Customer(CustomerId id) {
		this.id = id;
	}

	// Property accessors

	public CustomerId getId() {
		return this.id;
	}

	public void setId(CustomerId id) {
		this.id = id;
	}

}