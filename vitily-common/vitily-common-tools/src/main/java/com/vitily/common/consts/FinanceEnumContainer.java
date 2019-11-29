package com.vitily.common.consts;

/**
 * creator : whh-lether
 * date    : 2018/11/26 9:43
 * desc    :
 **/
public class FinanceEnumContainer {


    /**
     * 收入/支出
     */
    public enum FundsDirection {
        //income
        收入(0,"收入"),
        支出(1,"支出"),
        预授权收入(2,"预授权收入"),
        预授权支出(3,"预授权支出")
        ;
        private final int value;
        private final String desc;
        FundsDirection(int value, String desc){
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
     * 充值模式
     * @author lether
     *
     */
    public enum RechargeMode {
        pc("pc","pc"),
        ios("ios","ios"),
        android("android","android"),
        wap("wap","wap"),
        后台充值("offline","后台充值[线下]"),
        其他("other","其他");
        private final String value;
        private final String desc;
        RechargeMode(String value, String desc){
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
     * 充值状态
     */
    public enum RechargeState {
        充值中(0,"充值中"),
        充值成功(1,"充值成功"),
        充值无效(2,"充值无效"),
        已超时(3,"已超时"),
        已作废(4,"已作废");
        private final int value;
        private final String desc;
        RechargeState(int value, String desc){
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
     * 提现申请模式
     * @author lether
     *
     */
    public enum WithdrawMode {
        pc("pc","pc"),
        ios("ios","ios"),
        android("android","android"),
        wap("wap","wap"),
        线下提现("offline","后台提现[线下]"),
        委托扣款("entrust","委托扣款[线下]"),
        其他("other","其他");
        private final String value;
        private final String desc;
        WithdrawMode(String value, String desc){
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
     * 提现状态
     */
    public enum WithdrawState {
        已申请未审核(0,"已申请未审核"),
        //通过申请未汇款(1,"通过申请未汇款"),
        提现成功(2,"提现成功"),
        驳回申请(3,"驳回申请");
        private final int value;
        private final String desc;
        WithdrawState(int value, String desc){
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
     * 资金流水类型：4位数
     * 对于用户来说，收入的，以2开头，支出的，以3开头
     * 对于平台来说，收入的，以2结尾，支出的，以3结尾
     *
     */
    public enum FundsType {
        //income
        充值(2000,"充值"),//收入，一笔
        后台充值(2010,"后台充值"),//收入，两笔
        回收本金(2020,"回收本金"),//收入，一笔
        投资利息收益(2030,"投资利息收益"),//收入，一笔
        逾期还款利息(2040,"逾期还款利息"),//收入，一笔
        提前还款罚息(2050,"提前还款罚息"),//收入，一笔
        放款收入(2120,"放款收入"),//收入，一笔
        提现申请驳回资金返还(2100,"提现申请驳回资金返还"),//收入，一笔
        流标返还资金(2220,"流标返还资金"),//收入，一笔

        活期利息(2003,"活期利息"),//收入<--平台，两笔
        加息(2013,"加息"),//收入<--平台，两笔
        推荐奖励(2023,"推荐奖励"),//收入<--平台，两笔
        红包奖励(2033,"红包奖励"),//收入<--平台，两笔
        活动奖励(2043,"活动奖励"),//收入<--平台，两笔

        //outcome
        提现申请(3000,"提现申请"),//支出，一笔
        线下提现申请(3010,"线下提现申请"),//支出，一笔
        投标预授权支出(3020,"投标预授权支出"),//支出，一笔
        偿还本金(3120,"偿还本金"),//支出，一笔
        偿还利息(3030,"偿还利息"),//支出，一笔
        偿还逾期利息(3040,"偿还逾期利息"),//支出，一笔
        偿还提前还款违约金(3050,"偿还提前还款违约金"),//支出，一笔
        提现成功(3100,"提现成功"),//支出，一笔，金额必须为0
        投标成功已放款(3220,"投标成功"),//支出，一笔，金额必须为0
        还款预授权支出(3130,"还款预授权支出"),//支出，一笔
        还款成功(3140,"还款成功"),//支出，一笔

        利息差额收入(3022,"利息差额收入"),//平台收入 --->平台，一笔
        借款居间服务费(3032,"借款居间服务费"),//支出 --->平台，两笔
        收益居间服务费(3012,"收益居间服务费"),//支出 --->平台，两笔
        委托扣款(3002,"委托扣款"),//支出 --->平台，两笔
        逾期管理费(3042,"逾期管理费"),//支出-->平台,两笔

        利息差额支出(1003,"利息差额支出"),//平台支出，一笔

        ;
        private final int value;
        private final String desc;
        FundsType(int value, String desc){
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
