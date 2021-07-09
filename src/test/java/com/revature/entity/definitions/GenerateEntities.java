package com.revature.entity.definitions;

public interface GenerateEntities {
	// Utility methods for entity creation
		static Employee createTestEmployee() {
			return new Employee(99999, "testEmpEmail@company.com", "testEmpPassword",  "testEmpFName", 'E', "testEmpLName", 
					"01-JAN-2000", 123456789, 100000.0, "02-JAN-2001");
		}
		
		static Associate createTestAssociate() {
			return new Associate(99998, "testAssocEmail@company.com", "testAssocPassword", "testAssocFName", 'A', "testAssocLName", 
					"03-JAN-2000", 101112131, 150000.0, "04-JAN-2001");
		}
		
		static Manager createTestManager() {
			return new Manager(99997, "testMngrEmail@company.com", "testMngrPassword",  "testFName", 'M', "testMngrLName", 
					"05-JAN-2000", 415161718, 200000.0, "06-JAN-2001", "07-JAN-2001");
		}
		static Reimbursement createTestReimbursementPending(Employee requestedBy) {
			return new Reimbursement(99999, requestedBy, 100.0, "testReason", "08-JAN-2001");
		}
		static void setTestReimbursementToApproved(Reimbursement reim, Manager approvedBy) {
			reim.setApprovalStatus("approved");
			reim.setReviewedBy(approvedBy);
			reim.setDateReviewed("09-JAN-2001");
		}
		
		static void setTestReimbursementToDenied(Reimbursement reim, Manager deniedBy) {
			reim.setApprovalStatus("denied");
			reim.setReviewedBy(deniedBy);
			reim.setDateReviewed("09-JAN-2001");
		}
}