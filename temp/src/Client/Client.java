package Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Vector;

import share.*;

public class Client {
	InputStream is = null;
	ObjectInputStream ois = null;
	static Vector<UserRoom> room = new Vector<UserRoom>();
	static Info info = null;
	Socket socket = null;

	public static void main(String[] args) {
		Client client = new Client();
		client.socket = client.start();// 서버접속

		try { // 룸,인포 초기화
			client.is = client.socket.getInputStream();
			client.ois = new ObjectInputStream(client.is);

			info = client.setInfo(client.ois);// 정보 받아오기

			client.setRoom(client.ois);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (true) {
				BufferedReader in = null;
				ReceiveCode code = new ReceiveCode();

				SendResponse sendResponse = new SendResponse(client.socket);
				sendResponse.start();

				in = new BufferedReader(new InputStreamReader(client.socket.getInputStream()));
				while (true) {
					String inputMsg = in.readLine();
					if (("님이 나가셨습니다").equals(inputMsg))// 나가기 코드
						break;
					code.start(inputMsg);

				}
			}
		} catch (Exception e) {
			System.out.println("에러");
		} finally {
			try {
				client.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("연결종료");
	}

	public void setRoom(ObjectInputStream ois) {// room 설정
		try {
			room = (Vector<UserRoom>) ois.readObject();
			//for (UserRoom room : room)
				//room.printUserRoomInfo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Info setInfo(ObjectInputStream ois) { // info 설정
		Info info = null;
		try {
			info = (Info) ois.readObject();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}

	public Socket start() {// 서버연결
		Socket socket = null;
		boolean re = true;
		try {
			socket = new Socket("localhost", 9000);
			System.out.println("연결되었습니다");
			while (re) {
				re = !login(socket);
			}
		} catch (IOException e) {
			System.out.println("서버 접속끊김");
		}
		return socket;
	}

	public boolean login(Socket socket) {
		Scanner sc = new Scanner(System.in);
		String id = null;
		String pass = null;

		System.out.println("아이디");
		id = sc.nextLine();
		System.out.println("비번");
		pass = sc.nextLine();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());

			out.println(id + "_" + pass);
			if (in.readLine().equals("1"))
				return true;
			else {
				in.close();
				out.close();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	static UserRoom findRoom(String roomNumber) {
		for (UserRoom room : room) {
			if (room.findRoom(roomNumber))
				return room;

		}
		return null;
	}
	/*
	 * public void startChat(Socket socket) { BufferedReader in = null; String name
	 * = "1234"; Scanner sc = new Scanner(System.in); try {
	 * 
	 * SendThread sendThread = new SendThread(socket, name); sendThread.start();
	 * 
	 * in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 * while (in != null) { String inputMsg = in.readLine(); if ((name +
	 * "님이 나가셨습니다").equals(inputMsg))// 나가기 break; System.out.println(inputMsg); } }
	 * catch (IOException e) { System.out.println("서버 접속끊김"); }
	 * 
	 * }
	 */
}