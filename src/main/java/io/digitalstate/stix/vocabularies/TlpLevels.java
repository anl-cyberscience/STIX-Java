package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TlpLevels implements StixVocabulary {

    @JsonProperty("tlp_levels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "white", "green", "amber", "red"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
