package app.lottery.com.lotteryapp.activity;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import app.lottery.com.lotteryapp.R;
import app.lottery.com.lotteryapp.data.NewsData;
import butterknife.BindView;

public class NewsDetailActivity extends BaseActivity {
    private NewsData newsData;

    @BindView(R.id.gank_detail_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.gank_detail_webview)
    WebView mWebView;

    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    private String html;

    private String s1 = "<label class=\"ep-share-tip\">分享到：</label> \n" +
            "       <div class=\"ep-share-top JS_NTES_LOG_FE\" data-module-name=\"xwwzy_16_sns\"> \n" +
            "        <ul id=\"article_share14\" class=\"nt-share14\" data-url=\"\" data-title=\"\" data-pic=\"\" data-digest=\"\" data-source=\"\"> \n" +
            "         <li class=\"nt-share-item\" data-type=\"yixin\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到易信\"><i class=\"nt-share-yixin\"></i></a></li> \n" +
            "         <li class=\"nt-share-item\" data-type=\"lofter-text\" data-from=\"news\" data-act=\"qbwbfx_20141018_02\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到LOFTER\"><i class=\"nt-share-lofter\"></i></a></li> \n" +
            "         <li class=\"nt-wx2dcode-icon\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到微信\"><i class=\"nt-share-weixin\"></i></a></li> \n" +
            "         <li class=\"nt-share-item\" data-type=\"qzone\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到QQ空间\"><i class=\"nt-share-qzone\"></i></a></li> \n" +
            "         <li class=\"nt-share-item\" data-type=\"sinawb\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到新浪微博\"><i class=\"nt-share-sinawb\"></i></a></li> \n" +
            "         <li class=\"nt-share-item\" data-type=\"renren\"><a href=\"javascript:;\" target=\"_self\" title=\"分享到人人网\"><i class=\"nt-share-renren\"></i></a></li> \n" +
            "         <li class=\"nt-share-item\" data-type=\"youdao\"><a href=\"javascript:;\" target=\"_self\" title=\"收藏到有道云笔记\"><i class=\"nt-share-youdao\"></i></a></li> \n" +
            "        </ul> \n" +
            "       </div> ";

    private String s2 = "<nav id=\"topNav\"> \n" +
            "   <div id=\"topNavWrap\"> \n" +
            "    <div id=\"topNavLeft\">\n" +
            "     <script type=\"text/javascript\">window.Core && window.Core.navInit(\"http://cdn.caipiao.163.com/caipiao\",easyNav.isLogin() ? easyNav.account : \"\",\"201501091822\");</script>\n" +
            "    </div> \n" +
            "    <ul id=\"topNavRight\"> \n" +
            "     <li><a href=\"https://epay.163.com/\" id=\"myEpay\" notice=\"false\" target=\"_blank\">我的网易宝</a>&nbsp;&nbsp;<span id=\"topEpayInfo\"></span>|</li> \n" +
            "     <li> \n" +
            "      <div id=\"myCoupon\"> \n" +
            "       <div class=\"mcDropMenuBox fl\"> \n" +
            "        <a target=\"_top\" user=\"y\" class=\"topNavHolder\" href=\"http://caipiao.163.com/my/coupon.html#from=top\" rel=\"nofollow\"><em class=\"text_icon\"></em>红包<i></i></a> \n" +
            "        <div class=\"mcDropMenu couponContent\"></div> \n" +
            "       </div> &nbsp;&nbsp;\n" +
            "       <a target=\"_blank\" user=\"y\" href=\"http://caipiao.163.com/sale/coupon_saleCouponIn.html#from=top\" id=\"buyCoupon\">购买</a>&nbsp;&nbsp;| \n" +
            "      </div> </li> \n" +
            "     <li>\n" +
            "      <div class=\"mcDropMenuBox myorder\"> \n" +
            "       <a target=\"_top\" user=\"y\" class=\"topNavHolder\" href=\"http://caipiao.163.com/my/order.html\" rel=\"nofollow\"><em class=\"text_icon\"></em>我的订单<i></i></a>\n" +
            "       <b class=\"holderLine\">|</b> \n" +
            "       <div class=\"mcDropMenu\"> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/my/order_followbuy.html#from=top\" rel=\"nofollow\">我的追号</a> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/my/order_autofollow.html#from=top\" rel=\"nofollow\">定制跟单</a> \n" +
            "       </div> \n" +
            "      </div></li> \n" +
            "     <li>\n" +
            "      <div class=\"mcDropMenuBox\"> \n" +
            "       <a target=\"_top\" user=\"y\" class=\"topNavHolder\" href=\"http://caipiao.163.com/order/mylottery_info.html#from=top\" rel=\"nofollow\">我的彩票<i></i></a>\n" +
            "       <b class=\"holderLine\">|</b> \n" +
            "       <div class=\"mcDropMenu\"> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/my/coupon.html#from=top\" rel=\"nofollow\">我的红包</a> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/my/credit.html#from=top\" rel=\"nofollow\">我的积分</a> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/order/myactivity_drawaward.html#from=top\" rel=\"nofollow\">我的活动</a> \n" +
            "        <a target=\"_top\" user=\"y\" href=\"http://caipiao.163.com/my/message.html#from=top\" rel=\"nofollow\">消息中心</a> \n" +
            "        <a target=\"_blank\" user=\"y\" href=\"http://caipiao.163.com/homepage/homepage_index.html#from=top\" rel=\"nofollow\">个人主页</a> \n" +
            "       </div> \n" +
            "      </div></li> \n" +
            "     <li><a target=\"_blank\" href=\"http://bbs.caipiao.163.com/\" rel=\"nofollow\">论坛</a>&nbsp;&nbsp;|</li> \n" +
            "     <li>\n" +
            "      <div class=\"mcDropMenuBox\"> \n" +
            "       <a target=\"_blank\" class=\"topNavHolder\" href=\"http://caipiao.163.com/help/\" rel=\"nofollow\">帮助<i></i></a> \n" +
            "       <div class=\"mcDropMenu\"> \n" +
            "        <a target=\"_blank\" href=\"http://caipiao.163.com/help/13/0222/19/8OBGCEEN00754KE8.html\" rel=\"nofollow\">如何充值</a> \n" +
            "        <a target=\"_blank\" href=\"http://caipiao.163.com/help/special/award/\" rel=\"nofollow\">如何领奖</a> \n" +
            "        <a target=\"_blank\" href=\"http://caipiao.163.com/help/13/0222/19/8OBGFK7200754KE9.html\" rel=\"nofollow\">如何提现</a> \n" +
            "        <a target=\"_blank\" href=\"http://caipiao.163.com/help/\" rel=\"nofollow\">更多帮助</a> \n" +
            "        <a target=\"_blank\" href=\"http://caipiao.163.com/kf.html\" rel=\"nofollow\">意见反馈</a> \n" +
            "       </div> \n" +
            "      </div></li> \n" +
            "    </ul> \n" +
            "   </div> \n" +
            "  </nav>";

