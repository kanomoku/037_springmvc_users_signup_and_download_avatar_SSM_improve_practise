package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mapper.FilesMapper;
import com.pojo.Files;
import com.pojo.User;
import com.service.FileService;

@Service
public class FilesServiceImpl implements FileService {
	@Resource
	private FilesMapper filesMapper;

	@Override
	public List<Files> show() {
		List<Files> selAll = filesMapper.selAll();
		return selAll;
	}

	@Override
	public int updCount(int id, User user, String name) {
		Logger logger = Logger.getLogger(FilesServiceImpl.class);
		logger.info(user.getUsername() + "下载了" + name);
		int updCountById = filesMapper.updCountById(id);
		return updCountById;
	}

}
