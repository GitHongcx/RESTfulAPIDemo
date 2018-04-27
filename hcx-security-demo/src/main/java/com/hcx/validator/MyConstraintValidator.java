package com.hcx.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.hcx.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	/*ConstraintValidator<A, T>
	参数一：验证的注解
	参数二：验证的类型
	ConstraintValidator<MyConstraint, String> 当前注解只能放在String类型字段上才会起作用
	*/
	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return false;//false：校验失败；true：校验成功
	}
	
}
