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
		@NamedQuery(name = "findAllCacCacsensors", query = "select myCacsensor from Cacsensor myCacsensor where myCacsensor.cac = ?1"),
		@NamedQuery(name = "findAllCacsensors", query = "select myCacsensor from Cacsensor myCacsensor"),
		@NamedQuery(name = "findCacsensorById", query = "select myCacsensor from Cacsensor myCacsensor where myCacsensor.id = ?1"),
		@NamedQuery(name = "findCacsensorByName", query = "select myCacsensor from Cacsensor myCacsensor where myCacsensor.name = ?1"),
		@NamedQuery(name = "findCacsensorByNameContaining", query = "select myCacsensor from Cacsensor myCacsensor where myCacsensor.name like ?1"),
		@NamedQuery(name = "findCacsensorByPrimaryKey", query = "select myCacsensor from Cacsensor myCacsensor where myCacsensor.id = ?1") })
@Table(catalog = "energydevice", name = "cacsensor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacsensor")
public class Cacsensor implements Serializable {

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

	@Column(name = "cac")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer cac;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cac", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cac relativeCac;

	@OneToMany(mappedBy = "relativeCacsensor", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacsensordata> relativeCacsensordatas;

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

	public void setRelativeCacsensordatas(
			Set<Cacsensordata> relativeCacsensordatas) {
		this.relativeCacsensordatas = relativeCacsensordatas;
	}

	public Set<Cacsensordata> getRelativeCacsensordatas() {
		if (relativeCacsensordatas == null) {
			relativeCacsensordatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacsensordata>();
		}
		return relativeCacsensordatas;
	}

	public void copy(Cacsensor that) {
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
		if (!(obj instanceof Cacsensor))
			return false;
		Cacsensor equalCheck = (Cacsensor) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}