package com.kodbook.web.entity;

import com.kodbook.userservice.entity.User;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private int likes;
    private List<String> comments;

    @ManyToOne
    private User user;

    @Lob
    @Basic(fetch = FetchType.LAZY)
//    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

    public Post() {
        super();
    }

    public Post(Long id, String caption, int likes, List<String> comments, User user, byte[] photo) {
        super();
        this.id = id;
        this.caption = caption;
        this.likes = likes;
        this.comments = comments;
        this.user = user;
        this.photo = photo;
    }

    // method to convert byte[] to String
    public String getPhotoBase64() {
        if (photo == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(photo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", caption=" + caption + ", likes=" + likes + ", comments=" + comments + ", user="
                + user + ", photo=" + Arrays.toString(photo) + "]";
    }

}
