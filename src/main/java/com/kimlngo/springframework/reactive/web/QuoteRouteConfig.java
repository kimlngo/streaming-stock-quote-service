package com.kimlngo.springframework.reactive.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_NDJSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Slf4j
@Configuration
public class QuoteRouteConfig {
    private static final String QUOTE_PATH = "/quotes";

    @Bean
    public RouterFunction<ServerResponse> quoteRoutes(QuoteHandler handler) {
        log.info(String.valueOf("is handler null? - " + handler == null));
        return route().GET(QUOTE_PATH, accept(APPLICATION_JSON), handler::fetchQuotes)
                      .GET(QUOTE_PATH, accept(APPLICATION_NDJSON), handler::streamQuotes)
                      .build();
    }
}
