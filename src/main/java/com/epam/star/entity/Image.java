package com.epam.star.entity;

@MappedEntityForAdmin("Image")
public class Image extends AbstractEntity {
    private String filename;
    private byte[] content;

    public Image() {
    }

    public Image(int id, String filename, byte[] content) {
        super(id);
        this.filename = filename;
        this.content = content;
    }

    public Image(String filename, byte[] content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
