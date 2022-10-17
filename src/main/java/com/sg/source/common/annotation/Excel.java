package com.sg.source.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Version : v1.0 2017. 8. 23.
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {

    /**
     * 필드명칭
     *
     * @return {@code String}
     */
    String value() default "";

    int order() default 100;
}
