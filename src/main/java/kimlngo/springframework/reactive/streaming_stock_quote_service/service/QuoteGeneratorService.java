package kimlngo.springframework.reactive.streaming_stock_quote_service.service;

import kimlngo.springframework.reactive.streaming_stock_quote_service.model.Quote;
import reactor.core.publisher.Flux;

import java.time.Duration;

public interface QuoteGeneratorService {

    Flux<Quote> fetchQuoteStream(Duration period);
}
