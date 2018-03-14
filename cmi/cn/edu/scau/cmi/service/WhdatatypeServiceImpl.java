package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhdatatypeDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhdatatypeService")
@Transactional
public class WhdatatypeServiceImpl implements WhdatatypeService {
	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdatatypeServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhdatatype(Whdatatype whdatatype) {
		Whdatatype existingWhdatatype = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(whdatatype.getId());
		if (existingWhdatatype != null) {
			if (existingWhdatatype != whdatatype) {
				existingWhdatatype.setName(whdatatype.getName());
			}

			if (whdatatype.getRelativeWhdevices() != null) {
				for (Whdevice relativeWhdevice : whdatatype
						.getRelativeWhdevices())
					entityManager.persist(relativeWhdevice);
				existingWhdatatype.setRelativeWhdevices(whdatatype
						.getRelativeWhdevices());
			} else
				existingWhdatatype.setRelativeWhdevices(null);
			if (whdatatype.getRelativeWhdevicedatas() != null) {
				for (Whdevicedata relativeWhdevicedata : whdatatype
						.getRelativeWhdevicedatas()) {
					relativeWhdevicedata
							.setRelativeWhdatatype(existingWhdatatype);
					entityManager.persist(relativeWhdevicedata);
				}
				existingWhdatatype.setRelativeWhdevicedatas(whdatatype
						.getRelativeWhdevicedatas());
			} else
				existingWhdatatype.setRelativeWhdevicedatas(null);
			entityManager.persist(existingWhdatatype);
		} else {
			entityManager.persist(whdatatype);
			whdatatype = whdatatypeDAO.store(whdatatype);
		}
		whdatatypeDAO.flush();
	}

	@Transactional
	public Whdatatype saveWhdatatypeWhdevices(Integer id,
			Whdevice related_whdevices) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(id,
				-1, -1);
		Whdevice existingWhdevices = whdeviceDAO
				.findWhdeviceByPrimaryKey(related_whdevices.getId());
		if (existingWhdevices != null) {
			existingWhdevices.setId(related_whdevices.getId());
			existingWhdevices.setNumber(related_whdevices.getNumber());
			related_whdevices = existingWhdevices;
			entityManager.persist(related_whdevices);
		} else {
			entityManager.persist(related_whdevices);
			whdeviceDAO.flush();
		}

		entityManager.persist(whdatatype);
		whdatatype.getRelativeWhdevices().add(related_whdevices);
		entityManager.persist(related_whdevices);
		whdeviceDAO.flush();

		return whdatatype;
	}

	@Transactional
	public Whdatatype saveWhdatatypeWhdevicedatas(Integer id,
			Whdevicedata related_whdevicedatas) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(id,
				-1, -1);
		Whdevicedata existingWhdevicedatas = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(related_whdevicedatas.getId());
		if (existingWhdevicedatas != null) {
			existingWhdevicedatas.setId(related_whdevicedatas.getId());
			existingWhdevicedatas.setTime(related_whdevicedatas.getTime());
			existingWhdevicedatas.setValue(related_whdevicedatas.getValue());
			existingWhdevicedatas.setIsupdate(related_whdevicedatas
					.getIsupdate());
			existingWhdevicedatas.setIsio(related_whdevicedatas.getIsio());
			related_whdevicedatas = existingWhdevicedatas;
			entityManager.persist(related_whdevicedatas);
		} else {
			entityManager.persist(related_whdevicedatas);
			whdevicedataDAO.flush();
		}
		entityManager.persist(whdatatype);
		related_whdevicedatas.setRelativeWhdatatype(whdatatype);
		whdatatype.getRelativeWhdevicedatas().add(related_whdevicedatas);
		entityManager.persist(whdatatype);
		entityManager.persist(related_whdevicedatas);
		whdatatypeDAO.flush();

		return whdatatype;
	}

	@Transactional
	public void deleteWhdatatype(Whdatatype whdatatype) {

		whdatatype.setRelativeWhdevices(null);
		Set<Whdevicedata> whdevicedataSet = whdatatype
				.getRelativeWhdevicedatas();
		whdatatype.setRelativeWhdevicedatas(null);
		entityManager.persist(whdatatype);
		whdatatypeDAO.remove(whdatatype);
		whdatatypeDAO.flush();
	}

	@Transactional
	public Whdatatype deleteWhdatatypeWhdevices(Integer whdatatype_id,
			Integer related_whdevices_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatype_id, -1, -1);
		Whdevice related_whdevices = whdeviceDAO.findWhdeviceByPrimaryKey(
				related_whdevices_id, -1, -1);
		whdatatype.getRelativeWhdevices().remove(related_whdevices);
		related_whdevices.getWhdatatypes().remove(whdatatype);
		whdatatype = whdatatypeDAO.store(whdatatype);
		whdatatypeDAO.flush();

		related_whdevices = whdeviceDAO.store(related_whdevices);
		whdeviceDAO.flush();
		whdeviceDAO.flush();
		return whdatatype;
	}

	@Transactional
	public Whdatatype deleteWhdatatypeWhdevicedatas(Integer whdatatype_id,
			Integer related_whdevicedatas_id) {
		Whdevicedata related_whdevicedatas = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(related_whdevicedatas_id, -1, -1);
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatype_id, -1, -1);
		Set<Whdevicedata> whdevicedataSet = whdatatype
				.getRelativeWhdevicedatas();
		Whdevicedata teache = new Whdevicedata();
		if (whdevicedataSet.size() > 0)
			for (Whdevicedata th : whdevicedataSet) {
				if (th == related_whdevicedatas) {
					teache = related_whdevicedatas;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeWhdatatype(null);
			whdatatype.getRelativeWhdevicedatas().remove(teache);
		}

		entityManager.persist(related_whdevicedatas);
		entityManager.persist(whdatatype);
		whdatatypeDAO.flush();
		return whdatatype;
	}

	@Transactional
	public Set<Whdatatype> loadWhdatatypes() {
		return whdatatypeDAO.findAllWhdatatypes();

	}

	@Transactional
	public Set<Whdatatype> loadWhdatatypes(int index, int size) {
		return whdatatypeDAO.findAllWhdatatypes(index, size);
	}

	@Transactional
	public List<Whdatatype> findAllWhdatatypes(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Whdatatype>(
				whdatatypeDAO.findAllWhdatatypes(startResult, maxRows));
	}

	@Transactional
	public Whdatatype findWhdatatypeByPrimaryKey(Integer id

	) {
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhdatatypes() {
		return ((Long) whdatatypeDAO.createQuerySingleResult(
				"select count(o) from Whdatatype o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countWhdatatypeWhdevices(Integer whdatatype) {
		String sql = "select count(*) from Whdatatype2whdevice where whdatatype="
				+ whdatatype;
		return ((Long) whdatatypeDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}
}
