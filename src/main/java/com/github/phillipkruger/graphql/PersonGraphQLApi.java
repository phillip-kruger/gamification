package com.github.phillipkruger.graphql;

import com.github.phillipkruger.model.CurencyCode;
import com.github.phillipkruger.model.ExchangeRate;
import com.github.phillipkruger.model.Person;
import com.github.phillipkruger.model.Score;
import com.github.phillipkruger.service.ExchangeRateService;
import com.github.phillipkruger.service.PersonService;
import com.github.phillipkruger.service.ScoreService;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@GraphQLApi
public class PersonGraphQLApi {

    @Inject
    PersonService personService;

    @Inject 
    ScoreService scoreService;
    
    @Inject
    ExchangeRateService exchangeRateService;
    
    @Query
    public CompletionStage<Person> getPerson(Long id){
        return CompletableFuture.supplyAsync(() -> personService.getPerson(id));
    }

    @Query
    public List<Person> getPeople(){
        return personService.getPeople();
    }
    
    @Mutation
    public Person updatePerson(Person person){
        return personService.updateOrCreate(person);
    }

    @Mutation
    public Person deletePerson(Long id){
        return personService.delete(id);
    }
    
    @Subscription
    public Multi<Person> personAdded(){
        return personService.personListener();
    }
    
    public List<List<Score>> getScores(@Source List<Person> people) {
        
        
        List<String> ids = people.stream().map(Person::getIdNumber).collect(Collectors.toList());
        return scoreService.getScores(ids);
    }
    
    public CompletionStage<ExchangeRate> getExchangeRate(@Source Person person, CurencyCode against){
        return CompletableFuture.supplyAsync(() -> exchangeRateService.getExchangeRate(against,person.getCurencyCode()));    
    }
}