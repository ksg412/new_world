package com.sg.source.common.util;

import com.sg.source.common.interceptor.MapperIdInterceptor;
/*import net.sf.log4jdbc.Spy;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomLog4JdbcCustomFormatter /*extends Log4JdbcCustomFormatter */{
    private Logger logger = LoggerFactory.getLogger("--");

    Pattern pattern = Pattern.compile("\\n[\\t\\s]+\\n");
    public static ThreadLocal<String> local = new ThreadLocal<>();
    /**
     * {@inheritDoc}
     * <p>
     * 기존 쿼리에서 공백라인을 제거함.
     */
    /*@Override
    public String sqlOccured(Spy spy, String methodCall, String rawSql) {
        String sql = StringUtils.replace(rawSql, "\\n[\\W+]\\n", "\n");
        String s = super.sqlOccured(spy, methodCall, sql);
        Matcher matcher = pattern.matcher(rawSql);
        String sql = matcher.replaceAll("\n");
        String s1 = MapperIdInterceptor.mapperId.get();
        String mapperId = null;
        if(null!=s1){*/
            //mapperId = String.format("/* %s */\n",s1);
            /*MapperIdInterceptor.mapperId.set(null);
        }
        String sqlWithMapperId  = String.format("%s%s",org.apache.commons.lang3.StringUtils.defaultString(mapperId),sql);
        String s = super.sqlOccured(spy, methodCall, sqlWithMapperId);
        String s = sqlWithMapperId;

        local.set(s);

        return s;
    }*/

    /*@Override
    public void exceptionOccured(Spy spy, String methodCall, Exception e, String sql, long execTime) {
        String s = local.get();
        super.exceptionOccured(spy, methodCall, e, s, execTime);
    }*/
    //
//    @Override
//    public void sqlTimingOccured(Spy spy, long execTime, String methodCall, String sql) {
//        String s = local.get();
//        logger.info(String.format("%s \n\t/* execTime : %,d msec */",s,execTime));
////        super.sqlTimingOccured(spy, execTime, methodCall, sql);
//    }


}
