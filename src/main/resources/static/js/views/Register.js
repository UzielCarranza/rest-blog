import createView from "../createView.js";
import {fetchAction} from "./PostIndex.js";

export default function Register(props) {

    //language=HTML
    return `<!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8"/>
        <title>Register Page</title>
    </head>
    <body>
    <section class="container d-flex flex-column justify-content-center">
        <h1 class="text-center mt-4 mb-4">Register with us</h1>

        <form id="Register-form" class="container w-50 mt-4 mb-4">
            <div class="form-group mb-4">
                <label for="username">Username</label>
                <input id="username" name="username" type="text" class="form-control"
                       placeholder="Enter your username"/>
            </div>

            <div class="form-group mb-4">
                <label for="email">Enter your email:</label>
                <input pattern=".+@globex\\.com" size="30" type="email" id="email" name="email" class="form-control"
                       placeholder="Enter your email" required>
            </div>


            <div class="form-group mb-4">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" autocomplete="on" class="form-control"
                       placeholder="Enter a password"/>
            </div>
            <div class="container mt-2 mb-2 p-0">
                <input id="register-btn" type="submit" value="Register" class="btn btn-primary mr-4"/>
                <input id="reset-btn" type="reset" value="reset" class="btn btn-primary ml-4"/>
            </div>
        </form>
    </section>
    </body>
    </html>`;

}

export function RegisterEvent() {
    registerButton();
    resetButton();
}


const registerButton = () => {
    $('#register-btn').click(function () {
        fetchAction("POST", {
            username: $("#username").val(),
            email: $("#email").val(),
            password: $("#password").val(),

        }, "http://localhost:8080/api/users/create", "/")
    });
}

const resetButton = () => {
    $('#reset-btn').click(function () {
        console.log("click")
        $("#username").val("");
        $("#email").val("")
        $("#password").val("")
    })
}