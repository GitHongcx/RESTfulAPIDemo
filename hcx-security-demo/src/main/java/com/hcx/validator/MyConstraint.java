package com.hcx.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD,ElementType.FIELD})//可以标注在方法和字段上
@Retention(RetentionPolicy.RUNTIME)//运行时注解
@Constraint(validatedBy = MyConstraintValidator.class)//validatedBy ：当前的注解需要使用什么类去校验，即校验逻辑
public @interface MyConstraint {
	
	String message();//校验不通过要发送的信息

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
}
