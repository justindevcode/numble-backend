package com.community.numble.app.board.dto;

import com.community.numble.app.board.domain.*;
import com.community.numble.app.user.domain.*;
import com.community.numble.common.*;
import com.community.numble.common.utils.*;
import lombok.*;
import org.springframework.security.core.context.*;

import java.time.*;

/**
 * packageName    : com.community.numble.app.board.dto
 * fileName       : BoardCreateDto
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */
@Data
public class BoardCreateDto {

    private String title;

    private String content;

    public static Board toEntity(BoardCreateDto boardCreateDto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Board.builder()
                .title(boardCreateDto.getTitle())
                .content(boardCreateDto.getContent())
                .user(user)
                .createDate(DateUtils.format(LocalDateTime.now(), Constants.DATE_FORMAT_YMDHMS))
                .build();
    }
}
