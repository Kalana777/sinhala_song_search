package com.kalana.elastic.sinhalasongsearch.repository;


import com.kalana.elastic.sinhalasongsearch.models.Song;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SongsRepo extends ElasticsearchRepository<Song, String> {
}
