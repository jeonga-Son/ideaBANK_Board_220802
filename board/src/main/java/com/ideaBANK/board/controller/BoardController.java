//글쓰기 Form에서 받은 데이터는 '글쓰기' 버튼을 누르면 /post로 Post 요청을 하게 됩니다.
//BoardController에 Post로 받은 데이터를 데이터베이스에 추가하는 것을 추가해 줍니다.

package com.ideaBANK.board.controller;

import com.ideaBANK.board.dto.BoardDto;
import com.ideaBANK.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String list() {
        return "board/list.html";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }
}


//Get방식은 어떠한 정보를 가져와서 조회하기 위해 사용되는 방식이다.
//Post방식은 데이터를 서버로 제출하여 추가 또는 수정하기 위해서 데이터를 전송하는 방식이다