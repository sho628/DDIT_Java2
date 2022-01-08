package kr.or.ddit.basic;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class T04_XPathParsing {
/*
    XPath 에 대하여...
    
     자바에서 내장 패키지(javax.xml.xpath)로 제공하는 라이브러리로 XML형식의 웹문서,
     파일, 문자열을 파싱하는데사용함.
   XPath는 XML문서 속에서 노드를 찾기 위해 패턴식(pattern expression)을 사용함.
   
   사용 예)
   
   /item				: "/"루트 노드의 자식 노드중에서 <item>엘리먼트 선택
   /item/item2			: <item>엘리먼트의 자식 노드 중에서 <item2>엘리먼트 선택
   //					: 현재 노드의 위치와 상관없이 지정된 노드부터 탐색
   //item				: 위치와 상관없이 <item>인 모든 엘리먼트 탐색
   //item/@id			: 모든 <item>엘리먼트의 id속성 노드를  모두 선택함
   //item[k]			: <item>엘리먼트 중에서 k번째 <item>엘리먼트 선택
   //item[price > 1000]	: price엘리먼트값이 1000보다 큰 <item>엘리먼트선택
   //item[@attr = val]	: attr속성값이 val인 모든 <item>엘리먼트 선택			  
*/
	public void parse() {
		try {
			//File file = new File(getClass()
			//		.getResource("./new_book.xml").getPath());
			File file = new File("./src/kr/or/ddit/basic/new_book.xml");
			FileReader fileReader = new FileReader(file);
			
			// XML Document 객체 생성
			InputSource is = new InputSource(fileReader);
			Document document = DocumentBuilderFactory
								.newInstance()
								.newDocumentBuilder()
								.parse(is);
			
			// XPath 객체 생성하기
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			// booklist 하위의 book 노드 중에서 price엘리먼트값이 25000 이상인
			// 모든 노드 가져오기
			NodeList bookList = (NodeList) xPath.evaluate(
					"/root/booklist/book[price > 25000]", 
					document, XPathConstants.NODESET);
			
			System.out.println("/root/booklist/book[price > 25000] 결과...");
			System.out.println("-------------------------------");
			for(int i = 0; i < bookList.getLength(); i++) {
				System.out.println(bookList.item(i).getTextContent());
			}
			System.out.println("-------------------------------");
			
			// kind 속성값이 JAVA인 Node의 isbn속성값 가져오기
			Node node = (Node) xPath.evaluate(
					"//*[@kind='JAVA']", 
					document, XPathConstants.NODE);
			System.out.println(node.getAttributes()
					.getNamedItem("isbn").getTextContent());
			
			// isbn 속성값이 B001인  Node의 textContent값 가져오기
			System.out.println(
					xPath.evaluate(
							"//*[@isbn='B001']", 
							document, XPathConstants.STRING)
					);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new T04_XPathParsing().parse();
		
		
	}
}


