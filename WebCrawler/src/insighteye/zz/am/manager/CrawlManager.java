package insighteye.zz.am.manager;

import insighteye.zz.am.item.Item;
import insighteye.zz.am.parser.JoongangParser;
import insighteye.zz.am.parser.JoseonParser;
import insighteye.zz.am.parser.Parser;
/* Class 생성일 : 2016-11-17
 * Class 작성자 : 곽민석
 * Class 용도 : 언론사 별 Crawler 매니저, 상속용
 * 각 언론사 별로 파싱시 생성되는 스레드 관리
 * 파서의 배열을 사용하여, 사전 정의된 개수만큼의 스레드를 생성하여 관리
 * 최근 저장한 기사의 인덱스를 저장하여, 그 인덱스를 기준으로 기사 수집 시작
 */
public class CrawlManager {
	final int MAX_THREAD = 30; //다중 쓰레딩 최대 개수 할당
	final int MAX_ITEMS = 30; //기사 정보 아이템 최대 개수 할당
	private DBmanager dbm; //데이터 베이스 매니저 할당
	private HttpManager hm;
	private Parser[] arr_thread;
	private Item[] items;
	private int item_num = 0; //TODO: items 배열의 index설정 필요
	
	private String base_url; //언론사별 접근 기반 url
	private String idx;//기사 URL index로 사용될 정수 혹은 형식 언론사 별 정의
	
	
	public CrawlManager(DBmanager _dbm, HttpManager _hm) {
		dbm = _dbm; // 공용 DB Manager 할당
		hm = _hm; // Http Manager 할당
		arr_thread = new Parser[MAX_THREAD]; // 쓰레드 할당
		items = new Item[MAX_ITEMS]; // Item 할당
		idx = getIndex(); //Index 획득
	}
	
	public void startCrawl() throws Exception {
		//크롤링 시작
		//언론사별 정의된 파서 배열을 통해 파싱 시작
		/* 루프 진행, 종료 할 상황에 대해 정의할 것 (기존 : 에러 발생 횟수 누적)
		 * 1. HttpManager를 이용해 주소 접근
		 * 2. DOM Tree 획득한 후 Parser에 할당
		 * 3. 파싱
		 * 4. 파싱 결과 Item에 저장
		 * 5. DB에 Save
		 * 
		 */

		//중앙일보
		base_url="http://news.joins.com/article/"+idx; //TODO: DB로부터 getIndex()및 URL변경
		hm.setURL(base_url);
		JoongangParser jnparser = new JoongangParser(hm.getDOM(hm.sendRequest().getEntity()), dbm);
		items[item_num] = jnparser.getItem();

		System.out.println("제목\t"+items[item_num].title);
		System.out.println("URL\t"+items[item_num].url);
		System.out.println("날짜\t"+items[item_num].date);
		System.out.println("내용\t"+items[item_num].contents);
		System.out.println("언론사\t"+items[item_num].publisher);
		System.out.println("분류1\t"+items[item_num].section1);
		System.out.println("분류2\t"+items[item_num].section2);
		item_num++; //items 배열의 index 1 증가


//		//조선일보
//		base_url="http://news.chosun.com/site/data/html_dir/2016/07/16/2016071601039.html";
//		hm.setURL(base_url);
//		JoseonParser jsparser = new JoseonParser(hm.getDOM(hm.sendRequest().getEntity()), dbm);
//		items[item_num] = jsparser.getItem();

		//기타 언론사도 각각 여기다가 구현?
		
		//쿼리 * TODO: 작은따옴표가 포함된 데이터를 다룰 수 있는 쿼리문 필요
		//String query = "INSERT INTO article (Date,Publisher,Title,Section1,Section2,Contents,URL) VALUES ('"+items[item_num].date+"','"+items[item_num].publisher+"','"+items[item_num].title.replace("'", "''")+"','"+items[item_num].section1+"','"+items[item_num].section2+"','"+items[item_num].contents.replace("'", "''")+"','"+items[item_num].url+"')";
		//items[item_num].available = dbm.saveDB(query);
	}
	
	private String getIndex() {
		//DB에서 index 획득
		String index ="20795335";
		//DB 접근 부
		return index;
	}
}
