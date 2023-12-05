package Servers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 * db에서 모든 채팅방 정보를 받아와 객체를 생성
 * 브로드캐스팅을 담당
 * 3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
 * 2(메세지삭제)_채팅메세지번호 ex)2_23
 * 6(방나가기)_방번호_유저아이디 ex)6_1_id
 */
public class Room {
   public String roomNumber;
   public String title;
   public List<User> joinUser = Collections.synchronizedList(new Vector<User>());

   public Room(String roomNumber, String title, Vector<String> vector) {
      this.roomNumber = roomNumber;
      this.title = title;
      for (String id : vector) {
         for (User user : Server.userList) {
            if (user.id.equals(id)) {
               joinUser.add(user);
            }
         }
      }
   }

   public void broadcasting(String msg, String code) {// 방 인원 전부에게 코드 전송
	  
      for (User user : joinUser) {
         if (user.out != null) {
            user.out.println(code + "_" + msg);
            user.out.flush();
            //System.out.println(roomNumber +" "+ user.id+"브로드캐스팅 실행");
         }
      }
   }

   public void printRoomInfo() {
      System.out.println("방제목 " + title);
      System.out.println("방번호 " + roomNumber);
      System.out.println("유저리스트 ");
      for (User user : joinUser)
         System.out.println("ID> " + user.id);
   }
   
   public boolean findUser(String id){
      for(User user:joinUser) {
         if(user.id.equals(id))
            return true;
      }
      return false;
   }
   public boolean removeUser(String id) {
      for(User user:joinUser) {
         if(user.id.equals(id)) {
            joinUser.remove(user);
            return true;
         }
      }
      return false;

   }
}