    private String s3 = "<header id=\"docHead\">\n" +
            "   <div id=\"docHeadWrap\"> \n" +
            "    <a target=\"_self\" href=\"http://caipiao.163.com\" class=\"logoLnk\" title=\"网易彩票\" hidefocus=\"true\"><span>网易彩票<img src=\"http://pimg1.126.net/caipiao/img/logos/caipiao.png?1\" alt=\"网易彩票\"></span></a> \n" +
            "    <a target=\"_blank\" href=\"http://caipiao.163.com/order/preBet_jczqspfp.html#from=dt\" rel=\"nofollow\" class=\"guideLnk\" hidefocus=\"true\"><span>网易彩票</span></a> \n" +
            "    <p><span class=\"serviceTel\"> <span class=\"serviceTel_tel\"> <span>客服热线</span><br> <strong>020-83568090</strong> </span> <a class=\"onlineService \" href=\"http://caipiao.163.com/kf.html\" target=\"_blank\">在线客服</a> </span></p> \n" +
            "   </div>\n" +
            "  </header>";

    private String s4 = "<nav id=\"topTabBox\"> \n" +
            "   <div id=\"topTab\"> \n" +
            "    <ul id=\"funcTab\">\n" +
            "     <li id=\"lotteryListEntry\"><a class=\"topNavHolder\" hidefocus=\"true\" rel=\"nofollow\">选择彩种<i></i></a> \n" +
            "      <div id=\"lotteryList\"> \n" +
            "       <div class=\"lotteryListWrap\"> \n" +
            "        <ul> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/ssq/#from=leftnav\" gid=\"ssq\"><em class=\"cz_logo35 logo35_ssq\"></em><strong>双色球</strong></a></li> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/dlt/#from=leftnav\" gid=\"dlt\"><em class=\"cz_logo35 logo35_dlt\"></em><strong>大乐透</strong></a></li> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/jczq/#from=leftnav\" gid=\"jczq\"><em class=\"cz_logo35 logo35_jczq\"></em><strong>竞彩足球</strong></a></li> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/jclq/mixp.html#from=leftnav\" gid=\"jclq_mix_p\"><em class=\"cz_logo35 logo35_jclq\"></em><strong>竞彩篮球</strong></a></li> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/11x5/#from=leftnav\" gid=\"d11\"><em class=\"cz_logo35 logo35_d11\"></em><strong>11选5</strong></a></li> \n" +
            "         <li class=\"zyGame\"><a href=\"http://caipiao.163.com/order/gxkuai3/#from=leftnav\" gid=\"gxkuai3\"><em class=\"cz_logo35 logo35_gxkuai3\"></em><strong>新快3</strong></a></li> \n" +
            "         <li class=\"otherGames clearfix\"> <h3>高频</h3> \n" +
            "          <div> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/gd11x5/#from=leftnav\" title=\"猜对1个号就中奖，每天84期\" gid=\"gdd11\">粤11选5</a></em>\n" +
            "           <em><a href=\"http://caipiao.163.com/order/jx11x5/#from=leftnav\" title=\"每天78期，任猜1-8个号都中奖\" gid=\"jxd11\">老11选5</a></em>\n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/hlj11x5/#from=leftnav\" title=\"猜中一个号就中奖，返奖率59%\" gid=\"hljd11\">好运11选5</a></em>\n" +
            "           <em><a href=\"http://caipiao.163.com/order/kl8/#from=leftnav\" title=\"5分钟一期，最高奖500万\" gid=\"kl8\">快乐8</a></em>\n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/kuai3/#from=leftnav\" title=\"10分钟一期，快乐猜大小\" gid=\"kuai3\">快3</a></em> \n" +
            "           <em><a href=\"http://caipiao.163.com/order/oldkuai3/#from=leftnav\" title=\"最容易中奖，全天82期\" gid=\"oldkuai3\">老快3</a></em>\n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/jxssc/#from=leftnav\" title=\"10分钟一期，最高奖11.6万\" gid=\"jxssc\">新时时彩</a></em>\n" +
            "           <em><a href=\"http://caipiao.163.com/order/ssc/#from=leftnav\" title=\"独有夜间版，01：55截止\" gid=\"ssc\">老时时彩</a></em>\n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/gdkuai2/#from=leftnav\" title=\"猜中一个号就中奖，5分钟一期\" gid=\"kuai2\">快2</a></em> \n" +
            "          </div> </li> \n" +
            "         <li class=\"otherGames clearfix\"> <h3>竞技</h3> \n" +
            "          <div> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/dc/#from=leftnav\" title=\"猜一场即中奖\" gid=\"football_dcspf\">足球单场</a></em> \n" +
            "           <em><a href=\"http://caipiao.163.com/order/f4cjq/#from=leftnav\" gid=\"football_f4cjq\">四场进球</a></em> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/sfc/#from=leftnav\" title=\"猜中14场大奖500万\" gid=\"football_sfc\">胜负彩</a></em> \n" +
            "           <em><a href=\"http://caipiao.163.com/order/bqc/#from=leftnav\" gid=\"football_bqc\">六场半全场</a></em> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/rx9/#from=leftnav\" title=\"任选九场比赛\" gid=\"football_9\">任选九场</a></em> \n" +
            "          </div> </li> \n" +
            "         <li class=\"otherGames end clearfix\"> <h3>数字</h3> \n" +
            "          <div> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/3d/#from=leftnav\" title=\"2元赢取1000元，天天开奖\" gid=\"x3d\">福彩3D</a></em> \n" +
            "           <em><a href=\"http://caipiao.163.com/order/qlc/#from=leftnav\" title=\"大奖500万 每周一、三、五开奖\" gid=\"qlc\">七乐彩</a></em> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/pl3/#from=leftnav\" title=\"2元赢取1000元，天天开奖\" gid=\"pl3\">排列3</a></em> \n" +
            "           <em><a href=\"http://caipiao.163.com/order/qxc/#from=leftnav\" title=\"大奖500万 每周二、五、日开奖\" gid=\"qxc\">七星彩</a></em> \n" +
            "           <em class=\"left\"><a href=\"http://caipiao.163.com/order/pl5/#from=leftnav\" title=\"2元赢取10万元，天天开奖\" gid=\"pl5\">排列5</a></em> \n" +
            "          </div> </li> \n" +
            "        </ul> \n" +
            "       </div> \n" +
            "      </div> </li>\n" +
            "     <li pid=\"home\"><a href=\"http://caipiao.163.com/\">首页</a>|</li> \n" +
            "     <li pid=\"hall\"><a title=\"彩票购彩大厅\" href=\"http://caipiao.163.com/order/\">购彩大厅</a>|</li> \n" +
            "     <li class=\"wordsNum2\" pid=\"groupbuy\"><a title=\"彩票合买大厅\" href=\"http://caipiao.163.com/groupbuy/\">合买<i></i></a>| \n" +
            "      <div class=\"mcDropMenu\"> \n" +
            "       <a href=\"http://caipiao.163.com/groupbuy/\">合买大厅</a> \n" +
            "       <a href=\"http://caipiao.163.com/followhall/\">定制跟单</a> \n" +
            "      </div></li> \n" +
            "     <li class=\"wordsNum4\" pid=\"award\"><a title=\"中国彩票开奖\" href=\"http://caipiao.163.com/award/\">彩票开奖<i></i></a>| \n" +
            "      <div class=\"mcDropMenu\"> \n" +
            "       <a href=\"http://caipiao.163.com/award/\">开奖公告</a> \n" +
            "       <a href=\"http://caipiao.163.com/hit/hit_ssq.html?index=1\">中奖排行</a> \n" +
            "      </div></li> \n" +
            "     <li pid=\"trend\"><a title=\"福彩体彩走势图\" href=\"http://zx.caipiao.163.com/trend/trend.html\">走势图</a>|</li> \n" +
            "     <li class=\"wordsNum4 tl\" pid=\"saishi\"><a href=\"http://live.caipiao.163.com/\">赛事数据<i></i></a>| \n" +
            "      <div class=\"mcDropMenu\"> \n" +
            "       <a href=\"http://live.caipiao.163.com/\">比分直播</a> \n" +
            "       <a href=\"http://saishi.caipiao.163.com\">足球资料库</a> \n" +
            "       <a href=\"http://odds.caipiao.163.com/\">赔率中心</a> \n" +
            "       <a href=\"http://basketball.caipiao.163.com/\">篮球资料库</a> \n" +
            "      </div></li> \n" +
            "     <li pid=\"cpInfo\" class=\"active\"><a title=\"彩票资讯\" href=\"http://zx.caipiao.163.com/\">彩票资讯</a>|</li> \n" +
            "     <li class=\"wordsNum2\" pid=\"coupon\"><a title=\"优惠\" href=\"http://caipiao.163.com/sale/coupon_saleCouponIn.html\">优惠<i></i></a>| \n" +
            "      <div class=\"mcDropMenu\"> \n" +
            "       <a href=\"http://caipiao.163.com/sale/coupon_saleCouponIn.html\">彩票红包</a> \n" +
            "       <a href=\"http://caipiao.163.com/jifen/\">积分乐园</a> \n" +
            "      </div></li> \n" +
            "     <li pid=\"mobile\"><a title=\"手机购买彩票\" href=\"http://caipiao.163.com/outside/getclient_cp.html\">手机购彩</a></li>\n" +
            "    </ul> \n" +
            "   </div> \n" +
            "  </nav>";

