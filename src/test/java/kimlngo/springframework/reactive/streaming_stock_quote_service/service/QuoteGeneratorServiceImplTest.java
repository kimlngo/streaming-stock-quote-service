package kimlngo.springframework.reactive.streaming_stock_quote_service.service;

import kimlngo.springframework.reactive.streaming_stock_quote_service.model.Quote;
import kimlngo.springframework.reactive.streaming_stock_quote_service.service.QuoteGeneratorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

class QuoteGeneratorServiceImplTest {
    QuoteGeneratorServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new QuoteGeneratorServiceImpl();
    }

    @Test
    void fetchQuoteStream() throws InterruptedException {
        Flux<Quote> quotesFlux = service.fetchQuoteStream(Duration.ofMillis(100));

        Consumer<Quote> quoteConsumer = System.out::println;

        Consumer<Throwable> throwableConsumer = e -> System.out.println(e.getMessage());

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable done = () -> countDownLatch.countDown();

        quotesFlux.take(30).subscribe(quoteConsumer, throwableConsumer, done);

        countDownLatch.await();
    }
}