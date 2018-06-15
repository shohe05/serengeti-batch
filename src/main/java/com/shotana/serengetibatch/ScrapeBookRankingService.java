package com.shotana.serengetibatch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ScrapeBookRankingService {
    private static final String KINOKUNIYA_DAILY_BUNKO_RANKING_URL =
            "https://www.kinokuniya.co.jp/disp/CKnRankingPageCList.jsp?dispNo=107002001002&vTp=d";

    public List<Book> scrape() throws IOException {
        List<Book> books = new ArrayList<>();

        // 紀伊国屋のサイトから、文庫のデイリーランキングのページを取得
        Document document = Jsoup.connect(KINOKUNIYA_DAILY_BUNKO_RANKING_URL).get();

        // ランキングのリストを取得
        Elements rankingElements = document.select(".rankingList");

        // ランキングの本を一つずつ処理する
        for (Element element : rankingElements) {
            try {
                // 順位を取得
                int rank = Integer.valueOf(
                        element
                                .getElementsByClass("rankingLabel")
                                .get(0)
                                .getElementsByTag("span")
                                .get(0)
                                .text()
                );

                // タイトルを取得
                String title =
                        element
                                .getElementsByClass("heightLine-2")
                                .get(0)
                                .getElementsByTag("a")
                                .get(0)
                                .text();

                // 著者名を取得
                String author =
                        element
                                .getElementsByClass("mt00")
                                .get(0)
                                .getElementsByTag("p")
                                .get(0)
                                .text()
                                .replace("【著】", "");

                // 税込価格を取得
                int price = Integer.valueOf(
                        element
                                .getElementsByClass("sale_price")
                                .get(0)
                                .text()
                                .replace("¥", "")
                                .replaceAll(",", "")
                );

                // 出版社を取得
                String tmp =
                        element
                                .getElementsByClass("mb05")
                                .get(0)
                                .getElementsByTag("li")
                                .get(0)
                                .text();
                Pattern p = Pattern.compile("(.*)(?=（)");
                Matcher m = p.matcher(tmp);
                String publisher = m.find() ? m.group() : "None";

                // 詳細ページのリンクを取得
                String detailLink =
                        element
                                .getElementsByClass("heightLine-1")
                                .get(0)
                                .attr("href");

                // 詳細ページを取得
                Document detailPageDocument = Jsoup.connect(detailLink).get();

                // 本の紹介文を取得
                String description = detailPageDocument
                        .getElementsByClass("career_box")
                        .get(0)
                        .getElementsByTag("p")
                        .get(0)
                        .text();

                // 著者の紹介文を取得
                String authorDescription = detailPageDocument
                        .getElementsByClass("career_box")
                        .get(0)
                        .getElementsByTag("p")
                        .get(1)
                        .text();

                Book book = new Book(rank, author, title, description, publisher, price, authorDescription, detailLink);
                books.add(book);
            } catch (Throwable throwable) {
                System.out.println("[ERROR] Failed to get a book info.");
                System.out.println(throwable.getMessage());
            }
        }
        return books;
    }
}
