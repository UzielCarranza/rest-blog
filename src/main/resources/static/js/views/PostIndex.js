import createView from "../createView.js";

export default function PostIndex(props) {
    // language=HTML
    return `
        <header class="container text-center">
            <h1>Find all your topics here</h1>
        </header>
        <main>
            <div id="posts-container" class="container mt-4 mb-4">
                ${props.posts.map(post => `
             <div class="containe text-center rounded border border-primary m-4">
            <h3 class="content-title">${post.title}</h3>
            <h6 class="content-post">${post.content}</h6>
            <p class="mt-4">Wrote by: ${post.user.username}</p>
                    <button class="btn edit-post  btn-primary mt-4 mb-1" data-id="${post.id}"> <i class="fa-solid fa-pencil"></i>  Edit Post</button>
                    <button class="btn delete-post  btn-primary mt-4 mb-1" data-id="${post.id}" >Delete Post  <i class="fa-solid fa-trash-can"></i></button> 
                    </div>
                `).join('')}

            </div>
            <div class="container mt-4 mb-4">
                <form id="create-newPost" class="text-center form-control mt-4">
                    <span class="post-update"></span>
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
                fetchAction("POST", {title: `${title}`, content: `${content}`},
                    `http://localhost:8080/api/posts`, "/posts");
            }
            // else do a PUT request
            else {
                fetchAction("PUT", {
                    id: `${updatingPost}`,
                    title: `${title}`,
                    content: `${content}`

                }, `http://localhost:8080/api/posts/${updatingPost}`, "/posts");
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
        $('.post-update').attr('data-id', `${postIdToBeUpdate}`).html("Editing: " + postTitleToBeUpdate).addClass('form-control');
    })
}

// delete request, grabs data-id and then sends it to a DELETE request
function deleteEventListener() {
    $(".delete-post").click(function () {
        let postIdToBeDeleted = $(this).attr('data-id');

        fetchAction("DELETE", {
            id: `${postIdToBeDeleted}`
        }, `http://localhost:8080/api/posts/${postIdToBeDeleted}`, "/posts");
    })
}


// Handles all fetch actions: POST, PUT, DELETE, depending in user choice
export const fetchAction = (fetchRequest, objectToFetch, url, createPage) => {
    const options = {
        method: fetchRequest,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(objectToFetch),
    };
    console.log(objectToFetch)
    fetch(`${url}`, options)
        .then(res => {
            console.log(res.status);
            createView(`${createPage}`)
        }).catch(error => {
        console.log(error);
        createView(`${createPage}`);
    });
}