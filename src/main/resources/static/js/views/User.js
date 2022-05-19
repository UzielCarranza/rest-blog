import createView from "../createView.js";
import {fetchAction} from "./PostIndex.js";

let page = "/myInformation";

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
    <div id="user-result" class="container">
    </div>

    </body>
    </html>`;

}

export function searchUsername() {
    fetchForUserName();
}


function userInformation(username) {
    console.log(username, "username")
    // language=HTML
    return `
        <div class="form-control mb-4">
            <h1>welcome <span>${username.username}</span></h1>
        </div>
        <form id="user-info-form" class="container ">
            <span id="current-id" data-id="${username.id}"></span>
            <div class="form-control">
                <p class="m-4">Your Current Username is: <span id="current-username">${username.username}</span></p>
                <label for="username-update" class="m-4">Update Your Username</label>
                <input id="username-update" name="username-update" type="text" class="form-control"/>
                <input id="update-username" type="submit" value="Update Username" class="btn btn-primary mt-4"/>
            </div>
            <div class="form-control">
                <p class="m-4">Your Current Email is: <span id="current-email">${username.email}</span></p>
                <label for="email-update" class="m-4">Update Your Email</label>
                <input id="email-update" name="email-update" type="text" class="form-control"/>
                <input id="update-email" type="submit" value="Update Email" class="btn btn-primary mt-4"/>
            </div>
            <div class="form-control">
                <p class="m-4">Your Current Password is: <span id="current-pass">${username.password}</span></p>
                <label for="pass-update" class="m-4">Update Your Password</label>
                <input id="pass-update" name="pass-update" type="text" class="form-control"/>
                <input id="update-pass" type="submit" value="Update Password" class="btn btn-primary mt-4"/>
            </div>
<!--            might not need this code right now, waiting on how the projects develops-->
<!--            <input id="update-btn" type="submit" value="Update" class="btn btn-primary mt-4"/>-->
        </form>
    `
}


// appends form into the form
const appendToBody = (data) => {

    $("#user-result").html(userInformation(data));
    // might not need this code... waiting to see how the project is going to developed
    // getCurrentUserValuesOnClick();

    // event listener for buttons
    updateUsername();
    updateEmail();
    updatePass();
}


const updateUsername = () => {
    $('#update-username').click(function () {
        let userId = $('#current-id').attr('data-id');
        let updatedUsername = $('#username-update').val();

        console.log("Updating username coming soon")
    })
}


const updateEmail = () => {
    $('#update-email').click(function () {
        let userId = $('#current-id').attr('data-id');
        let updatedEmail = $('#email-update').val();
        console.log("Updating email coming soon")
    })
}

const updatePass = () => {
    $('#update-pass').click(function () {
        let userId = $('#current-id').attr('data-id');
        let updatedPass = $('#pass-update').val();
        const userUpdatedInfo = {
            password: `${updatedPass}`
        };

        fetchAction("PATCH", userUpdatedInfo,
            `http://localhost:8080/api/users/${userId}/updatePassword?newPassword=${updatedPass}`,
            page)
    })
}

//////////////////         might not need this code.... waiting to see how the project is going to developed
// Edit actions
//
// const getCurrentUserValuesOnClick = () => {
//     $('#update-btn').click(function () {
//         let currentUsername = $('#current-username').html();
//         let currentEmail = $('#current-email').html();
//         let currentPass = $('#current-pass').html();
//         let userId = $('#current-id').attr('data-id')
//         let updatedUsername = $('#username-update').val();
//         let updatedPass = $('#pass-update').val();
//         let updatedEmail = $('#email-update').val();
//         // do put request
//         if (updatedUsername) {
//             currentUsername = updatedUsername;
//             if (updatedEmail) {
//                 currentEmail = updatedEmail;
//             }
//             if (updatedPass) {
//                 currentPass = updatedPass;
//             }
//             prepareToSentPutRequest(userId, currentUsername, currentEmail, currentPass)
//         }
//     })
// }
//
// // do fetch "PUT" when update button gets click
// const prepareToSentPutRequest = (id, username, email, pass) => {
//     const userUpdatedInfo = {
//         id: `${id}`,
//         username: `${username}`,
//         email: `${email}`,
//         password: `${pass}`
//     };
//     let action = 'PUT';
//     let url = `http://localhost:8080/api/users/${id}`
//     fetchAction(action, userUpdatedInfo, url, page)
// }

/////////////////////////////////////////////////////////////


// Look for user based on username
// can be  refactor to used fetchAction
const fetchForUserName = () => {
    $('#search-username').click(function () {
        let usernameToBeFound = $('#username').val();
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