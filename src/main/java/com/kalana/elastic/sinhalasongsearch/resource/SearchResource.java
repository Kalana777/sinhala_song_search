package com.kalana.elastic.sinhalasongsearch.resource;

import com.kalana.elastic.sinhalasongsearch.models.Song;
import com.kalana.elastic.sinhalasongsearch.repository.SongsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/search")
public class SearchResource {

    @Autowired
    SongsRepo songsRepo;

    @PostMapping(value = "/songs", consumes =  MediaType.APPLICATION_JSON_VALUE)
    public int saveSongs(@RequestBody List<Song> songsData) {
        songsRepo.saveAll(songsData);
        return songsData.size();
    }

    @GetMapping("/findAllSongs")
    public Iterable<Song> findAllSongs() {
        return songsRepo.findAll();
    }
}
