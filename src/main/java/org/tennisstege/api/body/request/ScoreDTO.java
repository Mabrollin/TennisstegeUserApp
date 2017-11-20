package org.tennisstege.api.body.request;

import java.util.List;

public class ScoreDTO {
	
private List<List<Integer>> setsAndGames;

public List<List<Integer>> getSetsAndGames() {
	return setsAndGames;
}

public void setSetsAndGames(List<List<Integer>> score) {
	this.setsAndGames = score;
}
	
	
	
}
