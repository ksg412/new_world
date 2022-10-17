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
 * IsNotNull
 * <p>
 * - NULL 확인<br>
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 *//*

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNotNullValidator.class)
public @interface IsNotNull {
    
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
     * 그룹
     * @return {@code Class<?>[]}
     *//*

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
*/
