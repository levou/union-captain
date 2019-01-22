package com.kxl.union.captain.pipe.attribute;

import com.kxl.union.captain.pipe.exception.EventPipelineException;

public interface ComplexAttribute extends Attribute {

    ComplexAttribute addAttribute(String attributeKey, Object value) throws EventPipelineException;

    ComplexAttribute removeAttribute(String attributeKey) throws EventPipelineException;
}
