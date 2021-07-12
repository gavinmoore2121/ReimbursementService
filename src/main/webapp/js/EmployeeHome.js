// Create my-reimbursements button
document.querySelector('#my-reimbursements').addEventListener('click', async function (event) {
    document.querySelector('#dialog').innerHTML = ""
    window.location.href = "..";
});
// Create new-reimbursements button
document.querySelector('#new-reimbursement').addEventListener('click', async function (event) {
    document.querySelector('#dialog').innerHTML = ""
    // Select and clear display table
    let t = document.querySelector("#display-table");
    t.innerHTML = ""

    // Create form
    let row = t.insertRow(-1);
    row.insertCell(-1).appendChild(document.createTextNode("Amount to Request"));
    let currentElement = row.insertCell(-1).appendChild(document.createElement("input"));
    currentElement.setAttribute("id", "amount-requested-field");
    currentElement.setAttribute("required", "required");
    currentElement.setAttribute("type", "number");

    row = t.insertRow(-1);
    row.insertCell(-1).appendChild(document.createTextNode("Reason for Request"));
    currentElement = row.insertCell(-1).appendChild(document.createElement('input'));
    currentElement.setAttribute("id", "reason-field");
    currentElement.setAttribute("required", "required");
    currentElement.setAttribute("type", "text");

    // Create form submission button and eventListener
    row = t.insertRow(-1);
    currentElement = row.insertCell(-1).appendChild(document.createElement('button'));
    currentElement.setAttribute("id", "reim-submit-button");
    currentElement.setAttribute('type', 'button');
    currentElement.innerHTML = "Submit";
    currentElement.addEventListener('click', async function (event) {
        let amountRequested = document.querySelector('#amount-requested-field').value;
        let requestReason = document.querySelector('#reason-field').value;
        let username = window.getCookie("username");

        // Get current date in proper SQLOracle format.
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
        let response = await fetch("http://localhost:8080/ReimbursementService/RequestReimbursement", {
            method: 'POST',
            headers: {
                'Content-Type': 'text/plain'
            },
            body: username + '\n' + amountRequested + '\n' + requestReason + '\n' + date,
        });
        console.log(response.text());
        document.querySelector('#dialog').innerHTML = "Request Submitted."
    });
});

// Create view-account button
document.querySelector('#view-account').addEventListener('click', async function (event) {
    document.querySelector('#dialog').innerHTML = ""
    window.location.href = "..";
});
// Create logout button
document.querySelector('#logout').addEventListener('click', async function (event) {
    // Clear cookies, redirect to login page.
    setCookie("username",'',0);
    setCookie("position", '', 0);
    window.location.href = "http://localhost:8080/ReimbursementService/"
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

// Check if employee is logged in, otherwise redirect.
let cookie = window.getCookie("position");
// Confirm employee is a manager, otherwise redirect.
if (cookie == null) window.location.href = "http://localhost:8080/ReimbursementService/"
else if (cookie === "manager") window.location.href = "http://localhost:8080/ReimbursementService/ManagerHome"
else if (cookie === "employee") {
    console.log("logged in as employee " + getCookie('username'));
}