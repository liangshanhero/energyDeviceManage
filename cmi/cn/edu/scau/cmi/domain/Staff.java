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
		@NamedQuery(name = "findAllCompanyStaffs", query = "select myStaff from Staff myStaff where myStaff.company = ?1"),
		@NamedQuery(name = "findAllStaffs", query = "select myStaff from Staff myStaff"),
		@NamedQuery(name = "findStaffById", query = "select myStaff from Staff myStaff where myStaff.id = ?1"),
		@NamedQuery(name = "findStaffByName", query = "select myStaff from Staff myStaff where myStaff.name = ?1"),
		@NamedQuery(name = "findStaffByNameContaining", query = "select myStaff from Staff myStaff where myStaff.name like ?1"),
		@NamedQuery(name = "findStaffByDuty", query = "select myStaff from Staff myStaff where myStaff.duty = ?1"),
		@NamedQuery(name = "findStaffByDutyContaining", query = "select myStaff from Staff myStaff where myStaff.duty like ?1"),
		@NamedQuery(name = "findStaffByToken", query = "select myStaff from Staff myStaff where myStaff.token = ?1"),
		@NamedQuery(name = "findStaffByTokenContaining", query = "select myStaff from Staff myStaff where myStaff.token like ?1"),
		@NamedQuery(name = "findStaffByType", query = "select myStaff from Staff myStaff where myStaff.type = ?1"),
		@NamedQuery(name = "findStaffByTypeContaining", query = "select myStaff from Staff myStaff where myStaff.type like ?1"),
		@NamedQuery(name = "findStaffByStatus", query = "select myStaff from Staff myStaff where myStaff.status = ?1"),
		@NamedQuery(name = "findStaffByStatusContaining", query = "select myStaff from Staff myStaff where myStaff.status like ?1"),
		@NamedQuery(name = "findStaffByLevel", query = "select myStaff from Staff myStaff where myStaff.level = ?1"),
		@NamedQuery(name = "findStaffByLevelContaining", query = "select myStaff from Staff myStaff where myStaff.level like ?1"),
		@NamedQuery(name = "findStaffByLoginname", query = "select myStaff from Staff myStaff where myStaff.loginname = ?1"),
		@NamedQuery(name = "findStaffByLoginnameContaining", query = "select myStaff from Staff myStaff where myStaff.loginname like ?1"),
		@NamedQuery(name = "findStaffByPassword", query = "select myStaff from Staff myStaff where myStaff.password = ?1"),
		@NamedQuery(name = "findStaffByPasswordContaining", query = "select myStaff from Staff myStaff where myStaff.password like ?1"),
		@NamedQuery(name = "findStaffByRemark", query = "select myStaff from Staff myStaff where myStaff.remark = ?1"),
		@NamedQuery(name = "findStaffByRemarkContaining", query = "select myStaff from Staff myStaff where myStaff.remark like ?1"),
		@NamedQuery(name = "findStaffByPrimaryKey", query = "select myStaff from Staff myStaff where myStaff.id = ?1") })
@Table(catalog = "energydevice", name = "staff")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Staff")
public class Staff implements Serializable {

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

	@Column(name = "duty")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String duty;

	@Column(name = "token")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String token;

	@Column(name = "type")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String type;

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String status;

	@Column(name = "level")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String level;

	@Column(name = "loginname")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String loginname;

	@Column(name = "password")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String password;

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

	@OneToMany(mappedBy = "relativeStaff", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Company> relativeCompanys;

	@OneToMany(mappedBy = "relativeStaff", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Project> relativeProjects;

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

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
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

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
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

	public void setRelativeCompanys(Set<Company> relativeCompanys) {
		this.relativeCompanys = relativeCompanys;
	}

	public Set<Company> getRelativeCompanys() {
		if (relativeCompanys == null) {
			relativeCompanys = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Company>();
		}
		return relativeCompanys;
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

	public void copy(Staff that) {
		setId(that.getId());
		setName(that.getName());
		setDuty(that.getDuty());
		setToken(that.getToken());
		setType(that.getType());
		setStatus(that.getStatus());
		setLevel(that.getLevel());
		setLoginname(that.getLoginname());
		setPassword(that.getPassword());
		setRemark(that.getRemark());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("name=[").append(name).append("] ");
		buffer.append("duty=[").append(duty).append("] ");
		buffer.append("token=[").append(token).append("] ");
		buffer.append("type=[").append(type).append("] ");
		buffer.append("status=[").append(status).append("] ");
		buffer.append("level=[").append(level).append("] ");
		buffer.append("loginname=[").append(loginname).append("] ");
		buffer.append("password=[").append(password).append("] ");
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
		if (!(obj instanceof Staff))
			return false;
		Staff equalCheck = (Staff) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}