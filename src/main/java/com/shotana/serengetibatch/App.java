package com.shotana.serengetibatch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class App {
    private final ScrapeBookRankingService scrapeBookRankingService;
    private final LineNotifyService lineNotifyService;

    public void run() throws IOException {
        List<Book> books = scrapeBookRankingService.scrape();

        lineNotifyService.notifyLine(LocalDate.now().toString() + "の文庫ランキング！");
        books.forEach(book -> {
            System.out.println(book);
            lineNotifyService.notifyLine(book.toString());
        });
    }
}
