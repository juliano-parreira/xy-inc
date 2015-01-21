package br.com.jcp.xyinc.ejb.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PointOfInterestAndDistanceDto  implements java.io.Serializable {
	
	private static final long serialVersionUID = 8454336274144508575L;
	
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "position_x")
	private int positionX;
	@Column(name = "position_y")
	private int positionY;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "distance")
	private double distance;

	public PointOfInterestAndDistanceDto() {
	}

	public PointOfInterestAndDistanceDto(Long id, String name, int positionX, int positionY, Date createDate, double distance) {
		this.id = id;
		this.name = name;
		this.positionX = positionX;
		this.positionY = positionY;
		this.createDate = createDate;
		this.distance = distance;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPositionX() {
		return this.positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return this.positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "PointAndDistanceDTO [id=" + id + ", name=" + name
				+ ", positionX=" + positionX + ", positionY=" + positionY
				+ ", createDate=" + createDate + ", distance=" + distance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		PointOfInterestAndDistanceDto other = (PointOfInterestAndDistanceDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}