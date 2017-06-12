package app.lottery.com.lotteryapp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import app.lottery.com.lotteryapp.data.NewsData;

/**
 * Author: Othershe
 * Time: 2016/8/17 10:01
 */
public class JsoupUtil {
    /**
     * 解析每一页妹子的数据
     *
     * @param response
     * @return
     */
    public static List<NewsData> parseNews(String response) {
        Document document = Jsoup.parse(response);
        Elements elements = document.select("div[class=img_single] > a");
        List<NewsData> list = new ArrayList<>();
        NewsData data;
        for (Element element : elements) {
            data = new NewsData();
//            data.setId(element.attr("href").substring(element.attr("href").lastIndexOf("/") + 1));
//            data.setTitle(element.select("img").attr("title"));
//            data.setUrl(element.select("img").attr("src"));
//            list.add(data);
        }

        return list;
    }
}
