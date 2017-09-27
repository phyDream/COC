package com.cdlixin.coc.model.network;

import com.cdlixin.coc.global.constants.Url;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * Created by phy on 2016/11/21.
 * 网络数据请求的API - 所有的网络请求
 */

public interface HttpService {

    /**
     *  我的供需删除
     * @param method
     * @param timestamp
     * @param s_token
     * @param trade_id
     * @param sign
     * @return
     */
    @FormUrlEncoded
    @POST(Url.TRADE)
    Observable<String> delete_my_trade(@Field("method") String method,
                                       @Field("timestamp") int timestamp,
                                       @Field("s_token") String s_token,
                                       @Field("trade_id") String trade_id,
                                       @Field("sign") String sign);
    /**
     * 获取机构成员列表
     * @param method
     * @param timestamp
     * @param s_token
     * @param coc_id
     * @param sign
     * @return
     */
    @GET(Url.ORG)
    Observable<String> get_cocmembers(@Query("method") String method,
                                      @Query("timestamp") int timestamp,
                                      @Query("s_token") String s_token,
                                      @Query("coc_id") String coc_id,
                                      @Query("page") int page,
                                      @Query("size") int size,
                                      @Query("sign") String sign);


    @GET(Url.ORG)
    Observable<String> get_gov(@Query("method") String method,
                               @Query("timestamp") int timestamp,
                               @Query("s_token") String s_token,
                               @Query("c_id") String c_id,
                               @Query("sign") String sign);


    @GET(Url.ORG)
    Observable<String> get_govs(@Query("method") String method,
                                @Query("timestamp") int timestamp,
                                @Query("s_token") String s_token,
                                @Query("country_code") String country_code,
                                @Query("region_code") String region_code,
                                @Query("c_name") String c_name,
                                @Query("page") int page,
                                @Query("size") int size,
                                @Query("sign") String sign);



    @FormUrlEncoded
    @POST(Url.ACCOUNT)
    Observable<String> join_coc(@Field("method") String method,
                                @Field("timestamp") int timestamp,
                                @Field("coc_id") String coc_id,
                                @Field("u_name") String u_name,
                                @Field("u_phone") String u_phone,
                                @Field("sign") String sign);


