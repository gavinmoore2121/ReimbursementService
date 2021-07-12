// Create all-reimbursements button
document.querySelector('#all-reimbursements').addEventListener('click', async function (event) {
    let response = await fetch("http://localhost:8080/ReimbursementService/ViewReimbursements");
    let result = await response.text();
    console.log(JSON.parse(result));
    let reims = JSON.parse(result);

    let t = document.querySelector("#display-table");

    // Delete old rows
    t.innerHTML = "";

    // Create head of table
    let row = t.insertRow(-1);
    row.insertCell(-1).appendChild(document.createTextNode("ReimbursementID"));
    row.insertCell(-1).appendChild(document.createTextNode("Requested By"));
    row.insertCell(-1).appendChild(document.createTextNode("Amount Requested"));
    row.insertCell(-1).appendChild(document.createTextNode("Date Requested"));
    row.insertCell(-1).appendChild(document.createTextNode("Approval Status"));
    row.insertCell(-1).appendChild(document.createTextNode("Reviewed By"));
    row.insertCell(-1).appendChild(document.createTextNode("Date Reviewed"));


    reims['reimbursements'].forEach(reim => {
        let row = t.insertRow(-1);
        row.insertCell(-1).appendChild(document.createTextNode(reim['reimbursementID']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['requestedByEmp']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['amountRequested']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['dateRequested']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['approvalStatus']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['dateReviewed']));

    });
});
// Create view-employees button
document.querySelector('#view-employees').addEventListener('click', async function (event) {
    let response = await fetch("http://localhost:8080/ReimbursementService/ViewEmployees");
    let result = await response.text();
    console.log(JSON.parse(result));
    let emps = JSON.parse(result);

    let t = document.querySelector("#display-table");

    // Delete old rows
    t.innerHTML = "";

    // Create head of table
    let row = t.insertRow(-1);
    row.insertCell(-1).appendChild(document.createTextNode("First Name"));
    row.insertCell(-1).appendChild(document.createTextNode("Middle Initial"));
    row.insertCell(-1).appendChild(document.createTextNode("Last Name"));
    row.insertCell(-1).appendChild(document.createTextNode("Email"));
    row.insertCell(-1).appendChild(document.createTextNode("Position"));
    row.insertCell(-1).appendChild(document.createTextNode("Salary"));

    emps['employees'].forEach(emp => {
        let row = t.insertRow(-1);
        row.insertCell(-1).appendChild(document.createTextNode(emp['firstName']));
        row.insertCell(-1).appendChild(document.createTextNode(emp['middleInit']));
        row.insertCell(-1).appendChild(document.createTextNode(emp['lastName']));
        row.insertCell(-1).appendChild(document.createTextNode(emp['userName']));
        row.insertCell(-1).appendChild(document.createTextNode(emp['employmentClass']));
        row.insertCell(-1).appendChild(document.createTextNode(emp['empYearlySalary']));

    });
});
// Create view-account button
document.querySelector('#view-account').addEventListener('click', async function (event) {
    window.location.href = "http://localhost:8080/ReimbursementService/";
});
// Create logout button
document.querySelector('#logout').addEventListener('click', async function (event) {
    window.location.href = "http://localhost:8080/ReimbursementService/";
});