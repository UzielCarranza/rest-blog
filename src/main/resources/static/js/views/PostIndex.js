import createView from "../createView.js";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header>
            <h1>Posts Page</h1>
        </header>
        <main>
            <div id="posts-container" class="container">
                ${props.posts.map(post => `
             <div class="containe text-center rounded border border-primary m-4">
            <h3 class="content-title">${post.title}</h3>
            <h3 class="content-post">${post.content}</h3>
                    <button class="btn edit-post" data-id="${post.id}">edit</button>
                    <button class="btn delete-post" data-id="${post.id}" >Delete post</button> 
                    </div>
                `).join('')}

            </div>
            <div class="container">
                <form id="create-newPost" class="text-center form-control">
                    <span class="post-update"></span>
                    <label for="post-title">Title:</label>
                    <input type="text" id="post-title" name="post-title" class="form-control" placeholder="Enter Title">
                    <label for="post-content">Content:</label>
                    <textarea type="text" id="post-content" name="post-content" class="form-control"
                              placeholder="Enter comment"
                              rows="4" cols="50"></textarea>
                    <button id="create-post" value="submit" type="submit" ${PostEvent}>button</button>
                </form>
            </div>
        </main>
    `;
}

export function PostEvent() {
    postEventListener();
    editEventListener();
    deleteEventListener();
}

function postEventListener() {
    $("#create-post").click(function () {

            let updatingPost = $('.post-update').attr('data-id')
            console.log(updatingPost)
            const title = $("#post-title").val();
            const content = $("#post-content").val();
            if (updatingPost === undefined) {
                const newPost = {
                    title: `${title}`,
                    content: `${content}`
                };
                let action = 'POST';
                let url = `http://localhost:8080/api/posts`
                fetchAction(action, newPost, url)
            } else {
                const postUpdate = {
                    id: `${updatingPost}`,
                    title: `${title}`,
                    content: `${content}`

                };
                let action = "PUT";
                let url = `http://localhost:8080/api/posts/${updatingPost}`
                fetchAction(action, postUpdate, url)
            }


        }
    )
}


function editEventListener() {
    $(".edit-post").click(function () {
        let postIdToBeUpdate = $(this).attr('data-id');
        let postTitleToBeUpdate = $(this).parent().children('.content-title').html();
        let postCommentToBeUpdate = $(this).parent().children('.content-post').html();
        $("#post-title").val(postTitleToBeUpdate)
        $("#post-content").html(postCommentToBeUpdate)
        $('.post-update').attr('data-id', `${postIdToBeUpdate}`).html("editing: " + postTitleToBeUpdate)
    })
}


function deleteEventListener() {
    $(".delete-post").click(function () {

        let postIdToBeDeleted = $(this).attr('data-id');
        const deleteMovie = {
            id: `${postIdToBeDeleted}`
        }
        let action = 'DELETE'
        let url = `http://localhost:8080/api/posts/${postIdToBeDeleted}`
        fetchAction(action, deleteMovie, url);
    })
}


const fetchAction = (action, postObject, url) => {
    const options = {
        method: action,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(postObject),
    };
    fetch(`${url}`, options)
        .then(res => {
            console.log(res.status);
            createView("/posts")
        }).catch(error => {
        console.log(error);
        createView("/posts");
    });
}