    /**
     * 检查版本号
     * @param check_ver 操作方法名，= check_ver
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param app_type 手机APP平台标识，0=iOS, 1=Andriod
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.APP)
    Observable<String> check_ver(@Query("method") String check_ver,
                                 @Query("timestamp") int timestamp,
                                 @Query("app_type") int app_type,
                                 @Query("sign") String sign);


    /**
     * 用户头像上传
     * @param method  操作方法名，= userlogo
     * @param timestamp  发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token   会话令牌
     * @param u_logo     用户头像图片采用Base64方式编码处理为字符串，服务器端接收后，进行Base64解码处理。
     * @param msg_type 图片类型；格式为：类型/编码格式
     * @param sign        签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.ACCOUNT)
    Observable<String> upload_user_logo(@Field("method") String method,
                                        @Field("timestamp") int timestamp,
                                        @Field("s_token") String s_token,
                                        @Field("u_logo") String u_logo,
                                        @Field("msg_type") String msg_type,
                                        @Field("sign") String sign);


    /**
     * 身份验证
     * @param verify 操作方法名，= verify
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param auth_mode 验证模式： (1) 10 - 手机uid验证； (2) 20 – 用户名/密码验证；
     * @param uid 登录用户名（可选）手机uid验证模式下，该参数为空。
     * @param pwd 密码（可选）；手机uid验证模式下，该参数为空。密码字符串使用ASCII方式编码后，采用SHA256进行加密。即：pwd=SHA256(ASCII(password))
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.ACCOUNT)
    Observable<String> verify(@Field("method") String verify,
                              @Field("timestamp") int timestamp,
                              @Field("auth_mode") int auth_mode,
                              @Field("mobileId") String mobileId,
                              @Field("uid") String uid,
                              @Field("pwd") String pwd,
                              @Field("sign") String sign);

    /**
     * 手机号码检查
     * @param check_mobile  操作方法名，= check_mobile
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param mobile 手机号码
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.ACCOUNT)
    Observable<String> check_mobile(@Field("method") String check_mobile,
                                    @Field("timestamp") int timestamp,
                                    @Field("mobile") String mobile,
                                    @Field("sign") String sign);


    /**
     * app开通申请
     * @param register  操作方法名，= register
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param uid 登录用户名（可选）手机uid验证模式下，该参数为空。
     * @param coc_id 商会代码
     * @param c_name 企业名称
     * @param u_mobile 机主手机号
     * @param u_name 机主姓名
     * @param u_post 职务名称
     * @param u_male 机主性别；true – 先生; false – 女士
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.ACCOUNT)
    Observable<String> register(@Field("method") String register,
                                @Field("timestamp") int timestamp,
                                @Field("uid") String uid,
                                @Field("coc_id") String coc_id,
                                @Field("c_name") String c_name,
                                @Field("u_mobile") String u_mobile,
                                @Field("u_name") String u_name,
                                @Field("u_post") String u_post,
                                @Field("u_male") boolean u_male,
                                @Field("sign") String sign);


    /**
     * 获取短信验证码
     * @param sms_verify 操作方法名，= sms_verify
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param mobile 手机号码
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.DEVICE)
    Observable<String> sms_verify(@Field("method") String sms_verify,
                                  @Field("timestamp") int timestamp,
                                  @Field("mobile") String mobile,
                                  @Field("uid") String uid,
                                  @Field("sign") String sign);


    /**
     * 设备注册
     * @param register 操作方法名，= register
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param mobileId 手机或移动设备的uid
     * @param uid 登录用户名（可选）手机uid验证模式下，该参数为空。
     * @param mobile 机主手机号
     * @param deviceType 移动设备类型 1：Andriod设备 2：iOS设备；3：Windows Phone设备；
     * @param verify_code 接收到的短信验证码
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.DEVICE)
    Observable<String> register(@Field("method") String register,
                                @Field("timestamp") int timestamp,
                                @Field("mobileId") String mobileId,
                                @Field("uid") String uid,
                                @Field("mobile") String mobile,
                                @Field("deviceType") int deviceType,
                                @Field("verify_code") String verify_code,
                                @Field("sign") String sign);


    /**
     * 地区维度列表
     * @param get_regions 操作方法名，= get_regions
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.REGION)
    Observable<String> get_regions(@Query("method") String get_regions,
                                   @Query("timestamp") int timestamp,
                                   @Query("sign") String sign);

    /**
     * 获取国家列表
     * @param get_countries -- 操作方法名
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param sign  签名字符串
     * @return
     */
    @GET(Url.REGION)
    Observable<String> get_countries(@Query("method") String get_countries,
                                     @Query("timestamp") int timestamp,
                                     @Query("sign") String sign);



