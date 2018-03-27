package org.tennisstege.api.body.request;

import org.tennisstege.api.JPA.entitymodell.Ladder.Joinability;
import org.tennisstege.api.JPA.entitymodell.Ladder.LadderType;
import org.tennisstege.api.JPA.entitymodell.Ladder.Visability;

public class NewLadderDTO {
	private String ladderName;
	private String creator;
	private LadderType ladderType;
	private Visability visability;
	private Joinability joinability; 
	
	public String getLadderName() {
		return ladderName;
	}
	public void setLadderName(String name) {
		this.ladderName = name;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public LadderType getLadderType() {
		return ladderType;
	}
	public void setLadderType(LadderType ladderType) {
		this.ladderType = ladderType;
	}
	public Visability getVisability() {
		return visability;
	}
	public void setVisability(Visability visability) {
		this.visability = visability;
	}
	public Joinability getJoinability() {
		return joinability;
	}
	public void setJoinability(Joinability joinability) {
		this.joinability = joinability;
	}
	

}
