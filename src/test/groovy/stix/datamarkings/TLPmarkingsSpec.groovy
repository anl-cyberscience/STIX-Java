package stix.datamarkings

import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

import io.digitalstate.stix.bundle.Bundle
import io.digitalstate.stix.common.StixInstant
import io.digitalstate.stix.datamarkings.MarkingDefinition
import io.digitalstate.stix.datamarkings.objects.Tlps
import io.digitalstate.stix.json.StixParsers
import io.digitalstate.stix.sdo.objects.Indicator
import spock.lang.Shared
import spock.lang.Specification

class TlpMarkingsSpec extends Specification {
	
    @Shared
    ObjectMapper mapper = new ObjectMapper()
	
    def "TLP Defaults Creation Test: WHITE"() {
        when: "Create TLP:white from pre-built TLPs"
	
        MarkingDefinition originalMarkingDefinition = Tlps.TLP_WHITE
		
        then: "Convert Marking Definition to Json"
            JsonNode originalJson = mapper.readTree(originalMarkingDefinition.toJsonString())
            String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

        then: "Parse Json back into Marking Definition Object"
            MarkingDefinition parsedMarkingDefinition = (MarkingDefinition)StixParsers.parseObject(originalJsonString)
             MarkingDefinition parsedMarkingDefinitionGeneric = StixParsers.parse(originalJsonString, MarkingDefinition.class)
//            println "Parsed Object: ${parsedMarkingDefinition}"

        //@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalMarkingDefinition == parsedMarkingDefinition

        then: "Convert Parsed Marking Definition Object back to into Json"
            JsonNode newJson =  mapper.readTree(parsedMarkingDefinition.toJsonString())
            String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

        then: "New Json should match Original Json"
            JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)
	
	}
	
    def "Test indicator with Default TLP markings"() {
        when: "Create TLP:green"
		
            MarkingDefinition TlpGreen = Tlps.TLP_GREEN
            StixInstant now = new StixInstant()
		
		Indicator ind = Indicator.builder()
						.id("indicator--59ccb738-921a-4941-8ab2-33da522bd4e1")
						.created(now)
						.modified(now)
                    .addLabel("malicious-activity")
						.name("128.0.0.1")
						.pattern("[ipv4-addr:value = '128.0.0.1']")
						.validFrom(now)
                    .addObjectMarkingRef(TlpGreen)
                    .build()
		
		then: "Convert Marking Definition to Json"
			JsonNode originalJson = mapper.readTree(ind.toJsonString())
			String originalJsonString = mapper.writeValueAsString(originalJson)
//            println "Original Json: ${originalJsonString}"

		then: "Parse Json back into Marking Definition Object"
			Indicator parsed = (Indicator)StixParsers.parseObject(originalJsonString)
            Indicator parsedGeneric = StixParsers.parse(originalJsonString, Indicator.class)
//            println "Parsed Object: ${parsed}"

		//@TODO needs to be setup to handle dehydrated object comparison
//        then: "Parsed object should match Original object"
//            assert originalMarkingDefinition == parsedMarkingDefinition

		then: "Convert Parsed Marking Definition Object back to into Json"
			JsonNode newJson =  mapper.readTree(parsed.toJsonString())
			String newJsonString = mapper.writeValueAsString(newJson)
//            println "New Json: ${newJsonString}"

		then: "New Json should match Original Json"
			JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)
	
	}
	
	def "bundle with statement and tlp"() {
		when:"setup file access to bundle"
		
			String bundleJson = getClass()
					.getResource("/stix/baseline/json/sdo/markings/datamarkings.json").getText("UTF-8")
		
		then: "Parse json into bundle"
			Bundle bundle = (Bundle)StixParsers.parseBundle(bundleJson)
			println bundle.inspect()
			println bundle.toJsonString()
			
		then: "Convert Bundle to Json"
			JsonNode originalJson = mapper.readTree(bundle.toJsonString())
			String originalJsonString = mapper.writeValueAsString(originalJson)
			println "Original Json: ${originalJsonString}"

		then: "Parse Json back into Bundle Object"
			Bundle parsed = StixParsers.parseBundle(originalJsonString)
			println "Parsed Object: ${parsed}"

		then: "Convert Parsed Bundlen Object back to into Json"
			JsonNode newJson =  mapper.readTree(parsed.toJsonString())
			String newJsonString = mapper.writeValueAsString(newJson)
			println "New Json: ${newJsonString}"

		then: "New Json should match Original Json"
			JSONAssert.assertEquals(originalJsonString, newJsonString, JSONCompareMode.NON_EXTENSIBLE)
	
	}
}
