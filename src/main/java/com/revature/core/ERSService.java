package com.revature.core;

import com.revature.daos.EmployeeDao;
import com.revature.daos.ReimbursementDao;
import com.revature.entity.definitions.Employee;
import com.revature.entity.definitions.Manager;
import com.revature.entity.definitions.Reimbursement;
import com.revature.utilities.hibernate.HibernateUtil;
import com.revature.utilities.log4j.LoggerTools;

import java.io.FileNotFoundException;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Facade class for the various business operations necessary for the reimbursement system.
 * Simplifies and combines all necessary processes into a single interface.
 */
public class ERSService {
	
	private static ReimbursementDao reimDao;
	private static EmployeeDao empDao;
	private static ERSService ersService;
	private static Logger log;
	
	private ERSService() {
		reimDao = new ReimbursementDao(HibernateUtil.getSessionFactory());
		empDao = new EmployeeDao(HibernateUtil.getSessionFactory());
		
		// Configure logger
		try {
			log = LoggerTools.getAndConfigureLogger(ERSService.class.getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		log.trace("Employee Reimbursement Service and logger instantiated.");
	}
	
	/* Remove comment to run as a Java application and add sample data to the db.
	public static void main(String[] args) {
		ERSService serv = getERSService();
		serv.createSampleDb();
	}
	*/
	
	public static ERSService getERSService() {
		if (ERSService.ersService == null) {
			ERSService.ersService = new ERSService();
		}
		return ersService;
	}
	
	
	/**
	 * Check a user's credentials and determine if they are a valid employee.
	 * @param username: The user-input username.
	 * @param password: The user-input password.
	 * @return 1 to indicate general employee, 2 to indicate manager, 3 to indicate
	 * 		 non-existing username, 4 to indicate incorrect password.
	 */
	public int verifyLogin(String username, String password) {
		Employee user = empDao.getEntity(username);
		if (user == null) {
			log.trace("User entered invalid user name.");
			return 3;
		}
		else if (user.getEmpPassword().equals(password)) {
			if (user.getClass().equals(Manager.class)) {
				log.trace("Manager " + username + " has logged in.");
				return 2;
			}
			else if (user.getClass().equals(Employee.class)) {
				log.trace("Employee " + username + " has logged in.");
				return 1;
			}
		}
		log.trace("User has entered an incorrect password.");
		return 3;
	}
	
	/**
	 * Given a valid user, determine their ranking and open either the 
	 * employee or manager web page.
	 * @param user: The Employee representing the current user.
	 */
	public void selectEmployeeClassPage(Employee user) {
		if (user.getClass().equals(Manager.class)) {
			System.out.println("Displaying Manager webpage.");
			// Add webpage logic.
		}
		else if (user.getClass().equals(Employee.class)) {
			System.out.println("Displaying Employee webpage.");
			// Add webpage logic.
		}
	}
	
	
	/**
	 * Create and several sample employees, managers, and reimbursements. For demonstration use only.
	 */
	public void createSampleDb() {
		Employee testEmp = new Employee(1, "employee1@companyname.com", "employee1password", 
				"Test", 'E', "Employee", "employee1 Address", 123456789, 10000.5, "01-JAN-2001");
		empDao.save(testEmp);
		Reimbursement reim = new Reimbursement(1, testEmp, 50.00, "Test request reason.", "01-JAN-2001");
		reimDao.save(reim);
		Employee testEmp2 = new Employee(2, "employee2@companyname.com", "employee2password", 
				"Test2", 'E', "Employee2", "employee2 Address", 987654321, 12000.50, "02-JAN-2001");
		empDao.save(testEmp2);
		Manager testMng = new Manager(3, "mngr1@company.com", "testMngrPassword",  "TestMng3", 'M', "Manager3", 
				"Mng3Address", 415161718, 200000.0, "06-JAN-2001", "07-JAN-2001");
		empDao.save(testMng);
		Reimbursement reim2 = new Reimbursement(2, testEmp2, 150.0, "Test request reason2.", "approved", "08-JAN-2001", testMng, "09-JAN-2001");
		reimDao.save(reim2);
		log.trace("Sample data added.");
	}
	
	/**
	 * Adapter methods from EmployeeDao and ReimbursementDao.
	 */
	public List<Employee> getAllEmployees() {
		return empDao.getAllEntities();
	}
	public List<Reimbursement> getAllReimbursements() {
		return reimDao.getAllEntities();
	}
	public List<Reimbursement> getAllEmpReimbursements(Employee e) {
		return reimDao.getAllEmpReimbursements(e);
	}
	public List<Reimbursement> getAllManagerReimbursements(Manager m) {
		return reimDao.getAllMngrReimbursements(m);
	}
}
