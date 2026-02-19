package org.example;

import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpClient;
import java.util.Scanner;

@Slf4j
public class Main {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    static void main() {
        log.info("Starting application");
        try (client; scanner) {
            GetRequestCommand getRequest = new GetRequestCommand(URLConstants.getURL);
            String getResponse = getRequest.execute();
            log.info("Get response: {}", getResponse);

            PostRequestCommand postRequest = new PostRequestCommand(URLConstants.postURL, getUserInput(scanner));
            String postResponse = postRequest.execute();
            log.info("Post response: {}", postResponse);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage(), e);
        }
    }

    private static String getUserInput(Scanner scanner) {
        log.info("Getting user input");

        System.out.println("Choice your favorite meme and enter id: :");
        int id = Integer.parseInt(scanner.nextLine());
        log.debug("Meme id: {}", id);

        System.out.println("Enter your imgflip username: ");
        String username = scanner.nextLine();
        log.debug("Username: {}", username);

        System.out.println("Enter your imgflip password: ");
        String password = scanner.nextLine();
        log.debug("Password: {}", password);

        System.out.println("Enter you text on the meme: ");
        String text1 = scanner.nextLine();
        log.debug("Text: {}", text1);

        System.out.println("Enter you another text on the meme: ");
        String text2 = scanner.nextLine();
        log.debug("Text: {}", text2);

        String formData = String.format("template_id=%d&username=%s&password=%s&text0=%s&text1=%s", id, username, password, text1, text2);
        log.info("Data request completed successfully.");

        return formData;
    }
}