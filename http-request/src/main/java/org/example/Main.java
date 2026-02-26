package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        try (client) {
            System.out.println("Task 1:");
            logger.info("Executing Task 1 - GET request for all posts");
            BlogPostResponse[] get1 = getRequest.execute();
            logger.info("Task 1 completed");
            logger.info("Response body: " + Arrays.toString(get1));
            System.out.println("Object version:");
            Arrays.stream(get1).forEach(System.out::println);
            logger.info("Task 1 - GET request for all posts completed");
            System.out.println();

            System.out.println("Task 2:");
            logger.info("Executing Task 2 - GET request for post with id 1");
            BlogPostResponse get2 = getRequestWithPost.execute();
            logger.info("Task 2 completed");
            logger.info("Response body: " + get2);
            System.out.println("Object version:");
            System.out.println(get2);
            logger.info("Task 2 - GET request for post with id 1 completed");
            System.out.println();

            System.out.println("Task 3:");
            logger.info("Executing Task 3 - GET request for all comments");
            BlogPostResponse2[] get3 = getRequestGetComments.execute();
            logger.info("Task 3 completed");
            logger.info("Response body: " + Arrays.toString(get3));
            System.out.println("Object version:");
            System.out.println(Arrays.toString(get3));
            logger.info("Task 3 - GET request for all comments completed");
            System.out.println();

            System.out.println("Task 4:");
            logger.info("Executing Task 4 - GET request for post with id 1");
            BlogPostResponse2[] get4 = getRequestGetPostById.execute();
            System.out.println(Arrays.toString(get4));
            logger.info("Task 4 completed");
            System.out.println();

            System.out.println("Task 5:");
            logger.info("Executing Task 5 - POST request");
            BlogPostResponse post = postRequest.execute();
            System.out.println(post);
            logger.info("Task 5 completed");
            System.out.println();

            System.out.println("Task 6:");
            logger.info("Executing Task 6 - PUT request");
            BlogPostResponse put = putRequest.execute();
            System.out.println(put);
            logger.info("Task 6 completed");
            System.out.println();

            System.out.println("Task 7:");
            logger.info("Executing Task 7 - PATCH request");
            BlogPostResponse patch = patchRequest.execute();
            System.out.println(patch);
            logger.info("Task 7 completed");
            System.out.println();

            System.out.println("Task 8:");
            logger.info("Executing Task 8 - DELETE request");
            Integer statusCode = (Integer) deleteRequest.execute();
            logger.info("Task 8 (Status code: " + statusCode+")");
        }
    }

    private static final HttpClient client = HttpClient.newHttpClient();
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static final Command<BlogPostResponse[]> getRequest =
            RequestCommandFactory.create(HttpMethod.GET, URLConstants.GET_POSTS, BlogPostResponse[].class);

    private static final Command<BlogPostResponse> getRequestWithPost =
            RequestCommandFactory.create(HttpMethod.GET, URLConstants.GET_POST_1, BlogPostResponse.class);

    private static final Command<BlogPostResponse2[]> getRequestGetComments =
            RequestCommandFactory.create(HttpMethod.GET, URLConstants.GET_COMMENTS, BlogPostResponse2[].class);

    private static final Command<BlogPostResponse2[]> getRequestGetPostById =
            RequestCommandFactory.create(HttpMethod.GET, URLConstants.GET_POST_ID_1, BlogPostResponse2[].class);

    private static final Command<BlogPostResponse> postRequest =
            RequestCommandFactory.create(HttpMethod.POST, URLConstants.POST, URLConstants.JSONPOST, BlogPostResponse.class);

    private static final Command<BlogPostResponse> putRequest =
            RequestCommandFactory.create(HttpMethod.PUT, URLConstants.PUT_POST_1, URLConstants.JSON_FOR_PUT, BlogPostResponse.class);

    private static final Command<BlogPostResponse> patchRequest =
            RequestCommandFactory.create(HttpMethod.PATCH, URLConstants.PATCH_POST_1, URLConstants.JSON_FOR_PATCH, BlogPostResponse.class);

    private static final Command deleteRequest =
            RequestCommandFactory.createDelete(URLConstants.DELETE_POST_1);
}