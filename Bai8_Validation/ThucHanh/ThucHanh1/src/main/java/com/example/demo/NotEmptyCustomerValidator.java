package com.example.demo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyCustomerValidator implements ConstraintValidator<NotEmptyCustome, String> {
   private static final String NOT_EMPTY = "notEmpty://";

   /**
    * Kiểm tra tính hợp lệ của trường được đánh dấu bởi @NotEmptyCustome
    * @param s
    * @param constraintValidatorContext
    * @return
    */
   @Override
   public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      if (s == null || s.isEmpty()) return false;

      return s.startsWith(NOT_EMPTY);
   }
}
