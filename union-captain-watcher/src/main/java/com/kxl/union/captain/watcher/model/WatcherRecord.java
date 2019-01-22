package com.kxl.union.captain.watcher.model;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Data
public class WatcherRecord implements Serializable {
    //单次唯一id
    private String watcherId;
    //序号
    private Integer sequence;
    //模块名
    private String moduleName;
    //操作类型
    private String operatorType;
    //发生时间
    private Date date;
    //请求相关信息
    private WatcherRecordRequest watcherRecordRequest;
    //结果相关
    private WatcherRecordResponse watcherRecordResponse;

    private String watcherRecordType;
    //额外扩展信息
    private String extendValue;

    public WatcherRecord() {
    }

    public WatcherRecord(String watcherId) {
        this.watcherId = watcherId;
    }

    public static WatcherRecord newRecord(String watcherId) {
        return new WatcherRecord(watcherId);
    }

    public WatcherRecord sequence(Integer sequence) {
        this.sequence = sequence;
        return this;
    }

    public WatcherRecord moduleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public WatcherRecord operatorType(String operatorType) {
        this.operatorType = operatorType;
        return this;
    }

    public WatcherRecord timestamp(Date date) {
        this.date = date;
        return this;
    }

    public WatcherRecord extendValue(String extendValue) {
        this.extendValue = extendValue;
        return this;
    }

    public WatcherRecord watcherRecordType(String watcherRecordType) {
        this.watcherRecordType = watcherRecordType;
        return this;
    }

    public WatcherRecordRequest newRecordRequest() {
        return this.new WatcherRecordRequest();
    }

    public WatcherRecordResponse newRecordResponse() {
        return this.new WatcherRecordResponse();
    }

    //执行的类相关信息
    @Data
    public class WatcherRecordRequest implements Serializable {
        //类名
        private String clazzName;
        //方法名
        private String methodName;
        //参数 json化
        private String jsonParameter;

        public WatcherRecordRequest clazzName(String clazzName) {
            this.clazzName = clazzName;
            return this;
        }

        public WatcherRecordRequest methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public WatcherRecordRequest jsonParameter(String jsonParameter) {
            this.jsonParameter = jsonParameter;
            return this;
        }
    }

    @Data
    public class WatcherRecordResponse implements Serializable {
        //异常类名
        private String exceptionClazzName;
        //异常消息
        private String exceptionMessage;
        //返回结果 json
        private String jsonResponse;
        //是否有异常
        private boolean isError;

        private String clazzName;

        private String methodName;

        public WatcherRecordResponse exceptionClazzName(String exceptionClazzName) {
            this.exceptionClazzName = exceptionClazzName;
            return this;
        }

        public WatcherRecordResponse exceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
            return this;
        }

        public WatcherRecordResponse response(String jsonResponse) {
            this.jsonResponse = jsonResponse;
            return this;
        }

        public WatcherRecordResponse isError(boolean isError) {
            this.isError = isError;
            return this;
        }
        public WatcherRecordResponse clazzName(String clazzName) {
            this.clazzName = clazzName;
            return this;
        }

        public WatcherRecordResponse methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }
    }

    public enum WatcherRecordType {
        //系统记录
        SYSTEM("SYSTEM"),
        //人工记录
        OPERATOR("OPERATOR"),
        IN("IN"),
        OUT("OUT"),
        EXCEPTION("EXCEPTION");
        @Getter
        String type;

        WatcherRecordType(String type) {
            this.type = type;
        }
    }
}
