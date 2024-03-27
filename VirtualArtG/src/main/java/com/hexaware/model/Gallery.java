/**
 * @author KISHORE M
 */

package com.hexaware.model;

/**
 * Gallery class represents a gallery within the virtual art gallery system
 */

public class Gallery {
    private int galleryID;
    private String name;
    private String description;
    private String location;
    private int curator; 
    private String openingHours;

    public Gallery() {
        // Default constructor
    }
    /**
     * 
     * @param galleryID
     * @param name
     * @param description
     * @param location
     * @param curator
     * @param openingHours
     */
    public Gallery(int galleryID, String name, String description, String location, int curator, String openingHours) {
        this.galleryID = galleryID;
        this.name = name;
        this.description = description;
        this.location = location;
        this.curator = curator;
        this.openingHours = openingHours;
    }

    public int getGalleryID() {
        return galleryID;
    }

    public void setGalleryID(int galleryID) {
        this.galleryID = galleryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCurator() {
        return curator;
    }

    public void setCurator(int curator) {
        this.curator = curator;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

	@Override
	public String toString() {
		return "Gallery [galleryID=" + galleryID + ", name=" + name + ", description=" + description + ", location="
				+ location + ", curator=" + curator + ", openingHours=" + openingHours + "]";
	}
  
}

