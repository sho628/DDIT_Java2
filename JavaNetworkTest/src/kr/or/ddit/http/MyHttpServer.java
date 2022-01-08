package kr.or.ddit.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.util.StringTokenizer;

/**
 * 간단한 웹서버 예제
 */
public class MyHttpServer {
	private final int port = 80;
	private final String encoding = "UTF-8";
	
	
	// Http서버 시작...
	public void start() {
		System.out.println("HTTP 서버가 시작되었습니다.");
		try(ServerSocket server = new ServerSocket(this.port)){
			
			while(true) {
				try {
					Socket socket = server.accept();
					
					// Http핸들러를 이용한 요청 처리하기
					HttpHandler handler = new HttpHandler(socket);
					new Thread(handler).start(); 
					
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private class HttpHandler implements Runnable {
		private final Socket socket;
		
		public HttpHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			
			OutputStream out = null;
			BufferedReader br = null;
			
			try {
				out = new BufferedOutputStream(
						socket.getOutputStream());
				br = new BufferedReader(
						new InputStreamReader(
							socket.getInputStream()));
				
				// 요청헤더 정보 파싱하기
				StringBuilder request = new StringBuilder();
				while(true) {
					String str = br.readLine();
					
					if(str.equals("")) break; // Empty Line 체크
					
					request.append(str + "\n");
				}
				System.out.println("요청헤더:\n" + request.toString());
				System.out.println("-------------------------------");
				
				String reqPath = "";
				
				// 요청 페이지 정보 가져오기
				StringTokenizer st = 
						new StringTokenizer(request.toString());
				while(st.hasMoreTokens()) {
					String token = st.nextToken();
					if(token.startsWith("/")) {
						reqPath = token;
					}
				}
				
				// 요청 파일 경로 정보
				String filePath = "./WebContent" + reqPath;
				
				//  해당 파일이름을 이용하여 Content-Type 정보 추출하기
				String contentType = URLConnection.getFileNameMap()
									.getContentTypeFor(filePath);
				
				// css파일인 경우 인식이  안되서 추가함.
				if(contentType == null 
					&& filePath.endsWith(".css")) {
					contentType = "text/css";
				}
				System.out.println("Content-Type => " + contentType);
				
				File file = new File(filePath);
				if(!file.exists()) {
					makeErrorPage(out, 404, "Not Found");
					return;
				}
				
				byte[] body = makeResponseBody(filePath);
				
				byte[] header = 
					makeResponseHeader(body.length, contentType);
				
				// 요청 헤더가 HTTP/1.0이나 그 이후의 버전을 지원할 경우 MIME헤더를
				// 전송한다.
				if(request.toString().indexOf("HTTP/") != -1) {
					out.write(header); // 응답헤더 보내기
				}
				
				out.write(body); // 응답 내용 보내기
				out.flush();
				
			}catch(IOException ex) {
				System.err.println("입출력 오류!!!");
			}finally {
				try {
					socket.close(); // 소켓 닫기(연결 끊기)
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
			
		}
	}
	
	/**
	 * 응답 헤더 생성하기
	 * @param length 응답내용 크기
	 * @param contentType 마임타입
	 * @return 바이트 배열
	 */
	private byte[] makeResponseHeader(int length, 
										String contentType) {
		String header = "HTTP/1.1 200 OK\r\n"
				+ "Server: MyHTTPServer 1.0\r\n"
				+ "Content-length: " + length + "\r\n"
				+ "Content-type: " + contentType + "; charset="
				+ this.encoding + "\r\n\r\n";
		
		return header.getBytes();
	}
	
	/**
	 * 응답 내용 생성
	 * @param filePath 응답으로 사용할 파일 경로
	 * @return 바이트 배열 데이터
	 */
	private byte[] makeResponseBody(String filePath) {
		
		FileInputStream fis = null;
		byte[] data = null;
		
		try {
			File file = new File(filePath);
			data = new byte[(int)file.length()];
			
			fis = new FileInputStream(file);
			fis.read(data);
			
		}catch(IOException ex) {
			System.err.println("입출력 오류!!!");
			ex.printStackTrace();
		}finally {
			if(fis != null) {
				try {fis.close();}catch(IOException ex) {}
			}
		}
		return data;
	}
	
	/**
	 * 에러페이지 생성
	 * @param out 출력스트림 객체
	 * @param statusCode 상태 코드
	 * @param errMsg 에러 메시지
	 */
	private void makeErrorPage(OutputStream out, 
			int statusCode, String errMsg) {
		String statusLine = "HTTP/1.1" + " " + statusCode
				           + " " + errMsg;
		try {
			out.write(statusLine.getBytes());
			out.flush();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MyHttpServer().start();
	}
}
