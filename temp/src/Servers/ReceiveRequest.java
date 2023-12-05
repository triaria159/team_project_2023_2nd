package Servers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;
import share.*;

/*
 * !!!!!!!클라이언트에서 받은 메세지를 받아 해석하고 알맞는 db 처리!!!!!!
 * 형식
 * 1(채팅방생성)_방제목_친구아이디 ex)1_방5_test1_test2_test3
 * 3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
 * 4(메세지삭제)_방번호_채팅메세지번호 ex)4_1_23
 * 5(메세지수정)_방번호_채팅메세지번호_바꿀내용 ex)5_1_23_변경후메세지
 * 6(방나가기)_방번호_유저아이디 ex)6_1_id
 */
public class ReceiveRequest {
   String msg = null;
   Room room = null;
   DAO dao = new DAO();
   public synchronized void start(String inputMsg, PrintStream out) {

      msg = inputMsg;
      System.out.println("확인용)서버 수신: " + inputMsg);
      
      String[] result = msg.split("_", 2);

      if (result[0].equals("1")) {//1(채팅방생성)_방제목_친구아이디 ex)1_방5_test1_test2_test3
         String[] result2 = result[1].split("_");
         Vector<String> joinUser = new Vector<String>();
         String roomNumber = null;
         String str = "1";

         for (int i = 2; i < result2.length; i++)
            joinUser.add(result2[i]);
         roomNumber = dao.createChatRoom(result2[0], result2[1], joinUser);

         Server.roomList.add(new Room(roomNumber, result2[0], joinUser));

         str += "_" + roomNumber + "_" + result2[0];
         for (String id : joinUser)
            str += "_" + id;
         out.println(str);// 1_roomNumber_title_유저아이디1_유저아이디2_....
         // 확인코드
         //Room room = Server.findRoom(roomNumber);
         //room.printRoomInfo();
      } else if (result[0].equals("3")) {// 3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
         String[] result2 = result[1].split("_");
         String msgNumber = null;
         room = Server.findRoom(result2[0]);
         if (room != null) {
        	msgNumber=dao.saveChatMessage(result2[0], result2[1], result2[2]);
            room.broadcasting(result2[0] + "_" +msgNumber+"_"+ result2[1] + "_" + result2[2], "3");
            //System.out.println("서버 확인용 ");
            // 3(메세지보내기)_방번호_메세지번호_유저아이디_내용 ex)3_1_53_id_testmessage
         }
         else
            room.broadcasting(inputMsg + ")방 넘버 오류", "0");
     
      } else if (result[0].equals("4")) {// 메세지 삭제
         String[] result2 = result[1].split("_", 2);
         room = Server.findRoom(result2[0]);
         if (room != null) {
            if (true) {// 조건문에 db에 접근해서 해당 메세지(result2[1])가 있는지 검사
               // db에서 해당 메세지를 삭제하는 코드
               room.broadcasting(result2[1], "4");
            } else {
               room.broadcasting(inputMsg + ")메세지 코드 삭제 오류", "0");
            }
         } else
            room.broadcasting(inputMsg + ")방 넘버 오류", "0");
         // 필요에 따라 db에 메세지 저장 코드도 추가
      } else if (result[0].equals("5")) {// 메세지 수정
         String[] result2 = result[1].split("_", 2);
         room = Server.findRoom(result2[0]);
         if (room != null) {
            String[] result3 = result2[1].split("_", 2);
            if (true) {// 조건문에 db에 접근해서 해당 메세지(result3[0])이 있는지 검사
               // db에서 해당 메세지를 result3[1]로 수정하는 코드
               room.broadcasting(result3[1], "3" + "_" + result3[0]);
            } else {
               room.broadcasting(inputMsg + ")메세지 코드 수정 오류", "0");
            }
         } else
            room.broadcasting(inputMsg + ")방 넘버 오류", "0");
      } else if (result[0].equals("6")) {// 방 나가기 6(방나가기)_방번호_유저아이디 ex)6_1_id
         String[] result2 = result[1].split("_");
         room = Server.findRoom(result2[0]);
         if (room != null) {
            if (room.findUser(result2[1])) {
               // db에서 해당 유저를 지우는 코드&한명이면 방 삭제
               room.removeUser(result2[1]);
               if (room.joinUser.size() <= 1)
                  Server.roomList.remove(room);
               room.broadcasting(result2[1], "6" + "_" + result2[0]);
            } else {
               room.broadcasting(inputMsg + ")방 나가기 오류", "0");
            }
         } else
            room.broadcasting(inputMsg + ")방 넘버 오류", "0");
      }
      /*
       * 문자열 나눠서 기능 추가
       */

   }
   // private void ~~
}