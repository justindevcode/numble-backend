package com.community.numble.app.board.service;

import com.community.numble.app.board.domain.*;
import com.community.numble.app.board.dto.*;
import com.community.numble.app.board.repository.*;
import com.community.numble.app.category.domain.*;
import com.community.numble.app.category.repository.*;
import com.community.numble.app.user.domain.*;
import com.community.numble.common.utils.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * packageName    : com.community.numble.app.board.service
 * fileName       : BoardService
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */
@RequiredArgsConstructor
@Service("boardService")
public class BoardService {

    private final BoardRepository boardRepository;

    private final CategoryRepository categoryRepository;

    public List<Board> getBoardList(int categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return boardRepository.findAllByCategory(category);
    }

    public Board getBoard(long boardId) {

        return boardRepository.findById(boardId).orElse(new Board());
    }

    public void insertBoard(BoardCreateDto boardCreateDto) {
        Board board = BoardCreateDto.toEntity(boardCreateDto);
        boardRepository.save(board);
    }

    public void updateBoard(BoardUpdateDto boardUpdateDto) {
        Board board = BoardUpdateDto.toEntity(boardUpdateDto);
        boardRepository.save(board);
    }

    public void deleteBoard(BoardDto boardDto){
        Board board = boardRepository.findById(boardDto.getId()).orElseThrow();
        User user = SecurityUtils.getUser();
        if(board.getUser().equals(user)){
            boardRepository.findById(boardDto.getId());
        }

    }
}
