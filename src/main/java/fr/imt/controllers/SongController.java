package fr.imt.controllers;

import fr.imt.exceptions.SongNotFoundException;
import fr.imt.models.Song;
import fr.imt.services.SongService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongService songService;

    // RETURN ALL SONGS
    @GetMapping(value = "/songs")
    public ResponseEntity<List<Song>> listSongs() {
        return new ResponseEntity<>(songService.getSongs(), HttpStatus.OK);
    }

    @GetMapping(value = "/songs/search")
    public ResponseEntity<List<Song>> searchSong(@RequestParam("q") final String query) {
        return new ResponseEntity<>(songService.searchSong(query), HttpStatus.OK);
    }

    //RETURN SONG IDENTIFIED BY GUID
    @GetMapping(value = "/songs/{guid}")
    public ResponseEntity<Song> getSong(@PathVariable("guid") final String guid) {
        try {
            return new ResponseEntity<>(songService.getSong(guid),HttpStatus.OK);
        } catch (SongNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //RETURN ALL THE SONG OF THE CURRENT ARTIST
    @GetMapping(value = "/artists/{artist}")
    public ResponseEntity<List<Song>> getArtistSongs(@PathVariable("artist") final String artist) {
        return new ResponseEntity<>(songService.getArtistSongs(artist), HttpStatus.OK);
    }

    //ADD SONG
    @PostMapping( value = "/songs")
    public ResponseEntity addSong(@Valid @RequestBody Song song){
        songService.addSong(song);
        return new ResponseEntity(song, HttpStatus.CREATED);
    }

    //MODIFY SONG
    @PutMapping( value = "/songs/{guid}")
    public ResponseEntity<Song> editSong(@PathVariable("guid") final String guid, @Valid @RequestBody Song song) {
        try {
            songService.editSong(song, guid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(SongNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //DELETE SONG
    @DeleteMapping( value = "/songs/{guid}")
    public ResponseEntity deleteSong(@PathVariable("guid") final String guid) {
        try {
            songService.deleteSong(guid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (SongNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
