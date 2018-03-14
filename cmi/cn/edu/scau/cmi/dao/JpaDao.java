package cn.edu.scau.cmi.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.Query;

public interface JpaDao<T extends Object> {
	public static final int DEFAULT_FIRST_RESULT_INDEX = 0;

	public static final int DEFAULT_MAX_RESULTS = 200;

	public void setDefaultMaxResults(int defaultMaxResults);

	public int getDefaultMaxResults();

	public Set<Class<?>> getTypes();

	public T store(T obj);

	public T merge(T obj);

	public void remove(Object obj);

	public void refresh(Object obj);

	public void flush();

	public T executeQueryByNameSingleResult(String queryName);

	public T executeQueryByNameSingleResult(String queryName,
			Object... parameters);

	public List<T> executeQueryByName(String queryName);

	public List<T> executeQueryByName(String queryName, Integer firstResult,
			Integer maxResults);

	public List<T> executeQueryByName(String queryName, Object... parameters);

	public List<T> executeQueryByName(String queryName, Integer firstResult,
			Integer maxResults, Object... parameters);

	public Object executeQuerySingleResult(String queryString);

	public T executeQuerySingleResult(String queryString, Object... parameters);

	public List<T> executeQuery(String queryString, Object... parameters);

	public List<T> executeQuery(String queryString, Integer firstResult,
			Integer maxResults, Object... parameters);

	public Query createNamedQuery(String queryName, Integer firstResult,
			Integer maxResults);

	public Query createNamedQuery(String queryName, Integer firstResult,
			Integer maxResults, Object... parameters);

	public Query createQuerySingleResult(String queryString,
			Object... parameters);

	public Query createQuery(String queryString, Integer firstResult,
			Integer maxResults);

	public Query createQuery(String queryString, Integer firstResult,
			Integer maxResults, Object... parameters);

	public boolean canBeMerged(T o);
}
