package org.preznicek.publiclibrary.model;

import java.math.BigDecimal;

/**
 * Objekt, ktery se uklada do <code>session</code>, pokud se uzivatel uspesne prihlasi do aplikace.
 * Obsahuje pouze nezbytne udaje, ktere nemaji vliv na bezpecnost.
 */
public class LoggedUser {

	private Long id;
	private String name;
	private String email;
	private BigDecimal gpsLatitude;
	private BigDecimal gpsLongitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getGpsLatitude() {
		return gpsLatitude;
	}
	public void setGpsLatitude(BigDecimal gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
	public BigDecimal getGpsLongitude() {
		return gpsLongitude;
	}
	public void setGpsLongitude(BigDecimal gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
}
