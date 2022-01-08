package kr.or.ddit.basic;

import java.io.File;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class T03_DOMParsingExam {
	public void parse() {
		try {
			
			// DOM document 객체 생성하기 위한 메서드
			DocumentBuilderFactory dbf = 
					DocumentBuilderFactory.newInstance();
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
			String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
			String startIdx = "1";	// 레시피 재료 시작 순번
			String endIdx = "5";	// 레시피 재료 마지막 순번
			String recipeId = "449";	// 레시피가 궁금한 음식ID
			
			URL url = new URL("http://211.237.50.150:7080/openapi/"
					+ apiKey +"/xml/" + svcKey + "/" + startIdx
					+ "/" + endIdx + "?RECIPE_ID=" + recipeId);
			
			//System.out.println(url.toString());
			
			// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
			Document document = builder.parse(url.toString());
			
			// DOM Document 객체로부터 루트 엘리먼트 가져오기
			Element root = document.getDocumentElement();
			//System.out.println("루트 엘리먼트 태그명: " 
			//					+ root.getTagName());
			
			
			String code = root.getElementsByTagName("code")
								.item(0).getTextContent();
			String totalCnt = root.getElementsByTagName("totalCnt")
								.item(0).getTextContent();
			
			endIdx = totalCnt;
			
			url = new URL("http://211.237.50.150:7080/openapi/"
					+ apiKey +"/xml/" + svcKey + "/" + startIdx
					+ "/" + endIdx + "?RECIPE_ID=" + recipeId);
			
			document = builder.parse(url.toString());
			
			root = document.getDocumentElement();
			
			// 하위 엘리먼트 접근하기
			NodeList rowNodeList = root.getElementsByTagName("row");
			
			if(code.equals("INFO-000")){
				
				for(int i = 0; i < rowNodeList.getLength(); i++) {
					Element element = (Element) rowNodeList.item(i);
					String rowNum = element.getElementsByTagName("ROW_NUM")
							.item(0).getTextContent();
					String irndNm = element.getElementsByTagName("IRDNT_NM")
							.item(0).getTextContent();
					String irdntCpcty = 
							element.getElementsByTagName("IRDNT_CPCTY")
							.item(0).getTextContent();
					String irdntTyNm = 
							element.getElementsByTagName("IRDNT_TY_NM")
							.item(0).getTextContent();
					String str = String.format("%3s %8s %10s %10s %8s", 
							rowNum, recipeId, irdntTyNm, irndNm, irdntCpcty);
					System.out.println(str);
					System.out.println("-------------------------------------");
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new T03_DOMParsingExam().parse();
	}
}
