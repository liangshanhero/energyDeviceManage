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
		@NamedQuery(name = "findAllWhstrategytypes", query = "select myWhstrategytype from Whstrategytype myWhstrategytype"),
		@NamedQuery(name = "findWhstrategytypeById", query = "select myWhstrategytype from Whstrategytype myWhstrategytype where myWhstrategytype.id = ?1"),
		@NamedQuery(name = "findWhstrategytypeByName", query = "select myWhstrategytype from Whstrategytype myWhstrategytype where myWhstrategytype.name = ?1"),
		@NamedQuery(name = "findWhstrategytypeByNameContaining", query = "select myWhstrategytype from Whstrategytype myWhstrategytype where myWhstrategytype.name like ?1"),
		@NamedQuery(name = "findWhstrategytypeByPrimaryKey", query = "select myWhstrategytype from Whstrategytype myWhstrategytype where myWhstrategytype.id = ?1") })
@Table(catalog = "energydevice", name = "whstrategytype")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "energyDeviceManage/cn.edu.scau.cmi/domain", name = "Whstrategytype")

public class Whstrategytype implements Serializable {

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

	@OneToMany(mappedBy = "whstrategytype", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@XmlElement(name = "", namespace = "")
	java.util.Set<cn.edu.scau.cmi.domain.Whstrategydetail> whstrategydetails;

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

	public void setWhstrategydetails(Set<Whstrategydetail> whstrategydetails) {
		this.whstrategydetails = whstrategydetails;
	}

	public Set<Whstrategydetail> getWhstrategydetails() {
		if (whstrategydetails == null) {
			whstrategydetails = new java.util.LinkedHashSet<cn.edu.scau.cmi.domain.Whstrategydetail>();
		}
		return whstrategydetails;
	}

	public void copy(Whstrategytype that) {
		setId(that.getId());
		setName(that.getName());
	}

	public String toString() {

		StringBuilder buffer = new StringBuilder();
		buffer.append("id=[").append(id).append("] ");
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
		if (!(obj instanceof Whstrategytype))
			return false;
		Whstrategytype equalCheck = (Whstrategytype) obj;
		if ((id == null && equalCheck.id != null) || (id != null && equalCheck.id == null))
			return false;
		if (id != null && !id.equals(equalCheck.id))
			return false;
		return true;
	}
}
