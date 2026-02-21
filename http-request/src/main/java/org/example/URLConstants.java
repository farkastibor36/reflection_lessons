package org.example;

public final class URLConstants {
    private URLConstants() {
    }

    protected static final String GET_POSTS = "https://jsonplaceholder.typicode.com/posts";
    protected static final String GET_POST_1 = "https://jsonplaceholder.typicode.com/posts/1";
    protected static final String GET_COMMENTS = "https://jsonplaceholder.typicode.com/posts/1/comments";
    protected static final String GET_POST_ID_1 = "https://jsonplaceholder.typicode.com/comments?postId=1";
    protected static final String POST = "https://jsonplaceholder.typicode.com/posts";
    protected static final String PUT_POST_1 = "https://jsonplaceholder.typicode.com/posts/1";
    protected static final String PATCH_POST_1 = "https://jsonplaceholder.typicode.com/posts/1";
    protected static final String DELETE_POST_1 = "https://jsonplaceholder.typicode.com/posts/1";
    protected static final String JSONPOST = """
              {"title":"hello","body":"world","userId":1}
            """;
    protected static final String JSON_FOR_PUT = """
              {"userId":1,"id":1,"title":"updated title","body":"updated body"}
            """;
    protected static final String JSON_FOR_PATCH = """
              {"userId":1,"id":1,"title":"updated title with patch","body":"updated body with patch"}
            """;
}