package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
/*
   서버는 클라이언트가 접속하면 서버 컴퓨터의 D:/D_Other폴더에 있는 파일을
   클라이언트로 전송한다. 
*/
	private ServerSocket server;
	private Socket socket;
	private OutputStream out;
	private FileInputStream fis;
	private File file = new File("D:/D_Other/사자.jpg");
	
	// 서버 시작
	public void serverStart() {
		while(true) {
			try {
				server = new ServerSocket(7777);
				System.out.println("서버 준비 완료...");
				
				socket = server.accept();
				System.out.println("파일 전송 시작...");
				
				fis = new FileInputStream(file);
				out = socket.getOutputStream();
				
				BufferedInputStream bis = 
						new BufferedInputStream(fis);
				BufferedOutputStream bos =
						new BufferedOutputStream(out);
				
				int c = 0;
				while((c = bis.read()) != -1) {
					bos.write(c);
				}
				bis.close();
				bos.close();
				
				System.out.println("파일 전송 완료...");
				
			}catch(IOException ex) {
				ex.printStackTrace();
			}finally {
				if(fis != null) {
					try {fis.close();}catch(IOException ex) {}
				}
				if(out != null) {
					try {out.close();}catch(IOException ex) {}
				}
				if(socket != null) {
					try {socket.close();}catch(IOException ex) {}
				}
				if(server != null) {
					try {server.close();}catch(IOException ex) {}
				}
			}
			
			
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
	
	
	
}
