package share;

import java.io.Serializable;
import java.util.Vector;

public class Info implements Serializable{
   private static final long serialVersionUID = 1L;
   public String id;
   public String name;
   public String profileText;
   public Vector<String[]> friendList = new Vector<String[]>();//[0]번은 아이디, [1]번은 프로필
   
   public Info(String id, String name, String profileText, Vector<String[]> friendIdList) {
      this.id = id;
      this.name = name;
      this.profileText = profileText;
      this.friendList = friendIdList;
   }
   
   public void printInfo() {
      System.out.println(id+" id\n"+name+" name\n"+profileText+ " profileText\n\n");
      for(String[] friend : friendList) {
         System.out.println(friend[0]+":아이디 "+friend[1]+":프로필");
      }
   }
   
   public Vector<String[]> getFriendIdList(){
      return friendList;
   }
}