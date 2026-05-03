package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public  class MacVendorsConsumer {
    public static final HttpClient cliente = HttpClient.newHttpClient();

    public static boolean isValid(String mac){
        try {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.macvendors.com"+mac)).GET().build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        boolean ok = response.statusCode()==200;
        boolean hasContent = !response.body().isBlank();

        return  ok && hasContent;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
       }
}
