package com.intuit.craft.demo.user.dto.mapper;

import com.intuit.craft.demo.user.dto.EnduserDTO;
import com.intuit.craft.demo.user.entity.Enduser;


public class EnduserMapperUtil  {

	public static Enduser toEntity(EnduserDTO dto) {
		Enduser enduser = new Enduser();
		enduser.setId(dto.getId());
		enduser.setName(dto.getFirstName() + " " + dto.getLastName());
		enduser.setBirthday(dto.getBirthday());
		return enduser;
	}

	public static EnduserDTO toDto(Enduser entity) {
		EnduserDTO dto = new EnduserDTO();
		dto.setId(entity.getId());
		dto.setBirthday(entity.getBirthday());
		String name = entity.getName();
		if(name != null) {
			String[] split = name.trim().split(" ");
			dto.setFirstName(split[0]);
			if(split.length > 1)
				dto.setLastName(split[1]);
		}
		return dto;
	}

	 
 
 
}