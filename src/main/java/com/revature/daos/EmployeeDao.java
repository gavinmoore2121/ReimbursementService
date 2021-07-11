package com.revature.daos;

import com.revature.entity.definitions.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao implements Dao<Employee> {
	
	private SessionFactory sessionFactory;
	
	public EmployeeDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get an employee from the database based on id.
	 * @param id: the employee's ID number.
	 * @return the employee with the given ID number, or null.
	 */
	@Override
	public Employee getEntity(int id) {
		Session session = sessionFactory.openSession();
		Employee emp = session.get(Employee.class, id);
		session.close();
		return emp;
	}
	
	/**
	 * Get an employee from the database based on their email.
	 * @param email: The employee's email.
	 * @return the employee with the given email, or null.
	 */
	public Employee getEntity(String email) {
		Session session = sessionFactory.openSession();
		Employee emp = session.byNaturalId(Employee.class).using("empEmail", email).load();
		session.close();
		return emp;
	}

	/**
	 * Return a list of all entities in the employee database.
	 * @return All employees in a result list of type Employee.
	 */
	@Override
	public List<Employee> getAllEntities() {
		Session session = sessionFactory.openSession();
		List<Employee> empList = session.createQuery("from Employee", Employee.class).getResultList();
		session.close();
		return empList;
	}

	/**
	 * Save an employee into the database. Transaction must still be committed.
	 * @param e The employee to save.
	 */
	@Override
	public void save(Employee e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.save(e);
		trns.commit();
		session.close();
	}

	/**
	 * Update the given employee.
	 * @param e: The employee to save.
	 */
	@Override
	public void update(Employee e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.update(e);
		trns.commit();
		session.close();
	}

	/**
	 * Delete the given employee from the database.
	 * @param e: The employee to delete.
	 */
	@Override
	public void delete(Employee e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.delete(e);
		trns.commit();
		session.close();
	}

}
