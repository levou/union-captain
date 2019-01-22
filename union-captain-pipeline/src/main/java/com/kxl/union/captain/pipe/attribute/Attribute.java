package com.kxl.union.captain.pipe.attribute;

import com.kxl.union.captain.pipe.exception.EventIllegalException;

public interface Attribute {
    //根据类型获取Map里面的值,可以携带更多的参数
    <M> M attribute(String key, Class<M> valueType) throws EventIllegalException;

    Object attribute(String key) throws EventIllegalException;
}
