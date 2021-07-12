package com.revature.entity.definitions;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


// General Employee class, for anyone working at company.
@Entity
@Table(name="EMPLOYEE")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="v_position", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value="employee")
public class Employee {
	
	@Id
	@Column(name= "n_employee_id")
	private int employeeId;
	@NaturalId
	@Column(name= "v_email", unique = true)
	private String empEmail;
	@Column(name= "v_password")
	private String empPassword;
	@Column(name= "v_first_name")
	private String empFirstName;
	@Column(name= "v_middle_init")
	private char empMiddleInitial;
	@Column(name= "v_last_name")
	private String empLastName;
	@Column(name= "v_address")
	private String empAddress;
	@Column(name= "n_bank_account_num")
	private int empBankNum;
	@Column(name= "n_salary")
	private double empYearlySalary;
	@Column(name= "d_hire_date")
	private String empHireDate;
	
	
	/**
	 * No-arg constructor.
	 */
	public Employee() {
		super();
	}


	/**
	 *
	 * @param employeeId
	 * @param empEmail
	 * @param empPassword
	 * @param empFirstName
	 * @param empMiddleInitial
	 * @param empLastName
	 * @param empAddress
	 * @param empBankNum
	 * @param empYearlySalary
	 * @param empHireDate
	 */
	public Employee(int employeeId, String empEmail, String empPassword, String empFirstName,
			char empMiddleInitial, String empLastName, String empAddress, int empBankNum, double empYearlySalary,
			String empHireDate) {
		super();
		this.employeeId = employeeId;
		this.empEmail = empEmail;
		this.empPassword = empPassword;
		this.empFirstName = empFirstName;
		this.empMiddleInitial = empMiddleInitial;
		this.empLastName = empLastName;
		this.empAddress = empAddress;
		this.empBankNum = empBankNum;
		this.empYearlySalary = empYearlySalary;
		this.empHireDate = empHireDate;
	}
	

	/**
	 * Generated toString method.
	 */
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", email=" + empEmail + ", empPassword=" + empPassword
				+ ", empEmail=" + empEmail + ", empFirstName=" + empFirstName + ", empMiddleInitial=" + empMiddleInitial
				+ ", empLastName=" + empLastName + ", empAddress=" + empAddress + ", empBankNum=" + empBankNum
				+ ", empYearlySalary=" + empYearlySalary + ", empHireDate=" + empHireDate + "]";
	}
	
	/**
	 * Convert the employee to a Json file as a String.
	 * @return The employee definition as a Json.
	 */
	public String toJson() {
		return "{ "
				+ "\"userId\" : " + employeeId + ", "
				+ "\"userName\" : \"" + empEmail + "\", "
				+ "\"password\" : \"" + empPassword + "\", "
				+ "\"firstName\" : \"" + empFirstName + "\", "
				+ "\"middleInit\" : \"" + empMiddleInitial + "\", "
				+ "\"lastName\" : \"" + empLastName + "\", "
				+ "\"address\" : \"" + empAddress + "\", "
				+ "\"empBankNum\" : "  + empBankNum + ", "
				+ "\"empYearlySalary\" : " + empYearlySalary + ", "
				+ "\"empHireDate\" : \"" + empHireDate + "\", "
				+ "\"employmentClass\" : \"employee\""
				+ "} ";
	}


	// Setters and getters
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	
	public String getEmpFirstName() {
		return empFirstName;
	}
	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}
	public char getEmpMiddleInitial() {
		return empMiddleInitial;
	}
	public void setEmpMiddleInitial(char empMiddleInitial) {
		this.empMiddleInitial = empMiddleInitial;
	}
	public String getEmpLastName() {
		return empLastName;
	}
	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public int getEmpBankNum() {
		return empBankNum;
	}
	public void setEmpBankNum(int empBankNum) {
		this.empBankNum = empBankNum;
	}
	public double getEmpYearlySalary() {
		return empYearlySalary;
	}
	public void setEmpYearlySalary(double empYearlySalary) {
		this.empYearlySalary = empYearlySalary;
	}
	public String getEmpHireDate() {
		return empHireDate;
	}
	public void setEmpHireDate(String empHireDate) {
		this.empHireDate = empHireDate;
	}
}
