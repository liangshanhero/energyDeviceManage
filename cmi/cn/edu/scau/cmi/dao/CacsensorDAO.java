package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacsensorDAO extends JpaDao<Cacsensor> {

	public Cacsensor findCacsensorById(Integer id) throws DataAccessException;

	public Cacsensor findCacsensorById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacsensor> findCacsensorByName(String name)
			throws DataAccessException;

	public Set<Cacsensor> findCacsensorByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacsensor> findCacsensorByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Cacsensor> findCacsensorByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Cacsensor findCacsensorByPrimaryKey(Integer id)
			throws DataAccessException;

	public Cacsensor findCacsensorByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacsensor> findAllCacsensors() throws DataAccessException;

	public Set<Cacsensor> findAllCacsensors(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacsensor> findCacCacsensors(Integer Cac, int start, int max);

}
