package com.vitily.common.consts;

/**
 * creator : whh-lether
 * date    : 2018/11/26 9:40
 * desc    :
 **/
public class CommonEnumContainer {

    /**
     * 删除位
     * @author lether
     *
     */
    public enum Deltag {
        正常(0,"正常"),
        已删除(1,"已删除"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        Deltag(int value, String desc){
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
     * 默认相关
     * @author lether
     *
     */
    public enum Default {
        否(0,"否"),
        是(1,"是"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        Default(int value,String desc){
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
    public enum State {
        无效(0,"无效"),
        正常(1,"正常"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        State(int value, String desc){
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
     * 是否
     * @author lether
     *
     */
    public enum YesOrNo {
        否(false,"否"),
        是(true,"是"),
        ;
        private final boolean value;
        private final String desc;
        YesOrNo(boolean value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public boolean getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }
    public enum JdbcType {
        mysql(0,"mysql"),
        oracle(1,"oracle"),
        sqlserver(2,"sqlserver"),
        other(99,"other");
        private final int value;
        private final String desc;
        JdbcType(int value, String desc){
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
     * 执行结果返回码
     * @author lether
     *
     */
    public enum ResultStatus {
        操作异常("400","操作异常，请联系管理员"),
        未登录("401","您未登录或者登录状态已过期"),
        用户名或密码错误("402","用户名或密码错误"),
        无权限("403","您无此操作权限"),
        查询不存在("404","查询不存在"),//如数据库中不存在等
        页面请求异常("405","页面请求异常"),
        接口不存在("406","请求接口不存在"),
        重复数据("407","已存在重复数据"),//如数据库中不存在等
        参数不全("408","参数不全"),
        用户不存在("409","用户不存在"),
        用户已禁用("501","用户已禁用"),
        正常("200","success"),
        Token无效("300","Token无效"),
        接口Token无效("301","Token无效"),
        参数不符合要求("302","参数不符合要求"),
        请求次数过多("303","请求次数过多"),
        不再使用该录("304","不再使用该录"),
        数据状态不正确("305","数据状态不正确"),
        余额不足了哟("306","余额不足了哟"),

        其他("509","其他");
        private final String value;
        private final String desc;
        ResultStatus(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 平台
     * @author lether 2016年03月19日12:05:42
     *
     */
    public enum Platform {
        连连支付(0,"连连支付"),
        平安托管(1,"平安托管"),
        其他(9,"其他");
        private final int value;
        private final String desc;
        Platform(int value, String desc){
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
     * 银行卡类型
     * @author lether 2016年03月19日12:05:42
     *
     */
    public enum BankCardType {
        借记卡(0,"借记卡"),
        信用卡(1,"信用卡"),
        其他(9,"其他");
        private final int value;
        private final String desc;
        BankCardType(int value, String desc){
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
     * @author : lether
     * @date : 2018/11/18 19:03
     **/
    public enum MqChannel {
        收益评估("c_coin_deal_profit_valuation","c_coin_deal_profit_valuation"),
        下单("c_coin_deal_order_info","c_coin_deal_order_info"),
        更新用户账户余额("c_coin_spot_account","c_coin_spot_account"),
        更新系统配置信息("update_sys_info_","update_sys_info_"),
        检查订单状态并撤单("c_check_order_state_and_cancel","c_check_order_state_and_cancel"),
        期现套利下单("c_coin_cash_future_order_info","c_coin_cash_future_order_info"),
        期期套利下单("c_coin_future_future_order_info","c_coin_future_future_order_info"),
        ;
        private final String value;
        private final String desc;
        MqChannel(String value, String desc){
            this.value=value;
            this.desc=desc;
        }
        public String getValue() {
            return value;
        }
        public String getDesc() {
            return desc;
        }
    }

    /**
     * 内容类型相关
     * @author lether
     *
     */
    public enum HtmlType {
        单选下拉框(0,"单选下拉框"),
        多选下拉框(1,"多选下拉"),
        单行文本框(2,"单行文本框"),
        多行文本框(3,"多行文本框"),
        单选框(4,"单选框"),
        多选框(5,"多选框"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        HtmlType(int value, String desc){
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
     * 备忘录状态
     */
    public enum AideMemoireState {
        Unprocessed(0,"未处理"),
        Completed(1,"已完成"),
        Expired(2,"已失效"),
        ;
        private final int value;
        private final String desc;
        AideMemoireState(int value, String desc){
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
     * 日志状态
     * @author lether
     *
     */
    public enum LogState {
        异常(0,"异常"),
        正常(1,"正常"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        LogState(int value, String desc){
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
     * 消息状态：各种消息可共享状态
     */
    public enum MsgState {
        Unread(0,"未读"),
        HaveRead(1,"已读"),
        Expired(2,"已过期"),
        ;
        private final int value;
        private final String desc;
        MsgState(int value, String desc){
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
     * 用户登陆模式
     * @author lether
     *
     */
    public enum UserLoginMode {
        Cookie登陆(0,"Cookie登陆"),
        用户名密码(1,"用户名密码"),
        token登陆(2,"token登陆"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        UserLoginMode(int value, String desc){
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
     * 证件类型
     * @author lether
     *
     */
    public enum IdType {
        身份证(0,"身份证"),
        驾照(1,"驾照"),
        护照(2,"护照"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        IdType(int value, String desc){
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
     * 性别
     * @author lether
     *
     */
    public enum Gender {
        先生(0,"先生"),
        女士(1,"女士"),
        隐藏(2,"隐藏"),
        其他(99,"其他");
        private final int value;
        private final String desc;
        Gender(int value, String desc){
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
