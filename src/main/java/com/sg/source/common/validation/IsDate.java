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
 * IsDate.java
 * 
 * @author (주)오상자이엘 황윤태
 * @since 2020. 3. 2.
 * @version 1.0
 * @see
 *
 *<pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일                 수정자         수정내용
 *  ------------- -------- ---------------------------
 *  2020. 3. 2.           황윤태           최초 생성
 *
 *</pre>
 *//*

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsDateValidator.class)
public @interface IsDate {
    
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
     * 그룹
     * @return {@code Class<?>[]}
     *//*

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
*/
