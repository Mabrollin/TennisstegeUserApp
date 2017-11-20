package org.tennisstege.api.JPA.entitymodell;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum MatchOutcome {
	 @JsonProperty("challengerWon")
	CHALLANGER_WON,
	 @JsonProperty("challengedWon")
	CHALLANGED_WON,
	 @JsonProperty("draw")
	DRAW,
	 @JsonProperty("cancelled")
	CANCELLED;
}
