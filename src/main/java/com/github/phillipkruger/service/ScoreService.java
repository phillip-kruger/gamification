package com.github.phillipkruger.service;

import com.github.phillipkruger.model.Score;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ScoreService {
    private static final Logger LOG = Logger.getLogger(ScoreService.class);
    
    private final Map<String,List<Score>> scoreDatabase = new HashMap<>();
    
    public List<Score> getScores(String idNumber){
        LOG.info("======= Getting scores [" + idNumber + "] =======");
        return scoreDatabase.get(idNumber);
    }
    
    public List<List<Score>> getScores(List<String> idNumbers){
        LOG.info("======= Getting scores " + idNumbers + " =======");
        List<List<Score>> allscores = new ArrayList<>();
        for(String idNumber:idNumbers){
            allscores.add(scoreDatabase.get(idNumber));
        }
        return allscores;
    }
    
    @PostConstruct
    void init(){
        try(InputStream jsonStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("score.json")){
            if(jsonStream!=null){
                List<List<Score>> loaded = JSONB.fromJson(jsonStream, new ArrayList<List<Score>>(){}.getClass().getGenericSuperclass());
                for(List<Score> s:loaded){
                    scoreDatabase.put(ids.pop(), s);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private static final Jsonb JSONB = JsonbBuilder.create(new JsonbConfig().withFormatting(true));
    
    private static Stack<String> ids = new Stack<>();
    static{
        ids.addAll(Arrays.asList(new String[]{"797-95-4822","373-95-3047","097-87-6795","347-01-8880","733-86-4423","560-99-2165","091-07-5401","539-70-2014","029-18-5986","287-58-0690"}));
    }
}