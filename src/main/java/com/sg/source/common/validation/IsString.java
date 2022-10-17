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
 * IsString
 * <p>
 * - 문자열 공백 확인<br>
 * - 문자열 길이(최소,최대) 확인<br>
 * - 문자열 정규식 확인<br>
 * - TODO 한글/숫자/공백 확인 추가 예정<br>
 * <p>
 * 
 * @Version : v1.0 2017. 8. 23.
 * @author : g388
 *//*

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsStringValidator.class)
public @interface IsString {
    
    */
/**
     * 필드명칭
     * 
     * @return {@code String}
     *//*

    String value() default "";
    
    */
/**
     * 최소길이
     * 
     * @return {@code int}
     *//*

    int min() default 0;
    
    */
/**
     * 최대길이
     * 
     * @return {@code int}
     *//*

    int max() default 0;
    
    */
/**
     * 최소 Byte (영문,숫자: 1byte, 한글: 3byte)
     * 
     * @return {@code int}
     *//*

    int minByte() default 0;
    
    */
/**
     * 최대 Byte (영문,숫자: 1byte, 한글: 3byte)
     * 
     * @return {@code int}
     *//*

    int maxByte() default 0;
    
    */
/**
     * 정규식
     * 
     * @return {@code String}
     *//*

    String format() default "";
    
    */
/**
     * 정규식
     * 
     * @return {@code String}
     *//*

    String formatMessage() default "";
    
    */
/**
     * Null 허용 여부
     * 
     * @return {@code String}
     *//*

    boolean notNull() default false;
    
    */
/**
     * Empty 허용 여부
     * 
     * @return {@code String}
     *//*

    boolean notEmpty() default false;
    
    */
/**
     * Blank 허용 여부
     * 
     * @return {@code String}
     *//*

    boolean notBlank() default false;
    
    */
/**
     * 영문
     * 
     * @return {@code String}
     *//*

    boolean english() default false;
    
    */
/**
     * 정수
     * 
     * @return {@code String}
     *//*

    boolean digit() default false;
    
    */
/**
     * 영문&정수
     * 
     * @return {@code String}
     *//*

    boolean englishDigit() default false;
    
    */
/**
     * 메세지
     * 
     * @return {@code String}
     *//*

    String message() default "";
    
    */
/**
     * 그룹
     * 
     * @return {@code Class<?>[]}
     *//*

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
*/
