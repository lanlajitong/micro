package com.intuit.craft.demo.feed.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.intuit.craft.demo.feed.controller.util.ResponseUtil;
import com.intuit.craft.demo.feed.entity.TweetAuthor;
import com.intuit.craft.demo.feed.repository.TweetAuthorRepository;
import com.intuit.craft.demo.feed.service.dto.TweetAuthorDTO;
import com.intuit.craft.demo.feed.service.mapper.TweetAuthorMapper;

import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing TweetAuthor.
 */
@RestController
@RequestMapping("/api")
public class TweetAuthorResource {

    private final Logger log = LoggerFactory.getLogger(TweetAuthorResource.class);

    private static final String ENTITY_NAME = "tweetAuthor";

    private final TweetAuthorRepository tweetAuthorRepository;

    private final TweetAuthorMapper tweetAuthorMapper;

    public TweetAuthorResource(TweetAuthorRepository tweetAuthorRepository, TweetAuthorMapper tweetAuthorMapper) {
        this.tweetAuthorRepository = tweetAuthorRepository;
        this.tweetAuthorMapper = tweetAuthorMapper;
    }

    /**
     * POST  /tweet-authors : Create a new tweetAuthor.
     *
     * @param tweetAuthorDTO the tweetAuthorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tweetAuthorDTO, or with status 400 (Bad Request) if the tweetAuthor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tweet-authors")
    @Timed
    public ResponseEntity<TweetAuthorDTO> createTweetAuthor(@Valid @RequestBody TweetAuthorDTO tweetAuthorDTO) throws URISyntaxException {
        log.debug("REST request to save TweetAuthor : {}", tweetAuthorDTO);
        if (tweetAuthorDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tweetAuthor cannot already have an ID")).body(null);
        }
        TweetAuthor tweetAuthor = tweetAuthorMapper.toEntity(tweetAuthorDTO);
        tweetAuthor = tweetAuthorRepository.save(tweetAuthor);
        TweetAuthorDTO result = tweetAuthorMapper.toDto(tweetAuthor);
        return ResponseEntity.created(new URI("/api/tweet-authors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tweet-authors : Updates an existing tweetAuthor.
     *
     * @param tweetAuthorDTO the tweetAuthorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tweetAuthorDTO,
     * or with status 400 (Bad Request) if the tweetAuthorDTO is not valid,
     * or with status 500 (Internal Server Error) if the tweetAuthorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tweet-authors")
    @Timed
    public ResponseEntity<TweetAuthorDTO> updateTweetAuthor(@Valid @RequestBody TweetAuthorDTO tweetAuthorDTO) throws URISyntaxException {
        log.debug("REST request to update TweetAuthor : {}", tweetAuthorDTO);
        if (tweetAuthorDTO.getId() == null) {
            return createTweetAuthor(tweetAuthorDTO);
        }
        TweetAuthor tweetAuthor = tweetAuthorMapper.toEntity(tweetAuthorDTO);
        tweetAuthor = tweetAuthorRepository.save(tweetAuthor);
        TweetAuthorDTO result = tweetAuthorMapper.toDto(tweetAuthor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tweetAuthorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tweet-authors : get all the tweetAuthors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tweetAuthors in body
     */
    @GetMapping("/tweet-authors")
    @Timed
    public List<TweetAuthorDTO> getAllTweetAuthors() {
        log.debug("REST request to get all TweetAuthors");
        List<TweetAuthor> tweetAuthors = tweetAuthorRepository.findAll();
        return tweetAuthorMapper.toDto(tweetAuthors);
    }

    /**
     * GET  /tweet-authors/:id : get the "id" tweetAuthor.
     *
     * @param id the id of the tweetAuthorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tweetAuthorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tweet-authors/{id}")
    @Timed
    public ResponseEntity<TweetAuthorDTO> getTweetAuthor(@PathVariable Long id) {
        log.debug("REST request to get TweetAuthor : {}", id);
        Optional<TweetAuthor> tweetAuthor =  tweetAuthorRepository.findById(id);
        TweetAuthorDTO tweetAuthorDTO = tweetAuthorMapper.toDto(tweetAuthor.get());
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tweetAuthorDTO));
    }

    /**
     * DELETE  /tweet-authors/:id : delete the "id" tweetAuthor.
     *
     * @param id the id of the tweetAuthorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tweet-authors/{id}")
    @Timed
    public ResponseEntity<Void> deleteTweetAuthor(@PathVariable Long id) {
        log.debug("REST request to delete TweetAuthor : {}", id);
        tweetAuthorRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
