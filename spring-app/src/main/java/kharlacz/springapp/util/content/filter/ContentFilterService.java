package kharlacz.springapp.util.content.filter;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentFilterService {
    
    public List<Reservation> filterContentBeingSaved(String content) {
        ContentFilter contentFilter = new NoFilter();
        contentFilter = new BadLangFilter(contentFilter);
        contentFilter = new XxsFilter(contentFilter);
        contentFilter = new SqlInjectionFilter(contentFilter);
        
        return getReservations(content, contentFilter);
    }
    
    public List<Reservation> filterSearchInput(String content) {
        ContentFilter contentFilter = new NoFilter();
        contentFilter = new SqlInjectionFilter(contentFilter);

        return getReservations(content, contentFilter);
    }
    
    private List<Reservation> getReservations(String content, ContentFilter filter) {
        final var reservations = new ArrayList<Reservation>();
        filter.filter(content, reservations);
        return reservations;
    }
}
