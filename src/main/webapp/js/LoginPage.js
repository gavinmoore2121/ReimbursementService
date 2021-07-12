document.querySelector('#login').addEventListener('submit', async function (event) {
    // Prevent default form submission
    event.preventDefault();
    console.log('button clicked');
    // Get user input name and password
    let username = document.querySelector('#username-field').value;
    let password = document.querySelector('#password-field').value;
    console.log(username);
    console.log(password);

    let user = {
        username: username,
        password: password
    }

    // Send credentials to servlet for validation
    let response = await fetch("http://localhost:8080/ReimbursementService/SubmitLogin", {
        method: 'POST',
        headers: {
            'Content-Type': 'text/plain'
        },
        body: username + '\n' + password,
    });
    let result = await response.text();
    console.log(result);

    try {
        let employee = JSON.parse(result);
        if (employee['employmentClass'] === 'employee') {
            // Set cookies, expire in 1 day.
            setCookie("username",employee['userName'],1);
            setCookie("position", employee['employmentClass'], 1);
            // Go to employee home
            window.location.href = "http://localhost:8080/ReimbursementService/EmployeeHome"
        } else if (employee['employmentClass'] === 'manager') {
            // Set cookies, expire in 1 day.
            setCookie("username",employee['userName'],1);
            setCookie("position", employee['employmentClass'], 1);
            // Go to manager home
            console.log("go to manager home");
            window.location.href = "http://localhost:8080/ReimbursementService/ManagerHome"
        }
    } catch(SyntaxError) {
        document.querySelector('#login-error-box').innerHTML = "Username or password incorrect." +
            " Contact Human Resources for assistance.";
    }
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

// Check if already logged in, redirect if appropriate.
let cookie = window.getCookie("position");
// Confirm employee is a manager, otherwise redirect.
if (cookie === "manager") {
    window.location.href = "http://localhost:8080/ReimbursementService/ManagerHome"
}
else if (cookie === "employee") {
    window.location.href = "http://localhost:8080/ReimbursementService/EmployeeHome"
}