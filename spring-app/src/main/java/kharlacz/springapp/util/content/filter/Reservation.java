package kharlacz.springapp.util.content.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Reservation {
    XSS("Possible XSS attempt", 1000),
    SQL_INJECTION("Possible SQL Injection attempt", 1000),
    INAPPROPRIATE_LANG("Inappropriate language", 480);
    
    private final String message;
    private final int penaltyHours;
}
