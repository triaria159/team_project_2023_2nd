package Servers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import Servers.DAO;

public class DAO {
   private final static String url = "jdbc:sqlserver://localhost:1433;databaseName=kakao;encrypt=false";
   private final static String username = "wlals";
   private final static String password = "wlals8899!*";

   private static DAO _dao;

   static {
      _dao = new DAO();
   }

   public static DAO getDAO() {
      return _dao;
   }

   // 회원가입 (관리자 기능 미구현으로 TYPE은 빼겠음)
   public static int register(DTO usermanage) throws SQLException {
      Connection con = null;
      PreparedStatement pstmt = null;
      PreparedStatement pstmt1 = null;
      int rows = 0;
      try {
         con = DriverManager.getConnection(url, username, password);
         con.setAutoCommit(false);
         String sql1 = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_NM, MEMBER_PHONE) VALUES (?, ?, ?, ?)";

         pstmt = con.prepareStatement(sql1);
         pstmt.setString(1, usermanage.getMemberId());
         pstmt.setString(2, usermanage.getMemberPw());
         pstmt.setString(3, usermanage.getMemberNm());
         pstmt.setString(4, usermanage.getMemberPhone());

         rows = pstmt.executeUpdate();
         if (rows > 0) {
         }
         con.commit();
      } catch (SQLException e) {
         if (con != null) {
            try {
               con.rollback();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         }
         e.printStackTrace();
      } finally {
         if (pstmt1 != null) {
            pstmt1.close();
         }
         if (pstmt != null) {
            pstmt.close();
         }
         if (con != null) {
            con.setAutoCommit(true);
            con.close();
         }
      }
      return rows;
   }

   // 로그인 @
   public static boolean login(String id, String pass) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      boolean isLoggedIn = false;
      try {
         con = DriverManager.getConnection(url, username, password);

         String sql = "select * from Member where MEMBER_ID = ? and MEMBER_PW = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         pstmt.setString(2, pass);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            isLoggedIn = true;
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return isLoggedIn;
   }

   // 모든 유저의 ID 반환 @
   public String[] getAllUserId() {
      List<String> userIdList = new ArrayList<>();

      // JDBC 연결 및 쿼리 실행
      try (Connection connection = DriverManager.getConnection(url, username, password)) {
         String sql = "SELECT MEMBER_ID FROM MEMBER";
         try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
               ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
               String userId = resultSet.getString("MEMBER_ID");
               userIdList.add(userId);
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
         // 예외 처리 - 로깅 또는 예외를 다시 던지기
      }

      // List를 배열로 변환하여 반환
      return userIdList.toArray(new String[0]);
   }

