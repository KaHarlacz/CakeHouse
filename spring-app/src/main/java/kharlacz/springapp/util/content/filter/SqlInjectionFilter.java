package kharlacz.springapp.util.content.filter;

import java.util.List;
import java.util.regex.Pattern;

/*  
    SQL Injection is most likely not possible in this app
    but this filter is used to punish user which tried to use it.
 */
public class SqlInjectionFilter extends ContentFilterDecorator {

    public SqlInjectionFilter(ContentFilter wrappedFilter) {
        super(wrappedFilter);
    }

    @Override
    public String filter(String content, List<Reservation> reservations) {
        // Weak implementation
        var sqlComment = Pattern.compile("--");
        var matcher = sqlComment.matcher(content);

        if (matcher.find()) {
            reservations.add(Reservation.SQL_INJECTION);
        }

        // TODO: clean content before passing to next filter
        return wrappedFilter.filter(content, reservations);
    }
}
