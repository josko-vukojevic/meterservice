package com.josko.meterservice.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meter_readings")
public class MeterReading {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "month_of_consuption")
	private String monthOfConsuption;
	@Column(name = "year_of_consuption")
	private int yearOfConsuption;
	@Column(name = "kwh")
	private int kwh;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
						CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "meter_id")
	private Meter meter2;

	public MeterReading() {}

	public MeterReading(String monthOfConsuption, int yearOfConsuption, int kwh) {
		this.monthOfConsuption = monthOfConsuption;
		this.yearOfConsuption = yearOfConsuption;
		this.kwh = kwh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMonthOfConsuption() {
		return monthOfConsuption;
	}

	public void setMonthOfConsuption(String monthOfConsuption) {
		this.monthOfConsuption = monthOfConsuption;
	}

	public int getYearOfConsuption() {
		return yearOfConsuption;
	}

	public void setYearOfConsuption(int yearOfConsuption) {
		this.yearOfConsuption = yearOfConsuption;
	}

	public int getKwh() {
		return kwh;
	}

	public void setKwh(int kwh) {
		this.kwh = kwh;
	}

	public Meter getMeter() {
		return meter2;
	}

	public void setMeter(Meter meter) {
		this.meter2 = meter;
	}

	@Override
	public String toString() {
		return "MeterReading [id=" + id + ", monthOfConsuption=" + monthOfConsuption + ", yearOfConsuption="
				+ yearOfConsuption + ", kwh=" + kwh + ", meter_id=" + meter2.getId() + "]";
	}
}
