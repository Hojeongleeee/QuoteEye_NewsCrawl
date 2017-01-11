package insighteye.zz.am.parser;

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
public class HaniParser extends Parser {
	private String dom;
	private Jsoup js;
	private DBmanager dbm;
	private Item item;
	public HaniParser(String _dom, DBmanager _dbm) {
		super();
		dom = _dom; //DOM Tree 저장
		dbm = _dbm; //공용 DB Manager 할당
		this.item = doparse();
	}
	
	private Item doparse(){  // override
		/* 파싱 본체
		 * title / contents / section1 / section2 / date / publisher / url
		 */
		Item item = new Item();
		// Jsoup Syntax 
		Document doc = js.parse(dom);
		item.title = doc.select("#article_title").text().toString(); //title
		item.contents = doc.select("#article_body").text().toString(); //contents
		item.section1 = doc.select("meta[property]").get(13).attr("content").toString(); //section1
		item.section2 = doc.select("meta[property]").get(14).attr("content").toString(); //section2
		item.date = doc.select("meta[property]").get(16).attr("content").toString().substring(0, 10); //date
		item.publisher = "중앙일보";//publisher
		item.url = doc.select("meta[property]").get(9).attr("content").toString(); //url
		return item;
	}
	
	public Item getItem(){
		return item;
	}
}
