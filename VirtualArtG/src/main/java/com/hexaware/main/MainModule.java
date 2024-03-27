package com.hexaware.main;

import com.hexaware.dao.VirtualArtGalleryImpl;
import com.hexaware.model.Artwork;
import com.hexaware.exception.ArtWorkNotFoundException;
import  com.hexaware.exception.UserNotFoundException;

import java.util.List;
import java.util.Scanner;

public class MainModule {

    private static VirtualArtGalleryImpl gallery = new VirtualArtGalleryImpl();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nVirtual Art Gallery Menu:");
            System.out.println("1. Add Artwork");
            System.out.println("2. Update Artwork");
            System.out.println("3. Remove Artwork");
            System.out.println("4. Get Artwork by ID");
            System.out.println("5. Search Artworks");
            System.out.println("6. Add Artwork to Favorites");
            System.out.println("7. Remove Artwork from Favorites");
            System.out.println("8. Get User's Favorite Artworks");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
            case 1:
                // Add Artwork
                System.out.println("Enter Artwork details:");
                System.out.print("Artwork ID: ");
                int artworkIdAdd = scanner.nextInt();
                System.out.print("Title: ");
                String titleAdd = scanner.next();
                System.out.print("Description: ");
                String descriptionAdd = scanner.next();
                System.out.print("Creation Date (YYYY-MM-DD): ");
                String creationDateAdd = scanner.next();
                System.out.print("Medium: ");
                String mediumAdd = scanner.next();
                System.out.print("Image URL: ");
                String imageURLAdd = scanner.next();
                System.out.print("Artist ID: ");
                int artistIdAdd = scanner.nextInt();
                try {
                    Artwork artworkAdd = new Artwork(artworkIdAdd, titleAdd, descriptionAdd, java.sql.Date.valueOf(creationDateAdd), mediumAdd, imageURLAdd, artistIdAdd);
                    boolean added = gallery.addArtwork(artworkAdd);
                    if (added) {
                        System.out.println("***** Artwork added successfully. *****");
                    } else {
                        System.out.println("Failed to add artwork.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 2:
                // Update Artwork
                System.out.println("Enter Artwork ID to update:");
                int artworkIdUpdate = scanner.nextInt();
                System.out.println("Enter updated Artwork details:");
                System.out.print("Title: ");
                String titleUpdate = scanner.next();
                System.out.print("Description: ");
                String descriptionUpdate = scanner.next();
                System.out.print("Creation Date (YYYY-MM-DD): ");
                String creationDateUpdate = scanner.next();
                System.out.print("Medium: ");
                String mediumUpdate = scanner.next();
                System.out.print("Image URL: ");
                String imageURLUpdate = scanner.next();
                System.out.print("Artist ID: ");
                int artistIdUpdate = scanner.nextInt();
                try {
                    Artwork artworkUpdate = new Artwork(artworkIdUpdate, titleUpdate, descriptionUpdate, java.sql.Date.valueOf(creationDateUpdate), mediumUpdate, imageURLUpdate, artistIdUpdate);
                    boolean updated = gallery.updateArtwork(artworkUpdate);
                    if (updated) {
                        System.out.println("***** Artwork updated successfully. *****");
                    } else {
                        System.out.println("Failed to update artwork. Artwork ID not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 3:
                // Remove Artwork
                System.out.println("Enter Artwork ID to remove:");
                int artworkIdRemove = scanner.nextInt();
                try {
                    boolean removed = gallery.removeArtwork(artworkIdRemove);
                    if (removed) {
                        System.out.println("***** Artwork removed successfully. *****");
                    } else {
                        System.out.println("Failed to remove artwork. Artwork ID not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
                break;
            case 4:
                // Get Artwork by ID
                System.out.println("Enter Artwork ID:");
                int artworkIdGet = scanner.nextInt();
                try {
                    Artwork artwork = gallery.getArtworkById(artworkIdGet);
                    System.out.println("Artwork Details: --->");
                    System.out.println(artwork); 
                } catch (ArtWorkNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
            	System.out.println("Enter keyword to search artworks:");
                String keyword = scanner.next();
                List<Artwork> searchResults = gallery.searchArtworks(keyword);
                if (!searchResults.isEmpty()) {
                    System.out.println("Search Results: --->");
                    for (Artwork artwork : searchResults) {
                        System.out.println(artwork); // Using toString() method here
                    }
                } else {
                    System.out.println("No artworks found matching the keyword.");
                }
                break;
            case 6:
                // Add Artwork to Favorites
                System.out.println("Enter User ID:");
                int userIdAddFavorite = scanner.nextInt();
                System.out.println("Enter Artwork ID to add to favorites:");
                int artworkIdAddFavorite = scanner.nextInt();
                try {
                    boolean addedFavorite = gallery.addArtworkToFavorite(userIdAddFavorite, artworkIdAddFavorite);
                    if (addedFavorite) {
                        System.out.println("***** Artwork added to favorites successfully. *****");
                    } else {
                        System.out.println("Failed to add artwork to favorites. User or Artwork ID not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                }
                break;

                case 7:
                	System.out.println("Enter User ID:");
                    int userIdRemoveFavorite = scanner.nextInt();
                    System.out.println("Enter Artwork ID to remove from favorites:");
                    int artworkIdRemoveFavorite = scanner.nextInt();
                    boolean removedFavorite = gallery.removeArtworkFromFavorite(userIdRemoveFavorite, artworkIdRemoveFavorite);
                    if (removedFavorite) {
                        System.out.println("***** Artwork removed from favorites successfully. *****");
                    } else {
                        System.out.println("Failed to remove artwork from favorites. User or Artwork ID not found.");
                    }
                    break;
                case 8:
                    // Get User's Favorite Artworks
                    System.out.println("Enter User ID:");
                    int userId = scanner.nextInt();
                    try {
                        List<Artwork> favoriteArtworks = gallery.getUserFavoriteArtworks(userId);
                        System.out.println("User's Favorite Artworks: --->");
                        for (Artwork artwork : favoriteArtworks) {
                            System.out.println(artwork); // Using toString() method here
                        }
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    System.out.println("Exiting Virtual Art Gallery...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 9);

        scanner.close();
    }
}

