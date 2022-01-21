package kharlacz.springapp.user.ban;

import kharlacz.springapp.user.User;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
}
