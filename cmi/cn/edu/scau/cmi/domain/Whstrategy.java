package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.util.Date;
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
		@NamedQuery(name = "findAllWhdeviceWhstrategys", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.whdeviceId = ?1"),
		@NamedQuery(name = "findAllWhstrategys", query = "select myWhstrategy from Whstrategy myWhstrategy"),
		@NamedQuery(name = "findWhstrategyById", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.id = ?1"),
		@NamedQuery(name = "findWhstrategyByEnable", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.enable = ?1"),
		@NamedQuery(name = "findWhstrategyByCreateDate", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.createDate = ?1"),
		@NamedQuery(name = "findWhstrategyByRemark", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.remark = ?1"),
		@NamedQuery(name = "findWhstrategyByName", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.name = ?1"),
		@NamedQuery(name = "findWhstrategyByNameContaining", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.name like ?1"),
		@NamedQuery(name = "findWhstrategyByPrimaryKey", query = "select myWhstrategy from Whstrategy myWhstrategy where myWhstrategy.id = ?1") })
@Table(catalog = "energydevice", name = "whstrategy")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whstrategy")

public class Whstrategy implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "enable")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Boolean enable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date createDate;

	@Column(name = "remark", columnDefinition = "BLOB")
	@Basic(fetch = FetchType.EAGER)
	@Lob
	@XmlElement
	byte[] remark;

	@Column(name = "name")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String name;

	@Column(name = "whdevice")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whdeviceId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whdevice whdevice;

	@OneToMany(mappedBy = "whstrategy", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whstrategydetail> whstrategydetailWhstrategys;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setRemark(byte[] remark) {
		this.remark = remark;
	}

	public byte[] getRemark() {
		return this.remark;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Integer getWhdeviceId() {
		return whdeviceId;
	}

	public void setWhdeviceId(Integer whdeviceId) {
		this.whdeviceId = whdeviceId;
	}

	public void setWhdevice(Whdevice whdevice) {
		this.whdevice = whdevice;
	}

	public Whdevice getWhdevice() {
		return whdevice;
	}

	public void setWhstrategydetails(Set<Whstrategydetail> whstrategydetails) {
		this.whstrategydetailWhstrategys = whstrategydetails;
	}

	public Set<Whstrategydetail> getWhstrategydetails() {
		if (whstrategydetailWhstrategys == null) {
			whstrategydetailWhstrategys = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whstrategydetail>();
		}
		return whstrategydetailWhstrategys;
	}

	public void copy(Whstrategy that) {
		setId(that.getId());
		setEnable(that.getEnable());
		setCreateDate(that.getCreateDate());
		setRemark(that.getRemark());
		setName(that.getName());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("enable=[").append(enable).append("] ");
		buffer.append("createDate=[").append(createDate).append("] ");
		buffer.append("remark=[").append(remark).append("] ");
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
		if (!(obj instanceof Whstrategy))
			return false;
		Whstrategy equalCheck = (Whstrategy) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
