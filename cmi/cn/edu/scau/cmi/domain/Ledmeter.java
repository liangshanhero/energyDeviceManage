package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Set;

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
		@NamedQuery(name = "findAllLedbuildingLedmeters", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.ledbuilding = ?1"),
		@NamedQuery(name = "findAllLedmeters", query = "select myLedmeter from Ledmeter myLedmeter"),
		@NamedQuery(name = "findLedmeterById", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.id = ?1"),
		@NamedQuery(name = "findLedmeterByNumber", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.number = ?1"),
		@NamedQuery(name = "findLedmeterByNumberContaining", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.number like ?1"),
		@NamedQuery(name = "findLedmeterByWell", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.well = ?1"),
		@NamedQuery(name = "findLedmeterByWellContaining", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.well like ?1"),
		@NamedQuery(name = "findLedmeterByStorey", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.storey = ?1"),
		@NamedQuery(name = "findLedmeterByTotalamout", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.totalamout = ?1"),
		@NamedQuery(name = "findLedmeterByTotaldays", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.totaldays = ?1"),
		@NamedQuery(name = "findLedmeterByPrimaryKey", query = "select myLedmeter from Ledmeter myLedmeter where myLedmeter.id = ?1") })
@Table(catalog = "energydevice", name = "ledmeter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Ledmeter")
public class Ledmeter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "number")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String number;

	@Column(name = "well")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String well;

	@Column(name = "storey")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer storey;

	@Column(name = "totalamout", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal totalamout;

	@Column(name = "totaldays", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal totaldays;

	@Column(name = "ledbuilding")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer ledbuilding;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "ledbuilding", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Ledbuilding relativeLedbuilding;

	@OneToMany(mappedBy = "relativeLedmeter", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Ledmeterdata> relativeLedmeterdatas;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return this.number;
	}

	public void setWell(String well) {
		this.well = well;
	}

	public String getWell() {
		return this.well;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public Integer getStorey() {
		return this.storey;
	}

	public void setTotalamout(BigDecimal totalamout) {
		this.totalamout = totalamout;
	}

	public BigDecimal getTotalamout() {
		return this.totalamout;
	}

	public void setTotaldays(BigDecimal totaldays) {
		this.totaldays = totaldays;
	}

	public BigDecimal getTotaldays() {
		return this.totaldays;
	}

	public Integer getLedbuilding() {
		return ledbuilding;
	}

	public void setLedbuilding(Integer ledbuilding) {
		this.ledbuilding = ledbuilding;
	}

	public void setRelativeLedbuilding(Ledbuilding relativeLedbuilding) {
		this.relativeLedbuilding = relativeLedbuilding;
	}

	public Ledbuilding getRelativeLedbuilding() {
		return relativeLedbuilding;
	}

	public void setRelativeLedmeterdatas(Set<Ledmeterdata> relativeLedmeterdatas) {
		this.relativeLedmeterdatas = relativeLedmeterdatas;
	}

	public Set<Ledmeterdata> getRelativeLedmeterdatas() {
		if (relativeLedmeterdatas == null) {
			relativeLedmeterdatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Ledmeterdata>();
		}
		return relativeLedmeterdatas;
	}

	public void copy(Ledmeter that) {
		setId(that.getId());
		setNumber(that.getNumber());
		setWell(that.getWell());
		setStorey(that.getStorey());
		setTotalamout(that.getTotalamout());
		setTotaldays(that.getTotaldays());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("number=[").append(number).append("] ");
		buffer.append("well=[").append(well).append("] ");
		buffer.append("storey=[").append(storey).append("] ");
		buffer.append("totalamout=[").append(totalamout).append("] ");
		buffer.append("totaldays=[").append(totaldays).append("] ");

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
		if (!(obj instanceof Ledmeter))
			return false;
		Ledmeter equalCheck = (Ledmeter) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}