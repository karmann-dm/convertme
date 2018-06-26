package com.karmanno.letmeconvert.model;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class UploadedFile extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "path_to_file")
    private String pathToFile;

    public UploadedFile() {}

    public UploadedFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }
}
