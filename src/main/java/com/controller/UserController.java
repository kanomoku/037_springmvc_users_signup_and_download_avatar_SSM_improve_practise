package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.pojo.User;
import com.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userServiceImpl;

	@RequestMapping("register")
	public String Register(User user,MultipartFile file,HttpServletRequest req) {
		int lastIndexOf = file.getOriginalFilename().lastIndexOf(".");
		String suffix = file.getOriginalFilename().substring(lastIndexOf);
		String fileName = UUID.randomUUID().toString()+suffix;
		String path = req.getServletContext().getRealPath("image");
		String pathAndName = path+"/"+fileName;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(pathAndName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//只能取到webapps文件夹内容
		user.setPhoto(fileName);
		int insRegister = userServiceImpl.insRegister(user);
		if (insRegister > 0) {
			req.getSession().setAttribute("user", user);
			return "redirect:/show";
		} else {
			return "redirect:/register.jsp";
		}
		
	}
}
