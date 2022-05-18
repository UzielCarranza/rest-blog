import createView from "../createView.js";

export default function Register(props) {

    //language=HTML
    return `<!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8"/>
        <title>Register Page</title>
    </head>
    <body>
    <h1>Register with us</h1>

    <form id="Register-form" class="container">
        <div class="form-group mb-2">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" class="form-control" placeholder="Enter your username"/>
        </div>

        <div class="form-group mb-2">
            <label for="email">Enter your email:</label>
            <input pattern=".+@globex\\.com" size="30" type="email" id="email" name="email" class="form-control"
                   placeholder="Enter your email" required>
        </div>


        <div class="form-group mb-2">
            <label for="password">Password</label>
            <input id="password" name="password" type="password" autocomplete="on" class="form-control"
                   placeholder="Enter a password"/>
        </div>
        <input id="register-btn" type="submit" value="Register" class="btn btn-primary mt-4"/>
    </form>
    </body>
    </html>`;

}

export function RegisterEvent() {
    $('#register-btn').click(function () {
        let username = $("#username").val();
        let password = $("#password").val();
        let email = $("#email").val();
        const newUser = {
            username: `${username}`,
            email: `${email}`,
            password: `${password}`,

        };
        let request = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(newUser),
        };

        fetch("http://localhost:8080/api/users", request)
            .then((response) => {
                console.log(response.status)
                createView("/");
            });
    })

}