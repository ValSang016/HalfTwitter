package com.gachon.tw2;

public class tweetContent {
    private String username;
    private String content;

    public tweetContent(String username, String content) {
        this.username = username;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}
