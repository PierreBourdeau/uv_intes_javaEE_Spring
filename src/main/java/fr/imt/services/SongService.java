package fr.imt.services;
import fr.imt.exceptions.SongNotFoundException;
import fr.imt.models.Song;
import fr.imt.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song getSong(String id) throws SongNotFoundException {
        return songRepository.findById(id).orElseThrow(SongNotFoundException::new);
    }

    public List<Song> searchSong(String query) {
        return songRepository.findByTitleContainingIgnoreCase(query);
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public void addSong(Song song) {
        songRepository.save(song);
    }

    public void deleteSong(String guid) throws SongNotFoundException {
        Song song = getSong(guid);
        songRepository.delete(song);
    }

    public List<Song> getArtistSongs(String artist) {
        return songRepository.findByArtist(artist);
    }

    public void editSong(Song song, String guid) throws SongNotFoundException {
        getSong(guid);
        song.setGuid(guid);
        songRepository.save(song);
    }
}
