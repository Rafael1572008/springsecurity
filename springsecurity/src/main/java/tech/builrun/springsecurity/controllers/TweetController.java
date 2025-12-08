package tech.builrun.springsecurity.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.builrun.springsecurity.controllers.dto.CreateTweetDTO;
import tech.builrun.springsecurity.model.Tweet;
import tech.builrun.springsecurity.repository.TweetRepository;
import tech.builrun.springsecurity.repository.UserRepository;

import java.util.UUID;

@RestController
public class TweetController {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetController(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweets(@RequestBody CreateTweetDTO dto, JwtAuthenticationToken token){

        var user = userRepository.findById(UUID.fromString(token.getName())); // Name aqui é o subject do JWT (IdUSer)

        var tweet = new Tweet();

        tweet.setContent(dto.content());
        tweet.setUser(user.get());

        tweetRepository.save(tweet);

        return ResponseEntity.ok().build();
    }
}
