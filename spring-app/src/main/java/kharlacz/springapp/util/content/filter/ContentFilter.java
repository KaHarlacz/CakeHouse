package kharlacz.springapp.util.content.filter;

import java.util.List;

public interface ContentFilter {
    
    String filter(String content, List<Reservation> reservations);
    
}
