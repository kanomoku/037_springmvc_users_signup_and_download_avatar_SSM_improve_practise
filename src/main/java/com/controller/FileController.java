package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pojo.Files;
import com.pojo.User;
import com.service.FileService;

@Controller
public class FileController {
	@Resource
	private FileService filesServiceImpl;

	@RequestMapping("show")
	public String Show(Model model) {
		List<Files> show = filesServiceImpl.show();
		model.addAttribute("list", show);
		return "/main.jsp";
	}

	@RequestMapping("download")
	public void download(int id, String name, HttpServletResponse resp, HttpServletRequest req) throws IOException {
		filesServiceImpl.updCount(id, (User) req.getSession().getAttribute("user"), name);
		resp.setHeader("ConTent-Disposition", "attachment;filename=" + name);
		ServletOutputStream os = resp.getOutputStream();// 字节流
		String realPath = req.getServletContext().getRealPath("file");
		File file2 = new File(realPath, name);
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file2);// 文件转成字节数组
		os.write(readFileToByteArray);
		os.flush();
		os.close();
	}
}
