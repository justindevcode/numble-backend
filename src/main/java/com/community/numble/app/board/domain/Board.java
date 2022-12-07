package com.community.numble.app.board.domain;

import com.community.numble.app.category.domain.*;
import com.community.numble.app.user.domain.*;
import com.community.numble.system.file.domain.*;
import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * packageName    : com.community.numble.app.board.domain
 * fileName       : Board
 * author         : jeon-eunseong
 * date           : 2022/12/04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/04        jeon-eunseong       최초 생성
 */

@Builder
@Getter
@Table(name="BOARD_TABLE")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private long id;

    private String title;

    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    private List<File> fileList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment;

    @OneToMany(mappedBy = "post")
    private List<PostLike> postLikes;

    private String createDate;

    private String updateDate;
}
