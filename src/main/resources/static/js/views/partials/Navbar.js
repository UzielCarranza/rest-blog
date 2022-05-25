export default function Navbar(props) {
    // language=HTML
    return `
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse d-flex justify-content-center" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-item nav-link active" href="/" data-link>Home <span
                            class="sr-only">(current)</span></a>
                    <a class="nav-item nav-link" href="/posts" data-link>Posts</a>
                    <a class="nav-item nav-link" href="/about" data-link>About</a>
                    <a class="nav-item nav-link" href="/login" data-link>Login</a>

                    <a class="nav-item nav-link" href="/register" data-link>Register</a>
                    <a class="nav-item nav-link" href="/myInformation" data-link>My Information</a>
                </div>
            </div>
        </nav>
    `;
}