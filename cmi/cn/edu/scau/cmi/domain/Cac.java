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
		@NamedQuery(name = "findAllProjectCacs", query = "select myCac from Cac myCac where myCac.project = ?1"),
		@NamedQuery(name = "findAllCacs", query = "select myCac from Cac myCac"),
		@NamedQuery(name = "findCacById", query = "select myCac from Cac myCac where myCac.id = ?1"),
		@NamedQuery(name = "findCacByRemark", query = "select myCac from Cac myCac where myCac.remark = ?1"),
		@NamedQuery(name = "findCacByRemarkContaining", query = "select myCac from Cac myCac where myCac.remark like ?1"),
		@NamedQuery(name = "findCacByPrimaryKey", query = "select myCac from Cac myCac where myCac.id = ?1") })
@Table(catalog = "energydevice", name = "cac")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cac")
public class Cac implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;

	@Column(name = "project")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer project;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "project", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Project relativeProject;

	@OneToMany(mappedBy = "relativeCac", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacdevice> relativeCacdevices;

	@OneToMany(mappedBy = "relativeCac", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacsensor> relativeCacsensors;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
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

	public void setRelativeCacdevices(Set<Cacdevice> relativeCacdevices) {
		this.relativeCacdevices = relativeCacdevices;
	}

	public Set<Cacdevice> getRelativeCacdevices() {
		if (relativeCacdevices == null) {
			relativeCacdevices = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacdevice>();
		}
		return relativeCacdevices;
	}

	public void setRelativeCacsensors(Set<Cacsensor> relativeCacsensors) {
		this.relativeCacsensors = relativeCacsensors;
	}

	public Set<Cacsensor> getRelativeCacsensors() {
		if (relativeCacsensors == null) {
			relativeCacsensors = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacsensor>();
		}
		return relativeCacsensors;
	}

	public void copy(Cac that) {
		setId(that.getId());
		setRemark(that.getRemark());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("remark=[").append(remark).append("] ");

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
		if (!(obj instanceof Cac))
			return false;
		Cac equalCheck = (Cac) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}