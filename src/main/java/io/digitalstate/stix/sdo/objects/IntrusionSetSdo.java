package io.digitalstate.stix.sdo.objects;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import io.digitalstate.stix.helpers.StixDataFormats;
import io.digitalstate.stix.redaction.Redactable;
import io.digitalstate.stix.sdo.DomainObject;
import io.digitalstate.stix.validation.contraints.defaulttypevalue.DefaultTypeValue;
import io.digitalstate.stix.validation.contraints.vocab.Vocab;
import io.digitalstate.stix.validation.groups.DefaultValuesProcessor;
import io.digitalstate.stix.vocabularies.AttackMotivations;
import io.digitalstate.stix.vocabularies.AttackResourceLevels;
import org.immutables.serial.Serial;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Value.Immutable @Serial.Version(1L)
@JsonTypeName("intrusion-set")
@DefaultTypeValue(value = "intrusion-set", groups = {DefaultValuesProcessor.class})
@Value.Style(typeAbstract="*Sdo", typeImmutable="*", validationMethod = Value.Style.ValidationMethod.NONE, additionalJsonAnnotations = {JsonTypeName.class})
@JsonSerialize(as = IntrusionSet.class) @JsonDeserialize(builder = IntrusionSet.Builder.class)
@JsonPropertyOrder({"type", "id", "created_by_ref", "created",
        "modified", "revoked", "labels", "external_references",
        "object_marking_refs", "granular_markings", "name", "description", "aliases",
        "first_seen", "last_seen", "goals", "resource_level",
        "primary_motivation", "secondary_motivation"})
@Redactable
public interface IntrusionSetSdo extends DomainObject {

    @NotBlank
    @JsonProperty("name")
    @Redactable(useMask = true)
    String getName();

    @JsonProperty("description") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getDescription();

    @NotNull
    @JsonProperty("aliases") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<String> getAliases();

    @JsonProperty("first_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable
    Optional<Instant> getFirstSeen();

    @JsonProperty("last_seen") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @JsonSerialize(using = InstantSerializer.class)
    @JsonFormat(pattern = StixDataFormats.TIMESTAMP_PATTERN, timezone = "UTC")
    @Redactable
    Optional<Instant> getLastSeen();

    @NotNull
    @JsonProperty("goals") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<String> getGoals();

    @Vocab(AttackResourceLevels.class)
    @JsonProperty("resource_level") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getResourceLevel();

    @Vocab(AttackMotivations.class)
    @JsonProperty("primary_motivation") @JsonInclude(value = NON_EMPTY, content= NON_EMPTY)
    @Redactable
    Optional<String> getPrimaryMotivation();

    @NotNull
    @Vocab(AttackMotivations.class)
    @JsonProperty("secondary_motivations") @JsonInclude(NON_EMPTY)
    @Redactable
    Set<String> getSecondaryMotivations();

}
