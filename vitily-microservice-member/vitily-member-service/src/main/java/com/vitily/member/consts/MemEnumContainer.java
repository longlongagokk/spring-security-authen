package com.vitily.member.consts;

/**
 * creator : whh-lether
 * date    : 2018/11/26 9:49
 * desc    :
 **/
public class MemEnumContainer {

    /**
     * 会员类型
     *
     */
    public enum MemType {
        个人用户(0,"个人用户"),
        企业用户(1,"企业用户"),
        ;
        private final int value;
        private final String desc;
        MemType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }

    }

    /**
     * 银行卡状态
     */
    public enum MemBankCardState {
        未认证(0,"未认证"),
        认证成功(1,"认证成功"),
        认证无效(2,"认证无效"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        MemBankCardState(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 会员注册来源
     * @author lether
     *
     */
    public enum RegFrom {
        后台添加(0,"后台添加"),
        手机号码注册(1,"手机号码注册"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        RegFrom(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }


    /**
     * 信用验证状态
     */
    public enum CreditfileCertifyState {
        未验证(0,"未验证"),
        申请验证中(2,"申请验证中"),
        验证通过(1,"验证通过"),
        验证无效(-1,"验证无效"),
        重新申请(3,"重新申请"),
        ;
        private final int value;
        private final String desc;
        CreditfileCertifyState(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 信用验证类型
     */
    public enum CreditfileType {
        企业营业执照(0,"企业营业执照"),
        企业注册资本(1,"企业注册资本"),
        企业车辆信息(2,"企业车辆信息"),
        企业房产信息(3,"企业房产信息"),
        企业持有股票基金(4,"企业持有股票基金"),
        企业公司资产(5,"企业公司资产"),
        企业其他信用信息(6,"企业其他信用信息"),

        个人教育认证(7,"个人教育认证"),
        个人工作信息(8,"个人工作信息"),
        个人驾照(9,"个人驾照"),
        个人车辆信息(10,"个人车辆信息"),
        个人房产信息(11,"个人房产信息"),
        个人持有股票基金(12,"个人持有股票基金"),
        个人其他信用信息(13,"个人其他信用信息"),

        开通个人信用账户(60,"开通个人信用账户"),
        开通企业信用账户(61,"开通企业信用账户"),

        其他(99,"其他"),
        ;
        private final int value;
        private final String desc;
        CreditfileType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 客户取件状态
     * @author lether
     *
     */
    public enum CustomerPickState {
        未取件(0,"未取件"),
        已取件(1,"已取件"),
        ;
        private final int value;
        private final String desc;
        CustomerPickState(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 客户支付状态
     * @author lether
     *
     */
    public enum CustomerPayState {
        未支付(0,"未支付"),
        已支付定金(1,"已支付定金"),
        已支付部分尾款(2,"已支付部分尾款"),
        已全部支付(3,"已全部支付"),
        ;
        private final int value;
        private final String desc;
        CustomerPayState(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }


    /**
     * 最高学历
     * @author lether
     *
     */
    public enum EducationLevel {
        无(0,"无"),
        本科(1,"本科"),
        大专(2,"大专"),
        硕士(3,"硕士"),
        博士(4,"博士"),
        博士后(5,"博士后"),
        高中(6,"高中"),
        中专(7,"中专"),
        初中(8,"初中"),
        专升本(9,"专升本"),

        其他(99,"其他");
        private final int value;
        private final String desc;
        EducationLevel(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 眼镜框架类型
     * @author lether
     *
     */
    public enum ClassesFrameType {
        框架(0,"框架"),
        隐形(1,"隐形"),
        ;
        private final int value;
        private final String desc;
        ClassesFrameType(int value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public int getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

























}
