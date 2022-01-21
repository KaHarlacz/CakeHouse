package kharlacz.springapp.user.ban;

public class BanDtoMapper {
    static BanDto map(Ban ban) {
        return BanDto.builder()
                .isBanned(true)
                .from(ban.getFrom())
                .to(ban.getTo())
                .cause(ban.getCause())
                .build();
    }
}
