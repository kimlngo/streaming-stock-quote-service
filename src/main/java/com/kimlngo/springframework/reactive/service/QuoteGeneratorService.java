package com.kimlngo.springframework.reactive.service;

import com.kimlngo.springframework.reactive.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
