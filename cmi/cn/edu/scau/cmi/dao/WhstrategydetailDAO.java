package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhstrategydetailDAO extends JpaDao<Whstrategydetail> {

	public Whstrategydetail findWhstrategydetailById(Integer id) throws DataAccessException;

	public Whstrategydetail findWhstrategydetailById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMax(BigDecimal max) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMax(BigDecimal max, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMaxContaining(BigDecimal max_1) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMaxContaining(BigDecimal max_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMin(BigDecimal min) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMin(BigDecimal min, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMinContaining(BigDecimal min_1) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByMinContaining(BigDecimal min_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByTime(Date time) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByTime(Date time, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByTimeContaining(Date time_1) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategydetailByTimeContaining(Date time_1, int startResult, int maxRows)
			throws DataAccessException;

	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id) throws DataAccessException;

	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategydetail> findAllWhstrategydetails() throws DataAccessException;

	public Set<Whstrategydetail> findAllWhstrategydetails(int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategydetail> findWhstrategyWhstrategydetails(Integer Whstrategy, int start, int max);

	public Set<Whstrategydetail> findWhstrategytypeWhstrategydetails(Integer Whstrategytype, int start, int max);

}

