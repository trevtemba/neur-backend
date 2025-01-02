package com.neur.app.rest.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "businessUsers")
public class BusinessUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private float rating;
    @Lob
    @Column
    private String profileLogo;
    @Lob
    @Column
    private String aboutMe;

    @Column
    private LocalDateTime dateCreated;
    @Column
    private boolean isActive;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getProfileLogo() {
        return profileLogo;
    }
    public void setProfileLogo(String profileLogo) {
        this.profileLogo = profileLogo;
    }

    public String getAboutMe() {
        return aboutMe;
    }
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}