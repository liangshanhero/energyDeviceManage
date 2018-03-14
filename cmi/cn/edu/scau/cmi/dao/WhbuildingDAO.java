package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhbuildingDAO extends JpaDao<Whbuilding> {

	public Whbuilding findWhbuildingById(Integer id) throws DataAccessException;

	public Whbuilding findWhbuildingById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whbuilding> findWhbuildingByName(String name)
			throws DataAccessException;

	public Set<Whbuilding> findWhbuildingByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whbuilding> findWhbuildingByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Whbuilding> findWhbuildingByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Whbuilding findWhbuildingByPrimaryKey(Integer id)
			throws DataAccessException;

	public Whbuilding findWhbuildingByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whbuilding> findAllWhbuildings() throws DataAccessException;

	public Set<Whbuilding> findAllWhbuildings(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whbuilding> findProjectWhbuildings(Integer Project, int start,
			int max);

}
