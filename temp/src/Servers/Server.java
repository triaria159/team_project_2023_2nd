package Servers;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * !!!!!!!클라이언트 수신만 담당!!!!!!!
 * 벡터로 관리
 */
public class Server {
   static List<Room> roomList = Collections.synchronizedList(new Vector<Room>());// 방 리스트
   static List<User> userList = Collections.synchronizedList(new Vector<User>());// 유저 리스트

   public static void main(String[] args) {
      ServerSocket serverSocket = null;
      Socket socket = null;
      String id = null;

      try {
         serverSocket = new ServerSocket(9000); // 서버 생성
         ReceiveRequest receiveRequest = new ReceiveRequest();
         Server.setUser();// 유저 설정
         Server.setRoom();// 방생성
         while (true) {
            System.out.println("클라이언트 연결대기중");

            socket = serverSocket.accept();
            id = Server.checkAccount(socket); // 로그인
            if (id != null) {
               for (User user : userList) {// 유저 리스트에서 검색
                  if (user.id.equals(id)) {// 동일한 아이디를 가진 객체에게
                     user.setSocket(socket, receiveRequest);
                     user.start();
                     break;
                  }
               }
            } else {
               System.out.println("확인용(id=null)");
               ;
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (serverSocket != null) {
            try {
               serverSocket.close();
               System.out.println("서버종료");
            } catch (IOException e) {
               e.printStackTrace();
               System.out.println("서버소켓통신에러");
            }
         }
      }
   }

   public static void setRoom() {
      DAO dao = new DAO();
      Vector<Object[]> allRoom = new Vector<Object[]>();

      String roomNumber = null;
      String title = null;
      Vector<String> joinUser = new Vector<String>();
      allRoom = dao.getAllRoomInfo();

      for (Object[] room : allRoom) {
         roomNumber = (String) room[0];
         title = (String) room[1];
         for (String id : (String[]) room[2])
            joinUser.add(id);
         roomList.add(new Room(roomNumber, title, joinUser));
         joinUser.clear();
      }
      /*
       * // 테스트 코드 건드리기x Vector<String> vector1 = new Vector<String>();
       * vector1.add("테스트1-1"); vector1.add("테스트1-2");
       * 
       * Vector<String> vector2 = new Vector<String>(); vector2.add("테스트1-3");
       * vector2.add("테스트1-4");
       * 
       * Vector<String> vector3 = new Vector<String>(); vector3.add("테스트1-1");
       * vector3.add("테스트1-3");
       * 
       * roomList.add(new Room("1", "방1", vector1)); roomList.add(new Room("2", "방2",
       * vector2)); roomList.add(new Room("3", "방3", vector3));
       */
      for (Room tempRoom : roomList) {// 생성된 모든 방 정보출력
         tempRoom.printRoomInfo();
      }
   }

   public static void setUser() {
      DAO dao = new DAO();
      String[] userId = null;
      userId = dao.getAllUserId();
      for (String id : userId) {
         User user = new User(id);
         userList.add(user);
      }
      /*
       * userList.add(new User("테스트1-1")); userList.add(new User("테스트1-2"));
       * userList.add(new User("테스트1-3")); userList.add(new User("테스트1-4"));
       */
   }

   public static String checkAccount(Socket socket) {// 로그인
      DAO dao = new DAO();
      try {
         while (true) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            String msg = in.readLine(); // id1234_pass1234 형식으로 전송

            String[] result = msg.split("_");

            if (dao.login(result[0], result[1])) {// 맞으면
               out.println("1");// 성공
               out.flush();
               return result[0];
            } else {// 아니면
               out.println("0");// 실패
               out.flush();
               in.close();
               out.close();
               return null;
            }
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;

   }

   public static Room findRoom(String roomNumber) {
      for (Room room : roomList) {
         if (room.roomNumber.equals(roomNumber)) {
            return room;
         }
      }
      return null;
   }

   public static User findUser(String id) {
      for (User user : userList) {
         if (user.id.equals(id)) {
            return user;
         }
      }
      return null;
   }
}