package com.hcx.web.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.hcx.validator.MyConstraint;

public class User {
	
	public interface UserSimpleView{};
	//有了该继承关系，在显示detail视图的时候同时会把simple视图的所有字段也显示出来
	public interface UserDetailView extends UserSimpleView{};
	
	//@MyConstraint(message="这是一个测试")
	private String username;
	
	@NotBlank(message = "密码不能为空")
	private String password;
	
	private String id;
	
	@Past(message="生日必须是过去的时间")
	private Date birthday;
	
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonView(UserSimpleView.class) //在简单视图上展示该字段
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
