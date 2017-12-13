package com.jianqing.intuit;

import java.util.Date;

/**
 * Created by jianqing_sun on 12/6/17.
 */
public class Note extends Entity{
    private String userId;
    private String noteId;
    private String text;
    private String title;
    private Date creationTime;
    private Date lastupdateTime;
    private String comments;
    private String version;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getLastupdateTime() {
        return lastupdateTime;
    }

    public void setLastupdateTime(Date lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    String getPK() {
        return userId+noteId;
    }
}
