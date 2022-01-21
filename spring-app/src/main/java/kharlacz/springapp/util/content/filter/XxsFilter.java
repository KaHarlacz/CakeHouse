package kharlacz.springapp.util.content.filter;

import lombok.SneakyThrows;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.Policy;

import java.util.List;

public class XxsFilter extends ContentFilterDecorator {

    public XxsFilter(ContentFilter wrappedFilter) {
        super(wrappedFilter);
    }

    @SneakyThrows
    @Override
    public String filter(String content, List<Reservation> reservations) {
        var policy = Policy.getInstance("src/main/resources/security/antisamy-slashdot.xml");
        var sanitizer = new AntiSamy(policy);

        var result = sanitizer.scan(content);
        var isSafeContent = result.getErrorMessages().isEmpty();

        if (!isSafeContent) {
            reservations.add(Reservation.XSS);
        }

        return wrappedFilter.filter(result.getCleanHTML(), reservations);
    }
}
