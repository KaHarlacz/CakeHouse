package kharlacz.springapp.util.content.filter;

import java.util.List;

public class NoFilter implements ContentFilter {
    @Override
    public String filter(String content, List<Reservation> reservations) {
        return content;
    }
}
