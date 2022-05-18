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
                    <button class="btn edit-post  btn-primary mt-4 mb-1" data-id="${post.id}"> <i class="fa-solid fa-pencil"></i>  Edit Post</button>
                    <button class="btn delete-post  btn-primary mt-4 mb-1" data-id="${post.id}" >Delete Post  <i class="fa-solid fa-trash-can"></i></button> 
                    </div>
                `).join('')}

            </div>
            <div class="container">
                <form id="create-newPost" class="text-center form-control">
                    <span class="post-update form-control"></span>
                    <label for="post-title">Title:</label>
                    <input type="text" id="post-title" name="post-title" class="form-control" placeholder="Enter Title">
                    <label for="post-content">Content:</label>
                    <textarea type="text" id="post-content" name="post-content" class="form-control"
                              placeholder="Enter comment"
                              rows="4" cols="50"></textarea>
                    <button class="btn btn-primary mt-4 mb-1" id="create-post" value="submit" type="submit"
                            ${PostEvent}>button
                    </button>
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

            // when click, grab the data-id attribute
            let updatingPost = $('.post-update').attr('data-id')

            //get current title and content values
            const title = $("#post-title").val();
            const content = $("#post-content").val();
            //

            // check if data-id is undefined
            if (updatingPost === undefined) {
                //if its undefined, do a POST request
                const newPost = {
                    title: `${title}`,
                    content: `${content}`
                };
                let action = 'POST';
                let url = `http://localhost:8080/api/posts`
                fetchAction(action, newPost, url)
            }
            // else do a PUT request
            else {
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

// when click, passed the Post's values to the form for an update
function editEventListener() {
    $(".edit-post").click(function () {
        let postIdToBeUpdate = $(this).attr('data-id');
        let postTitleToBeUpdate = $(this).parent().children('.content-title').html();
        let postCommentToBeUpdate = $(this).parent().children('.content-post').html();
        $("#post-title").val(postTitleToBeUpdate)
        $("#post-content").html(postCommentToBeUpdate)
        $('.post-update').attr('data-id', `${postIdToBeUpdate}`).html("Editing: " + postTitleToBeUpdate)
    })
}

// delete request, grabs data-id and then sends it to a DELETE request
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


// Handles all fetch actions: POST, PUT, DELETE, depending in user choice
export const fetchAction = (action, postObject, url) => {
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