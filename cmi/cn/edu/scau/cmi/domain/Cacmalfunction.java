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
		@NamedQuery(name = "findAllCacdeviceCacmalfunctions", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.cacdevice = ?1"),
		@NamedQuery(name = "findAllCacrecordtimeCacmalfunctions", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.cacrecordtime = ?1"),
		@NamedQuery(name = "findAllCacmalfunctions", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction"),
		@NamedQuery(name = "findCacmalfunctionByCacrecordtime", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.cacrecordtime = ?1"),
		@NamedQuery(name = "findCacmalfunctionByCacdevice", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.cacdevice = ?1"),
		@NamedQuery(name = "findCacmalfunctionByStatus", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.status = ?1"),
		@NamedQuery(name = "findCacmalfunctionByPrimaryKey", query = "select myCacmalfunction from Cacmalfunction myCacmalfunction where myCacmalfunction.cacrecordtime = ?1 and myCacmalfunction.cacdevice = ?2") })
@Table(catalog = "energydevice", name = "cacmalfunction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacmalfunction")
public class Cacmalfunction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "cacrecordtime", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacrecordtime;

	@Column(name = "cacdevice", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@XmlElement
	Integer cacdevice;

	@Column(name = "status")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacdevice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacdevice relativeCacdevice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({ @JoinColumn(name = "cacrecordtime", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) })
	@XmlTransient
	Cacrecordtime relativeCacrecordtime;

	public void setCacrecordtime(Integer cacrecordtime) {
		this.cacrecordtime = cacrecordtime;
	}

	public Integer getCacrecordtime() {
		return this.cacrecordtime;
	}

	public void setCacdevice(Integer cacdevice) {
		this.cacdevice = cacdevice;
	}

	public Integer getCacdevice() {
		return this.cacdevice;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
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

	public void copy(Cacmalfunction that) {
		setCacrecordtime(that.getCacrecordtime());
		setCacdevice(that.getCacdevice());
		setStatus(that.getStatus());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("cacrecordtime=[").append(cacrecordtime).append("] ");
		buffer.append("cacdevice=[").append(cacdevice).append("] ");
		buffer.append("status=[").append(status).append("] ");

		return buffer.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((cacrecordtime == null) ? 0
				: cacrecordtime.hashCode()));
		result = (int) (prime * result + ((cacdevice == null) ? 0 : cacdevice
				.hashCode()));
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Cacmalfunction))
			return false;
		Cacmalfunction equalCheck = (Cacmalfunction) obj;
		if ((cacrecordtime == null && equalCheck.cacrecordtime != null)
				|| (cacrecordtime != null && equalCheck.cacrecordtime == null))
			return false;
		if ((cacdevice == null && equalCheck.cacdevice != null)
				|| (cacdevice != null && equalCheck.cacdevice == null))
			return false;
		if (cacrecordtime != null
				&& !cacrecordtime.equals(equalCheck.cacrecordtime))
			return false;
		if (cacdevice != null && !cacdevice.equals(equalCheck.cacdevice))
			return false;
		return true;
	}
}