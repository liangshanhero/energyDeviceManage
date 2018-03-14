package cn.edu.scau.cmi.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhstrategyDAO extends JpaDao<Whstrategy> {

	public Whstrategy findWhstrategyById(Integer id) throws DataAccessException;

	public Whstrategy findWhstrategyById(Integer id, int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByEnable(Boolean enable) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByEnable(Boolean enable, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByEnableContaining(Boolean enable_1) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByEnableContaining(Boolean enable_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByCreateDate(Date createDate) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByCreateDate(Date createDate, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByCreateDateContaining(Date createDate_1) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByCreateDateContaining(Date createDate_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByRemark(byte[] remark) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByRemark(byte[] remark, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByRemarkContaining(byte[] remark_1) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByRemarkContaining(byte[] remark_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByName(String name) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByName(String name, int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByNameContaining(String name_1) throws DataAccessException;

	public Set<Whstrategy> findWhstrategyByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	public Whstrategy findWhstrategyByPrimaryKey(Integer id) throws DataAccessException;

	public Whstrategy findWhstrategyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategy> findAllWhstrategys() throws DataAccessException;

	public Set<Whstrategy> findAllWhstrategys(int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategy> findWhdeviceWhstrategys(Integer Whdevice, int start, int max);

}

