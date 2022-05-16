export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3>${post.title}</h3> <h3>${post.content}here</h3>`).join('')}
             
      
            </div>
            
            <div id="form-container" class="container text-center">
            <h1>Create a new post</h1>
            <form action="" action="" id="create-newPost" class="text-center">
                 <li class="justify-center text-decoration-none">
                    <label for="post-title">Title:</label>
                    <input type="text" id="post-title" name="post-title">
                 </li>
                 <li class="justify-center text-decoration-none">
                    <label for="post-content">Content:</label>
                    <input type="text" id="post-content" name="post-content">
                 </li>
            </ul>
            </form>
            </div>
        </main>
    `;
}