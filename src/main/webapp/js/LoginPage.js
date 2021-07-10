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
});