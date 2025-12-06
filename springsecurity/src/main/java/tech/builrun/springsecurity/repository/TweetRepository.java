package tech.builrun.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.builrun.springsecurity.model.Tweet;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
}

