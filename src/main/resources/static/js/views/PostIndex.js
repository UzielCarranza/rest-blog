export default function PostIndex(props) {

    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => `<h3><button class="delete-post">Delete post</button> ${post.title}</h3> <h3 class="edit-post">${post.content}here</h3>
                    <button class="edit-post">edit</button>
                `).join('')}

            </div>
        </main>
        <form id="create-newPost" class="text-center">
            <label for="post-title">Title:</label>
            <input type="text" id="post-title" name="post-title">
            <label for="post-content">Content:</label>
            <input type="text" id="post-content" name="post-content">
            <button id="create-form" value="submit" type="submit" ${PostEvent}>button</button>
        </form>

    `;
}

export function PostEvent() {
    postEventListener();
    editEventListener();
    deleteEventListener();
}

function postEventListener() {
    $("#create-form").click(function () {
        const content = $("#post-title").val();
        const title = $("#post-content").val();
        console.log(content, title)
    })
}


function editEventListener() {
    $(".edit-post").click(function () {
        alert("edit")
    })
}


function deleteEventListener() {
    $(".delete-post").click(function () {
        alert("delete")
    })
}
