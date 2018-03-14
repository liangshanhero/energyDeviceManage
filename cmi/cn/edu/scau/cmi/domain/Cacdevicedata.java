package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

import java.math.BigDecimal;

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
		@NamedQuery(name = "findAllCacdeviceCacdevicedatas", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.cacdevice = ?1"),
		@NamedQuery(name = "findAllCacrecordtimeCacdevicedatas", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.cacrecordtime = ?1"),
		@NamedQuery(name = "findAllCacdevicedatas", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata"),
		@NamedQuery(name = "findCacdevicedataByCacdevice", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.cacdevice = ?1"),
		@NamedQuery(name = "findCacdevicedataByCacrecordtime", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.cacrecordtime = ?1"),
		@NamedQuery(name = "findCacdevicedataByValue", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.value = ?1"),
		@NamedQuery(name = "findCacdevicedataByIsreport", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.isreport = ?1"),
		@NamedQuery(name = "findCacdevicedataByPrimaryKey", query = "select myCacdevicedata from Cacdevicedata myCacdevicedata where myCacdevicedata.cacdevice = ?1 and myCacdevicedata.cacrecordtime = ?2") })
@Table(catalog = "energydevice", name = "cacdevicedata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacdevicedata")
public class Cacdevicedata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cacdevice", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacdevice;

	@Column(name = "cacrecordtime", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacrecordtime;

	@Column(name = "value", precision = 22)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	BigDecimal value;

	@Column(name = "isreport")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isreport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacdevice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacdevice relativeCacdevice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacrecordtime", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacrecordtime relativeCacrecordtime;

	public void setCacdevice(Integer cacdevice) {
		this.cacdevice = cacdevice;
	}

	public Integer getCacdevice() {
		return this.cacdevice;
	}

	public void setCacrecordtime(Integer cacrecordtime) {
		this.cacrecordtime = cacrecordtime;
	}

	public Integer getCacrecordtime() {
		return this.cacrecordtime;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public void setIsreport(Integer isreport) {
		this.isreport = isreport;
	}

	public Integer getIsreport() {
		return this.isreport;
	}

	public void setRelativeCacdevice(Cacdevice relativeCacdevice) {
		this.relativeCacdevice = relativeCacdevice;
	}

	public Cacdevice getRelativeCacdevice() {
		return relativeCacdevice;
	}

	public void setRelativeCacrecordtime(Cacrecordtime relativeCacrecordtime) {
		this.relativeCacrecordtime = relativeCacrecordtime;
	}

	public Cacrecordtime getRelativeCacrecordtime() {
		return relativeCacrecordtime;
	}

	public void copy(Cacdevicedata that) {
		setCacdevice(that.getCacdevice());
		setCacrecordtime(that.getCacrecordtime());
		setValue(that.getValue());
		setIsreport(that.getIsreport());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("cacdevice=[").append(cacdevice).append("] ");
		buffer.append("cacrecordtime=[").append(cacrecordtime).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("isreport=[").append(isreport).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cacdevice == null) ? 0 : cacdevice
				.hashCode()));
		result = (int) (prime * result + ((cacrecordtime == null) ? 0
				: cacrecordtime.hashCode()));
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Cacdevicedata))
			return false;
		Cacdevicedata equalCheck = (Cacdevicedata) obj;
		if ((cacdevice == null && equalCheck.cacdevice != null)
				|| (cacdevice != null && equalCheck.cacdevice == null))
			return false;
		if ((cacrecordtime == null && equalCheck.cacrecordtime != null)
				|| (cacrecordtime != null && equalCheck.cacrecordtime == null))
			return false;
		if (cacdevice != null && !cacdevice.equals(equalCheck.cacdevice))
			return false;
		if (cacrecordtime != null
				&& !cacrecordtime.equals(equalCheck.cacrecordtime))
			return false;
		return true;
	}
}