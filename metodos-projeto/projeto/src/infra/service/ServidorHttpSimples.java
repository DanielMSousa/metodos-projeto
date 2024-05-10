package infra.service;

import java.io.*;
import java.net.*;

public class ServidorHttpSimples {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Lê a solicitação HTTP do cliente
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String request = reader.readLine(); // Lê a primeira linha (método e URL)
            String[] requestParts = request.split(" "); // Separa o método e a URL
            String method = requestParts[0]; // Obtém o método (GET, POST, etc.)
            String url = requestParts[1]; // Obtém a URL

            // Processa o método e a URL
            if (method.equals("GET")) {
                handleGet(url, outputStream);
            } else if (method.equals("POST")) {
                handlePost(url, inputStream, outputStream);
            } else {
                sendNotFound(outputStream);
            }

            socket.close();
        }
    }

    private static void handleGet(String url, OutputStream outputStream) throws IOException {
        if (url.equals("/usuarios")) {
            // Implement logic to retrieve user data (replace with your logic)
            // String userJson = getUsuariosJson(); // Replace with your method to get user data in JSON format
            String jsonResponse = "{\"message\": \"Hello World from JSON!\"}"; // Crie o JSON
            outputStream.write(("HTTP/1.1 200 OK\n" +
            "Content-Type: application/json\n" +
            "Content-Length: " + jsonResponse.length() + "\n\n" +
            jsonResponse).getBytes());
        } else {
            // Handle other GET requests (optional)
        }
    }

    private static void handlePost(String url, InputStream inputStream, OutputStream outputStream) throws IOException {
        // Implemente a lógica para processar solicitações POST
        // Leia o corpo da solicitação POST
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String postData = reader.readLine(); // Lê o corpo da solicitação

        // Processa o corpo da solicitação
        // Envie uma resposta adequada
        String response = "<!DOCTYPE html><html><head><title>Servidor HTTP Simples</title></head><body><h1>Dados POST Recebidos: " + postData + "</h1></body></html>";
        outputStream.write(response.getBytes());
    }

    private static void sendNotFound(OutputStream outputStream) throws IOException {
        // Envie uma resposta de erro 404
        String response = "HTTP/1.1 404 Not Found\nContent-Type: text/plain\n\nNot Found";
        outputStream.write(response.getBytes());
    }
}
