package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhdatatypeDAO extends JpaDao<Whdatatype> {

	public Whdatatype findWhdatatypeById(Integer id) throws DataAccessException;

	public Whdatatype findWhdatatypeById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdatatype> findWhdatatypeByName(String name)
			throws DataAccessException;

	public Set<Whdatatype> findWhdatatypeByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdatatype> findWhdatatypeByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Whdatatype> findWhdatatypeByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Whdatatype findWhdatatypeByPrimaryKey(Integer id)
			throws DataAccessException;

	public Whdatatype findWhdatatypeByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdatatype> findAllWhdatatypes() throws DataAccessException;

	public Set<Whdatatype> findAllWhdatatypes(int startResult, int maxRows)
			throws DataAccessException;

}
