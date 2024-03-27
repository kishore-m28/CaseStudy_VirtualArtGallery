package com.hexaware.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.hexaware.dao.VirtualArtGalleryImpl;
import com.hexaware.model.Artwork;
import com.hexaware.model.Gallery;
import com.hexaware.exception.ArtWorkNotFoundException;
import com.hexaware.exception.UserNotFoundException;

public class VirtualArtGalleryTest {

    private VirtualArtGalleryImpl gallery = new VirtualArtGalleryImpl();

    /*@Test
    public void testAddArtwork() {
        Artwork artwork = new Artwork(4, "Persistence ", "masterpiece ", java.sql.Date.valueOf("1931-01-01"), "oil", "persistence.jpg", 1);
        assertTrue(gallery.addArtwork(artwork));
    }

    @Test
    public void testUpdateArtwork() {
        Artwork artwork = new Artwork(2, "Scream", "anti-war ", java.sql.Date.valueOf("1937-01-01"), "oil", "scream.jpg", 2);
        assertTrue(gallery.updateArtwork(artwork));
    }

    @Test
    public void testRemoveArtwork() {
        assertTrue(gallery.removeArtwork(1));
    }*/

    @Test
    public void testSearchArtworks() {
        List<Artwork> searchResults = gallery.searchArtworks("nan");
        assertFalse(searchResults.isEmpty());
    }

    /*@Test
    public void testAddGallery() {
        // Implement test case for adding a new gallery
    	assertTrue(gallery.addGallery(104,"Tate", "Modern", "london", 1, "9:30-16:30"));
    }

    @Test
    public void testUpdateGallery() {
        // Test updating gallery information
        // Assuming method signature is similar to updateArtwork() in VirtualArtGalleryImpl
        assertTrue(gallery.updateGallery(102, "Unicorn", "Sky", "World", 1, "9:30-16:30"));
    }

    @Test
    public void testRemoveGallery() {
        // Test removing a gallery
        // Assuming method signature is similar to removeArtwork() in VirtualArtGalleryImpl
        assertTrue(gallery.removeGallery(1));
    }

    @Test
    public void testSearchGalleries() {
        // Test searching galleries
        // Assuming method signature is similar to searchArtworks() in VirtualArtGalleryImpl
        List<Gallery> searchResults = gallery.searchGalleries("DESC");
        assertFalse(searchResults.isEmpty());
    }*/
}

