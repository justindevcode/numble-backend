package com.community.numble.system.file.service;

import com.community.numble.system.file.domain.File;
import com.community.numble.system.file.repository.*;
import com.fasterxml.jackson.databind.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.jasypt.encryption.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.nio.file.*;

/**
 * packageName    : com.community.numble.system.file.service
 * fileName       : FileService
 * author         : jeon-eunseong
 * date           : 2022/12/03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/03        jeon-eunseong       최초 생성
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final ObjectMapper objectMapper;

    private final StringEncryptor encryptor;
    public InputStream getImage(String code) {

        String decryptCode = encryptor.decrypt(code);

        try {
            File decryptFile = objectMapper.readValue(decryptCode, File.class);

            File fileInfo = fileRepository.findByFileId(decryptFile.getFileId());

            final Path path = Paths.get(fileInfo.getFilePath());

            return Files.newInputStream(path);

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
