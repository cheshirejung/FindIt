package com.FindIt.FindIt.global.vlidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)//필드를 타켓으로 설정
@Retention(value = RetentionPolicy.RUNTIME)//어노테이션 유지 정책, 런타임 시까지 유지하도록 설정
@Constraint(validatedBy = ValidFileValidator.class)//유효성 검증 시 사용할 클래스 설정
public @interface ValidFile {
    String message() default "Invalid File";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}