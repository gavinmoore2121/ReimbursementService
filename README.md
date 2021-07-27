# EMPLOYEE REIMBURSEMENT SERVICE

## Project Description
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Tehcnologies Used
* Java v8
* HTML
* JavaScript
* Maven v1.8
* Log4J v1.2
* JUnit v5.7
* Javax Servlet v4.0
* Hibernate v5.5


## Features
* Persistent database of employees, and improved and denied reimbursements.
* All base-level employees can submit reimbursement requests, which can be reviewed by manager-status employees.
* Reimbursements are persistented and cannot be removed to ensure proper financial records.
* A logging system monitors all database transactions made.
* Errors are logged to a separate file to ensure unexpected errors can be reviewed.
* Rudimentary webpage for demonstration purposes.

To-do
* Refactor the html webpage for an easier and more pleasant user experience.
* Add a password-reset system to allow users to request new passwords from managers.
* Reconfigure the database to contain the salted hashes of user passwords.
* Refactor database connection details to enable a custom configuration file.

## Getting Started
1. Clone to desired work directory
2. For security purposes, Cloud database details are not included in this file. 
3. Create a hibernate.cfg.xml file with the required parameters for a Hibernate database connection to desired database.
4. Launch project packaged as war file in TomCat server.

## Usage
Enter user credentials.
Employees will be redirected to the employee homepage, Managers will be redirected to the manager homepage.
Employees can view and edit their profile, and submit reimbursements as desired.
Managers can view all employees, edit their profiles, and review reimbursements as desired.
