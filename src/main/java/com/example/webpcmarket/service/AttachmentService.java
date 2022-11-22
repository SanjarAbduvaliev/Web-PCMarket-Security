package com.example.webpcmarket.service;

import com.example.webpcmarket.entity.Attachment;
import com.example.webpcmarket.entity.AttachmentContent;
import com.example.webpcmarket.entity.teplate.Status;
import com.example.webpcmarket.payload.AttachmentDto;
import com.example.webpcmarket.repository.AttachmentContentRepository;
import com.example.webpcmarket.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    /**
     * ATTACHMENTNI IKKIGA BO'LIB SAQLAYMIZ
     * ATTACHMENT MALUMOTLARI ALOHIDA JADVAL
     * ATTACHMENTNI CONTENTENTI(MAG'ZI) ALOHIDA JADVAL
     * @param request
     * @return
     */

    public Status addAttachment( MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        if (!fileNames.hasNext()){
            return new Status("Fayl nomi mavjud  emas",false);
        }
        MultipartFile file = request.getFile(fileNames.next());
        assert file != null;
        if (!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();


            //FAYL MA'LUMOTLARINI SAQLASH

            String contentType = file.getContentType();
            Attachment attachment=new Attachment();
            attachment.setName(originalFilename);
            attachment.setContentType(contentType);
            attachment.setSize(size);
            Attachment savedAttachment = attachmentRepository.save(attachment);


            // Faylni contentini saqalash

            AttachmentContent attachmentContent=new AttachmentContent();
            try {
                attachmentContent.setAsosiyContent(file.getBytes());
            } catch (IOException e) {
                return new Status("Fayl bo'sh",false);
            }
            attachmentContent.setAttachment(savedAttachment);
            attachmentContentRepository.save(attachmentContent);
            return new Status("Fayl saqlandi",true);
        }
        return new Status("Fayl mavjud emas",false);
    }
    public void getFileId(Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()){
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.attachmentIdNative(id);
            if (optionalAttachmentContent.isPresent()){
                AttachmentContent attachmentContent = optionalAttachmentContent.get();
                response.setHeader("Content-Disposition",
                        "attachment; filename=\""+attachment.getName()+"\"");
                response.setContentType(attachment.getContentType());
                FileCopyUtils.copy(attachmentContent.getAsosiyContent(),response.getOutputStream());
            }


        }
    }
}
