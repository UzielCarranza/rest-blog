export default function Login(props) {
    // language=HTML
    return `<!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8"/>
        <title>Log In</title>
    </head>
    <body>
    <h1 class="container text-center mt-4 mb-4">Log In</h1>
    <section class="d-flex justify-content-center">
        <form id="login-form" class="d-flex flex-column w-50 align-items-center">
            <div class="form-group w-50 text-center">
                <label for="username" class="mt-4">Username</label>
                <input id="username" name="username" type="text" class="form-control mt-4 mb-1"
                       placeholder="Enter your username"/>
            </div>

            <div class="form-group w-50 text-center">
                <label for="password" class="mt-4">Password</label>
                <input id="password" name="password" type="password" class="form-control mt-4 mb-1"
                       placeholder="Enter your password">
                <input id="login-btn" type="submit" value="Log In" class="btn btn-primary mt-4"/>
            </div>
        </form>
    </section>
    </body>
    </html>`;

}


