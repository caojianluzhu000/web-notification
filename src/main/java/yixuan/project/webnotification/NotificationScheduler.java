package yixuan.project.webnotification;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * scheduled to run recursively.
 */
@Component
public class NotificationScheduler {

    private static final Logger log = LoggerFactory.getLogger(NotificationScheduler.class);

    private static final String SEARCHING_CONTENT = "护照";
//    private static final String SEARCHING_CONTENT = "哥伦布";

    @Autowired
    private EmailSender emailSender;

    @Scheduled(cron="*/5 * * * * ?")
    public void hitPage(){
        try {
            Document doc = Jsoup.connect("http://newyork.china-consulate.org/chn/fwzc/zxtz/").get();
            Elements newsHeadlines = doc.select("div.cbox_ul>ul>li>a");
            String latestNews = newsHeadlines.get(0).text();
            if(latestNews.contains(SEARCHING_CONTENT)){
                emailSender.sendEmail();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
