package com.revature.daos;

import com.revature.entity.definitions.Employee;
import com.revature.entity.definitions.GenerateEntities;
import com.revature.entity.definitions.Manager;
import com.revature.entity.definitions.Reimbursement;
import com.revature.utilities.hibernate.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DaoTests implements GenerateEntities {
	
	private static EmployeeDao empDao;
	private static ReimbursementDao reimDao;

	
	@BeforeAll
	public static void initClass() {
		
		// Initialize DAO's
		empDao = new EmployeeDao(HibernateUtil.getSessionFactory());
		reimDao = new ReimbursementDao(HibernateUtil.getSessionFactory());
		
		// Initialize test data.
		// Generate and save employees and manager.
		Employee emp1 = GenerateEntities.createTestEmployee();
		empDao.save(emp1);
		Employee emp2 = GenerateEntities.createTestEmployee();
		emp2.setEmployeeId(99996);
		emp2.setEmpEmail("TestEmp2Email@company.com");
		empDao.save(emp2);
		Manager mngr = GenerateEntities.createTestManager();
		empDao.save(mngr);
		
		// Generate various reimbursements.
		Reimbursement reim1 = GenerateEntities.createTestReimbursementPending(emp1);
		reimDao.save(reim1);
		Reimbursement reim2 = GenerateEntities.createTestReimbursementPending(emp1);
		reim2.setReimbursementID(99998);
		GenerateEntities.setTestReimbursementToApproved(reim2, mngr);
		reimDao.save(reim2);
		Reimbursement reim3 = GenerateEntities.createTestReimbursementPending(emp2);
		reim3.setReimbursementID(99997);
		GenerateEntities.setTestReimbursementToDenied(reim3, mngr);
		reimDao.save(reim3);
	}
	
	@AfterAll
	public static void endClass() {
		
		// Undo the changes made during testing.
		reimDao.delete(reimDao.getEntity(99999));
		reimDao.delete(reimDao.getEntity(99998));
		reimDao.delete(reimDao.getEntity(99997));
		empDao.delete(empDao.getEntity(99999));
		empDao.delete(empDao.getEntity(99997));
		empDao.delete(empDao.getEntity(99996));
	}
	
	
	// Start of test methods
	@Test
	public void testFindEmployeeByID() {
		assertNotNull(empDao.getEntity(99999));
	}
	
	@Test
	public void testFindManagerByID() {
		assertNotNull(empDao.getEntity(99997));
	}
	
	@Test
	public void testFoundManagerIsManagerClass() {
		assertEquals(empDao.getEntity(99997).getClass(), Manager.class);
	}
	
	@Test
	public void testFindReimbursementByID() {
		assertNotNull(reimDao.getEntity(99999));
	}
	
	@Test
	public void testFindAllReimbursementsByAnEmployee() {
		Employee emp = empDao.getEntity(99999);
		assertEquals(2, reimDao.getAllEmpReimbursements(emp).size());
	}
	
	@Test
	public void testFindAllReimbursementsByAManager() {
		Employee mngr = empDao.getEntity(99997);
		assertEquals(mngr.getClass(), Manager.class);
		assertEquals(2, reimDao.getAllMngrReimbursements((Manager) mngr).size());
	}
}
