package com.intuit.craft.demo.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.demo.feed.entity.TweetAuthor;


/**
 * Spring Data JPA repository for the TweetAuthor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TweetAuthorRepository extends JpaRepository<TweetAuthor,Long> {
    
}
