package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
    /**
     * NATIVE QUERY ORQALI
     * JPA QOUERY ORQALI
     * @param attachmentId
     * @return
     */
    @Query(value = "select *\n" +
            "from attachment_content\n" +
            "where attachment_id = :attachmentId",nativeQuery = true)
    Optional<AttachmentContent> attachmentIdNative(Integer attachmentId);


    //JPA QUERY
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);
}
