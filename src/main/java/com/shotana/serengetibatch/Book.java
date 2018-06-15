package com.shotana.serengetibatch;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Book {
    private final int rank;
    private final String author;
    private final String title;
    private final String description;
    private final String publisher;
    private final int price;
    private final String authorDescription;
    private final String detailPageLink;

    @Override
    public String toString() {
        return rank + "位" + "\n"
                + title + "\n"
                + "著者：" + author + "\n"
                + "説明：" + description + "\n"
                + "価格：￥" + price + "\n"
                + "著者について：" + authorDescription + "\n"
                + "詳細ページ：" + detailPageLink
                ;
    }
}
