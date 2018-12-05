package io.digitalstate.stix.vocabularies;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.digitalstate.stix.helpers.StixDataFormats;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ThreatActorLabels implements StixVocabulary {

    @JsonProperty("threat_actor_labels_vocabulary")
    private Set<String> terms = new HashSet<>(Arrays.asList(
            "activist", "competitor", "crime-syndicate",
            "criminal", "hacker", "insider-accidental",
            "insider-disgruntled", "nation-state", "sensationalist",
            "spy", "terrorist"));

    //
    // Getters and Setters
    //

    public Set<String> getAllTerms() {
        return terms;
    }

}
