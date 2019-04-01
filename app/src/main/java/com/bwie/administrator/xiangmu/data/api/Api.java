package com.bwie.administrator.xiangmu.data.api;

public class Api {

    //登录
    public static final String loginPath="http://mobile.bwstudent.com/small/user/v1/login";
    //注册
    public static final String registerPath="http://mobile.bwstudent.com/small/user/v1/register";
    //圈子展示
    public static final String circleListPath="circle/v1/findCircleList";
    //轮播
    public static final String XBannerPath="commodity/v1/bannerShow";
    //首页信息
    public static final String SPPath="commodity/v1/commodityList";
    //商品详情
    public static final String ShopXQPath="commodity/v1/findCommodityDetailsById";
    //更多商品
    public static final String MoreShopPath="commodity/v1/findCommodityListByLabel";
    //根据键词查询商品信息
    public static final String ByKeywordPath="commodity/v1/findCommodityByKeyword";
    //加入购物车
    public static final String addShopCarPath="order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String ShopCarPath="order/verify/v1/findShoppingCart";
    //查询用户资料
    public static final String GetUserByIdPath="user/verify/v1/getUserById";
    //修改用户名
    public static final String UpdataUsernamePath="user/verify/v1/modifyUserNick";
    //修改密码
    public static final String UpdataPasswordPath="user/verify/v1/modifyUserPwd";
    //我的圈子
    public static final String GetMyCirclePath="circle/verify/v1/findMyCircleById";
    //我的足迹
    public static final String GetMyFootPath="commodity/verify/v1/browseList";
    //新增收货地址
    public static final String AddnewAddressPath="http://mobile.bwstudent.com/small/user/verify/v1/addReceiveAddress";
    //收货地址列表
    public static final String AddressListPath="user/verify/v1/receiveAddressList";
    //圈子点赞
    public static final String CricleDZPath="http://mobile.bwstudent.com/small/circle/verify/v1/addCircleGreat";
    //圈子取消点赞
    public static final String UnCircleDZPath="http://mobile.bwstudent.com/small/circle/verify/v1/cancelCircleGreat";
    //默认收货地址
    public static final String DefaultAddress="http://mobile.bwstudent.com/small/user/verify/v1/setDefaultReceiveAddress";
    //修改默认地址
    public static final String UpdataAddressPath="user/verify/v1/changeReceiveAddress";
    //删除我发表过得圈子
    public static final String DelMyCirclePath="circle/verify/v1/deleteCircle";
    //我的钱包
    public static final String MyWalletPath="http://172.17.8.100/small/user/verify/v1/findUserWallet";

    //全部订单
    public static final String QUANBUDINGDAN_URL="http://172.17.8.100/small/order/verify/v1/createOrder";
}
