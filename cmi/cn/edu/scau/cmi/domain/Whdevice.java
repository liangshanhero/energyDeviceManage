package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllWhbuildingWhdevices", query = "select myWhdevice from Whdevice myWhdevice where myWhdevice.whbuildingId = ?1"),
		@NamedQuery(name = "findAllWhdevices", query = "select myWhdevice from Whdevice myWhdevice"),
		@NamedQuery(name = "findWhdeviceById", query = "select myWhdevice from Whdevice myWhdevice where myWhdevice.id = ?1"),
		@NamedQuery(name = "findWhdeviceByNumber", query = "select myWhdevice from Whdevice myWhdevice where myWhdevice.number = ?1"),
		@NamedQuery(name = "findWhdeviceByNumberContaining", query = "select myWhdevice from Whdevice myWhdevice where myWhdevice.number like ?1"),
		@NamedQuery(name = "findWhdeviceByPrimaryKey", query = "select myWhdevice from Whdevice myWhdevice where myWhdevice.id = ?1") })
@Table(catalog = "energydevice", name = "whdevice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whdevice")

public class Whdevice implements Serializable {

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

	@Column(name = "whbuilding")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whbuildingId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "whbuilding", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whbuilding whbuilding;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "energydevice", name = "whdatatype2whdevice", joinColumns = {
			@JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whdatatype> whdatatypes;

	@OneToMany(mappedBy = "whdevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whdevicedata> whdevicedatas;

	@OneToMany(mappedBy = "whdevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whstrategy> whstrategys;

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

	public Integer getWhbuildingId() {
		return whbuildingId;
	}

	public void setWhbuildingId(Integer whbuildingId) {
		this.whbuildingId = whbuildingId;
	}

	public void setWhbuilding(Whbuilding whbuilding) {
		this.whbuilding = whbuilding;
	}

	public Whbuilding getWhbuilding() {
		return whbuilding;
	}

	public void setWhdatatypes(Set<Whdatatype> whdatatypes) {
		this.whdatatypes = whdatatypes;
	}

	public Set<Whdatatype> getWhdatatypes() {
		if (whdatatypes == null) {
			whdatatypes = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whdatatype>();
		}
		return whdatatypes;
	}

	public void setWhdevicedatas(Set<Whdevicedata> whdevicedatas) {
		this.whdevicedatas = whdevicedatas;
	}

	public Set<Whdevicedata> getWhdevicedatas() {
		if (whdevicedatas == null) {
			whdevicedatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whdevicedata>();
		}
		return whdevicedatas;
	}

	public void setWhstrategys(Set<Whstrategy> whstrategys) {
		this.whstrategys = whstrategys;
	}

	public Set<Whstrategy> getWhstrategys() {
		if (whstrategys == null) {
			whstrategys = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whstrategy>();
		}
		return whstrategys;
	}

	public void copy(Whdevice that) {
		setId(that.getId());
		setNumber(that.getNumber());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("number=[").append(number).append("] ");

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
		if (!(obj instanceof Whdevice))
			return false;
		Whdevice equalCheck = (Whdevice) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
