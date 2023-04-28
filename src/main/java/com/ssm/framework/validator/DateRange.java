package com.ssm.framework.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {

	String message() default "日付は{startDate}から{endDate}の間である必要があります。";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String startDate();

	String endDate();
}
