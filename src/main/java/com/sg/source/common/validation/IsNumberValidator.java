package com.sg.source.common.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * IsNumber 유효성 검사 클래스
 * <p>
 * - 문자열에 대해 유효성을 검사한다.
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 */
@Component
public class IsNumberValidator implements ConstraintValidator<IsNumber, Number> {
    
    /**
     * 기본 메세지 키
     */
    public static final String MESSAGE = "validator.isNumber";
    public static final String MESSAGE_MIN = "validator.isNumber.min";
    public static final String MESSAGE_MAX = "validator.isNumber.max";
    public static final String MESSAGE_NULL = "validator.isNumber.null";

    /* 필드명 */
    private String value;
    /* 기본 메세지 */
    private String message;
    /* 최소 */
    private double min;
    /* 최대 */
    private double max;
    /* Null 허용 */
    private boolean notNull;
    
    @Override
    public void initialize(IsNumber annotation) {
        this.value = annotation.value();
        this.message = annotation.message();
        this.min = annotation.min();
        this.max = annotation.max();
        this.notNull = annotation.notNull();
    }
    
    @Override
    public boolean isValid(Number target, ConstraintValidatorContext context) {
        
        boolean isValid = true;
        String messageKey = "";
        
        // 유효성 검사
        if (isValid && notNull && target == null) {
            messageKey = MESSAGE_NULL;
            isValid = false;
        }
        if (isValid && target != null) {
            if (min > 0 && target.doubleValue() < min) {
                messageKey = MESSAGE_MIN;
                isValid = false;
            } else if (max > 0 && target.doubleValue() > max) {
                messageKey = MESSAGE_MAX;
                isValid = false;
            }
        }
        
        // 오류 메세지를 생성한다.
        if (isValid == false) {
            
            // 입력한 메세지키가 있다면 입력된 메세지키를 사용한다.
            if (StringUtils.isNotEmpty(message)) {
                messageKey = message;
            }
            
            /*// 메세지를 생성한다.
            String newValue = getMessage(value);
            String newMessage = getMessage(messageKey);
            newMessage = MessageFormat.format(newMessage, new Object[] { newValue, min, max });
            newMessage = LanguageUtil.processJosaFromMessage(newMessage);*/
            
            // 변경한 메세지를 사용하도록 설정한다.
            /*context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(newMessage).addConstraintViolation();*/
        }
        return isValid;
    }
    
   /* private String getMessage(String messageKey) {
        if (StringUtils.isNotEmpty(messageKey)) {
            try {
                return egovMessageSource.getMessage(messageKey);
            } catch (NoSuchMessageException e) {
                return messageKey;
            }
        }
        return "";
    }*/
}
