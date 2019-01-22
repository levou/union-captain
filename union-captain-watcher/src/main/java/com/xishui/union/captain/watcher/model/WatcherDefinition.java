package com.xishui.union.captain.watcher.model;

import lombok.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Data
public class WatcherDefinition {
    //可能是没有的,如果没有,可以默认生成
    private String watcherName;

    private String defineId;
    //class的全路径
    private String className;

    private Class<?> clazz;
    //方法必须是public的
    private Set<MethodDefine> methods;

    private boolean isAsync = false;

    public Set<String> methodNames() {
        final Set<String> mns = new HashSet<>();
        if (null != methods && methods.size() > 0) {
            methods.forEach(m -> mns.add(m.getMethodName()));
        }
        return mns;
    }

    @Data
    public class MethodDefine {
        private String watcherName;
        private String methodName;
        private Class<?>[] parameterTypes;
        private Class<?> returnTypes;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            MethodDefine that = (MethodDefine) o;

            if (watcherName != null ? !watcherName.equals(that.watcherName) : that.watcherName != null) return false;
            if (methodName != null ? !methodName.equals(that.methodName) : that.methodName != null) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(parameterTypes, that.parameterTypes);
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (watcherName != null ? watcherName.hashCode() : 0);
            result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(parameterTypes);
            return result;
        }
    }
}
