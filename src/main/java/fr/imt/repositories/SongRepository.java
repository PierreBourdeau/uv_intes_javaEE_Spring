package fr.imt.repositories;

import fr.imt.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, String> {
    List<Song> findByTitleContainingIgnoreCase(String title);
    List<Song> findByArtist(String artist);
}
