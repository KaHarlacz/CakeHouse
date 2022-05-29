package kharlacz.springapp.user.authentication;

import java.util.Date;

public record AuthRes(String jwtToken, Date expTime) {}
