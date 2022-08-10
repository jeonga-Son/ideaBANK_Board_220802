//글쓰기 Form에서 받은 데이터는 '글쓰기' 버튼을 누르면 /post로 Post 요청을 하게 됩니다.
//BoardController에 Post로 받은 데이터를 데이터베이스에 추가하는 것을 추가해 줍니다.

package com.ideaBANK.board.controller;

import com.ideaBANK.board.dto.BoardDto;
import com.ideaBANK.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시물의 목록을 가져오는 getBoardList()를 만들었으니,
    // 가져온 데이터를 Model을 통해 View에 전달해줍니다.
    //model.addAttribute("postList", boardDtoList);를 통하여 boardDtoList를
    //board/list.html에 postList로 전달해 줍니다.
    @GetMapping("/")
    public String map(Model model) {
        return "map/index";
    }

    @GetMapping("/board/list")
    public String list(Model model) {
        return "board/list";
    }

    @GetMapping("/post")
    public String post() {
        return "board/post.html";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/board/list";
    }

    //각 게시글을 클릭하면, /post/{id}으로 Get 요청을 합니다. (만약 1번 글을 클릭하면 /post/1로 접속됩니다.)
    //BoardController에 detail()을 아래와 같이 구현하여, 요청받았을 때 해당 id의 데이터가 View로 전달되도록 만들어줍니다.
    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail.html";
    }

    //글을 조회하는 페이지에서 '수정' 버튼을 누르면, /post/edit/{id}으로 Get 요청을 합니다.
    // (만약 1번 글에서 '수정' 버튼을 클릭하면 /post/edit/1로 접속됩니다.)
    @GetMapping("/post/edit/{id}")
    public String edit (@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit.html";
    }

    //서버에게 Put 요청이 오게되면, 데이터베이스에 변경된 데이터를 저장한다.
    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/";
    }


    //페이지에서 '삭제' 버튼을 누르면, /post/{id}으로 Delete 요청을 합니다. (만약 1번 글에서 '삭제' 버튼을 클릭하면 /post/1로 접속됩니다.)
    //id 값을 사용하여, 해당 글을 데이터베이스에서 삭제하는 것을 구현.
    @DeleteMapping("/post/{id}") //@DeleteMapping : 데이터를 삭제할 때 사용한다.
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/";
    }
}


//Get방식은 어떠한 정보를 가져와서 조회하기 위해 사용되는 방식이다.
//Post방식은 데이터를 서버로 제출하여 추가 또는 수정하기 위해서 데이터를 전송하는 방식이다