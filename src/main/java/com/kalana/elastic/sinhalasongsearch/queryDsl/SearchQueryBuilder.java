package com.kalana.elastic.sinhalasongsearch.queryDsl;

import com.kalana.elastic.sinhalasongsearch.models.Song;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchQueryBuilder {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    IndexCoordinates index = IndexCoordinates.of("test_index_my");

    public SearchScrollHits<Song> getAll(String text) {
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(text).lenient(true).field("english_track_name").field("sinhala_track_name", 50.0f)
                        .field("english_album_name").field("sinhala_album_name").field("english_artist_name", 50.0f).field("sinhala_artist_name", 50.0f)
                        .field("lyrics", 10.0f).fuzziness(Fuzziness.AUTO).analyzer("keyword"))
                .should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true).field("english_track_name").field("sinhala_track_name")
                        .field("english_album_name").field("sinhala_album_name").field("english_artist_name").field("sinhala_artist_name")
                        .field("lyrics").analyzer("pattern"))
                .should(QueryBuilders.multiMatchQuery(text).field("english_track_name").field("sinhala_track_name", 50.0f)
                        .field("english_album_name").field("sinhala_album_name", 50.0f).field("english_artist_name").field("sinhala_artist_name", 50.0f)
                        .field("lyrics", 10.0f).fuzziness(Fuzziness.AUTO).analyzer("keyword").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.searchScrollStart(1000, nativeSearchQuery, Song.class, index);
//        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.queryForObject( nativeSearchQuery,Song.class, index);
        return ssongs;
    }

    public SearchScrollHits<Song> getFromLyrics(String text) {
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("lyrics",5))
                .should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("lyrics", 5));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.searchScrollStart(1000, nativeSearchQuery, Song.class, index);
//        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.queryForObject( nativeSearchQuery,Song.class, index);
        return ssongs;
    }

    public SearchScrollHits<Song> getFromAlbums(String text) {
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("sinhala_album_name", 5))
                .should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("sinhala_album_name", 5));
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.searchScrollStart(1000, nativeSearchQuery, Song.class, index);
//        SearchScrollHits<Song> ssongs = elasticsearchRestTemplate.queryForObject( nativeSearchQuery,Song.class, index);
        return ssongs;
    }
}
