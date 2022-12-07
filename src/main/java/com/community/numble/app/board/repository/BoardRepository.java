package com.community.numble.app.board.repository;

import com.community.numble.app.board.domain.*;
import com.community.numble.app.category.domain.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 * packageName    : com.community.numble.app.board.repository
 * fileName       : BoardRepository
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByCategory(Category category);

}
