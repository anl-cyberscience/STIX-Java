package io.digitalstate.stix.bundle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This interface is typically inherited by other interfaces that are considered "objects" that are part of a Bundle.
 * Thus the name "BundleableObject".  A Bundleable Object by STIX standard is: SDO, SRO, and Marking Definition.
 * The Type field is used to determine the sub-types as registered in the {@link io.digitalstate.stix.json.StixParsers}
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.EXISTING_PROPERTY )
public interface BundleableObject {

    String getType();
    String getId();

}