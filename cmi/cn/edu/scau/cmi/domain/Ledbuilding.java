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
		@NamedQuery(name = "findAllProjectLedbuildings", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.project = ?1"),
		@NamedQuery(name = "findAllLedbuildings", query = "select myLedbuilding from Ledbuilding myLedbuilding"),
		@NamedQuery(name = "findLedbuildingById", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.id = ?1"),
		@NamedQuery(name = "findLedbuildingByName", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.name = ?1"),
		@NamedQuery(name = "findLedbuildingByNameContaining", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.name like ?1"),
		@NamedQuery(name = "findLedbuildingByWell", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.well = ?1"),
		@NamedQuery(name = "findLedbuildingByStorey", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.storey = ?1"),
		@NamedQuery(name = "findLedbuildingByPrimaryKey", query = "select myLedbuilding from Ledbuilding myLedbuilding where myLedbuilding.id = ?1") })
@Table(catalog = "energydevice", name = "ledbuilding")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Ledbuilding")
public class Ledbuilding implements Serializable {

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

	@Column(name = "well")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer well;

	@Column(name = "storey")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer storey;

	@Column(name = "project")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "project", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Project relativeProject;

	@OneToMany(mappedBy = "relativeLedbuilding", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Ledmeter> relativeLedmeters;

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

	public void setWell(Integer well) {
		this.well = well;
	}

	public Integer getWell() {
		return this.well;
	}

	public void setStorey(Integer storey) {
		this.storey = storey;
	}

	public Integer getStorey() {
		return this.storey;
	}

	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public void setRelativeProject(Project relativeProject) {
		this.relativeProject = relativeProject;
	}

	public Project getRelativeProject() {
		return relativeProject;
	}

	public void setRelativeLedmeters(Set<Ledmeter> relativeLedmeters) {
		this.relativeLedmeters = relativeLedmeters;
	}

	public Set<Ledmeter> getRelativeLedmeters() {
		if (relativeLedmeters == null) {
			relativeLedmeters = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Ledmeter>();
		}
		return relativeLedmeters;
	}

	public void copy(Ledbuilding that) {
		setId(that.getId());
		setName(that.getName());
		setWell(that.getWell());
		setStorey(that.getStorey());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("well=[").append(well).append("] ");
		buffer.append("storey=[").append(storey).append("] ");

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
		if (!(obj instanceof Ledbuilding))
			return false;
		Ledbuilding equalCheck = (Ledbuilding) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}