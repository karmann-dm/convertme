package com.karmanno.letmeconvert.payload.response;

import java.time.Instant;
import java.util.List;

public class ProfileResponse {
    private String username;
    private String email;
    private Instant createdAt;
    private List<String> files;

    public ProfileResponse() {
    }

    public ProfileResponse(String username, String email, Instant createdAt, List<String> files) {
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.files = files;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
