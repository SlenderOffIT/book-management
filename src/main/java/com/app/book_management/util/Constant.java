package com.app.book_management.util;

public enum Constant {

    AUTHOR_STATISTICS("author_statistics"),
    GENRE_STATISTICS("genre_statistics"),
    SALES_STATISTICS("sales_statistics");

    private final String value;

    Constant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
