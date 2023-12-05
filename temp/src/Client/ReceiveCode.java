package Client;

import java.io.ObjectInputStream;
import java.util.Vector;

import share.UserRoom;

/*
 * !!!!!!!서버에서 온 코드 해석!!!!!!
 * 형식
 * 1(채팅방생성)_방번호_방제목_친구아이디 ex)1_0005_방5_test1_test2_test3
 * 3(메세지보내기)_방번호_유저아이디_내용 ex)3_1_id_testmessage
 * 4(메세지삭제)_방번호_채팅메세지번호 ex)4_1_23
 * 5(메세지수정)_방번호_채팅메세지번호_바꿀내용 ex)5_1_23_변경후메세지
 * 6(방나가기)_방번호_유저아이디 ex)6_1_id
 */
public class ReceiveCode {
   ObjectInputStream ois = null;
   String msg = null;
   UserRoom room = null;

   public void start(String inputMsg) {
      msg = inputMsg;
      //System.out.println("확인용)클라이언트 수신: " + inputMsg);
      String[] result = msg.split("_", 2);
      
      if(result[0].equals("1")) {//1(채팅방생성)_방번호_방제목_친구아이디 ex)1_0005_방5_test1_test2_test3
         String[] result2 = result[1].split("_");
         Vector<String> joinUser = new Vector<String>();
         for(int i=2;i<result2.length;i++)
            joinUser.add(result2[i]);
            
         Client.room.add(new UserRoom(result2[0],result2[1],joinUser));//룸 객체 만들고 리스트에 저장
         //확인용 코드
         //UserRoom room = Client.findRoom(result2[0]);
         //room.printUserRoomInfo();
      }else if(result[0].equals("3")) {// 3(메세지보내기)_방번호_메세지번호_유저아이디_내용 ex)3_1_53_id_testmessage
         String[] result2 = result[1].split("_");
         room=Client.findRoom(result2[0]);
         String[] msg=new String[]{result2[1],result2[2],result2[3]};
 
        room.chatMessage.add(msg);
         System.out.println("from >> "+result2[2]+": "+result2[3]);
         
       //확인용 코드
         //room.printUserRoomInfo();
      }else if(result[0].equals("6")) {//6(방나가기)_방번호_유저아이디 ex)6_1_id
         String[] result2 = result[1].split("_");
         room=Client.findRoom(result2[0]);
         
         if(Client.info.id.equals(result2[1])){//내가 보낸 신호면 내 방리스트에서 지운다
            Client.room.remove(room);
         }
         else {//아니면(내가 보낸 신호가 아니고 방 참가 인원중 한명이 보냈으면)
            room.joinUser.remove(result2[1]);
         }
         System.out.println("from >> "+result2[1]+"가 나가셨습니다");
         
         room.printUserRoomInfo();//확인용
      }
   }

}