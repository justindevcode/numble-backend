package com.community.numble.system.file.controller;

import com.community.numble.system.file.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.community.numble.app.board.controller
 * fileName       : CommonController
 * author         : jeon-eunseong
 * date           : 2022/11/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/30        jeon-eunseong       최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class FileController {

    private final FileService fileService;

    @GetMapping(value = "image/{code}")
    public ResponseEntity<Object> image(@PathVariable String code){

        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return ResponseEntity.ok().headers(headers).body(fileService.getImage(code));

    }
}
