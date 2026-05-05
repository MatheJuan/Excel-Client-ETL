package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public  class MacVendorsConsumer {
    public static final HttpClient cliente = HttpClient.newHttpClient();

    public static Boolean isValid(String mac){
        try {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.macvendors.com/"+mac)).GET().build();

        HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        boolean accept = response.statusCode()==200;//(true)
        boolean hasContent = response.body().isBlank();
        String isrouterBoard = response.body();

        if(!accept || !hasContent){
            return false;
        }

        return isrouterBoard.equals("Routerboard.com");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
       }
}
