package com.marcusli.retrofit;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.marcusli.retrofit.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class DataAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();

	private File file;
	private String fileFileName;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void saveFile() {
		System.out.println("文件名:" + fileFileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		handlerUser();
		if (file != null && file.exists()) {
			user.setMessage("成功获取到文件了:" + fileFileName);
		} else {
			user.setMessage("没有获取到文件");
		}
		String json = getJson(user);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getUser() {
		System.out.println("获取到的值:" + user.getUsername());
		handlerUser();
		String json = getJson(user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getUserAndFile(){
		System.out.println("获取到的值:"+user.getUsername());
		System.out.println("文件名:" + fileFileName);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		user.setMessage("获取到的文件名:"+fileFileName);
		handlerUser();
		String json = getJson(user);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getJson(User user) {
		String json = "{\"username\":\"" + user.getUsername()
				+ "\",\"password\":\"" + user.getPassword()
				+ "\",\"address\":\"" + user.getAddress() + "\",\"message\":\""
				+ user.getMessage() + "\"}";
		return json;
	}

	private boolean isEmpty(String text) {
		if (text == null || text.trim().equals("")) {
			return true;
		}
		return false;
	}

	private void handlerUser() {
		if (isEmpty(user.getUsername())) {
			user.setUsername("Mike by server");
		} else {
			user.setUsername(user.getUsername() + " by Server");
		}

		if (user.getPassword() == 0) {
			user.setPassword(1234);
		} else {
			user.setPassword(user.getPassword() + 404);
		}

		if (isEmpty(user.getAddress())) {
			user.setAddress("America by server");
		} else {
			user.setAddress(user.getAddress() + " by Server");
		}

		if (isEmpty(user.getMessage())) {
			user.setMessage("Message by server");
		} else {
			user.setMessage(user.getMessage() + " by Server");
		}
	}

	@Override
	public User getModel() {
		return user;
	}
}
