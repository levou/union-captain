package com.kxl.union.captain.pipe.context;

import com.kxl.union.captain.pipe.attribute.ComplexAttribute;
import com.kxl.union.captain.pipe.attribute.DefaultMapAttribute;
import com.kxl.union.captain.pipe.exception.EventIllegalException;
import com.kxl.union.captain.pipe.exception.EventPipelineException;

public class DefaultEventRequest<Req> implements EventRequest<Req> {
    private final ComplexAttribute attribute = new DefaultMapAttribute();
    private Req request;

    @Override
    public Req request() throws EventPipelineException {
        return request;
    }

    @Override
    public DefaultEventRequest<Req> addRequest(Req req) throws EventPipelineException {
        this.request = req;
        return this;
    }

    @Override
    public ComplexAttribute addAttribute(String attributeKey, Object value) throws EventPipelineException {
        return attribute.addAttribute(attributeKey, value);
    }

    @Override
    public ComplexAttribute removeAttribute(String attributeKey) throws EventPipelineException {
        return attribute.removeAttribute(attributeKey);
    }

    @Override
    public <M> M attribute(String key, Class<M> valueType) throws EventIllegalException {
        return attribute.attribute(key, valueType);
    }

    @Override
    public Object attribute(String key) throws EventIllegalException {
        return attribute.attribute(key);
    }
}
