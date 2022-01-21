package kharlacz.springapp.user.ban;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
@Getter
public class BanDto {
    private boolean isBanned;
    private Date from;
    private Date to;
    private String cause;
    
    public static BanDto empty() {
        return new BanDto(false, null, null, null);
    }
}
