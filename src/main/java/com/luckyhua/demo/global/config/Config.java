//package com.luckyhua.demo.global.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * @author luckyhua
// * @date 2016/12/22
// * @description race config
// */
//@Component
//@ConfigurationProperties(prefix = "mall", ignoreUnknownFields = false)
//public class Config {
//
//    private final Mall mall = new Mall();
//
//    private final Game game = new Game();
//
//    public static class Mall {
//
//        private String serverUrl;
//        private String loginUrl;
//        private String shopLoginUrl;
//
//        public String getShopLoginUrl() {
//            return shopLoginUrl;
//        }
//
//        public void setShopLoginUrl(String shopLoginUrl) {
//            this.shopLoginUrl = shopLoginUrl;
//        }
//
//        public String getServerUrl() {
//            return serverUrl;
//        }
//
//        public void setServerUrl(String serverUrl) {
//            this.serverUrl = serverUrl;
//        }
//
//        public String getLoginUrl() {
//            return loginUrl;
//        }
//
//        public void setLoginUrl(String loginUrl) {
//            this.loginUrl = loginUrl;
//        }
//
//    }
//
//    public static class Game {
//
//        private String rootUrl;
//
//        private String whiteList;
//
//        public String getWhiteList() {
//            return whiteList;
//        }
//
//        public void setWhiteList(String whiteList) {
//            this.whiteList = whiteList;
//        }
//
//        public String getRootUrl() {
//            return rootUrl;
//        }
//
//        public void setRootUrl(String rootUrl) {
//            this.rootUrl = rootUrl;
//        }
//    }
//
//    public Mall getMall() {
//        return mall;
//    }
//
//    public Game getGame() {
//        return game;
//    }
//}
//package com.luckyhua.demo.global.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * @author luckyhua
// * @date 2016/12/22
// * @description race config
// */
//@Component
//@ConfigurationProperties(prefix = "mall", ignoreUnknownFields = false)
//public class Config {
//
//    private final Mall mall = new Mall();
//
//    private final Game game = new Game();
//
//    public static class Mall {
//
//        private String serverUrl;
//        private String loginUrl;
//        private String shopLoginUrl;
//
//        public String getShopLoginUrl() {
//            return shopLoginUrl;
//        }
//
//        public void setShopLoginUrl(String shopLoginUrl) {
//            this.shopLoginUrl = shopLoginUrl;
//        }
//
//        public String getServerUrl() {
//            return serverUrl;
//        }
//
//        public void setServerUrl(String serverUrl) {
//            this.serverUrl = serverUrl;
//        }
//
//        public String getLoginUrl() {
//            return loginUrl;
//        }
//
//        public void setLoginUrl(String loginUrl) {
//            this.loginUrl = loginUrl;
//        }
//
//    }
//
//    public static class Game {
//
//        private String rootUrl;
//
//        private String whiteList;
//
//        public String getWhiteList() {
//            return whiteList;
//        }
//
//        public void setWhiteList(String whiteList) {
//            this.whiteList = whiteList;
//        }
//
//        public String getRootUrl() {
//            return rootUrl;
//        }
//
//        public void setRootUrl(String rootUrl) {
//            this.rootUrl = rootUrl;
//        }
//    }
//
//    public Mall getMall() {
//        return mall;
//    }
//
//    public Game getGame() {
//        return game;
//    }
//}