    /**
     * 商会名录筛选
     * @param get_cocs 操作方法名，= get_cocs
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param region_code 地区代码
     * @param coc_name 用户输入的商会名称
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.COC)
    Observable<String> get_cocs(@Query("method") String get_cocs,
                                @Query("timestamp") int timestamp,
                                @Query("country_code") String country_code,
                                @Query("region_code") String region_code,
                                @Query("coc_name") String coc_name,
                                @Query("page") int page,
                                @Query("size") int size,
                                @Query("sign") String sign);


    /**
     * 会员企业所属商会
     * @param get_cocs 操作方法名，= get_cocs
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param c_id 企业ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.COC)
    Observable<String> get_cocs(@Query("method") String get_cocs,
                                @Query("timestamp") int timestamp,
                                @Query("c_id") String c_id,
                                @Query("sign") String sign);


    /**
     * 商会信息
     * @param get_coc 操作方法名，= get_coc
     * @param s_token 可选参数，会话令牌
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param coc_id 商会id
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.COC)
    Observable<String> get_coc(@Query("method") String get_coc,
                               @Query("s_token") String s_token,
                               @Query("timestamp") int timestamp,
                               @Query("coc_id") String coc_id,
                               @Query("sign") String sign);


    /**
     * 获取行业维度列表
     * @param get_industries 操作方法名，= get_industries
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param parent_code 父类行业代码
     * @param isAll 是否获取指定父类下的各级子行业；否，则只获取下一级
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.INDUSTRY)
    Observable<String> get_industries(@Query("method") String get_industries,
                                      @Query("timestamp") int timestamp,
                                      @Query("parent_code") String parent_code,
                                      @Query("isAll") boolean isAll,
                                      @Query("sign") String sign);

    /**
     * 企业名录筛选
     * @param get_enterprises 操作方法名，= get_enterprises
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param coc_id 商会代码
     * @param region_code 地区代码
     * @param industry_code 行业代码
     * @param c_name 用户输入的企业名称
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> get_enterprises(@Query("method") String get_enterprises,
                                       @Query("timestamp") int timestamp,
                                       @Query("s_token") String s_token,
                                       @Query("coc_id") String coc_id,
                                       @Query("region_code") String region_code,
                                       @Query("industry_code") String industry_code,
                                       @Query("c_name") String c_name,
                                       @Query("page") int page,
                                       @Query("size") int size,
                                       @Query("sign") String sign);


    /**
     * 企业基本资料
     * @param get_enterprise 操作方法名，= get_enterprise
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param c_id 企业ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> get_enterprise(@Query("method") String get_enterprise,
                                      @Query("timestamp") int timestamp,
                                      @Query("s_token") String s_token,
                                      @Query("c_id") String c_id,
                                      @Query("sign") String sign);


    /**
     * 获取企业联系人列表
     * @param get_linkmans 操作方法名，= get_linkmans
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     *
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> get_new_Linkmans(@Query("method") String get_linkmans,
                                        @Query("timestamp") int timestamp,
                                        @Query("s_token") String s_token,
                                        @Query("sign") String sign);

    /**
     * 搜索联系人
     * @param get_linkmans
     * @param timestamp
     * @param s_token
     * @param linkman_name
     * @param sign
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> search_linkmans(@Query("method") String get_linkmans,
                                       @Query("timestamp") int timestamp,
                                       @Query("s_token") String s_token,
                                       @Query("c_name") String linkman_name,
                                       @Query("sign") String sign);



    /**
     * 获取企业联系人列表
     * @param get_linkman 操作方法名，= get_linkman
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param u_id userId
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> get_linkman(@Query("method") String get_linkman,
                                   @Query("timestamp") int timestamp,
                                   @Query("s_token") String s_token,
                                   @Query("u_id") String u_id,
                                   @Query("sign") String sign);



    /**
     * 获取商会资讯列表
     * @param get_infos 操作方法名，= get_infos
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param coc_id 商会代码
     * @param c_id 联系人ID
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.INFO)
    Observable<String> get_infos(@Query("method") String get_infos,
                                 @Query("timestamp") int timestamp,
                                 @Query("coc_id") String coc_id,
                                 @Query("c_id") String c_id,
                                 @Query("page") int page,
                                 @Query("size") int size,
                                 @Query("sign") String sign);

    /**
     * 商会资讯信息
     * @param get_info 操作方法名，= get_info
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param info_id 资讯ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.INFO)
    Observable<String> get_info(@Query("method") String get_info,
                                @Query("timestamp") int timestamp,
                                @Query("info_id") String info_id,
                                @Query("sign") String sign);


    /**
     * 商会资讯发布
     * @param post_info 操作方法名，= post_info
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param range_code 发布/转发 范围代码
     * @param info_title 标题
     * @param info_text 资讯正文
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.INFO)
    Observable<String> post_info(@Field("method") String post_info,
                                 @Field("timestamp") int timestamp,
                                 @Field("s_token") String s_token,
                                 @Field("range_code") String range_code,
                                 @Field("info_title") String info_title,
                                 @Field("info_text") String info_text,
                                 @Field("sign") String sign);


    /**
     * 商会资讯转发
     * @param replay_info 操作方法名，= replay_info
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param range_code 发布/转发 范围代码
     * @param info_id 资讯ID
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.INFO)
    Observable<String> replay_info(@Field("method") String replay_info,
                                   @Field("timestamp") int timestamp,
                                   @Field("s_token") String s_token,
                                   @Field("range_code") String range_code,
                                   @Field("info_id") String info_id,
                                   @Field("sign") String sign);


    /**
     * 获取发布/转发范围列表
     * @param get_ranges 操作方法名，= get_ranges
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.RANGE)
    Observable<String> get_ranges(@Query("method") String get_ranges,
                                  @Query("timestamp") int timestamp,
                                  @Query("sign") String sign);



    /**
     * 获取商会通知列表
     * @param get_enterprises 操作方法名，= get_enterprises
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param coc_id 商会代码
     * @param c_id 企业ID（可选参数，筛选条件项）
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.NOTICE)
    Observable<String> get_notices(@Query("method") String get_enterprises,
                                   @Query("timestamp") int timestamp,
                                   @Query("s_token") String s_token,
                                   @Query("coc_id") String coc_id,
                                   @Query("c_id") String c_id,
                                   @Query("page") int page,
                                   @Query("size") int size,
                                   @Query("sign") String sign);

    /**
     * 商会通知信息
     * @param get_notice 操作方法名，= get_notice
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param notice_id 通知ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.NOTICE)
    Observable<String> get_notice(@Query("method") String get_notice,
                                  @Query("timestamp") int timestamp,
                                  @Query("s_token") String s_token,
                                  @Query("notice_id") String notice_id,
                                  @Query("sign") String sign);



    /**
     * 商会通知发布
     * @param post_notice 操作方法名，= post_notice
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param range_code 发布/转发 范围代码
     * @param notice_title 标题
     * @param notice_text 通知正文
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.NOTICE)
    Observable<String> post_notice(@Field("method") String post_notice,
                                   @Field("timestamp") int timestamp,
                                   @Field("s_token") String s_token,
                                   @Field("range_code") String range_code,
                                   @Field("notice_title") String notice_title,
                                   @Field("notice_text") String notice_text,
                                   @Field("sign") String sign);


    /**
     * 商会通知转发
     * @param replay_notice 操作方法名，= replay_notice
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param range_code 发布/转发 范围代码
     * @param notice_id 通知ID
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.NOTICE)
    Observable<String> replay_notice(@Field("method") String replay_notice,
                                     @Field("timestamp") int timestamp,
                                     @Field("s_token") String s_token,
                                     @Field("range_code") String range_code,
                                     @Field("notice_id") String notice_id,
                                     @Field("sign") String sign);



    /**
     * 供求信息发布
     * @param replay_notice 操作方法名，= replay_notice
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_dir 信息类型：supply=供应；want=需求
     * @param trade_tag 供应/需求标签(信息关键字)。多个标签，以逗号作为分隔符
     * @param trade_title 标题
     * @param trade_text 供求信息正文
     * @param trade_expires 有效期，单位：周。默认最大有效期为4周
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.TRADE)
    Observable<String> post_trade(@Field("method") String replay_notice,
                                  @Field("timestamp") int timestamp,
                                  @Field("s_token") String s_token,
                                  @Field("trade_dir") String trade_dir,
                                  @Field("trade_tag") String trade_tag,
                                  @Field("trade_title") String trade_title,
                                  @Field("trade_text") String trade_text,
                                  @Field("trade_expires") int trade_expires,
                                  @Field("isPublic") boolean isPublic,
                                  @Field("sign") String sign);


    /**
     * 获取供求信息列表
     * @param get_trades 操作方法名，= get_trades
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param from_date 从何时开始（Unix时间戳可选参数，筛选条件）
     * @param to_date 到何时结束（Unix时间戳可选参数，筛选条件）
     * @param coc_id 商会ID（可选参数，筛选条件项）
     * @param trade_dir 信息类型：supply=供应；want=需求
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.TRADE)
    Observable<String> get_trades(@Query("method") String get_trades,
                                  @Query("timestamp") int timestamp,
                                  @Query("s_token") String s_token,
                                  @Query("from_date") int from_date,
                                  @Query("to_date") int to_date,
                                  @Query("coc_id") String coc_id,
                                  @Query("trade_dir") String trade_dir,
                                  @Query("page") int page,
                                  @Query("size") int size,
                                  @Query("sign") String sign);



    /**
     * 获取供求信息列表
     * @param get_trade 操作方法名，= get_trade
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_id 供求信息ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.TRADE)
    Observable<String> get_trade(@Query("method") String get_trade,
                                 @Query("timestamp") int timestamp,
                                 @Query("s_token") String s_token,
                                 @Query("trade_id") String trade_id,
                                 @Query("sign") String sign);



    /**
     * 回复（私信留言）供求信息
     * @param reply_trade 操作方法名，= reply_trade
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_id 供求信息ID
     * @param reply 回复内容
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.TRADE)
    Observable<String> reply_trade(@Field("method") String reply_trade,
                                   @Field("timestamp") int timestamp,
                                   @Field("s_token") String s_token,
                                   @Field("trade_id") String trade_id,
                                   @Field("reply") String reply,
                                   @Field("sign") String sign);



    /**
     * 供求信息可信度评分/评价
     * @param get_trades 操作方法名，= get_trades
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_id 供求信息ID
     * @param rating 可信度评分值
     * @param comment 可信度评价内容（可选参数）
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.TRADE)
    Observable<String> comment_trade(@Field("method") String get_trades,
                                     @Field("timestamp") int timestamp,
                                     @Field("s_token") String s_token,
                                     @Field("trade_id") String trade_id,
                                     @Field("rating") int rating,
                                     @Field("comment") String comment,
                                     @Field("sign") String sign);



    /**
     * 我的供求信息列表
     * @param get_mytrades 操作方法名，= get_mytrades
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.TRADE)
    Observable<String> get_mytrades(@Query("method") String get_mytrades,
                                    @Query("timestamp") int timestamp,
                                    @Query("s_token") String s_token,
                                    @Query("page") int page,
                                    @Query("size") int size,
                                    @Query("sign") String sign);



    /**
     * 提交编辑后的供求信息
     * @param update_trade//操作方法名，= update_trade
     * @param timestamp//发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token//会话令牌
     * @param trade_id//供求信息ID
     * @param trade_title//标题
     * @param trade_text//供求信息正文
     * @param trade_expires//有效期，单位：周。默认最大有效期为4周
     * @param sign//签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.TRADE)
    Observable<String> update_trade(@Field("method") String update_trade,
                                    @Field("timestamp") int timestamp,
                                    @Field("s_token") String s_token,
                                    @Field("trade_id") String trade_id,
                                    @Field("trade_title") String trade_title,
                                    @Field("trade_text") String trade_text,
                                    @Field("trade_expires") int trade_expires,
                                    @Field("sign") String sign);



    /**
     * 浏览回复（私信留信）
     * @param get_replies 操作方法名，= get_replies
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_id 供求信息ID
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.TRADE)
    Observable<String> get_replies(@Query("method") String get_replies,
                                   @Query("timestamp") int timestamp,
                                   @Query("s_token") String s_token,
                                   @Query("trade_id") String trade_id,
                                   @Query("page") int page,
                                   @Query("size") int size,
                                   @Query("sign") String sign);



    /**
     * 浏览可信度评价
     * @param get_comments 操作方法名，= get_comments
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param trade_id 供求信息ID
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.TRADE)
    Observable<String> get_comments(@Query("method") String get_comments,
                                    @Query("timestamp") int timestamp,
                                    @Query("s_token") String s_token,
                                    @Query("trade_id") String trade_id,
                                    @Query("page") int page,
                                    @Query("size") int size,
                                    @Query("sign") String sign);




    /**
     * 获取点对点消息内容列表
     * @param get_msgs 操作方法名，= get_msgs
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param from_uid 发送方user_id（可选参数，筛选条件）
     * @param from_date 从何时开始（可选参数，筛选条件）
     * @param to_date 到何时结束（可选参数，筛选条件）
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.SESSION)
    Observable<String> get_msgs(@Query("method") String get_msgs,
                                @Query("timestamp") int timestamp,
                                @Query("s_token") String s_token,
                                @Query("from_uid") String from_uid,
                                @Query("from_date") String from_date,
                                @Query("to_date") String to_date,
                                @Query("page") int page,
                                @Query("size") int size,
                                @Query("sign") String sign);



    /**
     * 点对点消息发送
     * @param post_msg 操作方法名，= post_msg
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param to_uid 对方user_id
     * @param msg_type 消息类型；格式为：类型/编码格式text/utf-8=文本,image/jpeg=图片,audio/mp3=音频, video/mp4=视频
     * @param msg_content 消息内容，非文本的消息内容，采用Base64方式编码处理为字符串，服务器端接收后，根据需要，决定是否进行Base64解码处理。
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.SESSION)
    Observable<String> post_msg(@Field("method") String post_msg,
                                @Field("timestamp") int timestamp,
                                @Field("s_token") String s_token,
                                @Field("to_uid") String to_uid,
                                @Field("msg_type") String msg_type,
                                @Field("msg_content") String msg_content,
                                @Field("sign") String sign);

    /**
     * 获取点对点消息内容列表
     * @param get_chats 操作方法名，= get_chats
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param from_orgid 商会ID（可选参数，筛选条件）
     * @param from_date 从何时开始（可选参数，筛选条件）
     * @param to_date 到何时结束（可选参数，筛选条件）
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.SESSION)
    Observable<String> get_chats(@Query("method") String get_chats,
                                 @Query("timestamp") int timestamp,
                                 @Query("s_token") String s_token,
                                 @Query("from_orgid") String from_orgid,
                                 @Query("from_date") String from_date,
                                 @Query("to_date") String to_date,
                                 @Query("page") int page,
                                 @Query("size") int size,
                                 @Query("sign") String sign);


    /**
     * 群聊消息发送
     * @param post_chat 操作方法名，= post_chat
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param to_orgid 商会ID
     * @param msg_type 消息类型；格式为：类型/编码格式text/utf-8=文本,image/jpeg=图片,audio/mp3=音频, video/mp4=视频
     * @param msg_content 消息内容，非文本的消息内容，采用Base64方式编码处理为字符串，服务器端接收后，根据需要，决定是否进行Base64解码处理。
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.SESSION)
    Observable<String> post_chat(@Field("method") String post_chat,
                                 @Field("timestamp") int timestamp,
                                 @Field("s_token") String s_token,
                                 @Field("to_orgid") String to_orgid,
                                 @Field("msg_type") String msg_type,
                                 @Field("msg_content") String msg_content,
                                 @Field("sign") String sign);


    /**
     * 意见反馈
     * @param post_feedback 操作方法名，= post_feedback
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param feedback 反馈的意见内容
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.SYSTEM)
    Observable<String> post_feedback(@Field("method") String post_feedback,
                                     @Field("timestamp") int timestamp,
                                     @Field("s_token") String s_token,
                                     @Field("feedback") String feedback,
                                     @Field("sign") String sign);

    /**
     * 错误数据报告
     * @param post_msg 操作方法名，= post_msg
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param device_id 移动设备唯一标识符
     * @param err_data 错误数据
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.SYSTEM)
    Observable<String> post_error(@Field("method") String post_msg,
                                  @Field("timestamp") int timestamp,
                                  @Field("device_id") String device_id,
                                  @Field("err_data") String err_data,
                                  @Field("sign") String sign);


    /**
     * 获取用户协议
     * @param get_agreement 操作方法名，= get_agreement
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.SYSTEM)
    Observable<String> get_agreement(@Query("method") String get_agreement,
                                     @Query("timestamp") int timestamp,
                                     @Query("sign") String sign);


    /**
     * 云备份
     * @param post_linkmans 操作方法名，= post_linkmans
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param linkmans 进行云备份的联系人数组，JSON格式
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.LINKMAN)
    Observable<String> post_linkmans(@Field("method") String post_linkmans,
                                     @Field("timestamp") int timestamp,
                                     @Field("s_token") String s_token,
                                     @Field("linkmans") String linkmans,
                                     @Field("sign") String sign);



    /**
     * 云恢复
     * @param get_linkmans 操作方法名，= post_linkmans
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.LINKMAN)
    Observable<String> get_linkmans(@Query("method") String get_linkmans,
                                    @Query("timestamp") int timestamp,
                                    @Query("s_token") String s_token,
                                    @Query("sign") String sign);


    /**
     * 云变更
     * @param update_linkman 操作方法名，= update_linkman
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param mobile 手机号
     * @param name 联系人姓名
     * @param post 职务
     * @param male 性别
     * @param org 机构名称（公司名称）
     * @param mail 电子邮件地址
     * @param group 群组名称
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.LINKMAN)
    Observable<String> update_linkman(@Query("method") String update_linkman,
                                      @Query("timestamp") int timestamp,
                                      @Query("s_token") String s_token,
                                      @Query("mobile") String mobile,
                                      @Query("name") String name,
                                      @Query("post") String post,
                                      @Query("male") boolean male,
                                      @Query("org") String org,
                                      @Query("mail") String mail,
                                      @Query("group") String group,
                                      @Query("sign") String sign);


    /**
     * 百度推送需要提交的消息
     *
     * @param post_info 操作方法名，= post_info
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param bd_appid 百度推送平台分配的AppId
     * @param bd_userid 百度推送平台分配的UserId
     * @param bd_channelid 百度推送平台分配的ChannelId
     * @param sign 签名字符串
     * @return
     */
    @FormUrlEncoded
    @POST(Url.BAIDUPUSH)
    Observable<String> push_info(@Field("method") String post_info,
                                 @Field("timestamp") int timestamp,
                                 @Field("s_token") String s_token,
                                 @Field("bd_appid") String bd_appid,
                                 @Field("bd_userid") String bd_userid,
                                 @Field("bd_channelid") String bd_channelid,
                                 @Field("sign") String sign);

