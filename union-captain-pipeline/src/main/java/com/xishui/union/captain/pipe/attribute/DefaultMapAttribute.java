package com.xishui.union.captain.pipe.attribute;

import com.xishui.union.captain.pipe.exception.EventIllegalException;
import com.xishui.union.captain.pipe.exception.EventPipelineException;

import java.util.HashMap;
import java.util.Map;

public class DefaultMapAttribute implements ComplexAttribute {

    private Map<String, Object> ATTRIBUTE_MAP = null;

    public DefaultMapAttribute() {
        DefaultMapAttribute.this.ATTRIBUTE_MAP = new HashMap<>();
    }

    @Override
    public ComplexAttribute addAttribute(String attributeKey, Object value) throws EventPipelineException {

        return this;
    }

    @Override
    public ComplexAttribute removeAttribute(String attributeKey) throws EventPipelineException {
        return null;
    }

    @Override
    public <M> M attribute(String key, Class<M> valueType) throws EventIllegalException {
        if (DefaultMapAttribute.this.ATTRIBUTE_MAP.containsKey(key)) {
            Object value = DefaultMapAttribute.this.ATTRIBUTE_MAP.get(key);
            //做个简单的类型验证吧
            if (value.getClass().equals(value.getClass())) {
                return (M) value;
            } else {
                throw new EventIllegalException("Can't cast " + value.getClass().getName() + " to " + value.getClass().getName());
            }
        }
        return null;
    }

    @Override
    public Object attribute(String key) throws EventIllegalException {
        return DefaultMapAttribute.this.ATTRIBUTE_MAP.get(key);
    }
}
