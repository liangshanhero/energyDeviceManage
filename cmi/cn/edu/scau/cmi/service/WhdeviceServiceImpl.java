package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhdeviceDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhdeviceService")
@Transactional
public class WhdeviceServiceImpl implements WhdeviceService {
	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhbuildingService whbuildingService;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdeviceServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhdevice(Whdevice whdevice) {
		Whdevice existingWhdevice = whdeviceDAO
				.findWhdeviceByPrimaryKey(whdevice.getId());
		if (existingWhdevice != null) {
			if (existingWhdevice != whdevice) {
				existingWhdevice.setNumber(whdevice.getNumber());
			}

			if (whdevice.getWhbuilding() != null) {
				entityManager.persist(whdevice.getWhbuilding());
				existingWhdevice.setWhbuilding(whdevice
						.getWhbuilding());
			} else
				existingWhdevice.setWhbuilding(null);

			if (whdevice.getWhdatatypes() != null) {
				for (Whdatatype relativeWhdatatype : whdevice
						.getWhdatatypes())
					entityManager.persist(relativeWhdatatype);
				existingWhdevice.setWhdatatypes(whdevice
						.getWhdatatypes());
			} else
				existingWhdevice.setWhdatatypes(null);
			if (whdevice.getWhdevicedatas() != null) {
				for (Whdevicedata relativeWhdevicedata : whdevice
						.getWhdevicedatas()) {
					relativeWhdevicedata.setRelativeWhdevice(existingWhdevice);
					entityManager.persist(relativeWhdevicedata);
				}
				existingWhdevice.setWhdevicedatas(whdevice
						.getWhdevicedatas());
			} else
				existingWhdevice.setWhdevicedatas(null);
			entityManager.persist(existingWhdevice);
		} else {
			entityManager.persist(whdevice);
			whdevice = whdeviceDAO.store(whdevice);
		}
		whdeviceDAO.flush();
	}

	@Transactional
	public Whdevice saveWhdeviceWhbuilding(Integer id,
			Whbuilding related_whbuilding) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(id, -1, -1);
		Whbuilding existingWhbuilding = whbuildingDAO
				.findWhbuildingByPrimaryKey(related_whbuilding.getId());
		if (existingWhbuilding != null) {
			existingWhbuilding.setId(related_whbuilding.getId());
			existingWhbuilding.setName(related_whbuilding.getName());
			related_whbuilding = existingWhbuilding;
		} else {
			related_whbuilding = whbuildingDAO.store(related_whbuilding);
			whbuildingDAO.flush();
		}
		whdevice.setWhbuilding(related_whbuilding);
		related_whbuilding.getRelativeWhdevices().add(whdevice);
		whdevice = whdeviceDAO.store(whdevice);
		whdeviceDAO.flush();

		related_whbuilding = whbuildingDAO.store(related_whbuilding);
		whbuildingDAO.flush();

