package com.sg.source.common.util;

import com.google.common.base.CaseFormat;

import java.util.HashMap;

@SuppressWarnings("serial")
public class CamelMap extends HashMap {

    @Override
    public Object put(Object key, Object value){
        return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, (String) key), value);
    }
}
