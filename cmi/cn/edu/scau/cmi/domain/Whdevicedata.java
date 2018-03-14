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
		@NamedQuery(name = "findAllWhdatatypeWhdevicedatas", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.whdatatype = ?1"),
		@NamedQuery(name = "findAllWhdeviceWhdevicedatas", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.whdevice = ?1"),
		@NamedQuery(name = "findAllWhdevicedatas", query = "select myWhdevicedata from Whdevicedata myWhdevicedata"),
		@NamedQuery(name = "findWhdevicedataById", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.id = ?1"),
		@NamedQuery(name = "findWhdevicedataByTime", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.time = ?1"),
		@NamedQuery(name = "findWhdevicedataByValue", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.value = ?1"),
		@NamedQuery(name = "findWhdevicedataByIsupdate", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.isupdate = ?1"),
		@NamedQuery(name = "findWhdevicedataByIsio", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.isio = ?1"),
		@NamedQuery(name = "findWhdevicedataByPrimaryKey", query = "select myWhdevicedata from Whdevicedata myWhdevicedata where myWhdevicedata.id = ?1") })
@Table(catalog = "energydevice", name = "whdevicedata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whdevicedata")
public class Whdevicedata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date time;

	@Column(name = "value", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal value;

	@Column(name = "isupdate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isupdate;

	@Column(name = "isio")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isio;

	@Column(name = "whdatatype")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whdatatype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whdatatype relativeWhdatatype;

	@Column(name = "whdevice")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whdevice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whdevice relativeWhdevice;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return this.time;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setIsupdate(Integer isupdate) {
		this.isupdate = isupdate;
	}

	public Integer getIsupdate() {
		return this.isupdate;
	}

	public void setIsio(Integer isio) {
		this.isio = isio;
	}

	public Integer getIsio() {
		return this.isio;
	}

	public Integer getWhdatatype() {
		return whdatatype;
	}

	public void setWhdatatype(Integer whdatatype) {
		this.whdatatype = whdatatype;
	}

	public void setRelativeWhdatatype(Whdatatype relativeWhdatatype) {
		this.relativeWhdatatype = relativeWhdatatype;
	}

	public Whdatatype getRelativeWhdatatype() {
		return relativeWhdatatype;
	}

	public Integer getWhdevice() {
		return whdevice;
	}

	public void setWhdevice(Integer whdevice) {
		this.whdevice = whdevice;
	}

	public void setRelativeWhdevice(Whdevice relativeWhdevice) {
		this.relativeWhdevice = relativeWhdevice;
	}

	public Whdevice getRelativeWhdevice() {
		return relativeWhdevice;
	}

	public void copy(Whdevicedata that) {
		setId(that.getId());
		setTime(that.getTime());
		setValue(that.getValue());
		setIsupdate(that.getIsupdate());
		setIsio(that.getIsio());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("time=[").append(time).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("isupdate=[").append(isupdate).append("] ");
		buffer.append("isio=[").append(isio).append("] ");

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
		if (!(obj instanceof Whdevicedata))
			return false;
		Whdevicedata equalCheck = (Whdevicedata) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}