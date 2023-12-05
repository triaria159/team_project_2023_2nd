package Client;

/*
 * !!!!!!!서버에 코드 보내기!!!!!!
 * 형식
 * 1(채팅방생성)_방제목_친구아이디 ex)1_방5_test1_test2_test3
 * 3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
 * 4(메세지삭제)_방번호_채팅메세지번호 ex)4_1_23
 * 5(메세지수정)_방번호_채팅메세지번호_바꿀내용 ex)5_1_23_변경후메세지
 * 6(방나가기)_방번호_유저아이디 ex)6_1_id
 */
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

import java.util.Scanner;

import share.UserRoom;

public class SendResponse extends Thread {
   Socket socket = null;
   Scanner sc = new Scanner(System.in);

   public SendResponse(Socket socket) {
      this.socket = socket;
   }

   @Override
   public void run() { 
      try {
         PrintStream out = new PrintStream(socket.getOutputStream());
         int menu;
         while (true) {
            System.out.println("1.채팅방 생성 2. 채팅방 입장 3.친구 추가 4.친구 삭제 5.로그아웃");
            menu = sc.nextInt();
            if (menu == 1) {
               sc.nextLine();
               createRoom(out);
            } else if (menu == 2) {
               enterRoom(out);
            }
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void createRoom(PrintStream out) {//1(채팅방생성)_방제목_친구아이디 ex)1_방5_test1_test2_test3
      String str = null;
      String temp = null;
      String title = null;

      System.out.println("채팅방 이름");
      title = sc.nextLine();
      str = "1_" + title + "_" + Client.info.id;
      for (String[] id : Client.info.friendList)
         System.out.println(id[0]);
      System.out.println("넣을 ID/exit");
      temp = sc.nextLine();
      while (!temp.equals("exit")) {
         str += "_" + temp;
         temp = sc.nextLine();
      }
      out.println(str);
      out.flush();
   }

   public void enterRoom(PrintStream out) {
      UserRoom room = null;
      String roomNumber = null;
      Scanner sc = new Scanner(System.in);
      int menu;
      for (UserRoom temp : Client.room)
         System.out.println(temp.roomNumber + "번 " + temp.title);
      System.out.println("\n입장할 방의 번호");
      roomNumber = sc.nextLine();
      room = Client.findRoom(roomNumber);

      //모든 채팅 출력하는 콛,?
      while (room != null) {
         System.out.println("1.채팅 보내기 2. 채팅삭제 3.채팅수정 4.방 나가기(아예) 5.나가기");
         menu = sc.nextInt();
         sc.nextLine();
         if (menu == 1) {//3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
            String message = sc.nextLine();
            out.println("3_" + room.roomNumber + "_" + Client.info.id + "_" + message);

         }else if(menu==4) {//6(방나가기)_방번호_유저아이디 ex)6_1_id
            out.println("6_" + room.roomNumber + "_" + Client.info.id);
            break;
         }else if(menu==5) {
        	 break;
         }
      }
   }
}