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
        Elements elements = document.select("ul[class=clearfix] > li");
        List<NewsData> list = new ArrayList<>();
        NewsData data;
        for (Element element : elements) {
            data = new NewsData();
            data.setUrl(element.select("span > a").attr("href"));
            data.setTitle(element.select("span > a").text());
            data.setTime(element.select("em").get(0).text());
            list.add(data);
        }

        return list;
    }
}
