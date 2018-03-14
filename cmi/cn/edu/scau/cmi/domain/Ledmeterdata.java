package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import cn.edu.scau.cmi.domain.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllLedmeterLedmeterdatas", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata where myLedmeterdata.ledmeter = ?1"),
		@NamedQuery(name = "findAllLedmeterdatas", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata"),
		@NamedQuery(name = "findLedmeterdataById", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata where myLedmeterdata.id = ?1"),
		@NamedQuery(name = "findLedmeterdataByValue", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata where myLedmeterdata.value = ?1"),
		@NamedQuery(name = "findLedmeterdataByTime", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata where myLedmeterdata.time = ?1"),
		@NamedQuery(name = "findLedmeterdataByPrimaryKey", query = "select myLedmeterdata from Ledmeterdata myLedmeterdata where myLedmeterdata.id = ?1") })
@Table(catalog = "energydevice", name = "ledmeterdata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Ledmeterdata")
public class Ledmeterdata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "value", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal value;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date time;

	@Column(name = "ledmeter")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ledmeter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "ledmeter", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Ledmeter relativeLedmeter;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return this.time;
	}

	public Integer getLedmeter() {
		return ledmeter;
	}

	public void setLedmeter(Integer ledmeter) {
		this.ledmeter = ledmeter;
	}

	public void setRelativeLedmeter(Ledmeter relativeLedmeter) {
		this.relativeLedmeter = relativeLedmeter;
	}

	public Ledmeter getRelativeLedmeter() {
		return relativeLedmeter;
	}

	public void copy(Ledmeterdata that) {
		setId(that.getId());
		setValue(that.getValue());
		setTime(that.getTime());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("time=[").append(time).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((id == null) ? 0 : id.hashCode()));
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Ledmeterdata))
			return false;
		Ledmeterdata equalCheck = (Ledmeterdata) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}