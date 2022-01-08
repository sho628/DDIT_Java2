package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpFileReceiver {
	public static void main(String[] args) throws IOException {
		
		int port = 8888;
		
		long fileSize = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[1000];
		int readBytes = 0;
		
		System.out.println("파일 수신 대기 중...");
		
		DatagramSocket ds = new DatagramSocket(port);
		
		FileOutputStream fos = null;
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp); // 전송시작 문자열(start) 받기
		
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) { // Sender에서 start를 보낸 경우...
			buffer = new byte[1000]; // 초기화
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp); // 전송 파일명 받기
			str = new String(dp.getData()).trim();
			fos = new FileOutputStream("d:/C_Lib/" + str);
			
			buffer = new byte[1000]; // 초기화
			dp = new DatagramPacket(buffer, buffer.length);
			ds.receive(dp); // 전송파일 크기(bytes) 받기
			str = new String(dp.getData()).trim();
			fileSize = Long.parseLong(str);
			
			long startTime = System.currentTimeMillis();
			
			while(true) {
				ds.receive(dp);
				readBytes = dp.getLength();
				fos.write(dp.getData(), 0, readBytes);
				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes + "/"
						+ fileSize + "Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + "%)");
				
				if(totalReadBytes >= fileSize) { // 다 받았으면...
					break;
				}
			}
			
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;
			
			System.out.println("걸린 시간 : " + diffTime + "(ms)");
			System.out.println("평균 수신속도 : " + transferSpeed 
								+ "Bytes/ms");
			System.out.println("수신 완료...");
			
			fos.close();
			ds.close();
			
			
			
		}
		
		
		
		
	}
}
