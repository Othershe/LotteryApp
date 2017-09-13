package com.mylottery.appmain.utils;

import com.mylottery.appmain.data.NewsData;
import com.mylottery.appmain.data.ResultData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


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
        Elements elements = document.select("table[class=kjpage] > tbody > tr");
        List<ResultData> list = new ArrayList<>();
        ResultData data;

        for (int i = 1; i < elements.size(); i += 2) {
            data = new ResultData();

            data.setNum(elements.get(i).select("td > strong").text());
            Elements ems = elements.get(i).select("td > div");
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
