package com.revature.entity.definitions;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class to define the Associate table. Deprecated.
 */
@Entity
@DiscriminatorValue(value="associate")
public class Associate extends Employee {

	/**
	 * No-arg constructor
	 */
	public Associate() {
		super();
	}

	/**
	 * Parameterized constructor. Set position to associate.
	 * @param employeeId
	 * @param username
	 * @param empPassword
	 * @param empEmail
	 * @param empFirstName
	 * @param empMiddleInitial
	 * @param empLastName
	 * @param empAddress
	 * @param empBankNum
	 * @param empYearlySalary
	 * @param empHireDate
	 */
	public Associate(int employeeId, String empEmail, String empPassword,  String empFirstName,
			char empMiddleInitial, String empLastName, String empAddress, int empBankNum, double empYearlySalary,
			String empHireDate) {
		super(employeeId, empEmail, empPassword,  empFirstName, empMiddleInitial, empLastName, empAddress, empBankNum,
				empYearlySalary, empHireDate);
	}

	/**
	 * Generated toString method.
	 */
	@Override
	public String toString() {
		return "Associate [toString()=" + super.toString() + ", getEmployeeId()=" + getEmployeeId() 
				+ ", getEmpEmail()=" + getEmpEmail() + ", getEmpPassword()=" + getEmpPassword() 
				+ ", getEmpFirstName()=" + getEmpFirstName() + ", getEmpMiddleInitial()=" + getEmpMiddleInitial()
				+ ", getEmpLastName()=" + getEmpLastName() + ", getEmpAddress()=" + getEmpAddress()
				+ ", getEmpBankNum()=" + getEmpBankNum() + ", getEmpYearlySalary()=" + getEmpYearlySalary()
				+ ", getEmpHireDate()=" + getEmpHireDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}

}
