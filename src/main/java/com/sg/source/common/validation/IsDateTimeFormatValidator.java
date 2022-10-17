/*
package kr.or.beforeUse.source.common.validation;

import kr.or.kes.com.cmm.EgovMessageSource;
import kr.or.kes.com.cmm.util.LanguageUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

*/
/**
 * IsDateTimeFormat 유효성 검사 클래스
 * <p>
 * - 문자열의 날짜 형식 유효성을 검사한다.
 * <p>
 * @Version : v1.0 2017. 8. 23. 
 * @author  : g388
 *//*

@Component
public class IsDateTimeFormatValidator implements ConstraintValidator<IsDateTimeFormat, String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IsDateTimeFormatValidator.class);
    
    */
/**
     * 기본 메세지 키
     *//*

    public static final String MESSAGE = "validator.isDateTimeFormat";
    public static final String MESSAGE_NULL = "validator.isDateTimeFormat.null";
    public static final String MESSAGE_EMPTY = "validator.isDateTimeFormat.empty";
    public static final String MESSAGE_BLANK = "validator.isDateTimeFormat.blank";
    
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
    */
/* 날짜 *//*

    private String format;
    */
/* Null 허용 여부 *//*

    private boolean notNull;
    */
/* Empty 허용 여부 *//*

    private boolean notEmpty;
    */
/* Blank 허용 여부 *//*

    private boolean notBlank;
    
    @Override
    public void initialize(IsDateTimeFormat annotation) {
        this.value = annotation.value();
        this.message = annotation.message();
        this.format = annotation.format();
        this.notNull = annotation.notNull();
        this.notEmpty = annotation.notEmpty();
        this.notBlank = annotation.notBlank();
    }
    
    @Override
    public boolean isValid(String target, ConstraintValidatorContext context) {
        
        boolean isValid = true;
        String messageKey = "";
        
        // 유효성 검사
        if (isValid && notNull && target == null) {
            messageKey = MESSAGE_NULL;
            isValid = false;
        }
        if (isValid && notEmpty && StringUtils.isEmpty(target)) {
            messageKey = MESSAGE_EMPTY;
            isValid = false;
        }
        if (isValid && notBlank && StringUtils.isBlank(target)) {
            messageKey = MESSAGE_BLANK;
            isValid = false;
        }
        if (isValid && StringUtils.isNotEmpty(target)) {
            if (isValid && StringUtils.isNotEmpty(format)) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(format);
                    sdf.setLenient(false);
                    sdf.parse(target);
                } catch (ParseException pe) {
                    LOGGER.error(pe.getMessage());
                    messageKey = MESSAGE;
                    isValid = false;
                }
            }
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
            newMessage = MessageFormat.format(newMessage, new Object[] { newValue, format });
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
