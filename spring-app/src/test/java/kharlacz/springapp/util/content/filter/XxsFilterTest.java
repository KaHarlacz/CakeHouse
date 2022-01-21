package kharlacz.springapp.util.content.filter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class XxsFilterTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "<b>hi</b><script>alert('You are hacked')</script>"
    })
    public void shouldFilterScriptTag(String input) {
        ContentFilter contentFilter = new NoFilter();
        contentFilter = new XxsFilter(contentFilter);
        
        var reservations = new ArrayList<Reservation>();
        var cleanContent = contentFilter.filter(input, reservations);
        
        assertThat(cleanContent).isEqualTo("<b>hi</b>");
        assertThat(reservations).contains(Reservation.XSS);
    }
}