package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface LedbuildingDAO extends JpaDao<Ledbuilding> {

	public Ledbuilding findLedbuildingById(Integer id)
			throws DataAccessException;

	public Ledbuilding findLedbuildingById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByName(String name)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByWell(Integer well)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByWell(Integer well,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByWellContaining(Integer well_1)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByWellContaining(Integer well_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByStorey(Integer storey)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByStorey(Integer storey,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByStoreyContaining(Integer storey_1)
			throws DataAccessException;

	public Set<Ledbuilding> findLedbuildingByStoreyContaining(Integer storey_1,
			int startResult, int maxRows) throws DataAccessException;

	public Ledbuilding findLedbuildingByPrimaryKey(Integer id)
			throws DataAccessException;

	public Ledbuilding findLedbuildingByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledbuilding> findAllLedbuildings() throws DataAccessException;

	public Set<Ledbuilding> findAllLedbuildings(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledbuilding> findProjectLedbuildings(Integer Project, int start,
			int max);

}
