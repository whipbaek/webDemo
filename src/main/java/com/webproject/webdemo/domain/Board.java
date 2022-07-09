package com.webproject.webdemo.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 게시판의 기본 정보를 담는 클래스
 * 1. 게시물 번호
 * 2. 작성자
 * 3. 제목
 * 4. 내용
 */
@Getter
@Setter
public class Board {
    private Long boardNum;
    private String writer;
    private String title;
    private String content;

    public Board() {
    }

    public Board(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}


