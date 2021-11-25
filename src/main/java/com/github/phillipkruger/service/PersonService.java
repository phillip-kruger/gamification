package com.github.phillipkruger.service;

import com.github.phillipkruger.model.Person;
import io.smallrye.graphql.api.Context;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

@ApplicationScoped
public class PersonService {
    private static final Logger LOG = Logger.getLogger(PersonService.class);
    BroadcastProcessor<Person> processor = BroadcastProcessor.create();
    
    @PersistenceContext(name="PersonDS")
    EntityManager em;
    
    @Inject
    Context context;
    
    @Transactional
    public Person getPerson(Long id){
        LOG.info("======= Getting person [" + id +"] =======");
        return em.find(Person.class,id);
    }
    
    @Transactional    
    public List<Person> getPeople(){
        LOG.info("======= Getting all people =======");
        return (List<Person>)em.createQuery("SELECT p FROM Person p")
                .getResultList();
    }
    

    @Transactional
    public Person updateOrCreate(Person person) {
        
        System.err.println("person 1 = " + person);
        if(person.getId()==null){
            
            em.persist(person);
            System.err.println("person 2 = " + person);
            processor.onNext(person);
            return person;
        }else{
            return em.merge(person);
        }
    }

    @Transactional
    public Person delete(Long id) {
        Person p = em.find(Person.class,id);

        if(p!=null){
            em.remove(p);
        }
        return p;
    }

    public Multi<Person> personListener(){
        return processor;
    }
    
}
