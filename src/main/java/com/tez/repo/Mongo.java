/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tez.repo;

import com.tez.config.SpringMongoConfig;
import com.tez.model.Movie;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author celalkd
 */
public class Mongo {
    
    public ApplicationContext ctx;
    public MongoOperations mongoOperation;
    
    public Mongo(){
        this.ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	this.mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    }
    
    
    public Iterable<Movie> findByTags(String director, int yearMin, int yearMax, List<String> starring
                                                                    ,List<String> genre, double rating)
    {
        Criteria criteria = new Criteria();
        ArrayList<Criteria> criterias = new ArrayList<Criteria>();
        
        if(director!=null)
            criterias.add(Criteria.where("director").is(director));
        
        
        if(yearMin!=-1 && yearMax!=-1)
            criterias.add(Criteria.where("year").gte(yearMin).lte(yearMax));
        else if(yearMin!=-1)
            criterias.add(Criteria.where("year").gte(yearMin));
        else if(yearMax!=-1)
            criterias.add(Criteria.where("year").lte(yearMax));
        

        if(!starring.isEmpty())
            criterias.add(Criteria.where("starring").all(starring));
       
        if(!genre.isEmpty())
            criterias.add(Criteria.where("genre").all(genre));
       
        if(rating!=-1)
             criterias.add(Criteria.where("genre").all(genre));
        
        
        Criteria[] criteriaArray = criterias.toArray(new Criteria[criterias.size()]);
        criteria = criteria.andOperator(criteriaArray);
        Query query = new Query().addCriteria(criteria);

        return mongoOperation.find(query, Movie.class);

    }
}
