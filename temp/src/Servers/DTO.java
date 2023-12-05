package Servers;
import java.sql.Date;
import java.sql.Timestamp;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class DTO {
   private String memberId;
    private String memberPw;
    private String memberNm;
    private String memberPhone;
    private String profile_txt;
    private Date enrollDate;
   private String chatRoomNo;
    private String cmId;
    private String title;
    private String cmNo;
    private String cRNo;
    private String mmId;
    private String message;
    private Timestamp createDt;
    private byte[] profile;
    private byte[] photo;
    private byte[] file_;
    private byte type;
    private byte stop;
    private byte online;
}