package com.sg.source.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageUtil {
    
    private static final String REGEX_KOREAN = "ㄱ-ㅎㅏ-ㅣ가-힣";
    private static final String REGEX_ENGLISH = "a-zA-Z";
    private static final String REGEX_NUMBER = "0-9";
    
    /**
     * 영어인지 확인
     * 
     * @param target
     * @return {@code boolean}
     */
    public static boolean isEnglish(String target) {
        if (StringUtils.isNotEmpty(target)) {
            return target.matches("[" + REGEX_ENGLISH + "]+");
        } else {
            return false;
        }
    }
    
    /**
     * 숫자인지 확인
     * 
     * @param target
     * @return {@code boolean}
     */
    public static boolean isNumber(String target) {
        if (StringUtils.isNotEmpty(target)) {
            return target.matches("[" + REGEX_NUMBER + "]+");
        } else {
            return false;
        }
    }
    
    /**
     * 영어/숫자인지 확인
     * 
     * @param target
     * @return {@code boolean}
     */
    public static boolean isEnglishNumber(String target) {
        if (StringUtils.isNotEmpty(target)) {
            return target.matches("[" + REGEX_ENGLISH + REGEX_NUMBER + "]+");
        } else {
            return false;
        }
    }
    
    /**
     * 한글인지 확인
     * 
     * @param target
     * @return {@code boolean}
     */
    public static boolean isKorean(String target) {
        if (StringUtils.isNotEmpty(target)) {
            return target.matches("[" + REGEX_KOREAN + "]+");
        } else {
            return false;
        }
    }
    
    /**
     * 한글인지 확인
     * 
     * @param target
     * @return {@code boolean}
     */
    public static boolean isKorean(char target) {
        return ('ㄱ' <= target && target <= 'ㅎ') || 'ㅏ' <= target && target <= 'ㅣ' || '가' <= target && target <= '힣';
    }
    
    /**
     * 조사 처리
     * <p>
     * - 메세지의 조사를 처리하기 위한 메서드<br>
     * <p>
     * 
     * @param target
     *            문자
     * @param josa1
     *            받침이 있는 경우 사용 할 조사
     * @param josa2
     *            받침이 없는 경우 사용 할 조사
     * @return {@code String}
     */
    public static String processJosa(String target, String josa1, String josa2) {
        
        if (StringUtils.isNotEmpty(target)) {
            char lastChar = target.charAt(target.length() - 1);
            if (isKorean(lastChar)) {
                return MessageFormat.format("{0}{1}", new Object[] { target, ((lastChar - '가') % 28 > 0 ? josa1 : josa2) });
            } else {
                return MessageFormat.format("{0}{1}({2})", new Object[] { target, josa1, josa2 });
            }
        } else {
            return MessageFormat.format("{0}({1})", new Object[] { josa1, josa2 });
        }
    }
    
    /**
     * 메세지 조사 처리
     * <p>
     * - 메세지의 조사를 처리하기 위한 메서드<br>
     * - {@code 문자열$(조사1,조사2)} 형태의 문자열을 찾아 앞의 문자열에 따라 알맞은 조사를 선택한다.<br>
     * <p>
     * 
     * @param message
     * @return {@code String}
     */
    public static String processJosaFromMessage(String message) {
        
        String regex = "(?<target>[^\\s]*)[$][(](?<josa1>[^,$]+),(?<josa2>[^,$]+)[)]";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String matchedWord = matcher.group(0);
            String target = matcher.group("target");
            String josa1 = matcher.group("josa1");
            String josa2 = matcher.group("josa2");
            String changedWord = processJosa(target, josa1, josa2);
            message = message.replace(matchedWord, changedWord);
        }
        return message;
    }
}
