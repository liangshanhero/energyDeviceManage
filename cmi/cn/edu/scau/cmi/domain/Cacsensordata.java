package cn.edu.scau.cmi.domain;

import java.io.Serializable;

import java.lang.StringBuilder;

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
		@NamedQuery(name = "findAllCacrecordtimeCacsensordatas", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.cacrecordtime = ?1"),
		@NamedQuery(name = "findAllCacsensorCacsensordatas", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.cacsensor = ?1"),
		@NamedQuery(name = "findAllCacsensordatas", query = "select myCacsensordata from Cacsensordata myCacsensordata"),
		@NamedQuery(name = "findCacsensordataByCacrecordtime", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.cacrecordtime = ?1"),
		@NamedQuery(name = "findCacsensordataByCacsensor", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.cacsensor = ?1"),
		@NamedQuery(name = "findCacsensordataByValue", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.value = ?1"),
		@NamedQuery(name = "findCacsensordataByValueContaining", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.value like ?1"),
		@NamedQuery(name = "findCacsensordataByIsreport", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.isreport = ?1"),
		@NamedQuery(name = "findCacsensordataByPrimaryKey", query = "select myCacsensordata from Cacsensordata myCacsensordata where myCacsensordata.cacrecordtime = ?1 and myCacsensordata.cacsensor = ?2") })
@Table(catalog = "energydevice", name = "cacsensordata")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacsensordata")
public class Cacsensordata implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cacrecordtime", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacrecordtime;

	@Column(name = "cacsensor", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacsensor;

	@Column(name = "value")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String value;

	@Column(name = "isreport")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer isreport;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacrecordtime", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacrecordtime relativeCacrecordtime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacsensor", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacsensor relativeCacsensor;

	public void setCacrecordtime(Integer cacrecordtime) {
		this.cacrecordtime = cacrecordtime;
	}

	public Integer getCacrecordtime() {
		return this.cacrecordtime;
	}

	public void setCacsensor(Integer cacsensor) {
		this.cacsensor = cacsensor;
	}

	public Integer getCacsensor() {
		return this.cacsensor;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setIsreport(Integer isreport) {
		this.isreport = isreport;
	}

	public Integer getIsreport() {
		return this.isreport;
	}

	public void setRelativeCacrecordtime(Cacrecordtime relativeCacrecordtime) {
		this.relativeCacrecordtime = relativeCacrecordtime;
	}

	public Cacrecordtime getRelativeCacrecordtime() {
		return relativeCacrecordtime;
	}

	public void setRelativeCacsensor(Cacsensor relativeCacsensor) {
		this.relativeCacsensor = relativeCacsensor;
	}

	public Cacsensor getRelativeCacsensor() {
		return relativeCacsensor;
	}

	public void copy(Cacsensordata that) {
		setCacrecordtime(that.getCacrecordtime());
		setCacsensor(that.getCacsensor());
		setValue(that.getValue());
		setIsreport(that.getIsreport());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("cacrecordtime=[").append(cacrecordtime).append("] ");
		buffer.append("cacsensor=[").append(cacsensor).append("] ");
		buffer.append("value=[").append(value).append("] ");
		buffer.append("isreport=[").append(isreport).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cacrecordtime == null) ? 0
				: cacrecordtime.hashCode()));
		result = (int) (prime * result + ((cacsensor == null) ? 0 : cacsensor
				.hashCode()));
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Cacsensordata))
			return false;
		Cacsensordata equalCheck = (Cacsensordata) obj;
		if ((cacrecordtime == null && equalCheck.cacrecordtime != null)
				|| (cacrecordtime != null && equalCheck.cacrecordtime == null))
			return false;
		if ((cacsensor == null && equalCheck.cacsensor != null)
				|| (cacsensor != null && equalCheck.cacsensor == null))
			return false;
		if (cacrecordtime != null
				&& !cacrecordtime.equals(equalCheck.cacrecordtime))
			return false;
		if (cacsensor != null && !cacsensor.equals(equalCheck.cacsensor))
			return false;
		return true;
	}
}