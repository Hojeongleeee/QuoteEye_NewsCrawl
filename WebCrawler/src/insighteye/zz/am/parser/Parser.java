package insighteye.zz.am.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import insighteye.zz.am.item.Item;
import insighteye.zz.am.manager.DBmanager;

/* Class 생성일 : 2016-11-16
 * Class 작성자 : 곽민석
 * Class 용도 : 공용 HTML DOM 파싱, 상속용
 * 각 언론사 별로 DOM Tree 파싱 메소드 구현
 * 정규화된 형식은
 * 제목 / 내용 / 분야1 / 분야2 / 날짜 / 언론사 / url
 * title / contents / section1 / section2 / date / publisher / url
 * contents는 저작권의 문제가 있을 수 있으므로, 현재는 저장하지만 이 후에는 연산과정을 도중에 포함할 예정
 */
public class Parser extends Thread {
	private String dom;
	private insighteye.zz.am.manager.DBmanager dbm;
	private Jsoup js;
	private int index = 0;
	//언론사 식별용, 추후 정수를 사용하여 정의된 숫자로 구분해도 됨	
	public Parser() {
		//초기화
		//TODO: 언론사 식별 구조 코딩
		index = 1; //##연습: 중앙일보로 가정
	}
	
	public void setDOM (String _dom) {
		dom = _dom; //DOM 트리 저장
	}

	public Item doParse() {
		// 각 언론사 별 override
		// 실제 파싱 부분
		
		return null;
	}
	
	public String getDOM(String _url){
		return dom;
	}

}
