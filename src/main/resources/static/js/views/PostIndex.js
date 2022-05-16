export default function PostIndex(props) {
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3>${post.title}</h3> <h3>${post.content}here</h3>`).join('')}
             
      
            </div>
        </main>
    `;
}