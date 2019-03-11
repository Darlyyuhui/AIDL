package com.snbc.bcvm.reportjar.entity;

import java.util.List;

/**
 * so库返回的json对象
 * 包名称：com.snbc.bcvm.bcvmcommon.entity
 * 作者：qinhaonan 项目名称：Vem
 * 日期：2019/1/21
 * 公司：山东新北洋信息技术股份有限公司西安分公司
 * 邮箱：qinhaonan@newbeiyang.com
 */
public class ErrorEntity {

    /**
     * head : {"version":"1.0","id":"SNBC"}
     * body : {"error":[{"code":1,"system":"XX","display":"XX","reason":"XX","solution":"XX"},{"code":2,"system":"XX","display":"XX","reason":"XX","solution":"XX"}]}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * id : SNBC
         */

        private String version;
        private String id;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class BodyBean {
        private List<ErrorBean> error;

        public List<ErrorBean> getError() {
            return error;
        }

        public void setError(List<ErrorBean> error) {
            this.error = error;
        }

        public static class ErrorBean {
            /**
             * 故障编码
             */
            private int code;
            /**
             * 故障描述
             */
            private String display;
            /**
             * 诱发原因
             */
            private String reason;
            /**
             * 解决方案
             */
            private String solution;
            /**
             * 所属系统
             */
            private String system;
            public ErrorBean(){}
            public ErrorBean(int code,String display,String reason,String solution,String system){
                this.code=code;
                this.display=display;
                this.reason=reason;
                this.solution=solution;
                this.system=system;
            }
            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getSystem() {
                return system;
            }

            public void setSystem(String system) {
                this.system = system;
            }

            public String getDisplay() {
                return display;
            }

            public void setDisplay(String display) {
                this.display = display;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public String getSolution() {
                return solution;
            }

            public void setSolution(String solution) {
                this.solution = solution;
            }
        }
    }
}
