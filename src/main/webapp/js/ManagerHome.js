// Create all-reimbursements button
document.querySelector('#all-reimbursements').addEventListener('click', renderReimbursements);
async function renderReimbursements (event) {
    // Get list of reimbursements from server
    let response = await fetch("http://localhost:8080/ReimbursementService/ViewReimbursements");
    let result = await response.text();
    let reims = JSON.parse(result);

    // Select display table
    let t = document.querySelector("#display-table");

    // Delete old rows
    t.innerHTML = "";

    // Create head of table
    let row = t.insertRow(-1);
    row.insertCell(-1).appendChild(document.createTextNode("ReimbursementID"));
    row.insertCell(-1).appendChild(document.createTextNode("Requested By"));
    row.insertCell(-1).appendChild(document.createTextNode("Amount Requested"));
    row.insertCell(-1).appendChild(document.createTextNode("Reason For Request"));
    row.insertCell(-1).appendChild(document.createTextNode("Date Requested"));
    row.insertCell(-1).appendChild(document.createTextNode("Approval Status"));
    row.insertCell(-1).appendChild(document.createTextNode("Reviewed By"));
    row.insertCell(-1).appendChild(document.createTextNode("Date Reviewed"));
    row.insertCell(-1).appendChild(document.createTextNode("Approve?"));
    row.insertCell(-1).appendChild(document.createTextNode("Deny?"));

    // Create a row for each reimbursement and fill it.
    reims['reimbursements'].forEach(reim => {
        let currentElement;
        let row = t.insertRow(-1);
        row.insertCell(-1).appendChild(document.createTextNode(reim['reimbursementID']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['requestedByEmp']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['amountRequested']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['requestedFor']))
        row.insertCell(-1).appendChild(document.createTextNode(reim['dateRequested']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['approvalStatus']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['reviewedBy']));
        row.insertCell(-1).appendChild(document.createTextNode(reim['dateReviewed']));
        currentElement = row.insertCell(-1).appendChild(document.createElement("button"));
        currentElement.setAttribute("id", reim['reimbursementID']);
        currentElement.setAttribute("onclick", "approveRequest()");
        currentElement.innerHTML = 'Approve'
        currentElement = row.insertCell(-1).appendChild(document.createElement("button"));
        currentElement.setAttribute("onclick","denyRequest()");
        currentElement.setAttribute("id", reim['reimbursementID']);
        currentElement.innerHTML = 'Deny'
    });
}

async function approveRequest () {
    let reimID = event.target.id;
    // Get current date and manager username
    let d = new Date();
    let months = {
        0 :'JAN',
        1 :'FEB',
        2 :'MAR',
        3 :'APR',
        4 :'MAY',
        5 :'JUN',
        6 :'JUL',
        7 :'AUG',
        8 :'SEP',
        9 :'OCT',
        10 :'NOV',
        11 :'DEC'
    }
    let date = d.getDate() + '-' + months[d.getMonth()] + '-' + d.getFullYear();
    let username = window.getCookie('username');
    let response = await fetch('http://localhost:8080/ReimbursementService/ReviewReimbursement', {
        method: 'POST',
        headers:  {
        'Content-Type': 'text/plain'
        },
        body: 'approved\n' + reimID + '\n' + username + '\n' + date
    });
    console.log(await response.text());
    await renderReimbursements();
}

async function denyRequest () {
    let reimID = event.target.id;
    // Get current date and manager username
    let d = new Date();
    let months = {
        0 :'JAN',
        1 :'FEB',
        2 :'MAR',
        3 :'APR',
        4 :'MAY',
        5 :'JUN',
        6 :'JUL',
        7 :'AUG',
        8 :'SEP',
        9 :'OCT',
        10 :'NOV',
        11 :'DEC'
    }
    let date = d.getDate() + '-' + months[d.getMonth()] + '-' + d.getFullYear();
    let username = window.getCookie('username');
    let response = await fetch('http://localhost:8080/ReimbursementService/ReviewReimbursement', {
        method: 'POST',
        headers:  {
            'Content-Type': 'text/plain'
        },
        body: 'denied\n' + reimID + '\n' + username + '\n' + date
    });
    console.log(await response.text());
    await renderReimbursements();
}



// Create view-employees button
document.querySelector('#view-employees').addEventListener('click', async function (event) {
    let response = await fetch("http://localhost:8080/ReimbursementService/ViewEmployees");
    let result = await response.text();
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

/**
 * Create cookie with value given.
 * @param name name of cookie
 * @param value value of cookie
 * @param days days until cookie expires
 */
function setCookie(name,value,days) {
    let expires = "";
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

/**
 * Retrieve a cookie with a given name
 * @param name cookie to get
 * @returns {string|null} value of cookie, or null.
 */
function getCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for(let i=0;i < ca.length;i++) {
        let c = ca[i];
        while (c.charAt(0)===' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

// Create view-account button
document.querySelector('#view-account').addEventListener('click', async function (event) {
    let response = await fetch("http://localhost:8080/ReimbursementService/ViewEmployees", {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: window.getCookie('username'),
    });
    let result = await response.text();
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
// Create logout button
document.querySelector('#logout').addEventListener('click', async function (event) {
    setCookie("username",'',0);
    setCookie("position", '', 0);
    window.location.href = "http://localhost:8080/ReimbursementService/"
});

// Check cookies and confirm user is allowed on page.
let cookie = window.getCookie("position");
// Confirm employee is a manager, otherwise redirect.
if (cookie == null) window.location.href = "http://localhost:8080/ReimbursementService/"
else if (cookie === "employee") window.location.href = "http://localhost:8080/ReimbursementService/EmployeeHome"
else if (cookie === "employee") {
    console.log("logged in as manager " + getCookie('username'));
}