    private String s5 = "<div class=\"nav_2\"> \n" +
            "   <a href=\"http://caipiao.163.com/\">网易彩票</a> &gt; \n" +
            "   <a href=\"http://zx.caipiao.163.com/\">资讯</a> &gt; \n" +
            "   <a target=\"_blank\" href=\"http://zx.caipiao.163.com/zx/more.html?infoClass=news\">彩市新闻</a> &gt; 正文 \n" +
            "  </div>";

    private String s6 = "<div class=\"ep-tie-top\"> \n" +
            "        <a class=\"ep-icon-tie js-tieachor JS_NTES_LOG_FE\" target=\"_self\" data-module-name=\"xwwzy_15_tiepost1\" href=\"#tiePostBox\" title=\"快速发贴\"></a> \n" +
            "        <a class=\"ep-cnum-tie js-tielink js-tiecount JS_NTES_LOG_FE\" data-module-name=\"xwwzy_15_tieview1\" href=\"#\" title=\"点击查看跟贴\">0</a> \n" +
            "       </div> ";

    private String s7 = "<div class=\"hot_block seohot_block clearfix\" style=\"width: 990px;\"> \n" +
            "   <h2 class=\"hot_title\" id=\"seohotNav\"> <em rel=\"#hot_block\" class=\"active\">热点导航</em> <em rel=\"#sport_block\" class=\"\">体育赛事</em> </h2> \n" +
            "   <p id=\"hot_block\"> <a target=\"_blank\" href=\"http://trend.caipiao.163.com/\" title=\"彩票走势图\">彩票走势图</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/ssq/\" title=\"双色球投注\">双色球投注</a> <a target=\"_blank\" href=\"http://g.caipiao.163.com/activity/CouponPushIndexPc.html\">1元夺iphone6s</a> <a target=\"_blank\" href=\"http://sports.163.com/special/2016europeancuplottery/\" title=\"欧洲杯\">欧洲杯</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/11xuan5/ \" title=\"体彩11选5\">体彩11选5</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/\">彩票投注</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/dlt/\" title=\"体彩超级大乐透\">体彩大乐透</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/pl3/\" title=\"体彩排列3\">排列三</a> <a target=\"_blank\" href=\"http://zx.caipiao.163.com/dlt/yuce/\" title=\"体彩超级大乐透预测分析\">大乐透预测</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/qlc/\" title=\"福彩七乐彩\">七乐彩</a> <a target=\"_blank\" href=\"http://caipiao.163.com/award/gdklsf/\" title=\"粤快乐十分开奖\">粤快乐十分开奖</a> <a target=\"_blank\" href=\"http://cai.163.com/scrollNews/\">福彩预测</a> <a target=\"_blank\" href=\"http://caipiao.163.com/groupbuy/\">彩票合买</a> <a target=\"_blank\" href=\"http://caipiao.163.com/order/gdklsf/\" title=\"粤快乐十分\">粤快乐十分</a> <a target=\"_blank\" href=\"http://caipiao.163.com/help/12/0903/18/8AGGIO7L00754IPH.html\" title=\"双色球缩水工具\">双色球工具</a> <a target=\"_blank\" href=\"https://itunes.apple.com/cn/app/le-cai-piao-wang-yi-zu-cai/id618888447\">网易乐得彩票</a> <a target=\"_blank\" href=\"https://itunes.apple.com/cn/app/wang-yi-cai-piao-ji-su-ban/id687634749?mt=8\">网易彩票极速版</a> <a target=\"_blank\" href=\"https://itunes.apple.com/cn/app/wang-yi-cai-piao-gei-meng/id411654863?mt=8\">网易彩票</a> <a rel=\"nofollow\" class=\"showmore\" href=\"javascript:void(0)\">更多&gt;&gt;</a> </p> \n" +
            "   <p id=\"sport_block\" style=\"display: none;\"> <a target=\"_blank\" href=\"http://saishi.caipiao.163.com/250.html\" title=\"亚洲杯积分榜\">亚洲杯</a> <a rel=\"nofollow\" class=\"showmore\" href=\"javascript:void(0)\">更多&gt;&gt;</a> </p> \n" +
            "  </div> \n" +
            "  <footer id=\"docFoot\"> \n" +
            "   <ul id=\"guideList\"> \n" +
            "    <li class=\"first\"><em class=\"guide_1\"></em> <em class=\"guide_1_con\"></em> </li> \n" +
            "    <li><em class=\"guide_2\"></em><span> · <a title=\"购买彩票流程\" href=\"http://caipiao.163.com/help/\" target=\"_blank\">购彩流程</a><br> · <a title=\"彩票领奖流程\" href=\"http://caipiao.163.com/help/10/0726/16/6CHGN51T00754IHQ.html\" target=\"_blank\">领奖流程</a><br> · <a rel=\"nofollow\" href=\"https://epay.163.com/index.jsp\" target=\"_blank\">开通网易宝</a><br> · <a rel=\"nofollow\" href=\"https://epay.163.com/charge/chargeView.htm?from=caipiao\" target=\"_blank\">网易宝充值</a> </span></li> \n" +
            "    <li class=\"last\"><em class=\"guide_3\"></em><span> · <a title=\"新手购彩图解\" href=\"http://caipiao.163.com/imghelp/tz_pt_1.jsp\" target=\"_blank\">新手购彩图解</a><br> · <a title=\"网上购买彩票常见问题\" href=\"http://caipiao.163.com/help/special/00754II5/caipiao_qa_guide.html\" target=\"_blank\">常见问题</a><br> · <a title=\"网上购买彩票功能指引\" href=\"http://caipiao.163.com/help/special/00754IHC/caipiao_help_index.html\" target=\"_blank\">功能指引</a><br> · <a title=\"彩票彩种介绍\" href=\"http://caipiao.163.com/help/\" target=\"_blank\">彩种介绍</a> </span></li> \n" +
            "    <li><em class=\"guide_4\"></em><span> · <a rel=\"nofollow\" href=\"http://caipiao.163.com/help/10/0726/16/6CHHOPF900754IHP.html\" target=\"_blank\">网易宝支付</a><br> · <a rel=\"nofollow\" href=\"http://caipiao.163.com/help/10/0726/17/6CHLJHB100754IHP.html\" target=\"_blank\">网银支付</a><br> · <a rel=\"nofollow\" href=\"http://caipiao.163.com/help/10/0726/17/6CHLRB7600754IHP.html\" target=\"_blank\">支付宝支付</a><br> · <a rel=\"nofollow\" href=\"http://caipiao.163.com/help/10/0726/17/6CHM0HIM00754IHP.html\" target=\"_blank\">手机充值卡支付</a> </span></li> \n" +
            "    <li><em class=\"guide_5\"></em><span> · <a target=\"_blank\" href=\"http://caipiao.163.com/\">彩票</a>&nbsp; <a target=\"_blank\" href=\"http://piao.163.com/#from=cp_footer\">电影票</a><br> · <a target=\"_blank\" href=\"http://baoxian.163.com/?from=cp_footer\">保险</a>&nbsp; <a target=\"_blank\" href=\"http://trip.163.com/#from=cp_footer\">火车票</a><br> · <a target=\"_blank\" href=\"http://mm.163.com/#from=cp_footer\">美美</a>&nbsp; <a target=\"_blank\" href=\"http://yxp.163.com/#from=cp_footer\">印像派</a><br> · <a target=\"_blank\" href=\"http://mall.163.com/#from=cp_footer\">商城</a>&nbsp; <a target=\"_blank\" href=\"http://1.163.com/?from=caipiao\">一元夺宝</a> </span></li> \n" +
            "   </ul> \n" +
            "   <div id=\"aboutNEST\"> \n" +
            "    <a rel=\"nofollow\" href=\"http://corp.163.com/eng/about/overview.html\">About NetEase</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://gb.corp.163.com/gb/about/overview.html\">公司简介</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://gb.corp.163.com/gb/contactus.html\">联系方法</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://corp.163.com/gb/job/job.html\">招聘信息</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://help.163.com/\">客户服务</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://gb.corp.163.com/gb/legal.html\">隐私政策</a> - \n" +
            "    <a rel=\"nofollow\" href=\"http://emarketing.163.com/\">网络营销</a> - \n" +
            "    <a href=\"http://caipiao.163.com/sitemap.htm\">网站地图</a> - \n" +
            "    <a title=\"网易彩票网\" href=\"http://caipiao.163.com\">网易彩票网</a> \n" +
            "    <br>增值电信业务经营许可证：浙B2-20110418 | \n" +
            "    <a href=\"http://www.lede.com/prove.html\">网站相关资质证明</a> \n" +
            "    <br>网易旗下乐得公司版权所有 &copy;2011-2015 \n" +
            "    <br>网易彩票提醒您：理性购彩，热心公益。未满18周岁的未成年人禁止购彩及兑奖！ \n" +
            "   </div> \n" +
            "   <div id=\"seoFriendlyLink\" class=\"friendlyLink\"> \n" +
            "    <h3><strong>友情链接：</strong></h3> \n" +
            "    <ul> \n" +
            "     <li><a target=\"_blank\" href=\"http://caipiao.163.com/help/12/0331/11/7TU149BP00754JS4.html\" title=\"彩票充值\">彩票充值</a></li> \n" +
            "     <li class=\"more\"><a href=\"javascript:;\" rel=\"nofollow\">更多&gt;&gt;</a></li> \n" +
            "    </ul> \n" +
            "   </div> \n" +
            "  </footer>";

