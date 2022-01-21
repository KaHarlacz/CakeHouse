package kharlacz.springapp.util.content.filter;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllLines;

public class BadLangFilter extends ContentFilterDecorator {

    private final String BAD_LANG_SRC_FILE = "src/main/resources/security/bad-lang.txt";

    public BadLangFilter(ContentFilter wrappedFilter) {
        super(wrappedFilter);
    }

    @Override
    public String filter(String content, List<Reservation> reservations) {
        final var contentWords = getContentWords(content);
        final var badWordsInContent = getBadWordsInContent(contentWords);

        if (!badWordsInContent.isEmpty()) {
            reservations.add(Reservation.INAPPROPRIATE_LANG);
        }
        
        final var cleanContent = sanitizeContent(contentWords, badWordsInContent);
        return wrappedFilter.filter(cleanContent, reservations);
    }

    private List<String> getContentWords(String content) {
        return List.of(content.split(" "));
    }

    @SneakyThrows
    private List<String> getBadWordsInContent(List<String> contentWords) {
        final var badWords = readAllLines(Path.of(BAD_LANG_SRC_FILE));
        return contentWords.stream()
                .filter(w -> badWords.contains(w.toLowerCase()))
                .collect(Collectors.toList());
    }

    private String sanitizeContent(List<String> contentWords, List<String> badWordsInContent) {
        return contentWords.stream()
                .filter(w -> !badWordsInContent.contains(w))
                .reduce((ac, w) -> ac + w)
                .orElse("");
    }
}
