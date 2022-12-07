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
 * fileName       : BoardUpdateDto
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */
@Builder
@Data
public class BoardUpdateDto {

    private long id;

    private String title;

    private String content;

    public static Board toEntity(BoardUpdateDto boardUpdateDto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Board.builder()
                .id(boardUpdateDto.getId())
                .title(boardUpdateDto.getTitle())
                .content(boardUpdateDto.getContent())
                .user(user)
                .updateDate(DateUtils.format(LocalDateTime.now(), Constants.DATE_FORMAT_YMDHMS))
                .build();
    }
}
