package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacDAO extends JpaDao<Cac> {

	public Cac findCacById(Integer id) throws DataAccessException;

	public Cac findCacById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cac> findCacByRemark(String remark) throws DataAccessException;

	public Set<Cac> findCacByRemark(String remark, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cac> findCacByRemarkContaining(String remark_1)
			throws DataAccessException;

	public Set<Cac> findCacByRemarkContaining(String remark_1, int startResult,
			int maxRows) throws DataAccessException;

	public Cac findCacByPrimaryKey(Integer id) throws DataAccessException;

	public Cac findCacByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cac> findAllCacs() throws DataAccessException;

	public Set<Cac> findAllCacs(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cac> findProjectCacs(Integer Project, int start, int max);

}
