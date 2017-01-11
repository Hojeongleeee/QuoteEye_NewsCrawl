package insighteye.zz.am.parser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import insighteye.zz.am.item.Item;
import insighteye.zz.am.manager.CrawlManager;
import insighteye.zz.am.manager.DBmanager;

/* Class 생성일 : 2016-11-16
 * Class 작성자 : 곽민석
 * Class 용도 : 중앙일보 HTML DOM 파싱
 * 
 */
public class JoseonParser extends Parser {
	private String dom;
	private Jsoup js;
	private DBmanager dbm;
	private Item item;
	public JoseonParser(String _dom, DBmanager _dbm) throws Exception {
		super();
		dom = _dom; //DOM Tree 저장
		dbm = _dbm; //공용 DB Manager 할당
		this.item = doparse();
	}
	
	private Item doparse() throws UnsupportedEncodingException{  // override
		/* 파싱 본체
		 * title / contents / section1 / section2 / date / publisher / url
		 */
		Item item = new Item();
		// Jsoup Syntax 
		Document doc = js.parse(URLEncoder.encode(dom, "UTF-8").toString());
		
		
		//---------인코딩문제있음!
		item.title = doc.select("#news_title_text_id").text().toString(); //title
		item.contents = doc.select("#news_body_id").text().toString(); //contents
		
		//Section 가져오는데에 문제있음! (분류가 3개?)
		int first = item.title.toString().indexOf(">");
		int last = item.title.toString().lastIndexOf(">");
		item.section1 = doc.select("title").toString(); //section1: title에서 
		item.section2 = "?";
//		item.date = doc.select("#date_text").text().substring(3,4).replace(".", "-");
//		item.date = doc.select(".news_date#date_text").text().toString();
		item.publisher = "Joseon";//publisher
//		item.url = doc.select("meta[property]").get(3).attr("content").toString(); //url
		
		System.out.println(item.title);
		System.out.println(item.contents);
//		System.out.println(item.section1);
//		System.out.println(item.section2);
//		System.out.println(item.date);
//		System.out.println(item.publisher);
//		System.out.println(item.url);
		
		return item;
	}
	
	public Item getItem(){
		return item;
	}
}
