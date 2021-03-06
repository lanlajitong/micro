package com.intuit.craft.demo.feed.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intuit.craft.demo.feed.entity.Tweet;
import com.intuit.craft.demo.feed.entity.TweetAuthor;
import com.intuit.craft.demo.feed.service.dto.TweetDTO;

/**
 * Mapper for the entity Tweet and its DTO TweetDTO.
 */
@Component
public class TweetMapper implements EntityMapper <TweetDTO, Tweet> {

	@Override
	public Tweet toEntity(TweetDTO dto) {
		  Tweet tweet = new Tweet();
		  tweet.setId(dto.getId());
		  tweet.setLang(dto.getLang());
		  tweet.setTweetText(dto.getTweetText());
		  tweet.setCreatedAt(dto.getCreatedAt());
		  TweetAuthor a = new TweetAuthor();
		  a.setId(dto.getCreatedById());
		  a.setName(dto.getCreatedByScreenname());
		  tweet.setCreatedBy(a);
		return tweet;
	}

	@Override
	public TweetDTO toDto(Tweet entity) {
		TweetDTO tweet = new TweetDTO();
		  tweet.setId(entity.getId());
		  tweet.setLang(entity.getLang());
		  tweet.setTweetText(entity.getTweetText());
		  tweet.setCreatedAt(entity.getCreatedAt());
		  tweet.setCreatedById(entity.getCreatedBy().getId());
		  tweet.setCreatedByScreenname(entity.getCreatedBy().getScreenname());

		return tweet;
	}

	@Override
	public List<Tweet> toEntity(List<TweetDTO> dtoList) {
		List<Tweet> entities = new ArrayList<Tweet>();
		for(TweetDTO dto : dtoList) {
			entities.add(toEntity(dto));
		 }
		 return entities; 
	}

	@Override
	public List<TweetDTO> toDto(List<Tweet> entityList) {
		List<TweetDTO> dtos = new ArrayList<TweetDTO>();
		for(Tweet e : entityList) {
			dtos.add(toDto(e));
		}
		return dtos;
	}

    
}
