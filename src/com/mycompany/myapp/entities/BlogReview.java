/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author med
 */
public class BlogReview {
    private int id, blogId;
    private float rate;
    private String comment;
    private String createdDate;

    public BlogReview(int id, float rate, String comment, String createdDate) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.createdDate = createdDate;
    }

    public BlogReview() {
    }

    public BlogReview(int blogId, float rate, String comment) {
        this.blogId = blogId;
        this.rate = rate;
        this.comment = comment;
    }

    public BlogReview(float rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BlogReview{" + "id=" + id + ", blogId=" + blogId + ", rate=" + rate + ", comment=" + comment + ", createdDate=" + createdDate + '}';
    }
    
    
    
}
