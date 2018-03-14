package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhdevicedataDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhdevicedataService")
@Transactional
public class WhdevicedataServiceImpl implements WhdevicedataService {
	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdatatypeService whdatatypeService;
	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdevicedataServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhdevicedata(Whdevicedata whdevicedata) {
		Whdevicedata existingWhdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedata.getId());
		if (existingWhdevicedata != null) {
			if (existingWhdevicedata != whdevicedata) {
				existingWhdevicedata.setTime(whdevicedata.getTime());
				existingWhdevicedata.setValue(whdevicedata.getValue());
				existingWhdevicedata.setIsupdate(whdevicedata.getIsupdate());
				existingWhdevicedata.setIsio(whdevicedata.getIsio());
			}

			if (whdevicedata.getRelativeWhdatatype() != null) {
				entityManager.persist(whdevicedata.getRelativeWhdatatype());
				existingWhdevicedata.setRelativeWhdatatype(whdevicedata
						.getRelativeWhdatatype());
			} else
				existingWhdevicedata.setRelativeWhdatatype(null);
			if (whdevicedata.getRelativeWhdevice() != null) {
				entityManager.persist(whdevicedata.getRelativeWhdevice());
				existingWhdevicedata.setRelativeWhdevice(whdevicedata
						.getRelativeWhdevice());
			} else
				existingWhdevicedata.setRelativeWhdevice(null);

			entityManager.persist(existingWhdevicedata);
		} else {
			entityManager.persist(whdevicedata);
			whdevicedata = whdevicedataDAO.store(whdevicedata);
		}
		whdevicedataDAO.flush();
	}

	@Transactional
	public Whdevicedata saveWhdevicedataWhdatatype(Integer id,
			Whdatatype related_whdatatype) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(id, -1, -1);
		Whdatatype existingWhdatatype = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(related_whdatatype.getId());
		if (existingWhdatatype != null) {
			existingWhdatatype.setId(related_whdatatype.getId());
			existingWhdatatype.setName(related_whdatatype.getName());
			related_whdatatype = existingWhdatatype;
		} else {
			related_whdatatype = whdatatypeDAO.store(related_whdatatype);
			whdatatypeDAO.flush();
		}
		whdevicedata.setRelativeWhdatatype(related_whdatatype);
		related_whdatatype.getRelativeWhdevicedatas().add(whdevicedata);
		whdevicedata = whdevicedataDAO.store(whdevicedata);
		whdevicedataDAO.flush();

		related_whdatatype = whdatatypeDAO.store(related_whdatatype);
		whdatatypeDAO.flush();

		return whdevicedata;
	}

	@Transactional
	public Whdevicedata saveWhdevicedataWhdevice(Integer id,
			Whdevice related_whdevice) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(id, -1, -1);
		Whdevice existingWhdevice = whdeviceDAO
				.findWhdeviceByPrimaryKey(related_whdevice.getId());
		if (existingWhdevice != null) {
			existingWhdevice.setId(related_whdevice.getId());
			existingWhdevice.setNumber(related_whdevice.getNumber());
			related_whdevice = existingWhdevice;
		} else {
			related_whdevice = whdeviceDAO.store(related_whdevice);
			whdeviceDAO.flush();
		}
		whdevicedata.setRelativeWhdevice(related_whdevice);
		related_whdevice.getWhdevicedatas().add(whdevicedata);
		whdevicedata = whdevicedataDAO.store(whdevicedata);
		whdevicedataDAO.flush();

		related_whdevice = whdeviceDAO.store(related_whdevice);
		whdeviceDAO.flush();

		return whdevicedata;
	}

	@Transactional
	public void deleteWhdevicedata(Whdevicedata whdevicedata) {

		whdevicedata.setRelativeWhdatatype(null);
		whdevicedata.setRelativeWhdevice(null);

		entityManager.persist(whdevicedata);
		whdevicedataDAO.remove(whdevicedata);
		whdevicedataDAO.flush();
	}

	@Transactional
	public Whdevicedata deleteWhdevicedataWhdatatype(Integer whdevicedata_id,
			Integer related_whdatatype_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedata_id, -1, -1);
		Whdatatype related_whdatatype = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(related_whdatatype_id, -1, -1);
		whdevicedata.setRelativeWhdatatype(null);
		related_whdatatype.getRelativeWhdevicedatas().remove(whdevicedata);
		whdevicedata = whdevicedataDAO.store(whdevicedata);
		whdevicedataDAO.flush();
		related_whdatatype = whdatatypeDAO.store(related_whdatatype);

		whdatatypeDAO.flush();
		return whdevicedata;
	}

	@Transactional
	public Whdevicedata deleteWhdevicedataWhdevice(Integer whdevicedata_id,
			Integer related_whdevice_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedata_id, -1, -1);
		Whdevice related_whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(
				related_whdevice_id, -1, -1);
		whdevicedata.setRelativeWhdevice(null);
		related_whdevice.getWhdevicedatas().remove(whdevicedata);
		whdevicedata = whdevicedataDAO.store(whdevicedata);
		whdevicedataDAO.flush();
		related_whdevice = whdeviceDAO.store(related_whdevice);

		whdeviceDAO.flush();
		return whdevicedata;
	}

	@Transactional
	public Set<Whdevicedata> loadWhdevicedatas() {
		return whdevicedataDAO.findAllWhdevicedatas();

	}

	@Transactional
	public Set<Whdevicedata> loadWhdevicedatas(int index, int size) {
		return whdevicedataDAO.findAllWhdevicedatas(index, size);
	}

	@Transactional
	public List<Whdevicedata> findAllWhdevicedatas(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Whdevicedata>(
				whdevicedataDAO.findAllWhdevicedatas(startResult, maxRows));
	}

	@Transactional
	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id

	) {
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhdevicedatas() {
		return ((Long) whdevicedataDAO.createQuerySingleResult(
				"select count(o) from Whdevicedata o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeWhdatatypeWhdevicedatas(Integer whdatatype) {
		String sql = "select count(*) from Whdevicedata where whdatatype="
				+ whdatatype;
		return ((Long) whdevicedataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeWhdeviceWhdevicedatas(Integer whdevice) {
		String sql = "select count(*) from Whdevicedata where whdevice="
				+ whdevice;
		return ((Long) whdevicedataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
