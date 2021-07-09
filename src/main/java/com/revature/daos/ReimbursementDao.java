package com.revature.daos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.revature.entity.definitions.Employee;
import com.revature.entity.definitions.Manager;
import com.revature.entity.definitions.Reimbursement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ReimbursementDao implements Dao<Reimbursement> {
	
	private SessionFactory sessionFactory;
	
	public ReimbursementDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Get an Reimbursement from the database based on id.
	 * @param id: The Reimbursement's ID number.
	 * @return the Reimbursement with the given ID number, or null.
	 */
	@Override
	public Reimbursement getEntity(int id) {
		Session session = sessionFactory.openSession();
		Reimbursement reim = session.get(Reimbursement.class, id);
		session.close();
		return reim;
	}

	/**
	 * Return a list of all entities in the employee database.
	 * @return All Reimbursements in a result list of type Reimbursement
	 */
	@Override
	public List<Reimbursement> getAllEntities() {
		Session session = sessionFactory.openSession();
		List<Reimbursement> reimList = session.createQuery("from Reimbursement", Reimbursement.class).getResultList();
		session.close();
		return reimList;
	}
	
	
	/**
	 * Create and execute a query to find all reimbursement requests made by the given employee.
	 * @param e: The employee to find reimbursements.
	 * @return all reimbursements made by the employee.
	 */
	public List<Reimbursement> getAllEmpReimbursements(Employee e) {
		// Create connection and criteria builder to select Reimbursements.
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		// Create and define a query to select Reimbursements requests made 
		// by the employee based on their employee ID.
		CriteriaQuery<Reimbursement> query = builder.createQuery(Reimbursement.class);
		Root<Reimbursement> root = query.from(Reimbursement.class);
		query.select(root).where(builder.equal(root.get("requestedByEmp"), e.getEmployeeId()));
		return session.createQuery(query).getResultList();
	}
	
	
	/**
	 * Create and execute a query to find all reimbursement reviewed by the given manager.
	 * @param m: The employee to find reimbursements.
	 * @return all reimbursements made by the employee.
	 */
	public List<Reimbursement> getAllMngrReimbursements(Manager m) {
		// Create connection and criteria builder to select Reimbursements.
		Session session = sessionFactory.openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		// Create and define a query to select Reimbursements reviewed by the 
		// manager based on their employee ID.
		CriteriaQuery<Reimbursement> query = builder.createQuery(Reimbursement.class);
		Root<Reimbursement> root = query.from(Reimbursement.class);
		query.select(root).where(builder.equal(root.get("reviewedBy"), m.getEmployeeId()));
		return session.createQuery(query).getResultList();
	}

	/**
	 * Save a reimbursement into the database. Transaction must still be committed.
	 * @param e The reimbursement to save.
	 */
	@Override
	public void save(Reimbursement e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.save(e);
		trns.commit();
		session.close();
	}

	/**
	 * Update the given reimbursement.
	 * @param e The reimbursement to update.
	 */
	@Override
	public void update(Reimbursement e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.update(e);
		trns.commit();
		session.close();
	}

	/**
	 * Delete the given reimbursement from the database.
	 * @param e The reimbursement to delete.
	 */
	@Override
	public void delete(Reimbursement e) {
		Session session = sessionFactory.openSession();
		Transaction trns = session.beginTransaction();
		session.delete(e);
		trns.commit();
		session.close();
	}

}