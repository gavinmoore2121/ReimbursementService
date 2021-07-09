package com.revature.entity.definitions;

import com.revature.utilities.hibernate.HibernateUtil;
import com.revature.utilities.log4j.LoggerTools;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EntityTests {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction trns;

    @BeforeAll
    public static void initClass() {
        // Initialize logger and Hibernate Session Factory.
        try {
            Logger log = LoggerTools.getAndConfigureLogger(EntityTests.class.getName());
            sessionFactory = HibernateUtil.getSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void initEach() {
        // Open a session and a transaction.
        session = sessionFactory.openSession();
        trns = session.beginTransaction();
    }

    @AfterEach
    public void endEach() {
        // Undo testing transaction, end current session.
        trns.rollback();
        session.close();
    }

    // Start of test methods
    @Test
    public void testAddEmployeeToDatabase() {
        Employee emp = GenerateEntities.createTestEmployee();

        assertNotNull(session.save(emp));
    }

    @Test
    public void testAddAssociateToDatabase() {
        Associate assoc = GenerateEntities.createTestAssociate();

        assertNotNull(session.save(assoc));
    }

    @Test
    public void testAddManagerToDatabase() {
        Manager mngr = GenerateEntities.createTestManager();

        assertNotNull(session.save(mngr));
    }


    @Test
    public void testFindEmployeeByEmpID() {
        Employee emp = GenerateEntities.createTestEmployee();
        session.save(emp);

        assertEquals(emp, session.get(Employee.class, emp.getEmployeeId()));
    }

    @Test
    public void testFindAssociateByEmpID() {
        Associate assoc = GenerateEntities.createTestAssociate();
        session.save(assoc);

        assertEquals(assoc, session.get(Employee.class, assoc.getEmployeeId()));
    }

    @Test
    public void testFindManagerByEmpID() {
        Manager mngr = GenerateEntities.createTestManager();
        session.save(mngr);

        assertEquals(mngr, session.get(Employee.class, mngr.getEmployeeId()));
    }

    @Test
    public void testRetrievedEmployeeIsEmployeeClass() {
        Employee emp = GenerateEntities.createTestEmployee();
        session.save(emp);

        assertEquals(Employee.class, session.get(Employee.class, emp.getEmployeeId()).getClass());
    }

    @Test
    public void testRetrievedAssociateIsAssociateClass() {
        Associate assoc = GenerateEntities.createTestAssociate();
        session.save(assoc);

        assertEquals(Associate.class, session.get(Employee.class, assoc.getEmployeeId()).getClass());
    }

    @Test
    public void testRetrievedManagerIsManagerClass() {
        Manager mngr = GenerateEntities.createTestManager();
        session.save(mngr);

        assertEquals(Manager.class, session.get(Employee.class, mngr.getEmployeeId()).getClass());
    }

    @Test
    public void testAddedEmployeeAssociateAndManagerAddedToSameTable() {
        // Get all on employee table, determine size.
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        int numInDatabaseBeforeTest = query.getResultList().size();

        // Add all three employee types, confirm employee table size increased by 3.
        session.save(GenerateEntities.createTestEmployee());
        session.save(GenerateEntities.createTestAssociate());
        session.save(GenerateEntities.createTestManager());

        assertEquals(numInDatabaseBeforeTest + 3, query.getResultList().size());
    }

    @Test
    public void testAddReimbursementToDB() {
        Employee emp = GenerateEntities.createTestEmployee();
        Manager mngr = GenerateEntities.createTestManager();

        session.save(emp);
        session.save(mngr);

        Reimbursement reim = GenerateEntities.createTestReimbursementPending(emp);
        GenerateEntities.setTestReimbursementToApproved(reim, mngr);

        assertNotNull(session.save(reim));
    }

    @Test
    public void testAddReimbursementWithNullManagerToDB() {
        Employee emp = GenerateEntities.createTestEmployee();
        session.save(emp);

        Reimbursement reim = GenerateEntities.createTestReimbursementPending(emp);
        assertNotNull(session.save(reim));
    }

    @Test
    public void testAddingReimbursementWithInvalidApprovalThrowsException() {
        Employee emp = GenerateEntities.createTestEmployee();
        session.save(emp);

        Reimbursement reim = GenerateEntities.createTestReimbursementPending(emp);
        reim.setApprovalStatus("foo");
        session.save(reim);

        Exception e = assertThrows(javax.persistence.PersistenceException.class, () -> trns.commit());
        assertEquals(e.getCause().getClass(), ConstraintViolationException.class);
    }
}