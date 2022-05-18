import createView from "../createView.js";

export default function myInformation(props) {
    //language=HTML
    return `<!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8"/>
        <title>myInformation Page</title>
    </head>
    <body>
    <form id="get-username" class="container">
        <h1 class="mb-4">Enter your information</h1>
        <div class="form-group mb-2">
            <label for="username">Enter your username</label>
            <input id="username" name="username" type="text" class="form-control"
                   placeholder="Your Username goes here..."/>
            <button id="search-username" class="btn btn-primary mt-4 mb-4" type="submit">Search</button>
        </div>
    </form>

    </body>
    </html>`;

}

export function searchUsername() {
    getFormValuesOnClick();
    fetchForUserName();
}


function userInformation(username) {
    // language=HTML
    return `
        <div class="form-control mb-4">
            <h1>welcome <span>${username.username}</span></h1>
        </div>
        <form id="user-info-form" class="container ">
            <div class="form-control">
                <p class="m-4">Your Current Username is: <span id="current-username">${username.username}</span></p>
                <label for="username-name" class="m-4">Update Your Username</label>
                <input id="username-name" name="username-name" type="text" class="form-control"/>
            </div>
            <div class="form-control">
                <p class="m-4">Your Current Email is: <span id="current-email">${username.email}</span></p>
                <label for="username-name" class="m-4">Update Your Email</label>
                <input id="user-email" name="username-email" type="text" class="form-control"/>
            </div>
            <div class="form-control">
                <p class="m-4">Your Current Password is: <span id="current-pass">${username.password}</span></p>
                <label for="username-name" class="m-4">Update Your Password</label>
                <input id="username--pass" name="username-pass" type="text" class="form-control"/>
            </div>
            <input id="update-btn" type="submit" value="Update" class="btn btn-primary mt-4"/>
        </form>
    `
}

const appendToBody = (data) => {

    $("#get-username").append(userInformation(data));
}

const getFormValuesOnClick = () => {
    $('#update-btn').click(function () {
        console.log("hello")
    })
}

const fetchForUserName = () => {
    $('#search-username').click(function () {
        let usernameToBeFound = $('#username').val();
        console.log(usernameToBeFound)
        const options = {
            method: 'GET',
        };
        fetch(`http://localhost:8080/api/users/username/${usernameToBeFound}`, options)
            .then(res =>
                res.json()
            )
            .then(data => {
                appendToBody(data)
            })
            .catch(error => {
                console.log(error);
                createView("/myInformation");
            });
    })
}