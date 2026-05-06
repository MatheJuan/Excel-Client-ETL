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
        String isrouterBoard = response.body();

        if(!accept || response.body().isEmpty()){
            System.out.println("mac "+ mac + " vazio/not200");
            return false;
        }
            System.out.println("----------------------");
            System.out.println("MAC: " + mac);
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: [" + response.body() + "]");
            System.out.println("----------------------");

        return isrouterBoard.equalsIgnoreCase("Routerboard.com");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
       }
}
