package com.intuit.craft.demo.feed.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.craft.demo.feed.controller.util.HeaderUtil;
import com.intuit.craft.demo.feed.controller.util.PaginationUtil;
import com.intuit.craft.demo.feed.controller.util.ResponseUtil;
import com.intuit.craft.demo.feed.entity.Tweet;
import com.intuit.craft.demo.feed.repository.TweetRepository;
import com.intuit.craft.demo.feed.service.dto.TweetDTO;
import com.intuit.craft.demo.feed.service.mapper.TweetMapper;

import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing Tweet.
 */
@RestController
@RequestMapping("/api")
public class TweetResource {

    private final Logger log = LoggerFactory.getLogger(TweetResource.class);

    private static final String ENTITY_NAME = "tweet";

    private final TweetRepository tweetRepository;

    private final TweetMapper tweetMapper;

    public TweetResource(TweetRepository tweetRepository, TweetMapper tweetMapper) {
        this.tweetRepository = tweetRepository;
        this.tweetMapper = tweetMapper;
    }

    /**
     * POST  /tweets : Create a new tweet.
     *
     * @param tweetDTO the tweetDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tweetDTO, or with status 400 (Bad Request) if the tweet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tweets")
    @Timed
    public ResponseEntity<TweetDTO> createTweet(@Valid @RequestBody TweetDTO tweetDTO) throws URISyntaxException {
        log.debug("REST request to save Tweet : {}", tweetDTO);
        if (tweetDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tweet cannot already have an ID")).body(null);
        }
        Tweet tweet = tweetMapper.toEntity(tweetDTO);
        tweet = tweetRepository.save(tweet);
        TweetDTO result = tweetMapper.toDto(tweet);
        return ResponseEntity.created(new URI("/api/tweets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tweets : Updates an existing tweet.
     *
     * @param tweetDTO the tweetDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tweetDTO,
     * or with status 400 (Bad Request) if the tweetDTO is not valid,
     * or with status 500 (Internal Server Error) if the tweetDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tweets")
    @Timed
    public ResponseEntity<TweetDTO> updateTweet(@Valid @RequestBody TweetDTO tweetDTO) throws URISyntaxException {
        log.debug("REST request to update Tweet : {}", tweetDTO);
        if (tweetDTO.getId() == null) {
            return createTweet(tweetDTO);
        }
        Tweet tweet = tweetMapper.toEntity(tweetDTO);
        tweet = tweetRepository.save(tweet);
        TweetDTO result = tweetMapper.toDto(tweet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tweetDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tweets : get all the tweets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tweets in body
     */
    @GetMapping("/tweets")
    @Timed
    public ResponseEntity<List<TweetDTO>> getAllTweets(Pageable pageable) {
        log.debug("REST request to get a page of Tweets");
        Page<Tweet> page = tweetRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tweets");
        return new ResponseEntity<>(tweetMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /feed : get all the tweets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tweets in body
     */
    @GetMapping("/feed")
    @Timed
    public ResponseEntity<List<TweetDTO>> getTweetFeed(Pageable pageable) {
        log.debug("REST request to get tweet feed");
        pageable = PageRequest.of(pageable.getPageNumber(), 100);
        Page<Tweet> page = tweetRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/feed");
        return new ResponseEntity<>(tweetMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /tweets/:id : get the "id" tweet.
     *
     * @param id the id of the tweetDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tweetDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tweets/{id}")
    @Timed
    public ResponseEntity<TweetDTO> getTweet(@PathVariable Long id) {
        log.debug("REST request to get Tweet : {}", id);
        Optional<Tweet> tweet = tweetRepository.findById(id);
        TweetDTO tweetDTO = tweetMapper.toDto(tweet.get());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tweetDTO));
    }

    /**
     * DELETE  /tweets/:id : delete the "id" tweet.
     *
     * @param id the id of the tweetDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tweets/{id}")
    @Timed
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        log.debug("REST request to delete Tweet : {}", id);
        tweetRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
