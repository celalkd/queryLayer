/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tez.query;

import com.tez.config.SpringMongoConfig;
import com.tez.model.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author celalkd
 */
public class MongoDB {
    
    public ApplicationContext ctx;
    public MongoOperations mongoOperation;
    
    public MongoDB(){
        this.ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	this.mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    }
    
    public Movie searchById(int input_id){
        Query query = new Query(Criteria.where("id").is(input_id));
	return (mongoOperation.findOne(query, Movie.class));	
    }
    public Movie searchByTitle(String input_title){
        Query query = new Query(Criteria.where("title").is(input_title));
	return (mongoOperation.findOne(query, Movie.class));	
    }
}
