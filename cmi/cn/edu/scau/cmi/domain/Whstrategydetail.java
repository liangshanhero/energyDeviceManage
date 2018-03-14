package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

import java.util.Date;

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
		@NamedQuery(name = "findAllWhstrategyWhstrategydetails", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.whstrategyId = ?1"),
		@NamedQuery(name = "findAllWhstrategytypeWhstrategydetails", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.whstrategytypeId = ?1"),
		@NamedQuery(name = "findAllWhstrategydetails", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail"),
		@NamedQuery(name = "findWhstrategydetailById", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.id = ?1"),
		@NamedQuery(name = "findWhstrategydetailByMax", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.max = ?1"),
		@NamedQuery(name = "findWhstrategydetailByMin", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.min = ?1"),
		@NamedQuery(name = "findWhstrategydetailByTime", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.time = ?1"),
		@NamedQuery(name = "findWhstrategydetailByPrimaryKey", query = "select myWhstrategydetail from Whstrategydetail myWhstrategydetail where myWhstrategydetail.id = ?1") })
@Table(catalog = "energydevice", name = "whstrategydetail")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whstrategydetail")

public class Whstrategydetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Column(name = "max", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal max;

	@Column(name = "min", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal min;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date time;

	@Column(name = "whstrategy")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whstrategyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "whstrategy", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whstrategy whstrategy;

	@Column(name = "whstrategytype")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer whstrategytypeId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "whstrategytype", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Whstrategytype whstrategytype;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setMax(BigDecimal max) {
		this.max = max;
	}

	public BigDecimal getMax() {
		return this.max;
	}

	public void setMin(BigDecimal min) {
		this.min = min;
	}

	public BigDecimal getMin() {
		return this.min;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getTime() {
		return this.time;
	}

	public Integer getWhstrategyId() {
		return whstrategyId;
	}

	public void setWhstrategyId(Integer whstrategyId) {
		this.whstrategyId = whstrategyId;
	}

	public void setWhstrategy(Whstrategy whstrategy) {
		this.whstrategy = whstrategy;
	}

	public Whstrategy getWhstrategy() {
		return whstrategy;
	}

	public Integer getWhstrategytypeId() {
		return whstrategytypeId;
	}

	public void setWhstrategytypeId(Integer whstrategytypeId) {
		this.whstrategytypeId = whstrategytypeId;
	}

	public void setWhstrategytype(Whstrategytype whstrategytype) {
		this.whstrategytype = whstrategytype;
	}

	public Whstrategytype getWhstrategytype() {
		return whstrategytype;
	}

	public void copy(Whstrategydetail that) {
		setId(that.getId());
		setMax(that.getMax());
		setMin(that.getMin());
		setTime(that.getTime());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("max=[").append(max).append("] ");
		buffer.append("min=[").append(min).append("] ");
		buffer.append("time=[").append(time).append("] ");

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
		if (!(obj instanceof Whstrategydetail))
			return false;
		Whstrategydetail equalCheck = (Whstrategydetail) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
