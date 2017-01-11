package insighteye.zz.am.main;

import org.apache.http.HttpResponse;

import insighteye.zz.am.manager.CrawlManager;
import insighteye.zz.am.manager.DBmanager;
import insighteye.zz.am.manager.HttpManager;

public class Launch {
	public static void main(String[] args) throws Exception {

		HttpManager hm = new HttpManager("http://www.naver.com");
//		HttpResponse hr = hm.sendRequest();
		DBmanager dbm = new DBmanager();
//		hm.getDOM(hr.getEntity());
		
		CrawlManager crm = new CrawlManager(dbm, hm);
		crm.startCrawl();

//		new Thread() {
//			public void run() {
//				HttpManager hm = new HttpManager("http://news.joins.com/article/20795335");
//				HttpResponse hr = hm.sendRequest();
//				Parser parser = new Parser();
//				hm.getDOM(hr.getEntity());
//			};
//		}.start();
//
//		new Thread() {
//			public void run() {
//				HttpManager hm = new HttpManager("http://news.joins.com/article/20795348");
//				HttpResponse hr = hm.sendRequest();
//				Parser parser = new Parser();
//				parser.setDOM( hm.getDOM(hr.getEntity()) );				
//				//String src = hm.getDOM(hr.getEntity());				
//			};
//		}.start();
//
//		new Thread() {
//			public void run() {
//				HttpManager hm = new HttpManager("http://news.joins.com/article/20795343");
//				HttpResponse hr = hm.sendRequest();
//				Parser parser = new Parser();
//				parser.setDOM( hm.getDOM(hr.getEntity()) );				
//				//String src = hm.getDOM(hr.getEntity());
//			};
//		}.start();

	}
}