   // 모든 방의 정보 반환 @
   public Vector<Object[]> getAllRoomInfo() {
       Vector<Object[]> info = new Vector<>();

       try (Connection connection = DriverManager.getConnection(url, username, password)) {
           String sql = "SELECT CR.CHAT_ROOM_NO, CR.TITLE, STRING_AGG(CRJ.MEM_ID, ',') AS JOINED_USERS " +
                        "FROM CHAT_ROOM CR " +
                        "LEFT JOIN CHAT_ROOM_JOIN CRJ ON CR.CHAT_ROOM_NO = CRJ.C_ROOM_NO " +
                        "GROUP BY CR.CHAT_ROOM_NO, CR.TITLE";

           try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {

               while (resultSet.next()) {
                   String roomNumber = resultSet.getString("CHAT_ROOM_NO");
                   String title = resultSet.getString("TITLE");
                   String joinedUsers = resultSet.getString("JOINED_USERS");

                   // 사용자 목록을 콤마로 분리하여 배열로 변환
                   String[] users = joinedUsers.split(",");

                   // Object 배열을 생성하여 정보를 추가
                   Object[] roomInfo = new Object[]{roomNumber, title, users};
                   info.add(roomInfo);
               }
           }
       } catch (SQLException e) {
           e.printStackTrace();
           // 예외 처리 - 로깅 또는 예외를 다시 던지기
       }

       return info;
   }
   //유저 한명의 이름,프로필,친구목록([0]:아이디, [1]프로필)을 반환@
   public Vector<Object> getUserInfo(String id) {
       Vector<Object> info = new Vector<>();

       String name = null; // 내 이름
       String profileText = null; // 내 소개글
       Vector<String[]> friendList = new Vector<>(); // [0]: 아이디, [1] 소개글

       Connection connection = null;
       PreparedStatement preparedStatement = null;
       ResultSet resultSet = null;

       try {
           connection = DriverManager.getConnection(url, username, password);

           // 이름과 프로필 텍스트 가져오기
           String userInfoQuery = "SELECT MEMBER_NM, PROFILE_TXT FROM MEMBER WHERE MEMBER_ID = ?";
           preparedStatement = connection.prepareStatement(userInfoQuery);
           preparedStatement.setString(1, id);
           resultSet = preparedStatement.executeQuery();

           if (resultSet.next()) {
               name = resultSet.getString("MEMBER_NM");
               profileText = resultSet.getString("PROFILE_TXT");
           }

           // 친구 목록 가져오기
           String friendQuery = "SELECT FREIND_ID, PROFILE_TXT FROM FRIEND f JOIN MEMBER m ON f.FREIND_ID = m.MEMBER_ID WHERE M_ID = ?";
           preparedStatement = connection.prepareStatement(friendQuery);
           preparedStatement.setString(1, id);
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next()) {
               String[] friendInfo = new String[2];
               friendInfo[0] = resultSet.getString("FREIND_ID");
               friendInfo[1] = resultSet.getString("PROFILE_TXT");
               friendList.add(friendInfo);
           }

           info.add(name);
           info.add(profileText);
           info.add(friendList);

       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           // Close resultSet, preparedStatement, connection
       }

