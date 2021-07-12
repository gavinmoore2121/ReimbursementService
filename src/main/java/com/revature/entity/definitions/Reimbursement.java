package com.revature.entity.definitions;

import org.hibernate.annotations.Check;

import javax.persistence.*;

/**
 * Class to define reimbursements, both filled and pending.
 * Contains variables to define status and details of the request,
 * along with it's Hibernate mapping.
 */
@Entity
@Table(name= "REIMBURSEMENT")
@Check(constraints= "v_approval_status IN ('approved', 'denied', 'pending')")
public class Reimbursement {
	@Id
	@Column(name="n_reimbursement_id")
	int reimbursementID;
	@ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name= "n_requested_by", referencedColumnName="n_employee_id", 
		columnDefinition="INT", foreignKey=@ForeignKey(name="REIM_EMP_REQUESTED_FK"), nullable=true)
	Employee requestedByEmp;
	@Column(name= "n_amount_requested")
	double amountRequested;
	@Column(name= "v_requested_for")
	String requestedFor;
	@Column(name= "v_approval_status")
	String approvalStatus;
	@Column(name= "d_date_requested")
	String dateRequested;
	@ManyToOne(cascade= {CascadeType.REFRESH, CascadeType.PERSIST}, fetch=FetchType.EAGER)
	@JoinColumn(name="n_reviewed_by", referencedColumnName="n_employee_id", 
		columnDefinition="INT", foreignKey=@ForeignKey(name="REIM_EMP_REVIEWED_FK"), nullable=true)
	Manager reviewedBy;
	@Column(name= "d_date_reviewed")
	String dateReviewed;
	
	
	/**
	 * No-arg constructor
	 */
	public Reimbursement() {
		super();
	}


	/**
	 * Parameterized constructor. Includes manager and date reviewed.
	 * @param reimbursementID
	 * @param requestedByEmp
	 * @param amountRequested
	 * @param requestedFor
	 * @param approvalStatus
	 * @param dateRequested
	 * @param reviewedBy
	 * @param dateReviewed
	 */
	public Reimbursement(int reimbursementID, Employee requestedByEmp, double amountRequested, String requestedFor,
			String approvalStatus, String dateRequested, Manager reviewedBy, String dateReviewed) {
		super();
		this.reimbursementID = reimbursementID;
		this.requestedByEmp = requestedByEmp;
		this.amountRequested = amountRequested;
		this.requestedFor = requestedFor;
		this.approvalStatus = approvalStatus;
		this.dateRequested = dateRequested;
		this.reviewedBy = reviewedBy;
		this.dateReviewed = dateReviewed;
	}
	
	/**
	 * Convert the employee to a Json file as a String.
	 * @return The employee definition as a Json.
	 */
	public String toJson() {
		return "{ "
				+ "\"reimbursementID\" : " + reimbursementID + ","
				+ "\"requestedByEmp\" : " + requestedByEmp.getEmployeeId() + ","
				+ "\"amountRequested\" : " + amountRequested + ","
				+ "\"requestedFor\" : \"" + requestedFor + "\","
				+ "\"approvalStatus\" : \"" + approvalStatus + "\","
				+ "\"dateRequested\" : \"" + dateRequested + "\","
				+ "\"reviewedBy\" : " + getReviewerID() + ","
				+ "\"dateReviewed\" : \"" + dateReviewed + "\""
				+ "} ";
	}

	/**
	 * Small change of getter-method to allow null values when Reimbursement is not yet reviewed.
	 */
	private Integer getReviewerID() {
		if (reviewedBy == null) {
			return null;
		}
		else return reviewedBy.getEmployeeId();
	}
	
	
	
	/**
	 * Constructor for reimbursements not yet approved. Sets approvalStatus to 'pending', 
	 * dateReviewed and reviewedBy to null.
	 * 
	 * @param reimbursementID
	 * @param requestedByEmp
	 * @param amountRequested
	 * @param requestedFor
	 * @param dateRequested
	 */
	public Reimbursement(int reimbursementID, Employee requestedByEmp, double amountRequested, String requestedFor,
			String dateRequested) {
		super();
		this.reimbursementID = reimbursementID;
		this.requestedByEmp = requestedByEmp;
		this.amountRequested = amountRequested;
		this.requestedFor = requestedFor;
		this.dateRequested = dateRequested;
		this.approvalStatus = "pending";
		this.reviewedBy = null;
		this.dateReviewed = null;
	}
	
	@Override
	public String toString() {
		return "Reimbursement [reimbursementID=" + reimbursementID + ", requestedByEmp=" + requestedByEmp
				+ ", amountRequested=" + amountRequested + ", requestedFor=" + requestedFor + ", approvalStatus="
				+ approvalStatus + ", dateRequested=" + dateRequested + ", reviewedBy=" + reviewedBy + ", dateReviewed="
				+ dateReviewed + "]";
	}


	// Setters and getters.
	public int getReimbursementID() {
		return reimbursementID;
	}
	public void setReimbursementID(int reimbursementID) {
		this.reimbursementID = reimbursementID;
	}
	public Employee getRequestedByEmp() {
		return requestedByEmp;
	}
	public void setRequestedByEmp(Employee requestedByEmp) {
		this.requestedByEmp = requestedByEmp;
	}
	public double getAmountRequested() {
		return amountRequested;
	}
	public void setAmountRequested(double amountRequested) {
		this.amountRequested = amountRequested;
	}
	public String getRequestedFor() {
		return requestedFor;
	}
	public void setRequestedFor(String requestedFor) {
		this.requestedFor = requestedFor;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getDateRequested() {
		return dateRequested;
	}
	public void setDateRequested(String dateRequested) {
		this.dateRequested = dateRequested;
	}
	public Manager getReviewedBy() {
		return reviewedBy;
	}
	public void setReviewedBy(Manager reviewedBy) {
		this.reviewedBy = reviewedBy;
	}
	public String getDateReviewed() {
		return dateReviewed;
	}
	public void setDateReviewed(String dateReviewed) {
		this.dateReviewed = dateReviewed;
	}
	
	

}
