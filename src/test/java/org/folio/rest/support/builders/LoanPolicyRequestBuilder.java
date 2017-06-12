package org.folio.rest.support.builders;

import io.vertx.core.json.JsonObject;

import java.util.UUID;

public class LoanPolicyRequestBuilder {

  private final UUID id;

  public LoanPolicyRequestBuilder() {
    this(UUID.randomUUID());
  }

  private LoanPolicyRequestBuilder(UUID id) {
    this.id = id;
  }

  public JsonObject create() {
    JsonObject request = new JsonObject();

    if(id != null) {
      request.put("id", id.toString());
    }
    
    request.put("name", "Example Loan Policy");
    request.put("description", "An example loan policy");
    request.put("loanable", true);

    JsonObject loansPolicy = new JsonObject();

    loansPolicy.put("profileId", "ROLLING");
    loansPolicy.put("period", createPeriod(1, "MONTH"));
    loansPolicy.put("closedLibraryDueDateManagementId", "KEEP_CURRENT_DATE");
    loansPolicy.put("existingRequestsPeriod", createPeriod(1, "WEEK"));
    loansPolicy.put("gracePeriod", createPeriod(7, "DAYS"));

    request.put("loansPolicy", loansPolicy);

    request.put("renewable", true);

    JsonObject renewalsPolicy = new JsonObject();

    renewalsPolicy.put("unlimited", true);
    renewalsPolicy.put("renewFromId", "CURRENT_DUE_DATE");
    renewalsPolicy.put("differentPeriod", true);
    renewalsPolicy.put("period", createPeriod(30, "DAYS"));

    request.put("renewalsPolicy", renewalsPolicy);

    return request;
  }

  private JsonObject createPeriod(Integer duration, String intervalId) {
    JsonObject period = new JsonObject();

    period.put("duration", duration);
    period.put("intervalId", intervalId);

    return period;
  }

  public LoanPolicyRequestBuilder withId(UUID id) {
    return new LoanPolicyRequestBuilder(id);
  }

  public LoanPolicyRequestBuilder withNoId() {
    return new LoanPolicyRequestBuilder(null);
  }
}