    private String s8 = "<span class=\"blank20\"></span> \n" +
            "     <span class=\"ep-main-parting\"></span> \n" +
            "     <span class=\"blank9\"></span> \n" +
            "     <div class=\"ep-keywords clearfix\"> \n" +
            "      <div class=\"ep-keywords-main\"> \n" +
            "       <div class=\"mod-title ep-title-3 clearfix\">\n" +
            "        关键词阅读： \n" +
            "        <span class=\"cBlue normal\"> <a href=\"/keywords/5/6/5f697968/1.html\">彩票</a> <a href=\"/keywords/5/7/53735f007968/1.html\">即开票</a> <a href=\"/keywords/7/a/7eaa68c0/1.html\">纪检</a> </span> \n" +
            "       </div> \n" +
            "       <span class=\"blank12\"></span> \n" +
            "       <ul class=\"mod-f14list ep-list-1 JS_NTES_LOG_FE\" data-module-name=\"xwwzy_35_bottomnewslink\"> \n" +
            "        <li><span class=\"title\"><a href=\"http://cai.163.com/article/17/0503/08/CJGET34C00052DT2.html#from=relevant#xwwzy_35_bottomnewskwd\">一周彩票行业动态:亚博与顺丰启动即开票业务</a></span> <span class=\"time\">2017/05/03</span></li> \n" +
            "        <li><span class=\"title\"><a href=\"http://cai.163.com/article/16/0217/15/BG1LGQUP00052DT2.html#from=relevant#xwwzy_35_bottomnewskwd\">中超竞猜或先选择即开票 竞猜型彩票仍需等待</a></span> <span class=\"time\">2016/02/17</span></li> \n" +
            "        <li><span class=\"title\"><a href=\"http://sports.163.com/14/0916/08/A68H1EDR0005227R.html#from=relevant#xwwzy_35_bottomnewskwd\">“刮刮乐”网点即开票百万大奖彩票黄金盛典介绍</a></span> <span class=\"time\">2014/09/16</span></li> \n" +
            "        <li><span class=\"title\"><a href=\"http://cai.163.com/article/12/1112/16/8G4GEUID00052DT2.html#from=relevant#xwwzy_35_bottomnewskwd\">异域彩风：美国推出圣诞即开票 奖金最高达100万</a></span> <span class=\"time\">2012/11/12</span></li> \n" +
            "        <li><span class=\"title\"><a href=\"http://2012.163.com/12/0723/14/873TBFE8000506A2.html#from=relevant#xwwzy_35_bottomnewskwd\">体彩奥运即开票上市 两款\"顶呱刮\"彩票限量发行</a></span> <span class=\"time\">2012/07/23</span></li> \n" +
            "       </ul> \n" +
            "      </div> \n" +
            "      <div class=\"ep-keywords-side\" id=\"js-ep-reletag\" style=\"display: none\"> \n" +
            "       <div class=\"mod-title ep-title-3 clearfix\">\n" +
            "        站内焦点\n" +
            "        <span class=\"ep-icon-new\"></span>\n" +
            "       </div> \n" +
            "       <span class=\"blank12\"></span> \n" +
            "       <ul class=\"mod-f14list ep-list-1 ep-list-nodot\"> \n" +
            "        <li><a href=\"http://news.tag.163.com/#from=reletag\" rel=\"nofollow\">网易聚合阅读</a></li> \n" +
            "       </ul> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "     <span class=\"blank20\"></span> \n" +
            "     <script type=\"text/javascript\">\n" +
            "                (function() {\n" +
            "    NTES.ajax.importJs('http://zx.caipiao.163.com/special/relevant/', function() {\n" +
            "        var data = window.relevantTagData || [];\n" +
            "        if (data.count > 0) {\n" +
            "            var relevantTag = NTES('#js-ep-reletag'),\n" +
            "                html = [];\n" +
            "            for (var i = 0; i < data.count && i < 5; i++) {\n" +
            "                html.push('<li><a href=\"' + data.hits[i].url + '#from=reletag\">' + data.hits[i].name + '</a></li>');\n" +
            "            }\n" +
            "            relevantTag.NTES('ul')[0].innerHTML = html.join('');\n" +
            "            relevantTag.style.display = 'block';\n" +
            "        }\n" +
            "    }, 'utf-8');\n" +
            "})();\n" +
            "            </script> \n" +
            "     <div class=\"hot_experts clearfix\">\n" +
            "      <span class=\"ep-main-parting\"></span> \n" +
            "      <div class=\"hot\"> \n" +
            "       <iframe frameborder=\"0\" width=\"295\" height=\"340\" scrolling=\"no\" src=\"http://zx.caipiao.163.com/zx/hotNews.html\"></iframe> \n" +
            "      </div> \n" +
            "      <div class=\"experts\"> \n" +
            "       <h2>专家分析</h2> \n" +
            "       <div class=\"tabExperts\">\n" +
            "        <span class=\"active\" rel=\"#szc\">数字彩</span>|\n" +
            "        <span rel=\"#dcc\">单场彩</span>|\n" +
            "        <span rel=\"#zc\">足彩</span>\n" +
            "       </div> \n" +
            "       <div class=\"expertsCon\" id=\"szc\"> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img3.cache.netease.com/sports/2015/8/24/2015082410472460ff4.jpg\" alt=\"河南倍投\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://dyc98198.blog.163.com/blog/?COLLCC=183498300&amp;#m=0&amp;t=1&amp;c=fks_084074086086087069084080094095086082082069084094086069087\" target=\"_blank\" class=\"gray3\">永春彩盘本期双色球走势分析已更新，查看请点击</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://dyc98198.blog.163.com/blog/?COLLCC=183498300&amp;#m=0&amp;t=1&amp;c=fks_084074086086087069084080094095086082082069084094086069087\" target=\"_blank\"></a> <a href=\"http://dyc98198.blog.163.com/blog/?COLLCC=183498300&amp;#m=0&amp;t=1&amp;c=fks_084074086086087069084080094095086082082069084094086069087\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img4.cache.netease.com/sports/2015/10/12/20151012150317f51a7.jpg\" alt=\"亦夫\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://booksmells.blog.163.com/\" target=\"_blank\" class=\"gray3\">书香公子足彩解盘已更新，查看请点击</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://booksmells.blog.163.com/\" target=\"_blank\"></a> <a href=\"http://booksmells.blog.163.com/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "       </div> \n" +
            "       <div class=\"expertsCon hide\" id=\"dcc\"> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img1.cache.netease.com/sports/2008/6/18/20080618171909b410d.jpg\" alt=\"朱金伟\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://zhujinwei801.blog.163.com/\" target=\"_blank\" class=\"gray3\">朱金伟：最新竞彩单场彩推荐</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://zhujinwei801.blog.163.com/\" target=\"_blank\">资深竞彩名家，足彩博客不定时更新，敬请彩民们关注</a> <a href=\"http://zhujinwei801.blog.163.com/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://cimg21.163.com/sports/2008/3/28/20080328190724aad00.jpg\" alt=\"范伟杰\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://wanghe265.blog.163.com/blog/static/2455011620141023101914924/\" target=\"_blank\" class=\"gray3\">范伟杰意甲:换帅换刀 国米旧貌换新颜 </a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://wanghe265.blog.163.com/blog/static/2455011620141023101914924/\" target=\"_blank\"></a> <a href=\"http://wanghe265.blog.163.com/blog/static/2455011620141023101914924/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img1.cache.netease.com/sports/2012/11/13/20121113173419fa54e.jpg\" alt=\"阿飞\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://qinzhiyun999.blog.163.com/\" target=\"_blank\" class=\"gray3\">阿飞：最新竞彩单场彩推荐</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://qinzhiyun999.blog.163.com/\" target=\"_blank\">全国知名足彩媒体《体坛导报·博彩》资深足彩专家，每日赛事推荐</a> <a href=\"http://qinzhiyun999.blog.163.com/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "       </div> \n" +
            "       <div class=\"expertsCon hide\" id=\"zc\"> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img2.cache.netease.com/sports/2014/5/17/201405171639198db24.jpg\" alt=\"翔宇\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://wishball442.blog.163.com/\" target=\"_blank\" class=\"gray3\">翔宇:最新竞彩单场彩推荐</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://wishball442.blog.163.com/\" target=\"_blank\">资深竞彩名家，足彩博客不定时更新，敬请彩民们关注!</a> <a href=\"http://wishball442.blog.163.com/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img6.cache.netease.com/sports/2013/9/26/20130926101425e674b.jpg\" alt=\"林达乐\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://lindaledino.blog.163.com/blog/static/2261831722015104102246976/#\" target=\"_blank\" class=\"gray3\">林达乐欧冠:拜仁回安联复仇枪手</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://lindaledino.blog.163.com/blog/static/2261831722015104102246976/#\" target=\"_blank\">摘要：拜仁往绩占优，枪手伤情加剧。</a> <a href=\"http://lindaledino.blog.163.com/blog/static/2261831722015104102246976/#\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "        <dl> \n" +
            "         <dt>\n" +
            "          <img src=\"http://img1.cache.netease.com/sports/2014/5/19/20140519145646bb61a.jpg\" alt=\"轩易玩彩\" title=\"\" width=\"48\" height=\"48\">\n" +
            "         </dt> \n" +
            "         <dd>\n" +
            "          <div>\n" +
            "           <a href=\"http://blog.163.com/xuanyicai/\" target=\"_blank\" class=\"gray3\">轩易玩彩:最新数字彩心水推荐</a>\n" +
            "          </div>\n" +
            "         </dd> \n" +
            "        </dl> \n" +
            "        <p><a href=\"http://blog.163.com/xuanyicai/\" target=\"_blank\">最新数字彩心水推荐</a> <a href=\"http://blog.163.com/xuanyicai/\" target=\"_blank\" class=\"c_ba2636\">[详细]</a></p> \n" +
            "       </div> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "     <span class=\"blank20\"></span> \n" +
            "     <div class=\"clearfix\"> \n" +
            "      <div class=\"right JS_NTES_LOG_FE\" data-module-name=\"xwwzy_34_appdownload\" style=\"margin-top:6px;\">\n" +
            "       <a href=\"http://caipiao.163.com/outside/getclient_cp.html\" title=\"下载网易彩票客户端\" id=\"bot_appdownload\" rel=\"nofollow\"><img src=\"http://pimg1.126.net/caipiao_info/img/articles/download.png\" alt=\"下载网易彩票客户端\" width=\"178\" height=\"24\"></a>\n" +
            "      </div> \n" +
            "      <div class=\"left ep-tie-simple JS_NTES_LOG_FE\" data-module-name=\"xwwzy_33_tieview2\">\n" +
            "       <a href=\"#\" class=\"js-tielink left\" rel=\"nofollow\"><span class=\"ep-tie-count js-tiecount\">0</span></a>\n" +
            "       <span class=\"left\">人参与</span>\n" +
            "      </div> \n" +
            "     </div> \n" +
            "     <span class=\"blank6\"></span> \n" +
            "     <span class=\"ep-parting\"></span> \n" +
            "     <div> ";

