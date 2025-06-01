/**
 *
 */
package com.kodbook.web.entity;

import com.kodbook.userservice.entity.User;
import jakarta.persistence.*;

import java.util.UUID;

/**
 *
 */
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;

    public Like() {
        super();
    }

    public Like(UUID uuid, User user, Post post) {
        super();
        this.uuid = uuid;
        this.user = user;
        this.post = post;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Like [uuid=" + uuid + ", user=" + user + ", post=" + post + "]";
    }

}
