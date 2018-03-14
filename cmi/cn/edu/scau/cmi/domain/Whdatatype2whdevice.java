package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllWhdatatypeWhdatatype2whdevices", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice where myWhdatatype2whdevice.whdatatype = ?1"),
		@NamedQuery(name = "findAllWhdeviceWhdatatype2whdevices", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice where myWhdatatype2whdevice.whdevice = ?1"),
		@NamedQuery(name = "findAllWhdatatype2whdevices", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice"),
		@NamedQuery(name = "findWhdatatype2whdeviceByWhdatatype", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice where myWhdatatype2whdevice.whdatatype = ?1"),
		@NamedQuery(name = "findWhdatatype2whdeviceByWhdevice", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice where myWhdatatype2whdevice.whdevice = ?1"),
		@NamedQuery(name = "findWhdatatype2whdeviceByPrimaryKey", query = "select myWhdatatype2whdevice from Whdatatype2whdevice myWhdatatype2whdevice where myWhdatatype2whdevice.whdatatype = ?1 and myWhdatatype2whdevice.whdevice = ?2") })
@Table(catalog = "energydevice", name = "whdatatype2whdevice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whdatatype2whdevice")
public class Whdatatype2whdevice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "whdatatype", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer whdatatype;

	@Column(name = "whdevice", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer whdevice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whdatatype relativeWhdatatype;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whdevice relativeWhdevice;

	public void setWhdatatype(Integer whdatatype) {
		this.whdatatype = whdatatype;
	}

	public Integer getWhdatatype() {
		return this.whdatatype;
	}

	public void setWhdevice(Integer whdevice) {
		this.whdevice = whdevice;
	}

	public Integer getWhdevice() {
		return this.whdevice;
	}

	public void setRelativeWhdatatype(Whdatatype relativeWhdatatype) {
		this.relativeWhdatatype = relativeWhdatatype;
	}

	public Whdatatype getRelativeWhdatatype() {
		return relativeWhdatatype;
	}

	public void setRelativeWhdevice(Whdevice relativeWhdevice) {
		this.relativeWhdevice = relativeWhdevice;
	}

	public Whdevice getRelativeWhdevice() {
		return relativeWhdevice;
	}

	public void copy(Whdatatype2whdevice that) {
		setWhdatatype(that.getWhdatatype());
		setWhdevice(that.getWhdevice());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("whdatatype=[").append(whdatatype).append("] ");
		buffer.append("whdevice=[").append(whdevice).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((whdatatype == null) ? 0 : whdatatype
				.hashCode()));
		result = (int) (prime * result + ((whdevice == null) ? 0 : whdevice
				.hashCode()));
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Whdatatype2whdevice))
			return false;
		Whdatatype2whdevice equalCheck = (Whdatatype2whdevice) obj;
		if ((whdatatype == null && equalCheck.whdatatype != null)
				|| (whdatatype != null && equalCheck.whdatatype == null))
			return false;
		if ((whdevice == null && equalCheck.whdevice != null)
				|| (whdevice != null && equalCheck.whdevice == null))
			return false;
		if (whdatatype != null && !whdatatype.equals(equalCheck.whdatatype))
			return false;
		if (whdevice != null && !whdevice.equals(equalCheck.whdevice))
			return false;
		return true;
	}
}