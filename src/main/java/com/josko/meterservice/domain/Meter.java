package com.josko.meterservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "meters")
public class Meter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "meter2",
		 		cascade={CascadeType.PERSIST, CascadeType.MERGE,
		 				 CascadeType.DETACH, CascadeType.REFRESH})
	private List<MeterReading> meterReadings;

	public Meter() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void add(MeterReading meterReading) {
		if (meterReadings == null) {
			meterReadings = new ArrayList<MeterReading>();
		}
		meterReadings.add(meterReading);
		meterReading.setMeter(this);
	}

	@Override
	public String toString() {
		return "Meter [id=" + id + ", client=" + client + ", meterReadings=" + meterReadings + "]";
	}
}
