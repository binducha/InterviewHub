package com.app.model;

import javax.persistence.*;

@Entity
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String company;
    private String candidateName;
    private String round;

    @Column(length = 2000)
    private String experienceText;

    @Column(length = 1000)
    private String guidance;

    private String materialLink;

    /* NEW */
    private String uploadedFile;
    
    private String originalFileName;

    // getters & setters
    public int getId() { 
    	return id;
    	}
    public void setId(int id) {
    	this.id = id;
    	}

    public String getCompany() {
    	return company; 
    	}
    public void setCompany(String company) {
    	this.company = company; 
    	}

    public String getCandidateName() {
    	return candidateName; 
    	}
    public void setCandidateName(String candidateName) {
    	this.candidateName = candidateName; 
    	}

    public String getRound() { 
    	return round;
    	}
    public void setRound(String round) { 
    	this.round = round;
    	}

    public String getExperienceText() {
    	return experienceText;
    	}
    public void setExperienceText(String experienceText) { 
    	this.experienceText = experienceText; 
    	}

    public String getGuidance() {
    	return guidance;
    	}
    public void setGuidance(String guidance) { 
    	this.guidance = guidance;
    	}

    public String getMaterialLink() {
    	return materialLink;
    	}
    public void setMaterialLink(String materialLink) { 
    	this.materialLink = materialLink; 
    	}
    public String getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(String uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }
}