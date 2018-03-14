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
		@NamedQuery(name = "findAllStaffCompanys", query = "select myCompany from Company myCompany where myCompany.staff = ?1"),
		@NamedQuery(name = "findAllCompanys", query = "select myCompany from Company myCompany"),
		@NamedQuery(name = "findCompanyById", query = "select myCompany from Company myCompany where myCompany.id = ?1"),
		@NamedQuery(name = "findCompanyByName", query = "select myCompany from Company myCompany where myCompany.name = ?1"),
		@NamedQuery(name = "findCompanyByNameContaining", query = "select myCompany from Company myCompany where myCompany.name like ?1"),
		@NamedQuery(name = "findCompanyByPhone", query = "select myCompany from Company myCompany where myCompany.phone = ?1"),
		@NamedQuery(name = "findCompanyByPhoneContaining", query = "select myCompany from Company myCompany where myCompany.phone like ?1"),
		@NamedQuery(name = "findCompanyByFax", query = "select myCompany from Company myCompany where myCompany.fax = ?1"),
		@NamedQuery(name = "findCompanyByFaxContaining", query = "select myCompany from Company myCompany where myCompany.fax like ?1"),
		@NamedQuery(name = "findCompanyByPostcode", query = "select myCompany from Company myCompany where myCompany.postcode = ?1"),
		@NamedQuery(name = "findCompanyByAddress", query = "select myCompany from Company myCompany where myCompany.address = ?1"),
		@NamedQuery(name = "findCompanyByAddressContaining", query = "select myCompany from Company myCompany where myCompany.address like ?1"),
		@NamedQuery(name = "findCompanyByWebsite", query = "select myCompany from Company myCompany where myCompany.website = ?1"),
		@NamedQuery(name = "findCompanyByWebsiteContaining", query = "select myCompany from Company myCompany where myCompany.website like ?1"),
		@NamedQuery(name = "findCompanyByPersonduty", query = "select myCompany from Company myCompany where myCompany.personduty = ?1"),
		@NamedQuery(name = "findCompanyByPersondutyContaining", query = "select myCompany from Company myCompany where myCompany.personduty like ?1"),
		@NamedQuery(name = "findCompanyByDetail", query = "select myCompany from Company myCompany where myCompany.detail = ?1"),
		@NamedQuery(name = "findCompanyByRemark", query = "select myCompany from Company myCompany where myCompany.remark = ?1"),
		@NamedQuery(name = "findCompanyByPrimaryKey", query = "select myCompany from Company myCompany where myCompany.id = ?1") })
@Table(catalog = "energydevice", name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Company")
public class Company implements Serializable {

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

	@Column(name = "phone")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String phone;

	@Column(name = "fax")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String fax;

	@Column(name = "postcode")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String postcode;

	@Column(name = "address")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String address;

	@Column(name = "website")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String website;

	@Column(name = "personduty")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String personduty;

	@Column(name = "detail")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String detail;

	@Column(name = "remark")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String remark;

	@Column(name = "representative")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer staff;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "representative", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Staff relativeStaff;

	@OneToMany(mappedBy = "relativeCompany", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Project> relativeProjects;

	@OneToMany(mappedBy = "relativeCompany", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Staff> relativeStaffs;

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

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setPersonduty(String personduty) {
		this.personduty = personduty;
	}

	public String getPersonduty() {
		return this.personduty;
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

	public void setRelativeProjects(Set<Project> relativeProjects) {
		this.relativeProjects = relativeProjects;
	}

	public Set<Project> getRelativeProjects() {
		if (relativeProjects == null) {
			relativeProjects = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Project>();
		}
		return relativeProjects;
	}

	public void setRelativeStaffs(Set<Staff> relativeStaffs) {
		this.relativeStaffs = relativeStaffs;
	}

	public Set<Staff> getRelativeStaffs() {
		if (relativeStaffs == null) {
			relativeStaffs = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Staff>();
		}
		return relativeStaffs;
	}

	public void copy(Company that) {
		setId(that.getId());
		setName(that.getName());
		setPhone(that.getPhone());
		setFax(that.getFax());
		setPostcode(that.getPostcode());
		setAddress(that.getAddress());
		setWebsite(that.getWebsite());
		setPersonduty(that.getPersonduty());
		setDetail(that.getDetail());
		setRemark(that.getRemark());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("phone=[").append(phone).append("] ");
		buffer.append("fax=[").append(fax).append("] ");
		buffer.append("postcode=[").append(postcode).append("] ");
		buffer.append("address=[").append(address).append("] ");
		buffer.append("website=[").append(website).append("] ");
		buffer.append("personduty=[").append(personduty).append("] ");
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
		if (!(obj instanceof Company))
			return false;
		Company equalCheck = (Company) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}