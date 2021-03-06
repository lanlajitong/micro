package com.intuit.craft.demo.feed.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Tweet entity
 */
@Entity
@Table(name = "tweet")
public class Tweet  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 140)
    @Column(name = "tweet_text", length = 140, nullable = false)
    private String tweetText;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @NotNull
    @Size(max = 5, message="max size of lang is 5")
    @Column(name = "lang", length = 5, nullable = false)
    private String lang;

    @ManyToOne(optional = false)
    @NotNull
    private TweetAuthor createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTweetText() {
        return tweetText;
    }

    public Tweet tweetText(String tweetText) {
        this.tweetText = tweetText;
        return this;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Tweet createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLang() {
        return lang;
    }

    public Tweet lang(String lang) {
        this.lang = lang;
        return this;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public TweetAuthor getCreatedBy() {
        return createdBy;
    }

    public Tweet createdBy(TweetAuthor tweetAuthor) {
        this.createdBy = tweetAuthor;
        return this;
    }

    public void setCreatedBy(TweetAuthor tweetAuthor) {
        this.createdBy = tweetAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tweet tweet = (Tweet) o;
        if (tweet.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tweet.getId());
    }
 
    @Override
    public String toString() {
        return "Tweet{" +
            "id=" + getId() +
            ", tweetText='" + getTweetText() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", lang='" + getLang() + "'" +
            "}";
    }
}
