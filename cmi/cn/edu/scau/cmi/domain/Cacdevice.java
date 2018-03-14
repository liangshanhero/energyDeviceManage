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
		@NamedQuery(name = "findAllCacCacdevices", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.cac = ?1"),
		@NamedQuery(name = "findAllCacdevices", query = "select myCacdevice from Cacdevice myCacdevice"),
		@NamedQuery(name = "findCacdeviceById", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.id = ?1"),
		@NamedQuery(name = "findCacdeviceByName", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.name = ?1"),
		@NamedQuery(name = "findCacdeviceByNameContaining", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.name like ?1"),
		@NamedQuery(name = "findCacdeviceByUnit", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.unit = ?1"),
		@NamedQuery(name = "findCacdeviceByUnitContaining", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.unit like ?1"),
		@NamedQuery(name = "findCacdeviceByPrimaryKey", query = "select myCacdevice from Cacdevice myCacdevice where myCacdevice.id = ?1") })
@Table(catalog = "energydevice", name = "cacdevice")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacdevice")
public class Cacdevice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	@Column(name = "unit")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String unit;

	@Column(name = "cac")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cac;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cac", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cac relativeCac;

	@OneToMany(mappedBy = "relativeCacdevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacdevicedata> relativeCacdevicedatas;

	@OneToMany(mappedBy = "relativeCacdevice", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacmalfunction> relativeCacmalfunctions;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return this.unit;
	}

	public Integer getCac() {
		return cac;
	}

	public void setCac(Integer cac) {
		this.cac = cac;
	}

	public void setRelativeCac(Cac relativeCac) {
		this.relativeCac = relativeCac;
	}

	public Cac getRelativeCac() {
		return relativeCac;
	}

	public void setRelativeCacdevicedatas(
			Set<Cacdevicedata> relativeCacdevicedatas) {
		this.relativeCacdevicedatas = relativeCacdevicedatas;
	}

	public Set<Cacdevicedata> getRelativeCacdevicedatas() {
		if (relativeCacdevicedatas == null) {
			relativeCacdevicedatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacdevicedata>();
		}
		return relativeCacdevicedatas;
	}

	public void setRelativeCacmalfunctions(
			Set<Cacmalfunction> relativeCacmalfunctions) {
		this.relativeCacmalfunctions = relativeCacmalfunctions;
	}

	public Set<Cacmalfunction> getRelativeCacmalfunctions() {
		if (relativeCacmalfunctions == null) {
			relativeCacmalfunctions = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacmalfunction>();
		}
		return relativeCacmalfunctions;
	}

	public void copy(Cacdevice that) {
		setId(that.getId());
		setName(that.getName());
		setUnit(that.getUnit());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("unit=[").append(unit).append("] ");

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
		if (!(obj instanceof Cacdevice))
			return false;
		Cacdevice equalCheck = (Cacdevice) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}