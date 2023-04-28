package com.ssm.framework.validator;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDate>{
	private LocalDate startDate;
	
	private LocalDate endDate;

	@Override
	public void initialize(DateRange dateRange) {
		startDate = LocalDate.parse(dateRange.startDate());
		endDate = LocalDate.parse(dateRange.endDate());
	}

	@Override
	public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return value.isEqual(startDate) || value.isEqual(endDate)
				|| (value.isAfter(startDate) && value.isBefore(endDate));
	}
}
