
package de.jhulsch.library.service.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.jhulsch.library.common.Gender;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Value.Immutable
@Value.Style(stagedBuilder = true)
@JsonSerialize(as = ImmutableUser.class)
@JsonDeserialize(as = ImmutableUser.class)
public interface User {

    UUID getUserId();

    String getName();

    String getFirstname();

    Gender getGender();

    LocalDate getCreatedOn();

    Optional<LocalDate> getDeletedOn();


}
