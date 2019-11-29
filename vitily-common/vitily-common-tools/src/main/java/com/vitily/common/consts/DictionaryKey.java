package com.vitily.common.consts;

public class DictionaryKey {

	//以下是会员事件
	public enum Keys{
		上传图片宽度("PIC_WIDTH"),//图片宽度
		上传图片高度("PIC_HEIGHT"),//图片高度
		会员登陆尝试次数("DAY_MEM_TRY_LOGIN_COUNT"),//会员当天登录错误几次无法再次登陆
		会员每天可发送短信次数("DAY_SEND_VERIFYCODE_COUNT"),//会员每天可以发多少次短信验证码（每种类型）
		会员Token("MEMBER_TOKEN"),//服务器存为key（值为memberId），客户端（请求api的源存为值）
		公共缓存次数保存时长("COMMON_SERVICE_TRY_COUNT"),

		APIToken保存时长("API_TOKEN_TIME"),
		每个IP最多可以同时存在TOKEN数("API_TOKEN_MAX_COUNT"),
		SESSION保存时长("SESSION_CACHE_PERIOD"),

		
		//以下是数据库没有的
		系统字典_NOSQL("SYS_DICTIONARY"),//独立
		其他("COMMON_SERVICE_OTHER"),
		会员分组拥有的资源("GROUP_HAS_RESOURCES"),

		;
		public String value;
		Keys(String value){
			this.value=value;
		}
		public String getValue() {
			return value;
		}
	}
	/**
	 * 后台用户
	 * @author lether
	 *
	 */
	public enum AdminServiceKeyType{
		后台用户登陆尝试次数(0),
		验证码问题尝试次数(1);
		public final int value;
		AdminServiceKeyType(int value){
			this.value=value;
		}
		public int getValue() {
			return value;
		}
	}
	/**
	 * 会员公共缓存次数保存 类型
	 * @author lether
	 *
	 */
	public enum MemServiceKeyType{
		会员登陆尝试次数(0),
		会员验证码问题尝试次数(1),
		后台用户登陆尝试次数(2),
		后台验证码问题尝试次数(3),
		每个IP最多可以同时存在TOKEN数(4),
		;
		public final int value;
		MemServiceKeyType(int value){
			this.value=value;
		}
		public int getValue() {
			return value;
		}
	}
	public enum IdentityVerifyType{
		手机验证码校验(0),
		密保问题校验(1),
		密保卡校验(2),
		支付密码校验(3);
		public final int value;
		IdentityVerifyType(int value){
			this.value=value;
		}
		public int getValue() {
			return value;
		}
	}
	public enum ViyCacheSubstrTopic{
		更新字典("vcs_update_dictionary"),
		图片收集器("vcs_collection_upload_pic"),
		;
		public final String value;
		ViyCacheSubstrTopic(String value){
			this.value=value;
		}
		public String getValue() {
			return value;
		}
	}

}
