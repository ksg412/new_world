package com.sg.source.common.interceptor;

import com.sg.source.common.util.CommonUtil;
import com.sg.source.common.util.CustomLog4JdbcCustomFormatter;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * MapperId check.
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
        , @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MapperIdInterceptor implements Interceptor {
    static final int MAPPED_STATEMENT_INDEX = 0;
    static final int PARAMETER_INDEX = 1;
    public static ThreadLocal<String> mapperId = new ThreadLocal<>();
    static Logger logger = LoggerFactory.getLogger(MapperIdInterceptor.class);

    public Object intercept(final Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        String id = ms.getId();
        mapperId.set(String.format("id : %s ", id));
        long l = System.currentTimeMillis();
        Object proceed = invocation.proceed();

        //SQL로깅
        long t = System.currentTimeMillis()-l;
        String s = CustomLog4JdbcCustomFormatter.local.get();
        String n = CommonUtil.getMessages().getText("Globals.sqlLog.catch", "N");
        /*if ("Y".equals(n)) {
            CustomLoggerService bean = (CustomLoggerService) CmmnUtil.getBean(CustomLoggerService.class);
            bean.addSqlLog(s, t);
        }*/

        return proceed;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.trace("setProperties : {}", properties.toString());
    }
}
