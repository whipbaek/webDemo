package com.webproject.webdemo.web.basic;

import com.webproject.webdemo.domain.Board;
import com.webproject.webdemo.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/boards")
public class BasicController {

    private final BoardRepository boardRepository; //DataBase

    @Autowired
    public BasicController(BoardRepository boardRepository) {
        boardRepository.save(new Board("John","title1","empty content"));
        boardRepository.save(new Board("Emy","title2","empty content2"));

        this.boardRepository = boardRepository;
    }

    @GetMapping //게시판 페이지 GetMapping
    public String board(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards); //Model 데이터를 받아, view 로 넘겨준다.
        return "basic/boards";
    }

    @GetMapping("/add")
    public String addBoard(){
        return "basic/addBoard";
    }

}
