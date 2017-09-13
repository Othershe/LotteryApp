package com.mylottery.appmain.data;

public class AdData1 {

    /**
     * jsonrpc : 2.0
     * id : 1
     * result : {"appid":"com.app","showWap":1,"wapUrl":"www.baidu.com","version":"1.1.1"}
     */

    private String jsonrpc;
    private int id;
    /**
     * appid : com.app
     * showWap : 1
     * wapUrl : www.baidu.com
     * version : 1.1.1
     */

    private ResultBean result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String appid;
        private int showWap;
        private String wapUrl;
        private String version;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public int getShowWap() {
            return showWap;
        }

        public void setShowWap(int showWap) {
            this.showWap = showWap;
        }

        public String getWapUrl() {
            return wapUrl;
        }

        public void setWapUrl(String wapUrl) {
            this.wapUrl = wapUrl;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
