package com.rickandmortyapi.controller;

import com.rickandmortyapi.client.RickAndMortyClient;
import com.rickandmortyapi.response.CharacterResponse;
import com.rickandmortyapi.response.EpisodeResponse;
import com.rickandmortyapi.response.ListOfEpisodeResponse;
import com.rickandmortyapi.response.LocationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/webclient")
public class RickAndMortyController {

    RickAndMortyClient rickAndMortyClient;

    @GetMapping("/character/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CharacterResponse> getCharacterById(@PathVariable String id){
        return rickAndMortyClient.findACharacterById(id);

    }

    @GetMapping("/location/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<LocationResponse> getLocationById(@PathVariable String id){
        return rickAndMortyClient.findALocationById(id);

    }

    @GetMapping("/episode/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<EpisodeResponse> getEspisodeById(@PathVariable String id){
        return rickAndMortyClient.findAEpisodeById(id);

    }

    @GetMapping("/episodes")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ListOfEpisodeResponse> listAllEpisodes(){
        return rickAndMortyClient.ListAllEpisodes();
    }
}
