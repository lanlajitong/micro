package com.intuit.craft.demo.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.craft.demo.feed.entity.Tweet;


/**
 * Spring Data JPA repository for the Tweet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    
}
