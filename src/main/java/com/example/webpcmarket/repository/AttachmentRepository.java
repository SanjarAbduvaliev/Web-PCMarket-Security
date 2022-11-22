package com.example.webpcmarket.repository;

import com.example.webpcmarket.entity.Attachment;
import com.example.webpcmarket.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
