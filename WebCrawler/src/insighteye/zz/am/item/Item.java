package insighteye.zz.am.item;

import java.util.ArrayList;

/* Class 생성일 : 2016-11-17
 * Class 작성자 : 곽민석
 * Class 용도 : 기사 정보 메모리 상 상주용 클래스
 * 언론사별 마이닝 된 기사를 정규화하여 프로그램에서 상주
 * 데이터 베이스에 저장할 때까지 사용
 * 파싱 후 데이터 저장 시 available = false
 * 데이터 베이스에 저장 후 available = true
 */
public class Item {
	public String title;
	public String contents;
	public String section1;
	public String section2;
	public String date;
	public String url;
	public String publisher;
	public Boolean available = true;
	
	
	public void setItem(String title, String contents, String section1, String section2, String date, String url, String publisher){
		available = false;
		this.title=title;
		this.contents=contents;
		this.section1=section1;
		this.section2=section2;
		this.date=date;
		this.url=url;
		this.publisher=publisher;
	
	}	
	
	public void updateDB(){
		
	}
}
