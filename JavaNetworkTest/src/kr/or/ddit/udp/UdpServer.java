package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {
	// 패킷 송수신을 위한 객체변수 선언
	private DatagramSocket socket;
	
	// 시작
	public void start() throws IOException {
		
		// 포트번호 8888번을 사용하여 수신하는 소켓을 생성한다.
		socket = new DatagramSocket(8888);
		
		DatagramPacket inPacket, outPacket; // 패킷정보담기위한 변수 선언
		
		 byte[] inMsg = new byte[1]; // 패킷수신을 위한 바이트배열 선언
		 byte[] outMsg;				 // 패킷송신을 위한 바이트 배열 선언
		 
		 while(true) {
			 // 데이터를  수신하기 위한 패킷을 생성한다.
			 inPacket = new DatagramPacket(inMsg, inMsg.length);
			 
			 System.out.println("패킷 수신 대기중...");
			 
			 // 패킷을 통해 데이터를 수신(receive)한다.
			 socket.receive(inPacket);
			 
			 System.out.println("패킷 수신 완료...");
			 
			 // 수신한 패킷으로 부터 client의 IP주소와 Port번호를 얻는다.
			 InetAddress address = inPacket.getAddress();
			 int port = inPacket.getPort();
			 
			 // 서버의 현재 시간은 시분초 형태([hh:mm:ss])로 반환한다.
			 SimpleDateFormat sdf = 
					 new SimpleDateFormat("[hh:mm:ss]");
			 String time = sdf.format(new Date());
			 outMsg = time.getBytes(); // 문자열을 byte배열로 변환한다.
			 
			 // 패킷을 생성해서 client에게  전송(send)한다.
			 outPacket = 
					 new DatagramPacket(
							 outMsg, outMsg.length, address, port);
			 socket.send(outPacket); // 전송
		 }
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}
