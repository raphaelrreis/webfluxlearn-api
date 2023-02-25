package com.rickandmortyapi.client;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.rickandmortyapi.response.CharacterResponse;
import com.rickandmortyapi.response.EpisodeResponse;
import com.rickandmortyapi.response.ListOfEpisodeResponse;
import com.rickandmortyapi.response.LocationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RickAndMortyClient {


    private final WebClient webClient;
    public RickAndMortyClient(WebClient.Builder builder) {
        webClient = builder.baseUrl("https://rickandmortyapi.com/api").build();
    }

    public Mono<CharacterResponse> findACharacterById(String id) {
        log.info("find character by id [{}], id");
        return webClient
                .get()
                .uri("/character/" +id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify character invalid")))
                .bodyToMono(CharacterResponse.class);

    }

    public Mono<LocationResponse> findALocationById(String id) {
        log.info("Find location by id [{}], id");
        return webClient
                .get()
                .uri("/location/" +id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify location invalid")))
                .bodyToMono(LocationResponse.class);

    }

    public Mono<EpisodeResponse> findAEpisodeById(String id) {
        log.info("Find location by id [{}], id");
        return webClient
                .get()
                .uri("/episode/" +id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify episode invalid")))
                .bodyToMono(EpisodeResponse.class);

    }

public Flux<ListOfEpisodeResponse> ListAllEpisodes() {
        log.info("List all episodes");
        return webClient
                .get()
                .uri("/episode")
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("Verify informed list episodes")))
                .bodyToFlux(ListOfEpisodeResponse.class);

    }
}
