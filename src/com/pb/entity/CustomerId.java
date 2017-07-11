package com.pb.entity;

/**
 * CustomerId entity. @author MyEclipse Persistence Tools
 */

public class CustomerId implements java.io.Serializable {

	// Fields

	private String logo;
	private String name;
	private String address;

	// Constructors

	/** default constructor */
	public CustomerId() {
	}

	/** full constructor */
	public CustomerId(String logo, String name, String address) {
		this.logo = logo;
		this.name = name;
		this.address = address;
	}

	// Property accessors

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CustomerId))
			return false;
		CustomerId castOther = (CustomerId) other;

		return ((this.getLogo() == castOther.getLogo()) || (this.getLogo() != null
				&& castOther.getLogo() != null && this.getLogo().equals(
				castOther.getLogo())))
				&& ((this.getName() == castOther.getName()) || (this.getName() != null
						&& castOther.getName() != null && this.getName()
						.equals(castOther.getName())))
				&& ((this.getAddress() == castOther.getAddress()) || (this
						.getAddress() != null && castOther.getAddress() != null && this
						.getAddress().equals(castOther.getAddress())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLogo() == null ? 0 : this.getLogo().hashCode());
		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getAddress() == null ? 0 : this.getAddress().hashCode());
		return result;
	}

}