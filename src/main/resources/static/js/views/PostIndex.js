import createView from "../createView.js";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container">
                ${props.posts.map(post => ` <div class="container">
            <h3 class="content-title">${post.title}</h3>
            <h3 class="content-post">${post.content}</h3>
                    <button class="btn edit-post" data-id="${post.id}">edit</button>
                    <button class="btn delete-post" data-id="${post.id}" >Delete post</button> 
                    </div>
                `).join('')}

            </div>
        </main>
        <form id="create-newPost" class="text-center">
            <label for="post-title">Title:</label>
            <input type="text" id="post-title" name="post-title">
            <label for="post-content">Content:</label>
            <input type="text" id="post-content" name="post-content">
            <button id="create-post" value="submit" type="submit" ${PostEvent}>button</button>
        </form>

    `;
}

export function PostEvent() {
    postEventListener();
    editEventListener();
    deleteEventListener();
}

function postEventListener() {
    $("#create-post").click(function () {
        const content = $("#post-title").val();
        const title = $("#post-content").val();
        const newPost = {
            title: `${title}`,
            content: `${content}`

        };
        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newPost),
        };
        fetch("http://localhost:8080/api/posts", options)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });
    })
}


function editEventListener() {
    $(".edit-post").click(function () {
        let postIdToBeUpdate = $(this).attr('data-id');
        console.log(postIdToBeUpdate)

        let postTitleToBeUpdate = $(this).parent().children('.content-title').html();

        let postCommentToBeUpdate = $(this).parent().children('.content-post').html();
        let title = prompt('enter new title');

        let comment = prompt('enter new comment');

        if (title) {
            postTitleToBeUpdate = title;
        }
        if (comment) {
            postCommentToBeUpdate = comment;
        }
        const updatedPost = {
            id: `${postIdToBeUpdate}`,
            title: `${postTitleToBeUpdate}`,
            content: `${postCommentToBeUpdate}`
        };
        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(updatedPost),
        };
        fetch(`http://localhost:8080/api/posts/${postIdToBeUpdate}`, options)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });

    })
}


function deleteEventListener() {
    $(".delete-post").click(function () {

        let postIdToBeDeleted = $(this).attr('data-id');
        const deleteMovie = {
            id: `${postIdToBeDeleted}`
        }
        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(deleteMovie),
        };
        fetch(`http://localhost:8080/api/posts/${postIdToBeDeleted}`, options)
            .then(res => {
                console.log(res.status);
                createView("/posts")
            }).catch(error => {
            console.log(error);
            createView("/posts");
        });
    })
}
