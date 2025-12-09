package tech.builrun.springsecurity.controllers;


import org.hibernate.annotations.NotFound;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.builrun.springsecurity.controllers.dto.CreateTweetDTO;
import tech.builrun.springsecurity.controllers.dto.FeedDto;
import tech.builrun.springsecurity.controllers.dto.FeedItemDto;
import tech.builrun.springsecurity.model.Role;
import tech.builrun.springsecurity.model.Tweet;
import tech.builrun.springsecurity.repository.TweetRepository;
import tech.builrun.springsecurity.repository.UserRepository;

import java.util.List;
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

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweets(@PathVariable("id") Long tweetId, JwtAuthenticationToken token) {

        var tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));

        var user = userRepository.findById(UUID.fromString(token.getName()));

        var isAdmin = user.get().getRoles()
                .stream().anyMatch(role -> role.getName().equals(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/tweets/user")
    public ResponseEntity<List<Tweet>>  userTweets(JwtAuthenticationToken token){

        var user = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE));


        /// O ideal seria que o Admin consguise visualizar todos os tweet

        var tweets = tweetRepository.findByUser_UserId(user.getUserId());

        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){



        return ResponseEntity.ok().build();
    }

}
