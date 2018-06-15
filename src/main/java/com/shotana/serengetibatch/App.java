package com.shotana.serengetibatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class App {
    private final ScrapeBookRankingService scrapeBookRankingService;

    public void run() throws IOException {
        List<Book> books = scrapeBookRankingService.scrape();

        books.forEach(book -> {
            System.out.println(book);
        });
    }
}
