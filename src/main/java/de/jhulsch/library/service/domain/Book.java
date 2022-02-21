
package de.jhulsch.library.service.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.UUID;

@Value.Immutable
@Value.Style(stagedBuilder = true)
@JsonSerialize(as = ImmutableBook.class)
@JsonDeserialize(as = ImmutableBook.class)
public interface Book {

    UUID getBookId();

    String getTitle();

    String getAuthor();

    String getGenre();

    String getPublisher();


}
