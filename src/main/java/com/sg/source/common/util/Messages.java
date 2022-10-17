package com.sg.source.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * 메시지소스
 */
@Component
public class Messages {
    static Logger logger = LoggerFactory.getLogger(Messages.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * 메시지소스로부터 key를 조회하여 메시지를 반환한다.
     *
     * @param paramKey
     * @return
     */
    public String getText(String paramKey) {
        return getText(paramKey, new String[]{});
    }

    /**
     * 메시지소스로부터 key를 조회하여 메시지를 반환한다.
     *
     * @param paramKey
     * @param params
     * @return
     */
    public String getText(String paramKey, Object[] params) {
        String key = paramKey;
        String value;
        if (messageSource == null) {
            value = "No bundle!";
        } else if (key == null) {
            value = "Null key!";
        } else {
            try {
                if (key.startsWith("#")) {
                    key = key.substring(1);
                }
                value = messageSource.getMessage(key, params, getLocale());
            } catch (final NoSuchMessageException e) {
                value = "!" + key;
            }
        }
        return value;
    }

    /**
     * 메시지소스로부터 key를 조회하여 메시지를 반환한다.
     * 메시지 조회에 실패하면 defaultString을 반환한다.
     *
     * @param paramKey
     * @param defaultString
     * @return
     */
    public String getText(String paramKey, Object defaultString) {
        return getText(paramKey, String.valueOf(defaultString), null);
    }

    /**
     * 메시지소스로부터 key를 조회하여 메시지를 반환한다.
     * 메시지 조회에 실패하면 defaultString을 반환한다.
     *
     * @param paramKey
     * @param defaultString
     * @param params
     * @return
     */
    public String getText(String paramKey, String defaultString, final Object[] params) {
        String key = paramKey;
        String value = null;
        if (messageSource == null) {
            value = defaultString;
        } else if (key == null) {
            value = defaultString;
        } else {
            try {
                if (key.startsWith("#")) {
                    key = key.substring(1);
                }
                value = messageSource.getMessage(key, params, getLocale());
            } catch (final NoSuchMessageException e) {
                value = defaultString;
            }
        }
        return value;
    }

    private Locale getLocale() {
        try {
            if(null==CommonUtil.getCurrentRequest())
                return Locale.KOREAN;
            Locale locale = CommonUtil.getCurrentRequest().getLocale();
            return locale;
        } catch (Exception e) {
            logger.error("error {}", e);
        }
        return Locale.KOREAN;
    }

    /**
     * 필드명으로 메시지소스를 가져온다.
     *
     * @param field vo의 field
     * @return
     */
    public String getText(Field field) {
        String key = String.format("%s.%s", field.getDeclaringClass().getSimpleName(), field.getName());
        return getText(key);
    }

    /**
     * 임시
     *
     * @param clz
     * @param e
     * @return
     */
    @SuppressWarnings("rawtypes")
    public String getText(Class clz, Enum e) {
        String key = String.format("%s.%s", clz.getSimpleName(), e.name());
        return getText(key);
    }

}
