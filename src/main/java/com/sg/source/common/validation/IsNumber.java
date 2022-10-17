package com.sg.source.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * IsNumber
 * <p>
 * - 숫자 크기(최소,최대) 확인<br>
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNumberValidator.class)
public @interface IsNumber {
    
    /**
     * 필드명칭
     * @return {@code String}
     */
    String value() default "";
    
    /**
     * 최소
     * @return {@code int}
     */
    double min() default 0;
    
    /**
     * 최대
     * @return {@code int}
     */
    double max() default 0;
    
    /**
     * NULL 허용 여부
     * @return {@code String}
     */
    boolean notNull() default false;
    
    /**
     * 메세지
     * @return {@code String}
     */
    String message() default "";
    
    /**
     * 그룹
     * @return {@code Class<?>[]}
     */
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
