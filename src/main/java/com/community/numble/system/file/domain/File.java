package com.community.numble.system.file.domain;

import lombok.*;

import javax.persistence.*;
import java.io.*;

/**
 * packageName    : com.community.numble.system.domain
 * fileName       : File
 * author         : jeon-eunseong
 * date           : 2022/11/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/30        jeon-eunseong       최초 생성
 */

@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
public class File {

    @EmbeddedId
    private FileID fileId;

    private String filePath;

    private String fileName;

    private String storedFileName;

    private String extension;

    private String size;

    private int downloadCount;

    private String createDate;

    private String updateDate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class FileID implements Serializable {

        @Column(name = "file_id")
        private long id;

        private String fileType;
    }
}
