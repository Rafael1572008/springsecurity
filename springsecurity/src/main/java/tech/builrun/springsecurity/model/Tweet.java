package tech.builrun.springsecurity.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweet_id")
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK -> tb_users.user_id
    private User user;

    @Column(name = "content")
    private String content;

    @CreationTimestamp
    private Instant creationTimestamp;

    // getters e setters
}