    private String s9 = "<script type=\"text/javascript\" language=\"javascript\">//<![CDATA[\n" +
            "var tieChannel = \"sports\",isStrict = true;\n" +
            "//]]></script> \n" +
            "      <div id=\"comment-jssdk-target\"></div> \n" +
            "      <script src=\"http://img1.cache.netease.com/f2e/products/tie/js/_tiesdk.jDtVnAgvZBZ1.6.js\"></script> \n" +
            "      <link rel=\"stylesheet\" type=\"text/css\" href=\"http://img1.cache.netease.com/f2e/products/tie/default.1180555.css\">\n" +
            "      <script>\n" +
            "\"use strict\";\n" +
            ";(function(){\n" +
            "        if ( window.isShowComments === undefined ) { window.isShowComments = true; }\n" +
            "        if (typeof isForceShowComment != \"undefined\") {\n" +
            "            isShowComments = isForceShowComment;\n" +
            "        };\n" +
            "\tvar config = {\n" +
            "\t\t\"productKey\" : \"a2869674571f77b5a0867c3d71db5856\",\n" +
            "\t\t\"docId\" :  \"CMQQL37000052DT2\",\n" +
            "\t\t\"target\" : document.getElementById(\"comment-jssdk-target\"),\n" +
            "\t\t\"operators\": [\"up\", \"down\", \"reply\", \"share\"],\n" +
            "\t\t\"showPaging\": false,\n" +
            "                \"isShowComments\": isShowComments,\n" +
            "\t\t\"hotSize\": 3,\n" +
            "\t\t\"newSize\": 10,\n" +
            "                \"submitType\": \"commentPage\"\n" +
            "\t};\n" +
            "\tTie.init(config, function(data) {\n" +
            "\t    Tie.api.buildPage();\n" +
            "\t});\n" +
            "})();\n" +
            "</script> \n" +
            "     </div> \n" +
            "    </div> \n" +
            "    <div class=\"ep-content-side\" id=\"epContentRight\"> \n" +
            "     <div id=\"ifCon\"> \n" +
            "      <!-- iframe --> \n" +
            "      <iframe frameborder=\"0\" width=\"329\" height=\"607\" scrolling=\"no\" src=\"http://zx.caipiao.163.com/zx/extraInfo.html?gameEn=\"></iframe> \n" +
            "      <div id=\"rOther\"> \n" +
            "       <div class=\"zhuanti\"> \n" +
            "        <div class=\"ep-title-2 clearfix\">\n" +
            "         <h2 class=\"title\">推荐专题</h2>\n" +
            "        </div> \n" +
            "        <div class=\"bigImg\"> \n" +
            "         <a href=\"http://caipiao.163.com/nfop/2015xijia/index.htm?529d1\"><img src=\"http://img1.cache.netease.com/sports/2015/8/21/201508211646255a282.jpg\" height=\"90\"></a> \n" +
            "        </div> \n" +
            "        <ul class=\"mod-f14list\"> \n" +
            "         <li><a href=\"http://caipiao.163.com/nfop/2015xijia/index.htm?529d1\">15-16赛季西甲投注全攻略</a> <a target=\"_blank\" href=\"http://caipiao.163.com/nfop/2015dejia/index.htm?26098\">德甲</a> <a target=\"_blank\" href=\"http://caipiao.163.com/nfop/2015yingchaoqianzhan/index.htm\">英超</a> <a target=\"_blank\" href=\"http://caipiao.163.com/nfop/2015yijia/index.htm?5a93e\">意甲</a></li> \n" +
            "         <li><a href=\"http://zx.caipiao.163.com/shahao/ssq/red_20.html\">双色球网易专家杀号</a> <a target=\"_blank\" href=\"http://zx.caipiao.163.com/shahao/dlt/front_50.html\">大乐透网易专家杀号</a></li> \n" +
            "         <li><a href=\"http://zx.caipiao.163.com/shahao/3d/zu_30.html\">福彩3D网易专家杀号</a> <a target=\"_blank\" href=\"http://zx.caipiao.163.com/shahao/cqssc/wanwei_20.html\">重庆时时彩专家杀号</a></li> \n" +
            "         <li><a href=\"http://zx.caipiao.163.com/shahao/gd11x5/first1_20.html\">广东11选5专家杀号</a> <a target=\"_blank\" href=\"http://zx.caipiao.163.com/shahao/jx11x5/first1_20.html\">江西11选5专家杀号</a></li> \n" +
            "         <li><a href=\"http://cai.163.com/article/special/11x5gaopin/\">网易彩票商学院之高频攻略:快速易中 玩赚11选5</a></li> \n" +
            "        </ul> \n" +
            "       </div> \n" +
            "       <div class=\"img310\"> \n" +
            "       </div> \n" +
            "      </div> \n" +
            "     </div> \n" +
            "    </div> \n" +
            "   </div> \n" +
            "   <span class=\"blank20\"></span> \n" +
            "   <script type=\"text/javascript\" src=\"http://img3.cache.netease.com/common/share/yixin/b02/yixin.min.js\"></script>";

    @Override
    protected int initLayoutId() {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    @Override
    protected void initData() {
        newsData = getIntent().getParcelableExtra("news_data");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(newsData.getUrl()).get();
                    html = doc.toString();
                    html = html.replace(s1, "");
                    html = html.replace(s2, "");
                    html = html.replace(s3, "");
                    html = html.replace(s4, "");
                    html = html.replace(s5, "");
                    html = html.replace(s6, "").replace(s7, "").replace(s8, "");
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initWebView();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initWebView() {
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                settings.setBlockNetworkImage(false);
            }
        });
//        mWebView.loadUrl(newsData.getUrl());
        mWebView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
    }

    private void initToolbar() {
        String desc = newsData.getTitle();
        desc = desc.length() > 10 ? desc.substring(0, 16) + "..." : desc;
        mToolbar.setTitle(desc);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }
}
