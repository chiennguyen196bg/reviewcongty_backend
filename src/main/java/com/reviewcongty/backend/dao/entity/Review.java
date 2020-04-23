package com.reviewcongty.backend.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Review {
    @Id
    private String id;
    private String name;
    private String position;
    private Integer rating;
    private Date created;
    private String content;
    private Integer numLikes;
    private Integer numDislikes;
    private Integer numDeleteRequests;
    private String companyId;
    private List<Reply> replies;
}