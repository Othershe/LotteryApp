package app.lottery.com.lotteryapp.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.lottery.com.lotteryapp.data.NewsData;
import app.lottery.com.lotteryapp.data.ResultData;


public class JsoupUtil {
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

    public static List<ResultData> parseResults(String response) {

        Document document = Jsoup.parse(response);
        Elements elements = document.select("table[class=historylist] > tbody > tr");
        List<ResultData> list = new ArrayList<>();
        ResultData data;
        for (Element element : elements) {
            data = new ResultData();
            data.setNum(element.select("td > a").text());
            data.setDate(element.select("td").get(1).text());
            Elements ems = element.select("td[class=balls] em");
            StringBuilder sb = new StringBuilder();
            for (Element em : ems) {
                sb.append(em.text());
            }
            data.setBalls(sb.toString());
            list.add(data);
        }

        return list;
    }

//    public static List<NewsData> parseNews(String response) {
//        Document document = Jsoup.parse(response);
//        Elements elements = document.select("li[class=newsItem] > a");
//        List<NewsData> list = new ArrayList<>();
//        NewsData data;
//        for (Element element : elements) {
//            String time = element.select("span[class=newsCtn] > i").text();
//            if (!"推广".equals(time)) {
//                data = new NewsData();
//                data.setIconUrl(element.select("span[class=newsImg] > img").attr("src"));
//                data.setUrl(element.attr("href"));
//                data.setTitle(element.select("span[class=newsCtn] > h2").text());
//                data.setDes(element.select("span[class=newsCtn] > p").text());
//                data.setTime(time);
//                list.add(data);
//            }
//        }
//
//        return list;
//    }
}
