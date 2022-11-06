package com.example.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NotEmptyFileValidator.class })
public @interface NotEmptyFile {
	
    String message() default "파일을 선택해주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
