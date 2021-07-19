package com.intuit.craft.demo.feed.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * TweetAuthor entity
 */
@Entity
@Table(name = "tweet_author")
public class TweetAuthor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "screenname", nullable = false)
    private String screenname;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Size(max = 1024)
    @Column(name = "profile_image_url", length = 1024)
    private String profileImageUrl;

    @OneToMany(mappedBy = "createdBy")
    @JsonIgnore
    private Set<Tweet> tweets = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenname() {
        return screenname;
    }

    public TweetAuthor screenname(String screenname) {
        this.screenname = screenname;
        return this;
    }

    public void setScreenname(String screenname) {
        this.screenname = screenname;
    }

    public String getName() {
        return name;
    }

    public TweetAuthor name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public TweetAuthor createdAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public TweetAuthor profileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Set<Tweet> getTweets() {
        return tweets;
    }

    public TweetAuthor tweets(Set<Tweet> tweets) {
        this.tweets = tweets;
        return this;
    }

    public TweetAuthor addTweet(Tweet tweet) {
        this.tweets.add(tweet);
        tweet.setCreatedBy(this);
        return this;
    }

    public TweetAuthor removeTweet(Tweet tweet) {
        this.tweets.remove(tweet);
        tweet.setCreatedBy(null);
        return this;
    }

    public void setTweets(Set<Tweet> tweets) {
        this.tweets = tweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TweetAuthor tweetAuthor = (TweetAuthor) o;
        if (tweetAuthor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tweetAuthor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TweetAuthor{" +
            "id=" + getId() +
            ", screenname='" + getScreenname() + "'" +
            ", name='" + getName() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", profileImageUrl='" + getProfileImageUrl() + "'" +
            "}";
    }
}
