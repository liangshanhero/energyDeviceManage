package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhstrategydetailDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhstrategydetailService")
@Transactional

public class WhstrategydetailServiceImpl implements WhstrategydetailService {
	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategyDAO whstrategyDAO;

	@Autowired
	private WhstrategyService whstrategyService;
	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;

	@Autowired
	private WhstrategytypeService whstrategytypeService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategydetailServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhstrategydetail(Whstrategydetail whstrategydetail) {
		Whstrategydetail existingWhstrategydetail = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(whstrategydetail.getId());
		if (existingWhstrategydetail != null) {
			if (existingWhstrategydetail != whstrategydetail) {
				existingWhstrategydetail.setMax(whstrategydetail.getMax());
				existingWhstrategydetail.setMin(whstrategydetail.getMin());
				existingWhstrategydetail.setTime(whstrategydetail.getTime());
			}

			if (whstrategydetail.getWhstrategy() != null) {
				entityManager.persist(whstrategydetail.getWhstrategy());
				existingWhstrategydetail.setWhstrategy(whstrategydetail.getWhstrategy());
			} else
				existingWhstrategydetail.setWhstrategy(null);
			if (whstrategydetail.getWhstrategytype() != null) {
				entityManager.persist(whstrategydetail.getWhstrategytype());
				existingWhstrategydetail.setWhstrategytype(whstrategydetail.getWhstrategytype());
			} else
				existingWhstrategydetail.setWhstrategytype(null);

			entityManager.persist(existingWhstrategydetail);
		} else {
			entityManager.persist(whstrategydetail);
			whstrategydetail = whstrategydetailDAO.store(whstrategydetail);
		}
		whstrategydetailDAO.flush();
	}

	@Transactional
	public Whstrategydetail saveWhstrategydetailWhstrategy(Integer id, Whstrategy related_whstrategy) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id, -1, -1);
		Whstrategy existingWhstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(related_whstrategy.getId());
		if (existingWhstrategy != null) {
			existingWhstrategy.setId(related_whstrategy.getId());
			existingWhstrategy.setEnable(related_whstrategy.getEnable());
			existingWhstrategy.setCreateDate(related_whstrategy.getCreateDate());
			existingWhstrategy.setRemark(related_whstrategy.getRemark());
			existingWhstrategy.setName(related_whstrategy.getName());
			related_whstrategy = existingWhstrategy;
		} else {
			related_whstrategy = whstrategyDAO.store(related_whstrategy);
			whstrategyDAO.flush();
		}
		whstrategydetail.setWhstrategy(related_whstrategy);
		related_whstrategy.getWhstrategydetails().add(whstrategydetail);
		whstrategydetail = whstrategydetailDAO.store(whstrategydetail);
		whstrategydetailDAO.flush();

		related_whstrategy = whstrategyDAO.store(related_whstrategy);
		whstrategyDAO.flush();

		return whstrategydetail;
	}

	@Transactional
	public Whstrategydetail saveWhstrategydetailWhstrategytype(Integer id, Whstrategytype related_whstrategytype) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id, -1, -1);
		Whstrategytype existingWhstrategytype = whstrategytypeDAO
				.findWhstrategytypeByPrimaryKey(related_whstrategytype.getId());
		if (existingWhstrategytype != null) {
			existingWhstrategytype.setId(related_whstrategytype.getId());
			existingWhstrategytype.setName(related_whstrategytype.getName());
			related_whstrategytype = existingWhstrategytype;
		} else {
			related_whstrategytype = whstrategytypeDAO.store(related_whstrategytype);
			whstrategytypeDAO.flush();
		}
		whstrategydetail.setWhstrategytype(related_whstrategytype);
		related_whstrategytype.getWhstrategydetails().add(whstrategydetail);
		whstrategydetail = whstrategydetailDAO.store(whstrategydetail);
		whstrategydetailDAO.flush();

		related_whstrategytype = whstrategytypeDAO.store(related_whstrategytype);
		whstrategytypeDAO.flush();

		return whstrategydetail;
	}

	@Transactional
	public void deleteWhstrategydetail(Whstrategydetail whstrategydetail) {

		whstrategydetail.setWhstrategy(null);
		whstrategydetail.setWhstrategytype(null);

		entityManager.persist(whstrategydetail);
		whstrategydetailDAO.remove(whstrategydetail);
		whstrategydetailDAO.flush();
	}

	@Transactional
	public Whstrategydetail deleteWhstrategydetailWhstrategy(Integer whstrategydetail_id,
			Integer related_whstrategy_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id,
				-1, -1);
		Whstrategy related_whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(related_whstrategy_id, -1, -1);
		whstrategydetail.setWhstrategy(null);
		related_whstrategy.getWhstrategydetails().remove(whstrategydetail);
		whstrategydetail = whstrategydetailDAO.store(whstrategydetail);
		whstrategydetailDAO.flush();
		related_whstrategy = whstrategyDAO.store(related_whstrategy);

		whstrategyDAO.flush();
		return whstrategydetail;
	}

	@Transactional
	public Whstrategydetail deleteWhstrategydetailWhstrategytype(Integer whstrategydetail_id,
			Integer related_whstrategytype_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id,
				-1, -1);
		Whstrategytype related_whstrategytype = whstrategytypeDAO
				.findWhstrategytypeByPrimaryKey(related_whstrategytype_id, -1, -1);
		whstrategydetail.setWhstrategytype(null);
		related_whstrategytype.getWhstrategydetails().remove(whstrategydetail);
		whstrategydetail = whstrategydetailDAO.store(whstrategydetail);
		whstrategydetailDAO.flush();
		related_whstrategytype = whstrategytypeDAO.store(related_whstrategytype);

		whstrategytypeDAO.flush();
		return whstrategydetail;
	}

	@Transactional
	public Set<Whstrategydetail> loadWhstrategydetails() {
		return whstrategydetailDAO.findAllWhstrategydetails();

	}

	@Transactional
	public Set<Whstrategydetail> loadWhstrategydetails(int index, int size) {
		return whstrategydetailDAO.findAllWhstrategydetails(index, size);
	}

	@Transactional
	public List<Whstrategydetail> findAllWhstrategydetails(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Whstrategydetail>(
				whstrategydetailDAO.findAllWhstrategydetails(startResult, maxRows));
	}

	@Transactional
	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id

	) {
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhstrategydetails() {
		return ((Long) whstrategydetailDAO.createQuerySingleResult("select count(o) from Whstrategydetail o")
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeWhstrategyWhstrategydetails(Integer whstrategy) {
		String sql = "select count(*) from Whstrategydetail where whstrategy=" + whstrategy;
		return ((Long) whstrategydetailDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeWhstrategytypeWhstrategydetails(Integer whstrategytype) {
		String sql = "select count(*) from Whstrategydetail where whstrategytype=" + whstrategytype;
		return ((Long) whstrategydetailDAO.createQuerySingleResult(sql).getSingleResult()).intValue();
	}

}

