package com.karmanno.letmeconvert.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class UploadedFile extends UserDateAudit {
    private String pathToFile;

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
