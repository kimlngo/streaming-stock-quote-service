package com.kimlngo.springframework.reactive.web;


import com.kimlngo.springframework.reactive.model.Quote;
import com.kimlngo.springframework.reactive.service.QuoteGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteHandler {
    private final QuoteGeneratorService quoteGeneratorService;

    public Mono<ServerResponse> fetchQuotes(ServerRequest req) {
        int size = Integer.parseInt(req.queryParam("size")
                                       .orElse("10"));

        return ServerResponse.ok()
                             .contentType(MediaType.APPLICATION_JSON)
                             .body(quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l))
                                                        .take(size), Quote.class);
    }

    public Mono<ServerResponse> streamQuotes(ServerRequest req) {
        return ServerResponse.ok()
                             .contentType(MediaType.APPLICATION_NDJSON)
                             .body(quoteGeneratorService.fetchQuoteStream(Duration.ofMillis(100l)), Quote.class);
    }
}
