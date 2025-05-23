package com.alura.spring_desafiofinal.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexao {
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;
    String endpoint;

    private void conectarEndoint(){
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                            .uri(URI.create(endpoint))
                            .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String obterRetorno(String endereco){
        this.endpoint = endereco;
        conectarEndoint();
        return response.body();
    }

}
