package com.unicap.sistemaTroca.services;

import com.unicap.sistemaTroca.exceptions.ApiCepException;
import com.unicap.sistemaTroca.models.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class EnderecoService {
    private final WebClient webClient;
    @Autowired
    public EnderecoService(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://viacep.com.br/ws/").build();
    }

    public Endereco buscarEndereco(String cep) {
        Mono<Endereco> endereco = webClient.get()
                                    .uri(cep + "/json")
                                    .accept(MediaType.APPLICATION_JSON)
                                    .retrieve()
                                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                                        return Mono.error(new ApiCepException("CEP inválido"));
                                    })
                                    .onStatus(HttpStatusCode::is5xxServerError, response -> {
                                        return Mono.error(new ApiCepException("Erro inesperado"));
                                    })
                                    .bodyToMono(Endereco.class)
                                    .onErrorResume(error -> {
                                        if(error instanceof ApiCepException) {
                                            throw new ApiCepException("CEP Inválido");
                                        }
                                        else {
                                            throw new ApiCepException("Erro inesperado");
                                        }
                                    });

        if(endereco.block().getCep() == null) {
            throw new ApiCepException("CEP não encontrado");
        }

        return endereco.block();
    }
}
