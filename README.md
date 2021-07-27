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
