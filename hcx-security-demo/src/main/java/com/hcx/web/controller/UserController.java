package com.hcx.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.hcx.exception.UserNotExistException;
import com.hcx.web.dto.User;

@RestController
@RequestMapping("/user")//在类上声明了/user，在方法中就可以省略了
public class UserController {
	
//	@RequestMapping(value="/user",method=RequestMethod.GET)
//	@GetMapping("/user")
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(@RequestParam(name="username",required=false,defaultValue="tom") String username){
		System.out.println(username);
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	
//	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
//	public User getInfo(@PathVariable(name="id") String userId) { //不指定name，必须保证参数名和传递的一致
	
//	@RequestMapping(value="/user/{id:\\d+}",method=RequestMethod.GET)//如果希望对传递进来的参数作一些限制，就需要使用正则表达式
//	@GetMapping("/user/{id:\\d+}")
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	
	//发生异常时，抛自己自定义的异常
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo1(@PathVariable String id) {
		throw new UserNotExistException(id);
	}
	
	
	@PostMapping
	public User create(@Valid @RequestBody User user,BindingResult errors) {
		
		if(errors.hasErrors()) {
			//有错误返回true
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			//may not be empty
		}
		
		System.out.println(user.getId()); //null
		System.out.println(user.getUsername()); //tom
		System.out.println(user.getPassword());//null
		
		System.out.println(user.getBirthday());//Thu Apr 26 19:13:49 CST 2018
		user.setId("1");
		return user;
	}
	
	//带着错误信息进入方法体 BindingResult
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		
		/*if(errors.hasErrors()) {
			//有错误返回true
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
			//may not be empty
		}*/
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				//FieldError fieldError = (FieldError)error;
				//String message = fieldError.getField()+" "+error.getDefaultMessage();
				System.out.println(error.getDefaultMessage()); 
				//密码不能为空
				//生日必须是过去的时间
//				birthday must be in the past
//				password may not be empty
			}
			);
		}
		System.out.println(user.getId()); //null
		System.out.println(user.getUsername()); //tom
		System.out.println(user.getPassword());//null
		
		System.out.println(user.getBirthday());//Thu Apr 26 19:13:49 CST 2018
		user.setId("1");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

}
