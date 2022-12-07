package com.community.numble.system.file.repository;

import com.community.numble.system.file.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;


/**
 * packageName    : com.community.numble.system.file.repository
 * fileName       : FileRepository
 * author         : jeon-eunseong
 * date           : 2022/12/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/03        jeon-eunseong       최초 생성
 */
@Repository
public interface FileRepository extends JpaRepository<File, File.FileID> {

    File findByFileId(File.FileID fileId);

}
