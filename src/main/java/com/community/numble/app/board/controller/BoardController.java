package com.community.numble.app.board.controller;

import com.community.numble.app.board.dto.*;
import com.community.numble.app.board.service.*;
import com.community.numble.common.service.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.community.numble.app.board.controller
 * fileName       : BoardController
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */

@Slf4j
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final ResponseService responseService;

    private final BoardService boardService;

    @GetMapping("/list/{categoryId}")
    public ResponseEntity<?> getList(@PathVariable int categoryId){

        return ResponseEntity.ok(responseService.getListResult(boardService.getBoardList(categoryId)));
    }

    @GetMapping("{boardId}")
    public ResponseEntity<?> getBoard(@PathVariable long boardId){

        return ResponseEntity.ok(responseService.getSingleResult(boardService.getBoard(boardId)));
    }

    @PostMapping("insert")
    public ResponseEntity<?> insertBoard(BoardCreateDto boardCreateDto){

        boardService.insertBoard(boardCreateDto);
        return ResponseEntity.ok(responseService.getSuccessResult());
    }

    @PostMapping("update")
    public ResponseEntity<?> updateBoard(BoardUpdateDto boardUpdateDto){

        boardService.updateBoard(boardUpdateDto);
        return ResponseEntity.ok(responseService.getSuccessResult());
    }

    @PostMapping("delete")
    public ResponseEntity<?> deleteBoard(BoardDto boardDto){

        boardService.deleteBoard(boardDto);
        return ResponseEntity.ok(responseService.getSuccessResult());
    }
}
