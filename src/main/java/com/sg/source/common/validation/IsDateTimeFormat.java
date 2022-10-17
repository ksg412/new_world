/*
package kr.or.beforeUse.source.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

*/
/**
 * IsDateTimeFormat
 * <p>
 * - 문자열이 날짜 형태인지 확인<br>
 * - TODO 날짜 비교(이전,이후) 기능 추가<br>
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 *//*

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsDateTimeFormatValidator.class)
public @interface IsDateTimeFormat {
    
    */
/**
     * 필드명칭
     * @return {@code String}
     *//*

    String value() default "";
    
    */
/**
     * 메세지
     * @return {@code String}
     *//*

    String message() default "";
    
    */
/**
     * NULL 허용 여부
     * @return {@code String}
     *//*

    boolean notNull() default false;
    
    */
/**
     * Empty 허용 여부
     * @return {@code String}
     *//*

    boolean notEmpty() default false;
    
    */
/**
     * Blank 허용 여부
     * @return {@code String}
     *//*

    boolean notBlank() default false;
    
    */
/**
     * 포멧
     * @return {@code String}
     *//*

    String format() default "yyyy-MM-dd";
    
    */
/**
     * 그룹
     * @return {@code Class<?>[]}
     *//*

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
*/
