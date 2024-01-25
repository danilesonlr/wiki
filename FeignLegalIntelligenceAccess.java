package projuris.legalintelligence;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import projuris.legalintelligence.domain.dto.LegalAccessDTO;

@FeignClient(name = "legal-service", url = "${legal-intelligence.embedded.access.client.microsoft.url}")
public interface FeignLegalIntelligenceAccess {

  public static final String TENANT_ID = "tenant_id";

  @PostMapping(value = "/{tenant_id}/oauth2/v2.0/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public JsonNode accessToken(@PathVariable(TENANT_ID) String id, @RequestBody LegalAccessDTO dto);
}
