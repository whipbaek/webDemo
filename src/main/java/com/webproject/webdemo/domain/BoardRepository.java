package com.webproject.webdemo.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DataBase 역할을 맡는 클래스
 * @Repository 어노테이션으로 Bean 으로 등록 및, DB 역할임을 스프링에게 알린다.
 * Map 으로 데이터들을 저장 및 관리한다. Key 는 게시물 번호, Value 는 게시물 객체
 * sequence 변수는 게시물 번호를 자동적으로 증가시키기 위함.
 */

@Repository
public class BoardRepository {

    private static final Map<Long, Board> store = new HashMap<>();
    private static long sequence = 0L;

    //새로운 객체를 저장하는 메소드
    public Board save(Board board) {
        board.setBoardNum(++sequence);
        store.put(board.getBoardNum(), board);
        return board;
    }

    //id로 게시물 객체를 반환하는 메소드
    public Board findByBoardNum(long num) {
        return store.get(num);
    }

    //게시물을 업데이트 해주는 메소드
    public void update(Long boardNum, Board newBoard) {
        Board board = findByBoardNum(boardNum);
        board.setContent(newBoard.getContent());
        board.setTitle(newBoard.getTitle());
        board.setContent(newBoard.getContent());
    }

    //store value 를 가지는 리스트 객체 반환
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
