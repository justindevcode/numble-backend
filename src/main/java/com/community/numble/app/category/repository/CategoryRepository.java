package com.community.numble.app.category.repository;

import com.community.numble.app.category.domain.*;
import org.springframework.data.jpa.repository.*;

/**
 * packageName    : com.community.numble.app.category.repository
 * fileName       : CategoryRepository
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
