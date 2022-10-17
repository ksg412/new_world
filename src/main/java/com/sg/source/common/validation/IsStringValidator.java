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
import java.nio.charset.Charset;
import java.text.MessageFormat;

*/
/**
 * IsString 유효성 검사 클래스
 * <p>
 * - 문자열에 대해 유효성을 검사한다.
 * <p>
 * 
 * @Version : v1.0 2017. 8. 23.
 * @author : g388
 *//*

@Component
public class IsStringValidator implements ConstraintValidator<IsString, String> {
    
    */
/**
     * 기본 메세지 키
     *//*

    public static final String MESSAGE = "validator.isString";
    public static final String MESSAGE_LENGTH = "validator.isString.length";
    public static final String MESSAGE_MIN = "validator.isString.min";
    public static final String MESSAGE_MAX = "validator.isString.max";
    public static final String MESSAGE_LENGTH_BYTE = "validator.isString.lengthByte";
    public static final String MESSAGE_MIN_BYTE = "validator.isString.minByte";
    public static final String MESSAGE_MAX_BYTE = "validator.isString.maxByte";
    public static final String MESSAGE_FORMAT = "validator.isString.format";
    public static final String MESSAGE_NULL = "validator.isString.null";
    public static final String MESSAGE_EMPTY = "validator.isString.empty";
    public static final String MESSAGE_BLANK = "validator.isString.blank";
    public static final String MESSAGE_ENGLISH = "validator.isString.english";
    public static final String MESSAGE_DIGIT = "validator.isString.digit";
    public static final String MESSAGE_ENGLISH_DIGIT = "validator.isString.englishDigit";
    
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
/* 최소 길이 *//*

    private int min;
    */
/* 최대 길이 *//*

    private int max;
    */
/* 최소 Byte *//*

    private int minByte;
    */
/* 최대 Byte *//*

    private int maxByte;
    */
/* 정규식 *//*

    private String format;
    */
/* 정규식 *//*

    private String formatMessage;
    */
/* Null 허용 여부 *//*

    private boolean notNull;
    */
/* Empty 허용 여부 *//*

    private boolean notEmpty;
    */
/* Blank 허용 여부 *//*

    private boolean notBlank;
    */
/* 영문 *//*

    private boolean english;
    */
/* 숫자 *//*

    private boolean digit;
    */
/* 영문&숫자 *//*

    private boolean englishDigit;
    
    @Override
    public void initialize(IsString annotation) {
        this.value = annotation.value();
        this.message = annotation.message();
        this.min = annotation.min();
        this.max = annotation.max();
        this.minByte = annotation.minByte();
        this.maxByte = annotation.maxByte();
        this.format = annotation.format();
        this.formatMessage = annotation.formatMessage();
        this.notNull = annotation.notNull();
        this.notEmpty = annotation.notEmpty();
        this.notBlank = annotation.notBlank();
        this.english = annotation.english();
        this.digit = annotation.digit();
        this.englishDigit = annotation.englishDigit();
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
            int length = target.length();
            int byteLength = target.getBytes(Charset.forName("UTF-8")).length;
            if (min > 0 && max > 0 && min == max && length != max) {
                messageKey = MESSAGE_LENGTH;
                isValid = false;
            } else if (min > 0 && length < min) {
                messageKey = MESSAGE_MIN;
                isValid = false;
            } else if (max > 0 && length > max) {
                messageKey = MESSAGE_MAX;
                isValid = false;
            } else if (minByte > 0 && maxByte > 0 && minByte == maxByte && byteLength != maxByte) {
                messageKey = MESSAGE_LENGTH_BYTE;
                isValid = false;
            } else if (minByte > 0 && byteLength < minByte) {
                messageKey = MESSAGE_MIN_BYTE;
                isValid = false;
            } else if (maxByte > 0 && byteLength > maxByte) {
                messageKey = MESSAGE_MAX_BYTE;
                isValid = false;
            } else if (StringUtils.isNotEmpty(format) && (target.matches(format) == false)) {
                if (StringUtils.isNotEmpty(formatMessage)) {
                    messageKey = formatMessage;
                } else {
                    messageKey = MESSAGE_FORMAT;
                }
                isValid = false;
            } else if (english && LanguageUtil.isEnglish(target) == false) {
                messageKey = MESSAGE_ENGLISH;
                isValid = false;
            } else if (digit && LanguageUtil.isNumber(target) == false) {
                messageKey = MESSAGE_DIGIT;
                isValid = false;
            } else if (englishDigit && LanguageUtil.isEnglishNumber(target) == false) {
                messageKey = MESSAGE_ENGLISH_DIGIT;
                isValid = false;
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
            newMessage = MessageFormat.format(newMessage, new Object[] { newValue, min, max, format, minByte, maxByte });
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
