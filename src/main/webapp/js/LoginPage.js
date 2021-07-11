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
        console.log(employee);
        if (employee['employmentClass'] === 'employee') {
            // Go to employee home
            console.log("go to employee home");
            window.location.href = "http://localhost:8080/ReimbursementService/EmployeeHome"
        } else if (employee['employmentClass'] === 'manager') {
            // Go to manager home
            console.log("go to manager home");
            window.location.href = "http://localhost:8080/ReimbursementService/ManagerHome"
        }
    } catch(SyntaxError) {
        document.querySelector('#login-error-box').innerHTML = "Username or password incorrect." +
            " Contact Human Resources for assistance.";
    }
});