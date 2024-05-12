package ua.tonkoshkur.tennis.common.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Page<T> {

    private int number;

    private int totalPages;

    private List<T> content;

    public <U> Page<U> map(Function<? super T, ? extends U> mapper) {
        return new Page<>(number, totalPages, getConvertedContent(mapper));
    }

    private <U> List<U> getConvertedContent(Function<? super T, ? extends U> mapper) {
        return content.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
