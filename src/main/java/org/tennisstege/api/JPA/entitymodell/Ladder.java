package org.tennisstege.api.JPA.entitymodell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Ladder {
	public enum LadderType{
		POSITION, RATING
	}
	
	public enum Visability {
		PUBLIC, PRIVATE
	}
	
	public enum Joinability {
		BY_ADMIN_INVITE, BY_MEMBER_INVITE, PUBLIC
	}
	
	private Map<String, Player> playerMap;
	
	private List<String> positionList;
	
	@DBRef(lazy = true)
	private Set<User> admins;
	
	@DBRef(lazy = true)
	private Set<Challenge> challenges;
	
	@DBRef(lazy = true)
	private Set<Record> records;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((admins == null) ? 0 : admins.hashCode());
		result = prime * result + ((challenges == null) ? 0 : challenges.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((joinability == null) ? 0 : joinability.hashCode());
		result = prime * result + ((ladderType == null) ? 0 : ladderType.hashCode());
		result = prime * result + ((positionList == null) ? 0 : positionList.hashCode());
		result = prime * result + (int) (maxSize ^ (maxSize >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((playerMap == null) ? 0 : playerMap.hashCode());
		result = prime * result + (positionUpdateable ? 1231 : 1237);
		result = prime * result + (ratingUpdateable ? 1231 : 1237);
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		result = prime * result + ((visability == null) ? 0 : visability.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ladder other = (Ladder) obj;
		if (admins == null) {
			if (other.admins != null)
				return false;
		} else if (!admins.equals(other.admins))
			return false;
		if (challenges == null) {
			if (other.challenges != null)
				return false;
		} else if (!challenges.equals(other.challenges))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (joinability != other.joinability)
			return false;
		if (ladderType != other.ladderType)
			return false;
		if (positionList == null) {
			if (other.positionList != null)
				return false;
		} else if (!positionList.equals(other.positionList))
			return false;
		if (maxSize != other.maxSize)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (playerMap == null) {
			if (other.playerMap != null)
				return false;
		} else if (!playerMap.equals(other.playerMap))
			return false;
		if (positionUpdateable != other.positionUpdateable)
			return false;
		if (ratingUpdateable != other.ratingUpdateable)
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		if (visability != other.visability)
			return false;
		return true;
	}

	private String id;
	
	private String name;
	
	private long maxSize;
	
	
	private LadderType ladderType;
	
	private Visability visability;
	
	private Joinability joinability;
	
	private boolean ratingUpdateable, positionUpdateable;
	
	public Map<String, Player> getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(Map<String, Player> playerMap) {
		this.playerMap = playerMap;
	}

	public List<String> getPositionList() {
		return positionList;
	}

	public void setLeaderBoard(List<String> positionList) {
		this.positionList = positionList;
	}

	public Set<User> getAdmins() {
		return admins;
	}

	public void setAdmins(Set<User> admins) {
		this.admins = admins;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Ladder(String name, User creator, LadderType ladderType, Visability visability, Joinability joinability) {
		this.playerMap = new HashMap<>();
		this.positionList = new ArrayList<>();
		this.challenges = new HashSet<>();
		this.records = new HashSet<>();
		this.name = name;
		this.admins = new HashSet<>();
		admins.add(creator);
		
		this.ladderType = ladderType;
		this.visability = visability;
		this.joinability = joinability;
		
		this.setPositionUpdateable(true);
		this.setRatingUpdateable(true);
		
	}

	public Set<Record> getRecords() {
		return records;
	}
	
	public Ladder() {
		
	}

	public void setRecords(Set<Record> records) {
		this.records = records;
	}


	public Set<Challenge> getChallenges() {
		return challenges;
	}

	public void setChallenges(Set<Challenge> challenges) {
		this.challenges = challenges;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
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

	public boolean isRatingUpdateable() {
		return ratingUpdateable;
	}

	public void setRatingUpdateable(boolean ratingUpdateable) {
		this.ratingUpdateable = ratingUpdateable;
	}

	public boolean isPositionUpdateable() {
		return positionUpdateable;
	}

	public void setPositionUpdateable(boolean positionUpdateable) {
		this.positionUpdateable = positionUpdateable;
	}

	public int positionOf(Player player) {
		return positionList.indexOf(player.getUser().getUsername());
	}
}
