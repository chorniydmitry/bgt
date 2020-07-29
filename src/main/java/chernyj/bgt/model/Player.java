package chernyj.bgt.model;

public class Player {
	private int id;
	private String name;
	private String bTag;
	private int place;
	//private Hero hero;
	private String heroId;
	private boolean isMainPlayer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public boolean isMainPlayer() {
		return isMainPlayer;
	}

	public void setMainPlayer(boolean isMainPlayer) {
		this.isMainPlayer = isMainPlayer;
	}

	public String getbTag() {
		return bTag;
	}

	public void setbTag(String bTag) {
		this.bTag = bTag;
	}

	public String getHeroId() {
		return heroId;
	}

	public void setHeroId(String heroId) {
		this.heroId = heroId;
	}

}