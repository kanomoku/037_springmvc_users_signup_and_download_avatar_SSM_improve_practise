package com.mapper;

import org.apache.ibatis.annotations.Insert;

import com.pojo.User;

public interface UserMapper {
	@Insert("insert into user values(default,#{username},#{password},#{photo})")
	int insUser(User user);

}
