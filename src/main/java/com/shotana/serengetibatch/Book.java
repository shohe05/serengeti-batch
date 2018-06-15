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
        return "\n\n" + rank + "位："
                + title + "\n\n"
                + "著者：\n" + author + "\n\n"
                + "説明：\n" + description + "\n\n"
                + "価格：￥" + price + "\n\n"
                + "著者について：\n" + authorDescription + "\n\n"
                + "詳細ページ：\n" + detailPageLink
                ;
    }
}
