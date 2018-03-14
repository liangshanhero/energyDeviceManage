package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhstrategytypeDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhstrategytypeService")
@Transactional

public class WhstrategytypeServiceImpl implements WhstrategytypeService {
	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategydetailService whstrategydetailService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategytypeServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhstrategytype(Whstrategytype whstrategytype) {
		Whstrategytype existingWhstrategytype = whstrategytypeDAO
				.findWhstrategytypeByPrimaryKey(whstrategytype.getId());
		if (existingWhstrategytype != null) {
			if (existingWhstrategytype != whstrategytype) {
				existingWhstrategytype.setName(whstrategytype.getName());
			}

			if (whstrategytype.getWhstrategydetails() != null) {
				for (Whstrategydetail relativeWhstrategydetail : whstrategytype.getWhstrategydetails()) {
					relativeWhstrategydetail.setWhstrategytype(existingWhstrategytype);
					entityManager.persist(relativeWhstrategydetail);
				}
				existingWhstrategytype.setWhstrategydetails(whstrategytype.getWhstrategydetails());
			} else
				existingWhstrategytype.setWhstrategydetails(null);
			entityManager.persist(existingWhstrategytype);
		} else {
			entityManager.persist(whstrategytype);
			whstrategytype = whstrategytypeDAO.store(whstrategytype);
		}
		whstrategytypeDAO.flush();
	}

	@Transactional
	public Whstrategytype saveWhstrategytypeWhstrategydetails(Integer id, Whstrategydetail related_whstrategydetails) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id, -1, -1);
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
		entityManager.persist(whstrategytype);
		related_whstrategydetails.setWhstrategytype(whstrategytype);
		whstrategytype.getWhstrategydetails().add(related_whstrategydetails);
		entityManager.persist(whstrategytype);
		entityManager.persist(related_whstrategydetails);
		whstrategytypeDAO.flush();

		return whstrategytype;
	}

	@Transactional
	public void deleteWhstrategytype(Whstrategytype whstrategytype) {

		Set<Whstrategydetail> whstrategydetailSet = whstrategytype.getWhstrategydetails();
		whstrategytype.setWhstrategydetails(null);
		entityManager.persist(whstrategytype);
		whstrategytypeDAO.remove(whstrategytype);
		whstrategytypeDAO.flush();
	}

	@Transactional
	public Whstrategytype deleteWhstrategytypeWhstrategydetails(Integer whstrategytype_id,
			Integer related_whstrategydetails_id) {
		Whstrategydetail related_whstrategydetails = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(related_whstrategydetails_id, -1, -1);
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id, -1, -1);
		Set<Whstrategydetail> whstrategydetailSet = whstrategytype.getWhstrategydetails();
		Whstrategydetail teache = new Whstrategydetail();
		if (whstrategydetailSet.size() > 0)
			for (Whstrategydetail th : whstrategydetailSet) {
				if (th == related_whstrategydetails) {
					teache = related_whstrategydetails;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setWhstrategytype(null);
			whstrategytype.getWhstrategydetails().remove(teache);
		}
		entityManager.persist(related_whstrategydetails);
		entityManager.persist(whstrategytype);
		whstrategytypeDAO.flush();

		return whstrategytype;
	}

	@Transactional
	public Set<Whstrategytype> loadWhstrategytypes() {
		return whstrategytypeDAO.findAllWhstrategytypes();

	}

	@Transactional
	public Set<Whstrategytype> loadWhstrategytypes(int index, int size) {
		return whstrategytypeDAO.findAllWhstrategytypes(index, size);
	}

	@Transactional
	public List<Whstrategytype> findAllWhstrategytypes(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Whstrategytype>(whstrategytypeDAO.findAllWhstrategytypes(startResult, maxRows));
	}

	@Transactional
	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id

	) {
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhstrategytypes() {
		return ((Long) whstrategytypeDAO.createQuerySingleResult("select count(o) from Whstrategytype o")
				.getSingleResult()).intValue();
	}

}

