package com.lerry.lerrysecurity.common;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  项目常量枚举
 * @author LErry.li
 * Date: 2018/7/8
 * Time: 下午3:27
 */
public class ConstantEnum {

    /**
     * 用户状态
     */
    public enum UserStatus{

        CANCEL(-1,"注销"),
        LOCKED(0,"锁定"),
        NORMAL(1,"正常");

        private Integer key ;
        private String value;

        private UserStatus(Integer key, String value){
            this.key = key ;
            this.value = value ;
        }

        public Integer getKey() {
            return key;
        }

        void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }
    }

}
