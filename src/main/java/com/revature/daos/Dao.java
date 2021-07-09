package com.revature.daos;

import java.util.List;

/**
 * Generic interface for generic access objects.
 * Defines several CRUD operations on databases.
 *
 * @param <T> The entity/table name to define.
 */
public interface Dao<T> {
	T getEntity(int id);
	List<T> getAllEntities();
	void save(T t);
	void update(T t);
	void delete(T t);
}
