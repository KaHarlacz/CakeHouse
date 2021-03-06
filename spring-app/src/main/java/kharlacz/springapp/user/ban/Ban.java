package kharlacz.springapp.user.ban;

import kharlacz.springapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.HOUR_OF_DAY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Ban implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "ban_from")
    private Date from;
    @Column(name = "ban_to")
    private Date to;
    private String cause;

    public static Ban of(User user, String cause, int banHours) {
        return Ban.builder()
                .cause(cause)
                .user(user)
                .from(new Date())
                .to(getBanEndDate(banHours))
                .build();
    }

    private static Date getBanEndDate(int banHours) {
        final var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(HOUR_OF_DAY, banHours);
        return calendar.getTime();
    }
}
