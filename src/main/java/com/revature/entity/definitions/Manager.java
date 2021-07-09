package com.revature.entity.definitions;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="manager")
public class Manager extends Employee {
	
	@Column(name= "d_promotion_date")
	private String mngrPromotionDate;
	
	
	
	/**
	 * No-arg constructor.
	 */
	public Manager() {
		super();
	}



	/**
	 * Parameterized constructor.
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
	 * @param mngrPromotionDate
	 */
	public Manager(int employeeId, String empEmail, String empPassword, String empFirstName,
			char empMiddleInitial, String empLastName, String empAddress, int empBankNum, double empYearlySalary,
			String empHireDate, String mngrPromotionDate) {
		super(employeeId, empEmail, empPassword,  empFirstName, empMiddleInitial, empLastName, empAddress, empBankNum,
				empYearlySalary, empHireDate);
		this.mngrPromotionDate=mngrPromotionDate;
	}



	@Override
	public String toString() {
		return "Manager [mngrPromotionDate=" + mngrPromotionDate + ", toString()=" + super.toString()
				+ ", getEmployeeId()=" + getEmployeeId() + ", getEmpEmail()=" + getEmpEmail() + ", getEmpPassword()="
				+ getEmpPassword() +  ", getEmpFirstName()=" + getEmpFirstName()
				+ ", getEmpMiddleInitial()=" + getEmpMiddleInitial() + ", getEmpLastName()=" + getEmpLastName()
				+ ", getEmpAddress()=" + getEmpAddress() + ", getEmpBankNum()=" + getEmpBankNum()
				+ ", getEmpYearlySalary()=" + getEmpYearlySalary() + ", getEmpHireDate()=" + getEmpHireDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}



	
	
	

}
