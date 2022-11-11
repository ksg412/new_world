package com.sg.source.common.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.sg.source.common.annotation.Excel;
import com.sg.source.common.vo.FileVO;
import lombok.Builder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    private static Messages messages;

    public static UserDetails getUserDetails(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }

    public static String getUploadPath(MultipartHttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("uploadFile");
    }


    /**
     * Empty 여부를 확인한다.
     * @param o Object
     * @return boolean
     * @exception IllegalArgumentException
     */
    public static boolean isEmpty(Object o) throws IllegalArgumentException {
        try {
            if(o == null) return true;

            if(o instanceof String) {
                if(((String)o).length() == 0){
                    return true;
                }
            } else if(o instanceof Collection) {
                if(((Collection<?>)o).isEmpty()){
                    return true;
                }
            } else if(o.getClass().isArray()) {
                if(Array.getLength(o) == 0){
                    return true;
                }
            } else if(o instanceof Map) {
                if(((Map<?, ?>)o).isEmpty()){
                    return true;
                }
            }else {
                return false;
            }

            return false;

        } catch(IllegalArgumentException e) {
            logger.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch(Exception e) {
            logger.error("["+e.getClass()+"] Try/Catch...Exception : " + e.getMessage());
        }
        return false;
    }

    /**
     * Not Empty 여부를 확인한다.
     * @param o Object
     * @return boolean
     * @exception IllegalArgumentException
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * Equal 여부를 확인한다.
     * @param obj Object, obj Object
     * @return boolean
     */

    public static boolean isEquals(Object obj, Object obj2){
        if(isEmpty(obj)) return false;

        if(obj instanceof String && obj2 instanceof String) {
            if( (String.valueOf(obj)).equals( String.valueOf(obj2) )){
                return true;
            }
        }else if(obj instanceof String && obj2 instanceof Character) {
            if( (String.valueOf(obj) ).equals( String.valueOf(obj2) )){
                return true;
            }
        }else if(obj instanceof String && obj2 instanceof Integer) {
            if( (String.valueOf(obj)).equals( String.valueOf((Integer)obj2) )){
                return true;
            }

        }else if(obj instanceof Integer && obj2 instanceof String) {
            if( (String.valueOf(obj2)).equals( String.valueOf((Integer)obj) )){
                return true;
            }
        } else if(obj instanceof Integer && obj instanceof Integer) {
            if((Integer)obj == (Integer)obj2){
                return true;
            }
        }

        return false;
    }

    /**
     * String의 Equal 여부를 확인한다.
     * @param obj Object, obj Object
     * @return boolean
     */
    public static boolean isEqualsStr(Object obj, String s){
        if(isEmpty(obj)) return false;

        if(s.equals(String.valueOf(obj))){
            return true;
        }
        return false;
    }

    /**
     * Null 여부를 확인한다.
     * @param o Object
     * @return boolean
     * @exception IllegalArgumentException
     */
    public static boolean isNull(Object o) throws IllegalArgumentException {
        try {
            if (o == null) {
                return true;
            } else {
                return false;
            }

        } catch(IllegalArgumentException e) {
            logger.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
        } catch(Exception e) {
            logger.error("["+e.getClass()+"] Try/Catch...Exception : " + e.getMessage());
        }
        return false;
    }

    /**
     * NotNull 여부를 확인한다.
     * @param o Object
     * @return boolean
     * @exception IllegalArgumentException
     */
    public static boolean isNotNull(Object o) throws IllegalArgumentException {
        return !isNull(o);
    }


    /**
     * 카멜케이스 변환
     *
     * @param str        - 변환대상 문자
     * @param firstUpper - 첫문자 대문자 여부
     * @return 변환된 문자열
     */
    public static String toCamelCase(String str, boolean firstUpper) {
        String camelCaseString = "";

        String[] parts = StringUtils.split(StringUtils.lowerCase(str), "_");
        for (int i = 0; i < parts.length; i++) {
            if (i == 0 && !firstUpper) {
                camelCaseString += parts[i];
            } else {
                camelCaseString += StringUtils.capitalize(parts[i]);
            }
        }

        return camelCaseString;
    }

    /**
     * 카멜케이스 변환
     *
     * @param str - 변환대상 문자
     * @return 변환된 문자열
     */
    public static String toCamelCase(String str) {
        return toCamelCase(str, false);
    }

    /**
     * 카멜케이스 문자 역변환
     *
     * @param str - 변환대상 카멜케이스 문자
     * @return 변환된 문자열
     */
    public static String fromCamelCase(String str) {
        return fromCamelCase(str, "_", false);
    }

    /**
     * 카멜케이스 문자 역변환
     *
     * @param str     - 변환대상 카멜케이스 문자
     * @param isLower - 소문자 변환 여부
     * @return 변환된 문자열
     */
    public static String fromCamelCase(String str, boolean isLower) {
        return fromCamelCase(str, "_", isLower);
    }

    /**
     * 카멜케이스 문자 역변환
     *
     * @param str       - 변환대상 카멜케이스 문자
     * @param delimiter - 변환 구분자
     * @param isLower   - 소문자 변환 여부
     * @return 변환된 문자열
     */
    public static String fromCamelCase(String str, String delimiter, boolean isLower) {
        String replaceString = "";

        String[] strs = StringUtils.splitByCharacterTypeCamelCase(str);
        for (String s : strs) {
            if (StringUtils.isNotEmpty(replaceString) && !StringUtils.isNumeric(s)) {
                replaceString += delimiter;
            }
            replaceString += (isLower) ? StringUtils.lowerCase(s) : StringUtils.upperCase(s);
        }

        return replaceString;
    }

    /**
     * Vo 객체에서 특정 어노테이션이 적용된 필드를 추출한다.
     *
     * @param voClass         - 대상 Vo 객체
     * @param annotationClass - 적용 된 어노테이션
     * @param isOrder         - 정렬 여부
     * @return 필드 목록
     */
    public static List<Field> annotaionFieldList(Class<?> voClass, final Class<? extends Annotation> annotationClass, boolean isOrder) {

        List<Field> fieldList = new ArrayList<>();

        try {
            fieldList = searchAnnotaionField(fieldList, voClass, annotationClass);

            if (isOrder) {
                // 정렬
                Collections.sort(fieldList, new Comparator<Field>() {

                    @Override
                    public int compare(Field first, Field second) {
                        int firstOrder = getOrder(first);
                        int secondOrder = getOrder(second);

                        int result = -1;
                        if (firstOrder > secondOrder) {
                            result = 1;
                        } else if (firstOrder == secondOrder) {
                            result = 0;
                        }
                        return result;
                    }

                    private int getOrder(Field first) {
                        int order = 0;
                        Annotation ac = first.getAnnotation(annotationClass);
                        if (ac instanceof Excel) {
                            order = ((Excel) ac).order();
//                        } else if (ac instanceof TableList) {
//                            order = ((TableList) ac).order();
//                        } else if (ac instanceof Form) {
//                            order = ((Form) ac).order();
                        }
                        return order;
                    }
                });
            }

        } catch (java.lang.Exception e) {
            logger.error("", e);
        }

        return fieldList;

    }

    @SuppressWarnings("unchecked")
    private static <T> List<Field> searchAnnotaionField(List<Field> fieldList, Class<?> voClass, Class<? extends Annotation> annotationClass) throws InstantiationException, IllegalAccessException {

        List<Field> annotationFieldList = fieldList;

        for (Field field : voClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                boolean dupleCheck = true;
                for (Field f : annotationFieldList) {
                    if (f.getName().equals(field.getName())) {
                        dupleCheck = false;
                    }
                }
                if (dupleCheck) {
                    annotationFieldList.add(field);
                }
            }
        }

        if (!StringUtils.equals(voClass.getSuperclass().getName(), "kr.or.kes.com.cmm.vo.BaseVO")
                && !StringUtils.equals(voClass.getSuperclass().getName(), "java.lang.Object")) {
            annotationFieldList = searchAnnotaionField(annotationFieldList, voClass.getSuperclass(), annotationClass);
        }

        return annotationFieldList;
    }

    /**
     * 문자 바이트사이즈로 자르기
     * @param str
     * @param i
     * @return
     */
    public static String byteLeft(String str, int i) {
        if(null==str){
            return "";
        }else{
            if(str.getBytes().length>i){
                StringBuilder sb = new StringBuilder(i);
                int cnt=0;
                for(char ch:str.toCharArray()){
                    cnt+=String.valueOf(ch).getBytes().length;
                    if(cnt>i)break;
                    sb.append(ch);
                }
                return sb.toString();
            }else{
                return str;
            }
        }
    }

    /**
     * Messages Bean 호출
     *
     * @return Messages Bean
     */
    public static Messages getMessages() {
        if (messages == null) {
            messages = (Messages) getBean(Messages.class);
        }
        return messages;
    }

    /**
     * Spring Bean을 가져온다.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Object getBean(Class beanType) {
        try {
            if (null != ContextLoader.getCurrentWebApplicationContext())
                return ContextLoader.getCurrentWebApplicationContext().getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            logger.warn("error {}", e);
        }
        return null;
    }

    public static HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (requestAttributes instanceof ServletRequestAttributes) {
            request = ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return request;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 지정한길이에다가 해당 문자열을 붙여주는 메서드.
     * @param source 원본 문자열 배열
     * @param output 더할문자열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, String output, int slength) {
        String returnVal = null;
        if (source != null) {
            if (source.length() > slength) {
                returnVal = source.substring(0, slength) + output;
            } else returnVal = source;
        }
        return returnVal;
    }

    /**
     * 문자열이 지정한 길이를 초과했을때 해당 문자열을 삭제하는 메서드
     * @param source 원본 문자열 배열
     * @param slength 지정길이
     * @return 지정길이로 잘라서 더할분자열 합친 문자열
     */
    public static String cutString(String source, int slength) {
        String result = null;
        if (source != null) {
            if (source.length() > slength) {
                result = source.substring(0, slength);
            } else result = source;
        }
        return result;
    }

    /**
     * <p>
     * String이 비었거나("") 혹은 null 인지 검증한다.
     * </p>
     *
     * <pre>
     *  StringUtil.isEmpty(null)      = true
     *  StringUtil.isEmpty("")        = true
     *  StringUtil.isEmpty(" ")       = false
     *  StringUtil.isEmpty("bob")     = false
     *  StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str
     *            - 체크 대상 스트링오브젝트이며 null을 허용함
     * @return <code>true</code> - 입력받은 String 이 빈 문자열 또는 null인 경우
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 기준 문자열에 포함된 모든 대상 문자(char)를 제거한다.
     * StringUtil.remove(null, *)       = null
     * StringUtil.remove("", *)         = ""
     * StringUtil.remove("queued", 'u') = "qeed"
     * StringUtil.remove("queued", 'z') = "queued"
     * @param str 입력받는 기준 문자열
     * @param remove 입력받는 문자열에서 제거할 대상 문자열
     * @return 제거대상 문자열이 제거된 입력문자열. 입력문자열이 null인 경우 출력문자열은 null
     */
    public static String remove(String str, char remove) {
        if (isEmpty(str) || str.indexOf(remove) == -1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int pos = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != remove) {
                chars[pos++] = chars[i];
            }
        }
        return new String(chars, 0, pos);
    }

    /**
     * 원본 문자열의 포함된 특정 문자열을 새로운 문자열로 변환하는 메서드
     * @param source 원본 문자열
     * @param subject 원본 문자열에 포함된 특정 문자열
     * @param object 변환할 문자열
     * @return sb.toString() 새로운 문자열로 변환된 문자열
     */
    public static String replace(String source, String subject, String object) {
        StringBuffer rtnStr = new StringBuffer();
        String preStr = "";
        String nextStr = source;
        String srcStr = source;

        while (srcStr.indexOf(subject) >= 0) {
            preStr = srcStr.substring(0, srcStr.indexOf(subject));
            nextStr = srcStr.substring(srcStr.indexOf(subject) + subject.length(), srcStr.length());
            srcStr = nextStr;
            rtnStr.append(preStr).append(object);
        }
        rtnStr.append(nextStr);

        return rtnStr.toString();
    }

    /**
     * html의 특수문자를 표현하기 위해
     *
     * @param srcString
     * @return String
     * @exception Exception
     * @see
     */
    public static String getHtmlStrCnvr(String srcString) {

        String tmpString = srcString;

        tmpString = tmpString.replaceAll("&lt;", "<");
        tmpString = tmpString.replaceAll("&gt;", ">");
        tmpString = tmpString.replaceAll("&amp;", "&");
        tmpString = tmpString.replaceAll("&nbsp;", " ");
        tmpString = tmpString.replaceAll("&apos;", "\'");
        tmpString = tmpString.replaceAll("&quot;", "\"");

        return tmpString;
    }

    /**
     * 오늘 날짜 조회
     * @param pattern 패턴(yyyy-MM-dd)
     * @return today(2021-09-03)
     */
    public static String getToday(String pattern) {
        Date date = new Date();
        String today = "";
        if(pattern.equals("yyyy-MM-dd")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            today = dateFormat.format(date);
        }else if(pattern.equals("yyyy-MM")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            today = dateFormat.format(date);
        }else if(pattern.equals("yyyy")) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            today = dateFormat.format(date);
        }
        return today;
    }
    
    /**
     * datatable 페이징 리턴값 생성
     * @param draw 페이지번호
     * @param data 목록 데이터
     * @param recordsTotal 전체목록카운트
     * @return map
     */
    public static Map datatableReturn(Integer draw, List data, Integer recordsTotal) {
        return ImmutableMap.builder().put("draw", draw).put("data", data).put("recordsTotal", recordsTotal).put("recordsFiltered", recordsTotal).build();
    }

    /**
     * request를 Map으로 변경
     * @param request
     * @return map
     */

    public static Map requestToMap(MultipartHttpServletRequest request){
        HashMap<String, Object> map = new HashMap<String, Object>();

        Enumeration<String> enumber = request.getParameterNames();

        while (enumber.hasMoreElements()) {
            String key = enumber.nextElement().toString();
            String value = request.getParameter(key);

            map.put(key, value);
        }
        return map;
    }

    public static List requestToFileVOList(MultipartHttpServletRequest request) throws Exception {

        List<FileVO> fileVOList = new ArrayList<>();

        Iterator<String> itr =  request.getMultiFileMap().keySet().iterator();
        Map<String, List<MultipartFile>> paramMap = request.getMultiFileMap();
        String path = getUploadPath(request);

        while(itr.hasNext()){
            MultipartFile mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() +" uploaded!");
            System.out.println("file length : " + mpf.getBytes().length);
            System.out.println("file name : " + mpf.getOriginalFilename());
            FileVO fileVO = FileUtil.fileUpload(mpf,path,mpf.getOriginalFilename());
            fileVOList.add(fileVO);
        }
        return fileVOList;
    }

    //TODO HTTP CLIENT
}
