package com.kxl.union.captain.component;

import com.kxl.union.captain.component.exception.ComponentDefineException;
import com.kxl.union.captain.component.transfer.ComponentDefineTransfer;
import lombok.Data;

/**
 * 组件定义
 */
@Data
public class ComponentDefine {
    /**
     * 组件名称
     */
    private String name;
    /**
     * 组件定义的class
     */
    private String defineClass;

    public ComponentDefine(String name, String defineClass) {
        this.name = name;
        this.defineClass = defineClass;
    }

    public static ComponentDefine builder(String defineLine) throws ComponentDefineException {
        if (null == defineLine || "".equals(defineLine.trim()) || defineLine.trim().equals("#")) {
            throw new ComponentDefineException("Component DefineLine not availability:" + defineLine);
        }
        final String[] defineSplits = defineLine.split(ComponentDefineTransfer.COMPONENT_DEFINE_SPLITER);
        if (null == defineSplits) {
            throw new ComponentDefineException("Read DefineLine is Null " + defineLine);
        }
        if (defineSplits.length < 2) {
            throw new ComponentDefineException("DefineLine By Split '=' length() less than 2 :" + defineLine);
        }
        return new ComponentDefine(defineSplits[0], defineSplits[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ComponentDefine that = (ComponentDefine) o;

        if (!name.equals(that.name)) return false;
        return defineClass.equals(that.defineClass);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + defineClass.hashCode();
        return result;
    }
}
