package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhstrategyDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhstrategyService")
@Transactional

public class WhstrategyServiceImpl implements WhstrategyService {
	@Autowired
	private WhstrategyDAO whstrategyDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategydetailService whstrategydetailService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategyServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhstrategy(Whstrategy whstrategy) {
		Whstrategy existingWhstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy.getId());
		if (existingWhstrategy != null) {
			if (existingWhstrategy != whstrategy) {
				existingWhstrategy.setEnable(whstrategy.getEnable());
				existingWhstrategy.setCreateDate(whstrategy.getCreateDate());
				existingWhstrategy.setRemark(whstrategy.getRemark());
				existingWhstrategy.setName(whstrategy.getName());
			}

			if (whstrategy.getWhdevice() != null) {
				entityManager.persist(whstrategy.getWhdevice());
				existingWhstrategy.setWhdevice(whstrategy.getWhdevice());
			} else
				existingWhstrategy.setWhdevice(null);

			if (whstrategy.getWhstrategydetails() != null) {
				for (Whstrategydetail relativeWhstrategydetail : whstrategy.getWhstrategydetails()) {
					relativeWhstrategydetail.setWhstrategy(existingWhstrategy);
					entityManager.persist(relativeWhstrategydetail);
				}
				existingWhstrategy.setWhstrategydetails(whstrategy.getWhstrategydetails());
			} else
				existingWhstrategy.setWhstrategydetails(null);
			entityManager.persist(existingWhstrategy);
		} else {
			entityManager.persist(whstrategy);
			whstrategy = whstrategyDAO.store(whstrategy);
		}
		whstrategyDAO.flush();
	}

	@Transactional
	public Whstrategy saveWhstrategyWhdevice(Integer id, Whdevice related_whdevice) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(id, -1, -1);
		Whdevice existingWhdevice = whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevice.getId());
		if (existingWhdevice != null) {
			existingWhdevice.setId(related_whdevice.getId());
			existingWhdevice.setNumber(related_whdevice.getNumber());
			related_whdevice = existingWhdevice;
		} else {
			related_whdevice = whdeviceDAO.store(related_whdevice);
			whdeviceDAO.flush();
		}
		whstrategy.setWhdevice(related_whdevice);
		related_whdevice.getWhstrategys().add(whstrategy);
		whstrategy = whstrategyDAO.store(whstrategy);
		whstrategyDAO.flush();

		related_whdevice = whdeviceDAO.store(related_whdevice);
		whdeviceDAO.flush();

		return whstrategy;
	}

	@Transactional
	public Whstrategy saveWhstrategyWhstrategydetails(Integer id, Whstrategydetail related_whstrategydetails) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(id, -1, -1);
		Whstrategydetail existingWhstrategydetails = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(related_whstrategydetails.getId());
		if (existingWhstrategydetails != null) {
			existingWhstrategydetails.setId(related_whstrategydetails.getId());
			existingWhstrategydetails.setMax(related_whstrategydetails.getMax());
			existingWhstrategydetails.setMin(related_whstrategydetails.getMin());
			related_whstrategydetails = existingWhstrategydetails;
			entityManager.persist(related_whstrategydetails);
		} else {
			entityManager.persist(related_whstrategydetails);
			whstrategydetailDAO.flush();
		}
		entityManager.persist(whstrategy);
		related_whstrategydetails.setWhstrategy(whstrategy);
		whstrategy.getWhstrategydetails().add(related_whstrategydetails);
		entityManager.persist(whstrategy);
		entityManager.persist(related_whstrategydetails);
		whstrategyDAO.flush();

		return whstrategy;
	}

	@Transactional
	public void deleteWhstrategy(Whstrategy whstrategy) {

		whstrategy.setWhdevice(null);

		Set<Whstrategydetail> whstrategydetailSet = whstrategy.getWhstrategydetails();
		whstrategy.setWhstrategydetails(null);
		entityManager.persist(whstrategy);
		whstrategyDAO.remove(whstrategy);
		whstrategyDAO.flush();
	}

	@Transactional
	public Whstrategy deleteWhstrategyWhdevice(Integer whstrategy_id, Integer related_whdevice_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id, -1, -1);
		Whdevice related_whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevice_id, -1, -1);
		whstrategy.setWhdevice(null);
		related_whdevice.getWhstrategys().remove(whstrategy);
		whstrategy = whstrategyDAO.store(whstrategy);
		whstrategyDAO.flush();
		related_whdevice = whdeviceDAO.store(related_whdevice);

		whdeviceDAO.flush();
		return whstrategy;
	}

	@Transactional
	public Whstrategy deleteWhstrategyWhstrategydetails(Integer whstrategy_id, Integer related_whstrategydetails_id) {
		Whstrategydetail related_whstrategydetails = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(related_whstrategydetails_id, -1, -1);
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id, -1, -1);
		Set<Whstrategydetail> whstrategydetailSet = whstrategy.getWhstrategydetails();
		Whstrategydetail teache = new Whstrategydetail();
		if (whstrategydetailSet.size() > 0)
			for (Whstrategydetail th : whstrategydetailSet) {
				if (th == related_whstrategydetails) {
					teache = related_whstrategydetails;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setWhstrategy(null);
			whstrategy.getWhstrategydetails().remove(teache);
		}
		entityManager.persist(related_whstrategydetails);
		entityManager.persist(whstrategy);
		whstrategyDAO.flush();

		return whstrategy;
	}

	@Transactional
	public Set<Whstrategy> loadWhstrategys() {
		return whstrategyDAO.findAllWhstrategys();

	}

	@Transactional
	public Set<Whstrategy> loadWhstrategys(int index, int size) {
		return whstrategyDAO.findAllWhstrategys(index, size);
	}

	@Transactional
	public List<Whstrategy> findAllWhstrategys(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Whstrategy>(whstrategyDAO.findAllWhstrategys(startResult, maxRows));
	}

	@Transactional
	public Whstrategy findWhstrategyByPrimaryKey(Integer id

	) {
		return whstrategyDAO.findWhstrategyByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhstrategys() {
		return ((Long) whstrategyDAO.createQuerySingleResult("select count(o) from Whstrategy o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeWhdeviceWhstrategys(Integer whdevice) {
		String sql = "select count(*) from Whstrategy where whdevice=" + whdevice;
		return ((Long) whstrategyDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

}

