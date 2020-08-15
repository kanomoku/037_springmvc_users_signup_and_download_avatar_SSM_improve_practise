package com.service;

import java.util.List;

import com.pojo.Files;
import com.pojo.User;

public interface FileService {
	List<Files> show();

	/**
	 * 根据Id修改下载次数
	 * @param id
	 * @return
	 */
	int updCount(int id,User users,String name);
}