		return whdevice;
	}

	@Transactional
	public Whdevice saveWhdeviceWhdatatypes(Integer id,
			Whdatatype related_whdatatypes) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(id, -1, -1);
		Whdatatype existingWhdatatypes = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(related_whdatatypes.getId());
		if (existingWhdatatypes != null) {
			existingWhdatatypes.setId(related_whdatatypes.getId());
			existingWhdatatypes.setName(related_whdatatypes.getName());
			related_whdatatypes = existingWhdatatypes;
			entityManager.persist(related_whdatatypes);
		} else {
			entityManager.persist(related_whdatatypes);
			whdatatypeDAO.flush();
		}

		entityManager.persist(whdevice);
		whdevice.getWhdatatypes().add(related_whdatatypes);
		entityManager.persist(related_whdatatypes);
		whdatatypeDAO.flush();

		return whdevice;
	}

	@Transactional
	public Whdevice saveWhdeviceWhdevicedatas(Integer id,
			Whdevicedata related_whdevicedatas) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(id, -1, -1);
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
		entityManager.persist(whdevice);
		related_whdevicedatas.setRelativeWhdevice(whdevice);
		whdevice.getWhdevicedatas().add(related_whdevicedatas);
		entityManager.persist(whdevice);
		entityManager.persist(related_whdevicedatas);
		whdeviceDAO.flush();

		return whdevice;
	}

	@Transactional
	public void deleteWhdevice(Whdevice whdevice) {

		whdevice.setWhbuilding(null);

		whdevice.setWhdatatypes(null);
		Set<Whdevicedata> whdevicedataSet = whdevice.getWhdevicedatas();
		whdevice.setWhdevicedatas(null);
		entityManager.persist(whdevice);
		whdeviceDAO.remove(whdevice);
		whdeviceDAO.flush();
	}

	@Transactional
	public Whdevice deleteWhdeviceWhbuilding(Integer whdevice_id,
			Integer related_whbuilding_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		Whbuilding related_whbuilding = whbuildingDAO
				.findWhbuildingByPrimaryKey(related_whbuilding_id, -1, -1);
		whdevice.setWhbuilding(null);
		related_whbuilding.getRelativeWhdevices().remove(whdevice);
		whdevice = whdeviceDAO.store(whdevice);
		whdeviceDAO.flush();
		related_whbuilding = whbuildingDAO.store(related_whbuilding);

		whbuildingDAO.flush();
		return whdevice;
	}

	@Transactional
	public Whdevice deleteWhdeviceWhdatatypes(Integer whdevice_id,
			Integer related_whdatatypes_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		Whdatatype related_whdatatypes = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(related_whdatatypes_id, -1, -1);
		whdevice.getWhdatatypes().remove(related_whdatatypes);
		related_whdatatypes.getRelativeWhdevices().remove(whdevice);
		whdevice = whdeviceDAO.store(whdevice);
		whdeviceDAO.flush();

		related_whdatatypes = whdatatypeDAO.store(related_whdatatypes);
		whdatatypeDAO.flush();
		whdatatypeDAO.flush();
		return whdevice;
	}

	@Transactional
	public Whdevice deleteWhdeviceWhdevicedatas(Integer whdevice_id,
			Integer related_whdevicedatas_id) {
		Whdevicedata related_whdevicedatas = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(related_whdevicedatas_id, -1, -1);
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		Set<Whdevicedata> whdevicedataSet = whdevice.getWhdevicedatas();
		Whdevicedata teache = new Whdevicedata();
		if (whdevicedataSet.size() > 0)
			for (Whdevicedata th : whdevicedataSet) {
				if (th == related_whdevicedatas) {
					teache = related_whdevicedatas;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeWhdevice(null);
			whdevice.getWhdevicedatas().remove(teache);
		}

		entityManager.persist(related_whdevicedatas);
		entityManager.persist(whdevice);
		whdeviceDAO.flush();
		return whdevice;
	}

	@Transactional
	public Set<Whdevice> loadWhdevices() {
		return whdeviceDAO.findAllWhdevices();

	}

	@Transactional
	public Set<Whdevice> loadWhdevices(int index, int size) {
		return whdeviceDAO.findAllWhdevices(index, size);
	}

	@Transactional
	public List<Whdevice> findAllWhdevices(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Whdevice>(whdeviceDAO.findAllWhdevices(
				startResult, maxRows));
	}

	@Transactional
	public Whdevice findWhdeviceByPrimaryKey(Integer id

	) {
		return whdeviceDAO.findWhdeviceByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhdevices() {
		return ((Long) whdeviceDAO.createQuerySingleResult(
				"select count(o) from Whdevice o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeWhbuildingWhdevices(Integer whbuilding) {
		String sql = "select count(*) from Whdevice where whbuilding="
				+ whbuilding;
		return ((Long) whdeviceDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countWhdeviceWhdatatypes(Integer whdevice) {
		String sql = "select count(*) from Whdatatype2whdevice where whdevice="
				+ whdevice;
		return ((Long) whdeviceDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}
}
