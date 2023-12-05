package share;

import java.io.Serializable;
import java.util.Vector;

public class UserRoom implements Serializable{
   private static final long serialVersionUID = 1L;
   public String roomNumber;
   public String title;
   public Vector<String> joinUser = new Vector<String>();
   public Vector<String[]> chatMessage = new Vector<String[]>();// ([0]:메세지번호,[1]:유저아이디,[2]:메세지본문)
   
   public UserRoom(String roomNumber, String title, Vector<String> joinUser, Vector<String[]> chatMessage) {
      this.roomNumber = roomNumber;
      this.title = title;
      this.joinUser = joinUser;
      this.chatMessage = chatMessage;
   }
   
   public UserRoom(String roomNumber, String title, Vector<String> joinUser) {
      this.roomNumber = roomNumber;
      this.title = title;
      this.joinUser = joinUser;
   }

   public void printUserRoomInfo() {
      System.out.println(roomNumber + " roomNumber\n" + title + " title\n");

      for (String id : joinUser)
         System.out.println(id + "\n");

      for (String[] message : chatMessage)
         System.out.println("번호 "+message[0]+" ID "+message[1]+" 본문 "+message[2]);

   }
   
   public void printAllMessage() {
	      System.out.println("방 제목" + title + "\n\n");

	      for (String[] message : chatMessage)
	         System.out.println("from >> "+message[1]+": "+message[2]+" 본문 ");

	   }
   
   public boolean findRoom(String roomNumber) {
      if(this.roomNumber.equals(roomNumber))
         return true;
      else
         return false;
   }
   
   public Vector<String> getJoinUser(){
      return joinUser;
   }
}