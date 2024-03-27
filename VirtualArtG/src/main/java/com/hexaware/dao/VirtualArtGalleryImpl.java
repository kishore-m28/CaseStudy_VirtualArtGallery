package com.hexaware.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import com.hexaware.model.Artwork;
import com.hexaware.model.Gallery;
import com.hexaware.util.DbConnUtil;
import com.hexaware.exception.*;

public class VirtualArtGalleryImpl implements IVirtualArtGallery {
	
	Scanner sc = new Scanner(System.in);
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * Adds an artwork to the virtual art gallery.
	 * 
	 * @param artwork The Artwork object to be added.
	 * @return True if the artwork was successfully added, false otherwise.
	 */
    public boolean addArtwork(Artwork artwork) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Artwork (ArtworkID, Title, Description, CreationDate, Medium, ImageURL, ArtistID) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            ps.setInt(1, artwork.getArtworkID());
            ps.setString(2, artwork.getTitle());
            ps.setString(3, artwork.getDescription());
            ps.setDate(4, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(5, artwork.getMedium());
            ps.setString(6, artwork.getImageURL());
            ps.setInt(7, artwork.getArtistID());
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateArtwork(Artwork artwork) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("UPDATE Artwork SET Title = ?, Description = ?, CreationDate = ?, Medium = ?, ImageURL = ?, ArtistID = ? WHERE ArtworkID = ?")) {
            ps.setString(1, artwork.getTitle());
            ps.setString(2, artwork.getDescription());
            ps.setDate(3, new java.sql.Date(artwork.getCreationDate().getTime()));
            ps.setString(4, artwork.getMedium());
            ps.setString(5, artwork.getImageURL());
            ps.setInt(6, artwork.getArtistID());
            ps.setInt(7, artwork.getArtworkID());
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean removeArtwork(int artworkID) {
        String sql = "DELETE FROM Artwork WHERE ArtworkID = ?";
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artworkID);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override

    public Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException {
        String sql = "SELECT * FROM Artwork WHERE ArtworkID = ?";
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artworkID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(rs.getInt("ArtworkID"));
                    artwork.setTitle(rs.getString("Title"));
                    artwork.setDescription(rs.getString("Description"));
                    artwork.setCreationDate(rs.getDate("CreationDate"));
                    artwork.setMedium(rs.getString("Medium"));
                    artwork.setImageURL(rs.getString("ImageURL"));
                    artwork.setArtistID(rs.getInt("ArtistID"));
                    return artwork;
                } else {
                    throw new ArtWorkNotFoundException("Artwork with ID " + artworkID + " not found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public List<Artwork> searchArtworks(String keyword) {
        List<Artwork> artworks = new ArrayList<>();
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Artwork WHERE Title LIKE ? OR Description LIKE ?")) {
            String searchTerm = "%" + keyword + "%";
            ps.setString(1, searchTerm);
            ps.setString(2, searchTerm);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(rs.getInt("ArtworkID"));
                    artwork.setTitle(rs.getString("Title"));
                    artwork.setDescription(rs.getString("Description"));
                    artwork.setCreationDate(rs.getDate("CreationDate"));
                    artwork.setMedium(rs.getString("Medium"));
                    artwork.setImageURL(rs.getString("ImageURL"));
                    artwork.setArtistID(rs.getInt("ArtistID"));
                    artworks.add(artwork);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artworks;
    }

    public boolean addArtworkToFavorite(int userId, int artworkId) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO User_Favorite_Artwork (UserID, ArtworkID) VALUES (?, ?)")) {
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeArtworkFromFavorite(int userId, int artworkId) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM User_Favorite_Artwork WHERE UserID = ? AND ArtworkID = ?")) {
            ps.setInt(1, userId);
            ps.setInt(2, artworkId);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException {
        List<Artwork> favoriteArtworks = new ArrayList<>();
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("SELECT a.* FROM Artwork a INNER JOIN User_Favorite_Artwork ufa ON a.ArtworkID = ufa.ArtworkID WHERE ufa.UserID = ?")) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Artwork artwork = new Artwork();
                    artwork.setArtworkID(rs.getInt("ArtworkID"));
                    artwork.setTitle(rs.getString("Title"));
                    artwork.setDescription(rs.getString("Description"));
                    artwork.setCreationDate(rs.getDate("CreationDate"));
                    artwork.setMedium(rs.getString("Medium"));
                    artwork.setImageURL(rs.getString("ImageURL"));
                    artwork.setArtistID(rs.getInt("ArtistID"));
                    favoriteArtworks.add(artwork);
                }
                if (favoriteArtworks.isEmpty()) {
                    throw new UserNotFoundException("User with ID " + userId + " has no favorite artworks");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteArtworks;
    }
    public boolean addGallery(int GalleryID,String name, String description, String location, int curator, String openingHours) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Gallery (GalleryID,Name, Description, Location, Curator, OpeningHours) VALUES (?, ?, ?, ?, ?, ?)")) {
        	ps.setInt(1, GalleryID);
        	ps.setString(2, name);
            ps.setString(3, description);
            ps.setString(4, location);
            ps.setInt(5, curator);
            ps.setString(6, openingHours);
            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updateGallery(int galleryId, String name, String description, String location, int curator, String openingHours) {
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("UPDATE Gallery SET Name = ?, Description = ?, Location = ?, Curator = ?, OpeningHours = ? WHERE GalleryID = ?")) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setInt(4, curator);
            ps.setString(5, openingHours);
            ps.setInt(6, galleryId);
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeGallery(int galleryId) {
        String sql = "DELETE FROM Gallery WHERE GalleryID = ?";
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, galleryId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<Gallery> searchGalleries(String keyword) {
        List<Gallery> galleries = new ArrayList<>();
        try (Connection conn = DbConnUtil.getDBConn();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Gallery WHERE Name LIKE ? OR Description LIKE ?")) {
            String searchTerm = "%" + keyword + "%";
            ps.setString(1, searchTerm);
            ps.setString(2, searchTerm);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Gallery gallery = new Gallery();
                    gallery.setGalleryID(rs.getInt("GalleryID"));
                    gallery.setName(rs.getString("Name"));
                    gallery.setDescription(rs.getString("Description"));
                    gallery.setLocation(rs.getString("Location"));
                    gallery.setCurator(rs.getInt("Curator"));
                    gallery.setOpeningHours(rs.getString("OpeningHours"));
                    galleries.add(gallery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return galleries;
    }
}

