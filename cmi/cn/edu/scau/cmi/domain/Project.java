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
		@NamedQuery(name = "findAllCompanyProjects", query = "select myProject from Project myProject where myProject.company = ?1"),
		@NamedQuery(name = "findAllStaffProjects", query = "select myProject from Project myProject where myProject.staff = ?1"),
		@NamedQuery(name = "findAllProjects", query = "select myProject from Project myProject"),
		@NamedQuery(name = "findProjectById", query = "select myProject from Project myProject where myProject.id = ?1"),
		@NamedQuery(name = "findProjectByName", query = "select myProject from Project myProject where myProject.name = ?1"),
		@NamedQuery(name = "findProjectByNameContaining", query = "select myProject from Project myProject where myProject.name like ?1"),
		@NamedQuery(name = "findProjectByType", query = "select myProject from Project myProject where myProject.type = ?1"),
		@NamedQuery(name = "findProjectByTypeContaining", query = "select myProject from Project myProject where myProject.type like ?1"),
		@NamedQuery(name = "findProjectByStatus", query = "select myProject from Project myProject where myProject.status = ?1"),
		@NamedQuery(name = "findProjectByStatusContaining", query = "select myProject from Project myProject where myProject.status like ?1"),
		@NamedQuery(name = "findProjectByProvince", query = "select myProject from Project myProject where myProject.province = ?1"),
		@NamedQuery(name = "findProjectByProvinceContaining", query = "select myProject from Project myProject where myProject.province like ?1"),
		@NamedQuery(name = "findProjectByCity", query = "select myProject from Project myProject where myProject.city = ?1"),
		@NamedQuery(name = "findProjectByCityContaining", query = "select myProject from Project myProject where myProject.city like ?1"),
		@NamedQuery(name = "findProjectByArea", query = "select myProject from Project myProject where myProject.area = ?1"),
		@NamedQuery(name = "findProjectByAreaContaining", query = "select myProject from Project myProject where myProject.area like ?1"),
		@NamedQuery(name = "findProjectByAddress", query = "select myProject from Project myProject where myProject.address = ?1"),
		@NamedQuery(name = "findProjectByAddressContaining", query = "select myProject from Project myProject where myProject.address like ?1"),
		@NamedQuery(name = "findProjectByDetail", query = "select myProject from Project myProject where myProject.detail = ?1"),
		@NamedQuery(name = "findProjectByRemark", query = "select myProject from Project myProject where myProject.remark = ?1"),
		@NamedQuery(name = "findProjectByPrimaryKey", query = "select myProject from Project myProject where myProject.id = ?1") })
@Table(catalog = "energydevice", name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Project")
public class Project implements Serializable {

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

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String status;

	@Column(name = "province")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String province;

	@Column(name = "city")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String city;

	@Column(name = "area")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String area;

	@Column(name = "address")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String address;

	@Column(name = "detail")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String detail;

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;

	@Column(name = "company")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer company;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "company", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Company relativeCompany;

	@Column(name = "responsibility")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer staff;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "responsibility", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Staff relativeStaff;

	@OneToMany(mappedBy = "relativeProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cac> relativeCacs;

	@OneToMany(mappedBy = "relativeProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Ledbuilding> relativeLedbuildings;

	@OneToMany(mappedBy = "relativeProject", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whbuilding> relativeWhbuildings;

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

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvince() {
		return this.province;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getArea() {
		return this.area;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
		return this.remark;
	}

	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}

	public void setRelativeCompany(Company relativeCompany) {
		this.relativeCompany = relativeCompany;
	}

	public Company getRelativeCompany() {
		return relativeCompany;
	}

	public Integer getStaff() {
		return staff;
	}

	public void setStaff(Integer staff) {
		this.staff = staff;
	}

	public void setRelativeStaff(Staff relativeStaff) {
		this.relativeStaff = relativeStaff;
	}

	public Staff getRelativeStaff() {
		return relativeStaff;
	}

	public void setRelativeCacs(Set<Cac> relativeCacs) {
		this.relativeCacs = relativeCacs;
	}

	public Set<Cac> getRelativeCacs() {
		if (relativeCacs == null) {
			relativeCacs = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cac>();
		}
		return relativeCacs;
	}

	public void setRelativeLedbuildings(Set<Ledbuilding> relativeLedbuildings) {
		this.relativeLedbuildings = relativeLedbuildings;
	}

	public Set<Ledbuilding> getRelativeLedbuildings() {
		if (relativeLedbuildings == null) {
			relativeLedbuildings = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Ledbuilding>();
		}
		return relativeLedbuildings;
	}

	public void setRelativeWhbuildings(Set<Whbuilding> relativeWhbuildings) {
		this.relativeWhbuildings = relativeWhbuildings;
	}

	public Set<Whbuilding> getRelativeWhbuildings() {
		if (relativeWhbuildings == null) {
			relativeWhbuildings = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whbuilding>();
		}
		return relativeWhbuildings;
	}

	public void copy(Project that) {
		setId(that.getId());
		setName(that.getName());
		setType(that.getType());
		setStatus(that.getStatus());
		setProvince(that.getProvince());
		setCity(that.getCity());
		setArea(that.getArea());
		setAddress(that.getAddress());
		setDetail(that.getDetail());
		setRemark(that.getRemark());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("province=[").append(province).append("] ");
		buffer.append("city=[").append(city).append("] ");
		buffer.append("area=[").append(area).append("] ");
		buffer.append("address=[").append(address).append("] ");
		buffer.append("detail=[").append(detail).append("] ");
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
		if (!(obj instanceof Project))
			return false;
		Project equalCheck = (Project) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}