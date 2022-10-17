/*
package kr.or.beforeUse.source.common.validation;

import kr.or.kes.com.cmm.EgovMessageSource;
import kr.or.kes.com.cmm.util.LanguageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;
import java.util.Date;


*/
/**
 * IsDateValidator.java
 * Date Validator
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
 *  2020. 3. 2.           황윤태          최초 생성
 *
 *</pre>
 *//*

@Component
public class IsDateValidator implements ConstraintValidator<IsDate, Date> {
    
    */
/**
     * 기본 메세지 키
     *//*
*/
/*
    public static final String MESSAGE = "validator.isDateTimeFormat";
    public static final String MESSAGE_NULL = "validator.isDateTimeFormat.null";
    
    *//*
*/
/* EgovMessageSource *//*
*/
/*
    @Autowired
    EgovMessageSource egovMessageSource;
    
    *//*
*/
/* 필드명 *//*
*/
/*
    private String value;
    *//*
*/
/* 기본 메세지 *//*
*/
/*
    private String message;
    *//*
*/
/* Null 허용 여부 *//*
*/
/*
    private boolean notNull;

    
    @Override
    public void initialize(IsDate annotation) {
        this.value = annotation.value();
        this.message = annotation.message();
        this.notNull = annotation.notNull();
    }
    
    @Override
    public boolean isValid(Date target, ConstraintValidatorContext context) {
        
        boolean isValid = true;
        String messageKey = "";
        
        // 유효성 검사
        if (isValid && notNull && target == null) {
            messageKey = MESSAGE_NULL;
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
    }*//*

}
*/
