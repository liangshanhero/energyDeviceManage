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
		@NamedQuery(name = "findAllProjectWhbuildings", query = "select myWhbuilding from Whbuilding myWhbuilding where myWhbuilding.project = ?1"),
		@NamedQuery(name = "findAllWhbuildings", query = "select myWhbuilding from Whbuilding myWhbuilding"),
		@NamedQuery(name = "findWhbuildingById", query = "select myWhbuilding from Whbuilding myWhbuilding where myWhbuilding.id = ?1"),
		@NamedQuery(name = "findWhbuildingByName", query = "select myWhbuilding from Whbuilding myWhbuilding where myWhbuilding.name = ?1"),
		@NamedQuery(name = "findWhbuildingByNameContaining", query = "select myWhbuilding from Whbuilding myWhbuilding where myWhbuilding.name like ?1"),
		@NamedQuery(name = "findWhbuildingByPrimaryKey", query = "select myWhbuilding from Whbuilding myWhbuilding where myWhbuilding.id = ?1") })
@Table(catalog = "energydevice", name = "whbuilding")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whbuilding")
public class Whbuilding implements Serializable {

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

	@Column(name = "project")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "project", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Project relativeProject;

	@OneToMany(mappedBy = "whbuilding", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whdevice> relativeWhdevices;

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

	public void setRelativeWhdevices(Set<Whdevice> relativeWhdevices) {
		this.relativeWhdevices = relativeWhdevices;
	}

	public Set<Whdevice> getRelativeWhdevices() {
		if (relativeWhdevices == null) {
			relativeWhdevices = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whdevice>();
		}
		return relativeWhdevices;
	}

	public void copy(Whbuilding that) {
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
		if (!(obj instanceof Whbuilding))
			return false;
		Whbuilding equalCheck = (Whbuilding) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}