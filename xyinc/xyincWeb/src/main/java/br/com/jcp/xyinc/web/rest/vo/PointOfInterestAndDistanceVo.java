package br.com.jcp.xyinc.web.rest.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * VO used to represent Point Of Interest with or without distance.
 */
@XmlRootElement
public class PointOfInterestAndDistanceVo implements Serializable{
	
	private static final long serialVersionUID = -3473940149020746597L;
	
	private Long id;
	private String name;
	private Integer positionX;
	private Integer positionY;
	private Double distance;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPositionX() {
		return positionX;
	}
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	public Integer getPositionY() {
		return positionY;
	}
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "PointOfInterestAndDistanceVo [id=" + id + ", name=" + name
				+ ", positionX=" + positionX + ", positionY=" + positionY
				+ ", distance=" + distance + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distance == null) ? 0 : distance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((positionX == null) ? 0 : positionX.hashCode());
		result = prime * result
				+ ((positionY == null) ? 0 : positionY.hashCode());
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
		PointOfInterestAndDistanceVo other = (PointOfInterestAndDistanceVo) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (positionX == null) {
			if (other.positionX != null)
				return false;
		} else if (!positionX.equals(other.positionX))
			return false;
		if (positionY == null) {
			if (other.positionY != null)
				return false;
		} else if (!positionY.equals(other.positionY))
			return false;
		return true;
	}
}
