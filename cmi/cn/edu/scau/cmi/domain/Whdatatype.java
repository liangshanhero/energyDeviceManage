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
		@NamedQuery(name = "findAllWhdatatypes", query = "select myWhdatatype from Whdatatype myWhdatatype"),
		@NamedQuery(name = "findWhdatatypeById", query = "select myWhdatatype from Whdatatype myWhdatatype where myWhdatatype.id = ?1"),
		@NamedQuery(name = "findWhdatatypeByName", query = "select myWhdatatype from Whdatatype myWhdatatype where myWhdatatype.name = ?1"),
		@NamedQuery(name = "findWhdatatypeByNameContaining", query = "select myWhdatatype from Whdatatype myWhdatatype where myWhdatatype.name like ?1"),
		@NamedQuery(name = "findWhdatatypeByPrimaryKey", query = "select myWhdatatype from Whdatatype myWhdatatype where myWhdatatype.id = ?1") })
@Table(catalog = "energydevice", name = "whdatatype")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whdatatype")
public class Whdatatype implements Serializable {

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(catalog = "energydevice", name = "whdatatype2whdevice", joinColumns = { @JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false, updatable = false) })
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whdevice> relativeWhdevices;

	@OneToMany(mappedBy = "relativeWhdatatype", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whdevicedata> relativeWhdevicedatas;

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

	public void setRelativeWhdevices(Set<Whdevice> relativeWhdevices) {
		this.relativeWhdevices = relativeWhdevices;
	}

	public Set<Whdevice> getRelativeWhdevices() {
		if (relativeWhdevices == null) {
			relativeWhdevices = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whdevice>();
		}
		return relativeWhdevices;
	}

	public void setRelativeWhdevicedatas(Set<Whdevicedata> relativeWhdevicedatas) {
		this.relativeWhdevicedatas = relativeWhdevicedatas;
	}

	public Set<Whdevicedata> getRelativeWhdevicedatas() {
		if (relativeWhdevicedatas == null) {
			relativeWhdevicedatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whdevicedata>();
		}
		return relativeWhdevicedatas;
	}

	public void copy(Whdatatype that) {
		setId(that.getId());
		setName(that.getName());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");

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
		if (!(obj instanceof Whdatatype))
			return false;
		Whdatatype equalCheck = (Whdatatype) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}