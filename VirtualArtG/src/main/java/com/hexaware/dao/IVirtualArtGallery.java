package com.hexaware.dao;

import java.util.List;

import com.hexaware.exception.ArtWorkNotFoundException;
import com.hexaware.exception.UserNotFoundException;
import com.hexaware.model.*;

public interface IVirtualArtGallery {

    boolean addArtwork(Artwork artwork);
    boolean updateArtwork(Artwork artwork);
    boolean removeArtwork(int artworkID);
    Artwork getArtworkById(int artworkID) throws ArtWorkNotFoundException;
    List<Artwork> searchArtworks(String keyword);

    boolean addArtworkToFavorite(int userId, int artworkId);
    boolean removeArtworkFromFavorite(int userId, int artworkId);
    List<Artwork> getUserFavoriteArtworks(int userId) throws UserNotFoundException;
}

