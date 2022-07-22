package com.webproject.webdemo.web.basic;

import com.webproject.webdemo.domain.Board;
import com.webproject.webdemo.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/boards")
public class BasicController {

    private final BoardRepository boardRepository; //DataBase

    @Autowired
    public BasicController(BoardRepository boardRepository) {
        boardRepository.save(new Board("John", "title1", "empty content"));
        boardRepository.save(new Board("Emy", "title2", "empty content2"));

        this.boardRepository = boardRepository;
    }

    @GetMapping //게시판 페이지 GetMapping
    public String board(Model model) {
        List<Board> boards = boardRepository.findAll();

        model.addAttribute("boards", boards); //Model 데이터를 받아, view 로 넘겨준다.
        return "basic/boards";
    }

    @GetMapping("/add") //글쓰기 페이지로 진입
    public String addBoard() {
        return "basic/addBoard";
    }

    @PostMapping("/add")
    public String addBoard(Board board, RedirectAttributes redirectAttributes) {
        System.out.println(board.getContent() + 12);
        Board savedBoard = boardRepository.save(board);
        redirectAttributes.addAttribute("boardNum", savedBoard.getBoardNum());

        return "redirect:/basic/boards/{boardNum}";
    }

    @GetMapping("/{boardNum}")
    public String board(@PathVariable long boardNum, Model model) {
        Board board = boardRepository.findByBoardNum(boardNum);
        model.addAttribute("board", board);

        return "basic/board";
    }

    @GetMapping("/{boardNum}/edit")
    public String editForm(@PathVariable Long boardNum, Model model) {
        Board board = boardRepository.findByBoardNum(boardNum);
        model.addAttribute("board", board);
        return "basic/editBoard";
    }

    @PostMapping("/{boardNum}/edit")
    public String edit(@PathVariable Long boardNum, @ModelAttribute Board board) {
        boardRepository.update(boardNum, board);
        return "redirect:/basic/boards/{boardNum}";
    }
}
