package kharlacz.springapp.util.content.filter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static kharlacz.springapp.util.content.filter.Reservation.INAPPROPRIATE_LANG;
import static org.assertj.core.api.Assertions.assertThat;

class BadLangFilterTest {

    private ContentFilter filter;

    @BeforeEach
    public void setUp() {
        filter = (content, reservations) -> content;
        filter = new BadLangFilter(filter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"hElL"})
    public void shouldFilterBadWords(String content) {
        final var expectedCleanContent = "";
        final var expectedReservations = singletonList(INAPPROPRIATE_LANG);

        final var reservations = new ArrayList<Reservation>();
        final var cleanContent = filter.filter(content, reservations);

        assertThat(cleanContent).isEqualTo(expectedCleanContent);
        assertThat(reservations).isEqualTo(expectedReservations);
    }

    @ParameterizedTest
    @ValueSource(strings = {"hElLo"})
    public void shouldNotFilterNormalWords(String content) {
        final var reservations = new ArrayList<Reservation>();
        final var cleanContent = filter.filter(content, reservations);

        assertThat(cleanContent).isEqualTo(content);
        assertThat(reservations).isEqualTo(emptyList());
    }

}