       return info;
   }
   
   //유저 한명이 속한 모든 방의 번호,제목,참여유저,모든메세지를 반환@
   /*
   public Vector<Object[]> getUserAllRoomInfo(String id) {
       Vector<Object[]> info = new Vector<>();
       //String, String, Vector<String>, Vector<String[]>([0]:메세지번호,[1]:유저아이디,[2]:메세지본문)
       try (Connection connection = DriverManager.getConnection(url, username, password)) {
           String roomQuery = "SELECT CHAT_ROOM_NO, TITLE FROM CHAT_ROOM WHERE CM_ID = ?";
           PreparedStatement roomStatement = connection.prepareStatement(roomQuery);
           roomStatement.setString(1, id);
           ResultSet roomResultSet = roomStatement.executeQuery();

           while (roomResultSet.next()) {
               String roomNumber = roomResultSet.getString("CHAT_ROOM_NO");
               String title = roomResultSet.getString("TITLE");

               Vector<String> joinUser = new Vector<>();
               String joinUserQuery = "SELECT MEM_ID FROM CHAT_ROOM_JOIN WHERE C_ROOM_NO = ?";
               PreparedStatement joinUserStatement = connection.prepareStatement(joinUserQuery);
               joinUserStatement.setString(1, roomNumber);
               ResultSet joinUserResultSet = joinUserStatement.executeQuery();

               while (joinUserResultSet.next()) {
                   joinUser.add(joinUserResultSet.getString("MEM_ID"));
               }

               Vector<String[]> message = new Vector<>();
               String messageQuery = "SELECT CM_NO, MM_ID, MESSAGE FROM CHAT_MESSAGE WHERE C_R_NO = ?";
               PreparedStatement messageStatement = connection.prepareStatement(messageQuery);
               messageStatement.setString(1, roomNumber);
               ResultSet messageResultSet = messageStatement.executeQuery();

               while (messageResultSet.next()) {
                   String[] singleMessage = {
                           messageResultSet.getString("CM_NO"),
                           messageResultSet.getString("MM_ID"),
                           messageResultSet.getString("MESSAGE")
                   };
                   message.add(singleMessage);
               }

               Object[] roomInfo = {roomNumber, title, joinUser, message};
               info.add(roomInfo);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

       return info;
   }
   */
   public Vector<Object[]> getUserAllRoomInfo(String userId) {
	    Vector<Object[]> roomInfoList = new Vector<>();

	    String query = "SELECT CR.CHAT_ROOM_NO, CR.TITLE, STRING_AGG(CRJ.MEM_ID, ',') AS JOINED_USERS " +
	                   "FROM CHAT_ROOM CR " +
	                   "INNER JOIN CHAT_ROOM_JOIN CRJ ON CR.CHAT_ROOM_NO = CRJ.C_ROOM_NO " +
	                   "WHERE CRJ.MEM_ID = ? " +
	                   "GROUP BY CR.CHAT_ROOM_NO, CR.TITLE";

	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement ps = connection.prepareStatement(query)) {

	        ps.setString(1, userId);
	        ResultSet resultSet = ps.executeQuery();

	        while (resultSet.next()) {
	            String roomNumber = resultSet.getString("CHAT_ROOM_NO");
	            String title = resultSet.getString("TITLE");
	            String joinedUsers = resultSet.getString("JOINED_USERS");

	            // 사용자 목록을 콤마로 분리하여 Vector로 변환
	            Vector<String> users = new Vector<>(Arrays.asList(joinedUsers.split(",")));

	            // 메시지 정보를 가져오는 쿼리
	            String messageQuery = "SELECT CM.CM_NO, CM.MESSAGE, CM.MM_ID " +
	                                 "FROM CHAT_MESSAGE CM " +
	                                 "WHERE CM.C_R_NO = ?";
	            PreparedStatement messageStatement = connection.prepareStatement(messageQuery);
	            messageStatement.setString(1, roomNumber);
	            ResultSet messageResultSet = messageStatement.executeQuery();

	            Vector<String[]> messages = new Vector<>();
	            while (messageResultSet.next()) {
	                String messageNo = messageResultSet.getString("CM_NO");
	                String message = messageResultSet.getString("MESSAGE");
	                String senderId = messageResultSet.getString("MM_ID");
	                String[] messageData = new String[]{messageNo, senderId, message};
	                messages.add(messageData);
	            }

	            Object[] roomInfo = new Object[]{roomNumber, title, users, messages};
	            roomInfoList.add(roomInfo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return roomInfoList;
	}



   
   //유저 아이디, 방제목, 참여할 아이디를 전달받아 db에 만들고 방번호 리턴@ 
   public String createChatRoom(String title, String cmId, Vector<String> joinIds) {
	    String chatRoomNo = "";
	    String insertChatRoomQuery = "INSERT INTO CHAT_ROOM (CM_ID, TITLE) VALUES (?, ?)";

	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement psChatRoom = connection.prepareStatement(insertChatRoomQuery)) {

	        psChatRoom.setString(1, cmId);
	        psChatRoom.setString(2, title);
	        psChatRoom.executeUpdate();

	        // 새로 생성된 채팅방 번호 가져오기
	        try (Statement stmt = connection.createStatement();
	             ResultSet rs = stmt.executeQuery("SELECT CHAT_ROOM_NO "
	                     + "FROM CHAT_ROOM "
	                     + "ORDER BY CHAT_ROOM_NO DESC")) {
	            if (rs.next()) {
	                chatRoomNo = rs.getString("CHAT_ROOM_NO");
	            }
	        }

	        // 참여자 추가
	        String insertChatRoomJoinQuery = "INSERT INTO CHAT_ROOM_JOIN (C_ROOM_NO, MEM_ID) VALUES (?, ?)";
	        try (PreparedStatement psChatRoomJoin = connection.prepareStatement(insertChatRoomJoinQuery)) {
	            // 방을 만드는 cmId를 참여자 목록에 포함하지 않았을 경우 추가
	            if (!joinIds.contains(cmId)) {
	                joinIds.add(cmId);
	            }

	            for (String joinId : joinIds) {
	                psChatRoomJoin.setString(1, chatRoomNo);
	                psChatRoomJoin.setString(2, joinId);
	                psChatRoomJoin.executeUpdate();
	            }
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return chatRoomNo;
	}

   /*
   public String createChatRoom(String title, String cmId, Vector<String> joinIds) {
	    String chatRoomNo = "";
	    String insertChatRoomQuery = "INSERT INTO CHAT_ROOM (CM_ID, TITLE) VALUES (?, ?)";

	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement psChatRoom = connection.prepareStatement(insertChatRoomQuery)) {

	        psChatRoom.setString(1, cmId);
	        psChatRoom.setString(2, title);
	        psChatRoom.executeUpdate();

	        
	        // 새로 생성된 채팅방 번호 가져오기
	        try (Statement stmt = connection.createStatement();
	        	ResultSet rs = stmt.executeQuery("SELECT CHAT_ROOM_NO "
	        			+ "FROM CHAT_ROOM "
	        			+ "ORDER BY CHAT_ROOM_NO DESC")) {
	            if (rs.next()) {
	                chatRoomNo = rs.getString("CHAT_ROOM_NO");
	            }
	        }

	        // 참여자 추가
	        
	        String insertChatRoomJoinQuery = "INSERT INTO CHAT_ROOM_JOIN (C_ROOM_NO, MEM_ID) VALUES (?, ?)";
	        try (PreparedStatement psChatRoomJoin = connection.prepareStatement(insertChatRoomJoinQuery)) {
	            for (String joinId : joinIds) {
	                psChatRoomJoin.setString(1, chatRoomNo);
	                psChatRoomJoin.setString(2, joinId);
	                psChatRoomJoin.executeUpdate();
	            }
	        }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return chatRoomNo;
	}
	*/


   public boolean checkDuplicateID(String id) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      boolean isDuplicate = false;
      try {
         con = DriverManager.getConnection(url, username, password);

         String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            isDuplicate = true;
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            if (rs != null)
               rs.close();
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return isDuplicate;
   }

   public boolean checkPassword(String memberId, String passwordToCheck) {
      /*
       * Connection con = null; PreparedStatement pstmt = null; ResultSet rs = null;
       */
      String getPasswordQuery = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_ID = ?";
      boolean isPasswordCorrect = false;

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(getPasswordQuery)) {

         ps.setString(1, memberId);
         ResultSet resultSet = ps.executeQuery();

         if (resultSet.next()) {
            String storedPassword = resultSet.getString("MEMBER_PW");
            isPasswordCorrect = storedPassword.equals(passwordToCheck);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return isPasswordCorrect;
   }

   public String retrieveID(String memberNm, String phoneNumber) {
      String getIDQuery = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_NM = ? AND MEMBER_PHONE = ?";
      String foundID = null;

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(getIDQuery)) {

         ps.setString(1, memberNm);
         ps.setString(2, phoneNumber);
         ResultSet resultSet = ps.executeQuery();

         if (resultSet.next()) {
            foundID = resultSet.getString("MEMBER_ID");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return foundID;
   }

   public boolean findPassword(String name, String memberId, String phoneNumber) {
      String findPasswordQuery = "SELECT MEMBER_PW FROM MEMBER WHERE MEMBER_NM = ? AND MEMBER_ID = ? AND MEMBER_PHONE = ?";
      boolean isPasswordFound = false;

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(findPasswordQuery)) {

         ps.setString(1, name);
         ps.setString(2, memberId);
         ps.setString(3, phoneNumber);

         ResultSet resultSet = ps.executeQuery();
         isPasswordFound = resultSet.next();

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return isPasswordFound;
   }

   // 접속중인 사용자의 id 받아와야함
   public List<Object[]> getProfiles(String loggedInMemberId) {
      List<Object[]> profileList = new ArrayList<>();
      String query = "SELECT PROFILE, MEMBER_NM, PROFILE_TXT " + "FROM MEMBER " + "WHERE MEMBER_ID = ? " + "UNION "
            + "SELECT PROFILE, MEMBER_NM, PROFILE_TXT " + "FROM MEMBER m "
            + "INNER JOIN FRIEND f ON m.MEMBER_ID = f.M_ID " + "WHERE f.FREIND_ID = ? " + "ORDER BY MEMBER_NM";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, loggedInMemberId);
         ps.setString(2, loggedInMemberId);

         ResultSet resultSet = ps.executeQuery();
         while (resultSet.next()) {
            byte[] profileImage = resultSet.getBytes("PROFILE");
            String memberName = resultSet.getString("MEMBER_NM");
            String profileText = resultSet.getString("PROFILE_TXT");
            Object[] profileInfo = { memberName, profileText, profileImage };
            profileList.add(profileInfo);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return profileList;
   }

   public String[] getFriendProfile(String friendId) {
      String[] profileInfo = new String[3]; // 프로필 정보를 담을 배열

      String query = "SELECT m.PROFILE, m.MEMBER_NM, m.PROFILE_TXT " + "FROM MEMBER m "
            + "INNER JOIN FRIEND f ON m.MEMBER_ID = f.FREIND_ID " + "WHERE f.M_ID = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, friendId);

         ResultSet resultSet = ps.executeQuery();
         if (resultSet.next()) {
            byte[] profileImage = resultSet.getBytes("PROFILE");
            String memberName = resultSet.getString("MEMBER_NM");
            String profileText = resultSet.getString("PROFILE_TXT");

            profileInfo[0] = memberName;
            profileInfo[1] = profileText;
            profileInfo[2] = Base64.getEncoder().encodeToString(profileImage);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return profileInfo;
   }

   // 접속중인 사용자의 id 받아와야함
   public void deleteFriend(String memberId, String friendId) {
      String query = "DELETE FROM FRIEND WHERE M_ID = ? AND FREIND_ID = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, memberId);
         ps.setString(2, friendId);

         int deletedRows = ps.executeUpdate();
         if (deletedRows > 0) {
            System.out.println("친구 삭제 완료");
         } else {
            System.out.println("친구 삭제 실패");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public List<Object[]> getChatRoomsMessages() {
      List<Object[]> chatRoomsInfo = new ArrayList<>();

      String query = "SELECT CR.TITLE AS CHAT_ROOM_TITLE, " + "       CM.MESSAGE AS LAST_MESSAGE, "
            + "       CM.CREATE_DT AS MESSAGE_TIME " + "FROM CHAT_ROOM CR " + "LEFT JOIN (SELECT C_R_NO, "
            + "                  MESSAGE, " + "                  CREATE_DT " + "           FROM CHAT_MESSAGE "
            + "           WHERE CM_NO IN (SELECT MAX(CM_NO) " + "                            FROM CHAT_MESSAGE "
            + "                            GROUP BY C_R_NO)) CM " + "ON CR.CHAT_ROOM_NO = CM.C_R_NO";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ResultSet resultSet = ps.executeQuery();

         while (resultSet.next()) {
            String chatRoomTitle = resultSet.getString("CHAT_ROOM_TITLE");
            String lastMessage = resultSet.getString("LAST_MESSAGE");
            Timestamp messageTime = resultSet.getTimestamp("MESSAGE_TIME");

            Object[] row = { chatRoomTitle, lastMessage, messageTime };
            chatRoomsInfo.add(row);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return chatRoomsInfo;
   }
   /*
   public void saveChatMessage(String chatRoomNo, String memberId, String message) {
      String query = "INSERT INTO CHAT_MESSAGE (C_R_NO, MM_ID, MESSAGE) VALUES (?, ?, ?)";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, chatRoomNo);
         ps.setString(2, memberId);
         ps.setString(3, message);

         int rowsAffected = ps.executeUpdate();
         if (rowsAffected > 0) {
            System.out.println("Message saved successfully!");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   */
   public String saveChatMessage(String chatRoomNo, String memberId, String message) {
	    String query = "INSERT INTO CHAT_MESSAGE (C_R_NO, MM_ID, MESSAGE) VALUES (?, ?, ?)";
	    String generatedKey = "";

	    try (Connection connection = DriverManager.getConnection(url, username, password);
	         PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, chatRoomNo);
	        ps.setString(2, memberId);
	        ps.setString(3, message);

	        int rowsAffected = ps.executeUpdate();
	        
	        if (rowsAffected > 0) {
	        	String query1 = "SELECT TOP 1 CM_NO FROM CHAT_MESSAGE WHERE C_R_NO = ? ORDER BY CM_NO DESC";
	        	try (Connection connection1 = DriverManager.getConnection(url, username, password);
	        	         PreparedStatement ps1 = connection1.prepareStatement(query1)) {

	        	        ps1.setString(1, chatRoomNo);
	        	        ResultSet resultSet = ps1.executeQuery();

	        	        if (resultSet.next()) {
	        	            generatedKey = String.valueOf(resultSet.getLong("CM_NO"));
	        	        }
	        	    } catch (SQLException e) {
	        	        e.printStackTrace();
	        	    }
	        }
	        

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return generatedKey;
	}

   public List<String[]> getChatRoomMembers(String chatRoomNo) {
      List<String[]> membersList = new ArrayList<>();
      String query = "SELECT MEMBER.PROFILE, MEMBER.MEMBER_NM " + "FROM MEMBER "
            + "INNER JOIN CHAT_ROOM_JOIN ON MEMBER.MEMBER_ID = CHAT_ROOM_JOIN.MEM_ID "
            + "WHERE CHAT_ROOM_JOIN.C_ROOM_NO = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, chatRoomNo);

         ResultSet resultSet = ps.executeQuery();
         while (resultSet.next()) {
            byte[] profileImage = resultSet.getBytes("PROFILE");
            String memberName = resultSet.getString("MEMBER_NM");

            String[] memberInfo = { memberName, new String(profileImage) };
            membersList.add(memberInfo);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return membersList;
   }

   // 접속중인 사용자의 id 받아와야함
   public boolean addFriend(String memberId, String friendId) {
      String query = "INSERT INTO FRIEND (FREIND_ID, M_ID) VALUES (?, ?)";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(query)) {

         ps.setString(1, friendId);
         ps.setString(2, memberId);

         int rowsAffected = ps.executeUpdate();
         return rowsAffected > 0;

      } catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   // 접속중인 사용자의 id 받아와야함
   public boolean changePassword(String memberId, String newPassword) {
      String updateQuery = "UPDATE MEMBER SET MEMBER_PW = ? WHERE MEMBER_ID = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

         preparedStatement.setString(1, newPassword);
         preparedStatement.setString(2, memberId);

         int rowsUpdated = preparedStatement.executeUpdate();
         return rowsUpdated > 0; // 비밀번호 변경이 성공하면 true 반환

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false; // 변경 실패 시 false 반환
   }

   // 접속중인 사용자의 id 받아와야함
   public boolean removeMember(String memberId) {
      String deleteQuery = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

         preparedStatement.setString(1, memberId);

         int rowsDeleted = preparedStatement.executeUpdate();
         return rowsDeleted > 0; // 회원 삭제 성공하면 true 반환

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return false; // 삭제 실패 시 false 반환
   }

   public String getMemberId(String memberName) {
      String memberId = null;
      String selectQuery = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_NM = ?";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

         preparedStatement.setString(1, memberName);

         ResultSet resultSet = preparedStatement.executeQuery();
         if (resultSet.next()) {
            memberId = resultSet.getString("MEMBER_ID");
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return memberId;
   }

   public List<String[]> getChatRoomInfo() {
      List<String[]> chatRooms = new ArrayList<>();
      String selectQuery = "SELECT CHAT_ROOM_NO, CM_ID, TITLE FROM CHAT_ROOM";

      try (Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement()) {

         ResultSet resultSet = statement.executeQuery(selectQuery);
         while (resultSet.next()) {
            String chatRoomNo = resultSet.getString("CHAT_ROOM_NO");
            String creatorId = resultSet.getString("CM_ID");
            String title = resultSet.getString("TITLE");
            String[] roomInfo = { chatRoomNo, creatorId, title };// 방넘버, 방 제목, 참여자1, 참여자2, 참여자.....
            chatRooms.add(roomInfo);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return chatRooms;
   }
}