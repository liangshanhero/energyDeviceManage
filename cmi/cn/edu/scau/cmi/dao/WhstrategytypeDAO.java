package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhstrategytypeDAO extends JpaDao<Whstrategytype> {

	public Whstrategytype findWhstrategytypeById(Integer id) throws DataAccessException;

	public Whstrategytype findWhstrategytypeById(Integer id, int startResult, int maxRows) throws DataAccessException;

	public Set<Whstrategytype> findWhstrategytypeByName(String name) throws DataAccessException;

	public Set<Whstrategytype> findWhstrategytypeByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategytype> findWhstrategytypeByNameContaining(String name_1) throws DataAccessException;

	public Set<Whstrategytype> findWhstrategytypeByNameContaining(String name_1, int startResult, int maxRows)
			throws DataAccessException;

	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id) throws DataAccessException;

	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whstrategytype> findAllWhstrategytypes() throws DataAccessException;

	public Set<Whstrategytype> findAllWhstrategytypes(int startResult, int maxRows) throws DataAccessException;

}

