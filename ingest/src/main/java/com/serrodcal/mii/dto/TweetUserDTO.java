package com.serrodcal.mii.dto;

public final class TweetUserDTO {
	private String idStr;
	private String name;
	private String screenName;
	private Integer followers;
	private Integer friends;

	public TweetUserDTO() { }

	public TweetUserDTO(String idStr, String name, String screenName, Integer followers, Integer friends) {
		this.idStr = idStr;
		this.name = name;
		this.screenName = screenName;
		this.followers = followers;
		this.friends = friends;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}

	public Integer getFriends() {
		return friends;
	}

	public void setFriends(Integer friends) {
		this.friends = friends;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStr == null) ? 0 : idStr.hashCode());
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
		TweetUserDTO other = (TweetUserDTO) obj;
		if (idStr == null) {
			if (other.idStr != null)
				return false;
		} else if (!idStr.equals(other.idStr))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TweetUser [idStr=" + idStr + ", name=" + name + ", screenName=" + screenName + ", followers="
				+ followers + ", friends=" + friends + "]";
	}

}