    /**
     * 下载文件
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@retrofit2.http.Url String fileUrl);


    /**
     * 获取行业资讯列表
     * @param get_newslist 操作方法名，= get_newslist
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param from_date 从何时开始（Unix时间戳可选参数，筛选条件）
     * @param to_date 到何时结束（Unix时间戳可选参数，筛选条件）
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.INDUSTRY)
    Observable<String> get_newslist(@Query("method") String get_newslist,
                                    @Query("timestamp") int timestamp,
                                    @Query("s_token") String s_token,
                                    @Query("from_date") String from_date,
                                    @Query("to_date") String to_date,
                                    @Query("page") int page,
                                    @Query("size") int size,
                                    @Query("sign") String sign);


    /**
     * 获取行业资讯详情
     * @param get_newslist 操作方法名，= get_newslist
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param news_id 资讯ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.INDUSTRY)
    Observable<String> get_news(@Query("method") String get_newslist,
                                @Query("timestamp") int timestamp,
                                @Query("s_token") String s_token,
                                @Query("news_id") String news_id,
                                @Query("sign") String sign);


    /**
     * 获取法律法规列表
     * @param get_laws 操作方法名，= get_laws
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param from_date 从何时开始（Unix时间戳可选参数，筛选条件）
     * @param to_date 到何时结束（Unix时间戳可选参数，筛选条件）
     * @param page 页号
     * @param size 每页记录数
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.LAW)
    Observable<String> get_laws(@Query("method") String get_laws,
                                @Query("timestamp") int timestamp,
                                @Query("s_token") String s_token,
                                @Query("from_date") String from_date,
                                @Query("to_date") String to_date,
                                @Query("page") int page,
                                @Query("size") int size,
                                @Query("sign") String sign);


    /**
     * 法律法规信息
     * @param get_law 操作方法名，= get_law
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param s_token 会话令牌
     * @param law_id 法律ID
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.LAW)
    Observable<String> get_law(@Query("method") String get_law,
                               @Query("timestamp") int timestamp,
                               @Query("s_token") String s_token,
                               @Query("law_id") String law_id,
                               @Query("sign") String sign);



    /**
     * 企业名称检查
     * @param check_enterprise 操作方法名，= check_enterprise
     * @param timestamp 发起请求时的Unix时间戳，格林威治标准时间
     * @param c_name 用户输入的企业名称
     * @param sign 签名字符串
     * @return
     */
    @GET(Url.ENTERPRISE)
    Observable<String> check_enterprise(@Query("method") String check_enterprise,
                                        @Query("timestamp") int timestamp,
                                        @Query("c_name") String c_name,
                                        @Query("sign") String sign);


    /**
     * 获取频道资讯列表
     * @param get_infos
     * @param timestamp
     * @param from_date
     * @param to_date
     * @param s_token
     * @param channel_id
     * @param page
     * @param size
     * @param sign
     * @return
     */
    @GET(Url.INFO)
    Observable<String> get_infos(@Query("method") String get_infos,
                                 @Query("timestamp") int timestamp,
                                 @Query("from_date") int from_date,
                                 @Query("to_date") int to_date,
                                 @Query("s_token") String s_token,
                                 @Query("channel_id") int channel_id,
                                 @Query("page") int page,
                                 @Query("size") int size,
                                 @Query("sign") String sign);

    /**
     * 获取频道列表
     * @param get_channels 方法名
     * @param timestamp 时戳
      * @param sign 签名
     * @return
     */
    @GET(Url.INFO)
    Observable<String> get_channels(@Query("method") String get_channels,
                                    @Query("timestamp") int timestamp,
                                    @Query("sign") String sign);

}

