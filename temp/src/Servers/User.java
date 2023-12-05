package Servers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import share.Info;
import share.UserRoom;

/*
 * 유저 객체
 * db에 있는 모든 유저를 객체로 만듬(서버랑 접속이 안되어있어도)
 * 서버에 접속해 로그인하면 그때 소켓이 설정
 * !!!!!메세지를 받아서 ReceiveRequest로 넘기는 역할만 수행!!!!!
 */
public class User extends Thread {
   public Socket socket;
   public BufferedReader in = null;
   PrintStream out = null;
   ReceiveRequest receiveRequest;

   String inputMsg = null;
   String outputMsg = null;
   public String id;

   public User(String id) {// 서버 처음 켜졌을때의 생성자
      this.id = id;
   }

   @Override
   public void run() {// 메세지 수신
      DAO dao = new DAO();
      OutputStream os =null;
      ObjectOutputStream oos = null;
      try {// 처음 접속하면 룸과 유저정보를 보낸다
         os = socket.getOutputStream();
         oos = new ObjectOutputStream(os);

         // 클라이언트의 유저 정보 (id,이름,프로필,친구목록(아이디,프로필))을 객체화해서 보냄
         Vector<Object> tempInfo = new Vector<Object>();
         Vector<String[]> friendList=new Vector<String[]>();
         tempInfo=dao.getUserInfo(id);

         friendList=(Vector<String[]>)tempInfo.get(2);
         //String, String, Vector<String[]>
         Info info = new Info(id, (String)tempInfo.get(0), (String)tempInfo.get(1), friendList);
         oos.writeObject(info);

         //유저의 모든 방의 정보를 객체화해서 보냄
         //String, String, Vector<String>, Vector<String[]>([0]:메세지번호,[1]:유저아이디,[2]:메세지본문)
         Vector<Object[]> tempRoom = new Vector<Object[]>();
         tempRoom=dao.getUserAllRoomInfo(id);
         Vector<UserRoom> userRoomList = new Vector<UserRoom>();

         for(Object[] ob:tempRoom) {
            userRoomList.add(new UserRoom((String)ob[0],(String)ob[1],
                  (Vector<String>)ob[2],(Vector<String[]>)ob[3]));
         }
         
         oos.writeObject(userRoomList);
         //for문으로 userRoomList에 저장 후 그걸 클라에 보내는 코드
         
         /*
         //테스트 코드
         Vector<String> joinUser = new Vector<String>();
         Vector<String> chatMessage = new Vector<String>();
         Vector<UserRoom> userRoomList = new Vector<UserRoom>();

         joinUser.add("테스트3-1");
         joinUser.add("테스트3-2");
         joinUser.add("테스트3-3");
         chatMessage.add("채팅아이디_테스트3-1_테스트메세지1");
         chatMessage.add("채팅아이디_테스트3-2_테스트메세지2");
         chatMessage.add("채팅아이디_테스트3-3_테스트메세지3");
         chatMessage.add("채팅아이디_테스트3-1_테스트메세지4");
         userRoomList.add(new UserRoom("1", "방1", joinUser, chatMessage));

         Vector<String> joinUser2 = new Vector<String>();
         Vector<String> chatMessage2 = new Vector<String>();

         joinUser2.add("테스트4-1");
         joinUser2.add("테스트4-2");
         joinUser2.add("테스트4-3");
         chatMessage2.add("채팅아이디_테스트4-1_테스트메세지1");
         chatMessage2.add("채팅아이디_테스트4-2_테스트메세지2");
         chatMessage2.add("채팅아이디_테스트4-3_테스트메세지3");
         chatMessage2.add("채팅아이디_테스트4-1_테스트메세지4");
         userRoomList.add(new UserRoom("2", "방2", joinUser2, chatMessage2));
          */
      } catch (IOException e) {
         e.printStackTrace();
      }

      try {
         while (true) {
            inputMsg = in.readLine();
            receiveRequest.start(inputMsg,out);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public String getInputMsg() { // ReceiveRequest로 문자열 넘기기
      return inputMsg;
   }

   public void setSocket(Socket socket, ReceiveRequest receiveRequest) throws IOException {// 클라이언트가 서버랑 접속했을때의 함수

      this.socket = socket;
      this.receiveRequest = receiveRequest;
      out = new PrintStream(this.socket.getOutputStream());
      in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
   }
}