package com.example.webpcmarket.controller;

import com.example.webpcmarket.entity.Attachment;
import com.example.webpcmarket.entity.AttachmentContent;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.repository.AttachmentContentRepository;
import com.example.webpcmarket.repository.AttachmentRepository;
import com.example.webpcmarket.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;


    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    @PostMapping(   "/upload")
    public HttpEntity<?> uploadFile(MultipartHttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentService.addAttachment(request));
    }
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    @GetMapping("/download/{id}")
    public void dovnloadFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {

        attachmentService.getFileId(id,response);
    }
}
