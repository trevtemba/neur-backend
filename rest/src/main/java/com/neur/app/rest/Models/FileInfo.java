package com.neur.app.rest.Models;

public class FileInfo {
    private String fileName;
    private long fileLength;
    private String fileUrl;
    private String contentType;
    private Boolean isReadable;
    private Boolean isFileEmpty;
    private byte[] fileData;

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Boolean getFileEmpty() {
        return isFileEmpty;
    }

    public void setFileEmpty(Boolean fileEmpty) {
        isFileEmpty = fileEmpty;
    }

    public Boolean getReadable() {
        return isReadable;
    }

    public void setReadable(Boolean readable) {
        isReadable = readable;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileLength() {
        return fileLength;
    }

    public void setFileLength(long fileLength) {
        this.fileLength = fileLength;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
