/*
package kr.or.beforeUse.source.common.validation;

import kr.or.kes.com.cmm.EgovMessageSource;
import kr.or.kes.com.cmm.util.LanguageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;

*/
/**
 * IsNotNull 유효성 검사 클래스
 * <p>
 * - NUll 유효성을 검사한다.
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 *//*

@Component
public class IsNotNullValidator implements ConstraintValidator<IsNotNull, Object> {
    
    */
/**
     * 기본 메세지 키
     *//*

    public static final String MESSAGE = "validator.isNotNull";
    
    */
/* EgovMessageSource *//*

    @Resource(name = "egovMessageSource")
    EgovMessageSource egovMessageSource;
    
    */
/* 필드명 *//*

    private String value;
    */
/* 기본 메세지 *//*

    private String message;
    
    @Override
    public void initialize(IsNotNull annotation) {
        this.value = annotation.value();
        this.message = annotation.message();
    }
    
    @Override
    public boolean isValid(Object target, ConstraintValidatorContext context) {
        
        boolean isValid = true;
        String messageKey = "";
        
        // 유효성 검사
        if (target == null) {
            messageKey = MESSAGE;
            isValid = false;
        }
        
        // 오류 메세지를 생성한다.
        if (isValid == false) {
            
            // 입력한 메세지키가 있다면 입력된 메세지키를 사용한다.
            if (StringUtils.isNotEmpty(message)) {
                messageKey = message;
            }
            
            // 메세지를 생성한다.
            String newValue = getMessage(value);
            String newMessage = getMessage(messageKey);
            newMessage = MessageFormat.format(newMessage, new Object[] { newValue });
            newMessage = LanguageUtil.processJosaFromMessage(newMessage);
            
            // 변경한 메세지를 사용하도록 설정한다.
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(newMessage).addConstraintViolation();
        }
        return isValid;
    }
    
    private String getMessage(String messageKey) {
        if (StringUtils.isNotEmpty(messageKey)) {
            try {
                return egovMessageSource.getMessage(messageKey);
            } catch (NoSuchMessageException e) {
                return messageKey;
            }
        }
        return "";
    }
}
*/
