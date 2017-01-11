/* Class 생성일 : 2016-11-16
 * Class 작성자 : 곽민석
 * Class 용도 : Apache http library wrapper
 * Known Issues
 * > 일부 사이트 DOM retrieve 실패 - 원인 불명
 * > 작동 시간 (단위 millisec)
 * sendRequest> 작동 시간 : 115 
 * getDOM> 작동 시간 : 15
 * sendRequest 소요 시간이 제일 큼
 * > 다중 스레딩 시 속도 계산 해볼 것
 * sendRequest> 작동 시간 : 145
 * sendRequest> 작동 시간 : 145
 * sendRequest> 작동 시간 : 145
 * getDOM> 작동 시간 : 15
 * getDOM> 작동 시간 : 16
 * getDOM> 작동 시간 : 16
 * 약 1.2배의 시간, 3배의 산출
 * 
 */

package insighteye.zz.am.manager;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpManager {
	
	private HttpClient httpClient; //http 연결 등을 수행할 클라이언트 클래스
	private String url; //접속 시도할 URL
	private HttpPost httpPost; //POST 방식 request를 위한 클래스
	private ContentType encode;
	
	public HttpManager() { //Constructor
		httpClient = HttpClientBuilder.create().build(); //클라이언트 객체 생성
	}
	
	public HttpManager(String _url) { //Constructor
		httpClient = HttpClientBuilder.create().build(); //클라이언트 객체 생성
		url = _url; //URL 저장
		httpPost = new HttpPost(url); //요청 URL 지정
	}
	
	public void setURL(String _url) {
		url = _url; //URL 저장
		httpPost = new HttpPost(url); //요청 URL 지정
	}
	
	public String getURL() { //이전 요청 URL 반환
		return url;
	}
	
	public HttpResponse sendRequest() {
		long start = System.currentTimeMillis(); //시작시간
		HttpResponse httpResponse = null; //http Request에 대한 Response 저장
		try {
			httpResponse = httpClient.execute(httpPost); // POST 요청 전송 및 응답 수신
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//해당 과정에서 HTML 태그를 가져오게 됨
			printTime("sendRequest", start, System.currentTimeMillis()); //단위 테스트
		}
		return httpResponse; //Response 반환
	}
	
	public String getDOM(HttpEntity _entity) { //HttpEntity : DOM 객체 저장
		long start = System.currentTimeMillis();
		String TAG = "getDOM";
		byte[] buffer = new byte[4096]; //InputStream으로부터 byte 배열을 읽어올 버퍼
		int len; //읽어온 길이 저장
		StringBuffer sb = new StringBuffer(); //String append에 사용할 버퍼
		encode = ContentType.getOrDefault(_entity); //인코딩 형식 (캐릭터셋)
		try {
			InputStream is = _entity.getContent(); //InputStream 제어를 통해 원하는 부분 부터 읽어오거나, 전부 읽어온 후 처리
			while((len = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, len)); //buffer를 len 만큼 String으로 만들어 StringBuffer에 더함
			}
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printTime(TAG, start, System.currentTimeMillis());
		
		return sb.toString();
		
	}
	
	private void printTime(String _TAG, long _start, long _end) {
		System.out.println(_TAG + "> 작동 시간 : " + (_end - _start));
	}
}

