package com.sg.source.common.util;

import java.util.LinkedHashMap;

/**
 * <p>ResultMap class.</p>
 *
 * @author fasteras
 */
public class LinkedResultMap extends LinkedHashMap<String, Object> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2816505728150942243L;

	/**
     * {@inheritDoc}
     */
    public LinkedResultMap put(String key, Object value) {
        super.put(CommonUtil.toCamelCase(key), value);
        return this;
    }

}
