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
		@NamedQuery(name = "findAllCacrecordtimes", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime"),
		@NamedQuery(name = "findCacrecordtimeById", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime where myCacrecordtime.id = ?1"),
		@NamedQuery(name = "findCacrecordtimeByRecordTime", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime where myCacrecordtime.recordTime = ?1"),
		@NamedQuery(name = "findCacrecordtimeByWatchkeeper", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime where myCacrecordtime.watchkeeper = ?1"),
		@NamedQuery(name = "findCacrecordtimeByWatchkeeperContaining", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime where myCacrecordtime.watchkeeper like ?1"),
		@NamedQuery(name = "findCacrecordtimeByPrimaryKey", query = "select myCacrecordtime from Cacrecordtime myCacrecordtime where myCacrecordtime.id = ?1") })
@Table(catalog = "energydevice", name = "cacrecordtime")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Cacrecordtime")
public class Cacrecordtime implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RecordTime")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Date recordTime;

	@Column(name = "Watchkeeper")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String watchkeeper;

	@OneToMany(mappedBy = "relativeCacrecordtime", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacdevicedata> relativeCacdevicedatas;

	@OneToMany(mappedBy = "relativeCacrecordtime", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacmalfunction> relativeCacmalfunctions;

	@OneToMany(mappedBy = "relativeCacrecordtime", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Cacsensordata> relativeCacsensordatas;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setRecordTime(Date RecordTime) {
		this.recordTime = RecordTime;
	}

	public Date getRecordTime() {
		return this.recordTime;
	}

	public void setWatchkeeper(String Watchkeeper) {
		this.watchkeeper = Watchkeeper;
	}

	public String getWatchkeeper() {
		return this.watchkeeper;
	}

	public void setRelativeCacdevicedatas(
			Set<Cacdevicedata> relativeCacdevicedatas) {
		this.relativeCacdevicedatas = relativeCacdevicedatas;
	}

	public Set<Cacdevicedata> getRelativeCacdevicedatas() {
		if (relativeCacdevicedatas == null) {
			relativeCacdevicedatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacdevicedata>();
		}
		return relativeCacdevicedatas;
	}

	public void setRelativeCacmalfunctions(
			Set<Cacmalfunction> relativeCacmalfunctions) {
		this.relativeCacmalfunctions = relativeCacmalfunctions;
	}

	public Set<Cacmalfunction> getRelativeCacmalfunctions() {
		if (relativeCacmalfunctions == null) {
			relativeCacmalfunctions = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacmalfunction>();
		}
		return relativeCacmalfunctions;
	}

	public void setRelativeCacsensordatas(
			Set<Cacsensordata> relativeCacsensordatas) {
		this.relativeCacsensordatas = relativeCacsensordatas;
	}

	public Set<Cacsensordata> getRelativeCacsensordatas() {
		if (relativeCacsensordatas == null) {
			relativeCacsensordatas = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Cacsensordata>();
		}
		return relativeCacsensordatas;
	}

	public void copy(Cacrecordtime that) {
		setId(that.getId());
		setRecordTime(that.getRecordTime());
		setWatchkeeper(that.getWatchkeeper());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
		buffer.append("RecordTime=[").append(recordTime).append("] ");
		buffer.append("Watchkeeper=[").append(watchkeeper).append("] ");

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
		if (!(obj instanceof Cacrecordtime))
			return false;
		Cacrecordtime equalCheck = (Cacrecordtime) obj;
		if ((id == null && equalCheck.id != null)
				|| (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}