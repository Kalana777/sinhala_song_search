package com.kalana.elastic.sinhalasongsearch.resource;

import com.kalana.elastic.sinhalasongsearch.models.Song;
import com.kalana.elastic.sinhalasongsearch.queryDsl.SearchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/manual/search")
public class ManualSearch {

    @Autowired
    private SearchQueryBuilder searchQueryBuilder;

    @CrossOrigin
    @GetMapping(value = "/{text}")
    public SearchScrollHits<Song> getAll(@PathVariable final String text) {

        String str = text;
        String[] arrOfStr = str.split(" ", 0);

        for (String a : arrOfStr)
            System.out.println(a);

        String lyr = "Lyrics";
        String alb = "Album";

        boolean lyr_present = Arrays.asList(arrOfStr).contains(lyr);

        for (String s: arrOfStr){
            if (lyr.equalsIgnoreCase(s)){
                lyr_present = true;
            }
        }
        System.out.println(lyr_present);
        if (lyr_present){
            List targetList = new ArrayList(Arrays.asList(arrOfStr));
            targetList.remove(lyr);
            targetList.remove(lyr.toLowerCase());
            targetList.remove(lyr.toUpperCase());
            targetList.remove(alb);
            targetList.remove(alb.toLowerCase());
            targetList.remove(alb.toUpperCase());
            String text2 = String.join(" ", targetList);
            System.out.println("text 2: "+text2);
            return searchQueryBuilder.getFromLyrics(text2);
        }


        boolean alb_present = Arrays.asList(arrOfStr).contains(alb);

        for (String s: arrOfStr){
            if (alb.equalsIgnoreCase(s)){
                alb_present = true;
            }
        }
        System.out.println(alb_present);
        if (alb_present){
            List targetList = new ArrayList(Arrays.asList(arrOfStr));
            targetList.remove(alb);
            targetList.remove(alb.toLowerCase());
            targetList.remove(alb.toUpperCase());
            String text3 = String.join(" ", targetList);
            System.out.println("text 3: "+text3);
            return searchQueryBuilder.getFromAlbums(text3);
        }


        System.out.println(text);
        return searchQueryBuilder.getAll(text);
    }
}
