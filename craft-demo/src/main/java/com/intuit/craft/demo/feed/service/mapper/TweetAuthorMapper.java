package com.intuit.craft.demo.feed.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.intuit.craft.demo.feed.entity.TweetAuthor;
import com.intuit.craft.demo.feed.service.dto.TweetAuthorDTO;

/**
 * Mapper for the entity TweetAuthor and its DTO TweetAuthorDTO.
 */
@Component
public class TweetAuthorMapper implements EntityMapper <TweetAuthorDTO, TweetAuthor> {
    
  

	@Override
	public TweetAuthorDTO toDto(TweetAuthor entity) {
		TweetAuthorDTO dto = new TweetAuthorDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setProfileImageUrl(entity.getProfileImageUrl());
		dto.setScreenname(entity.getScreenname());
		dto.setCreatedAt(entity.getCreatedAt());
		return dto;
	}

	@Override
	public List<TweetAuthor> toEntity(List<TweetAuthorDTO> dtoList) {
		List<TweetAuthor> entities = new ArrayList<TweetAuthor>();
		for(TweetAuthorDTO dto : dtoList) {
			entities.add(toEntity(dto));
		 }
		 return entities; 
	}

	@Override
	public List<TweetAuthorDTO> toDto(List<TweetAuthor> entityList) {
		List<TweetAuthorDTO> dtos = new ArrayList<TweetAuthorDTO>();
		for(TweetAuthor e : entityList) {
			dtos.add(toDto(e));
		}
		return dtos;
	}

	@Override
	public TweetAuthor toEntity(TweetAuthorDTO dto) {
        TweetAuthor tweetAuthor = new TweetAuthor();
        tweetAuthor.setId(dto.getId());
        tweetAuthor.setName(dto.getName());
        tweetAuthor.setProfileImageUrl(dto.getProfileImageUrl());
        tweetAuthor.setScreenname(dto.getScreenname());
        tweetAuthor.setCreatedAt(dto.getCreatedAt());
		return tweetAuthor;
	}
}
