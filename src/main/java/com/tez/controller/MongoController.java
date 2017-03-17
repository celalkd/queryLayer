/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tez.controller;

import com.tez.config.SpringMongoConfig;
import com.tez.model.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author celalkd
 */

@RestController
public class MongoController {
    public ApplicationContext ctx;
    public MongoOperations mongoOperation;
    
    public MongoController(){
        this.ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	this.mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    }
    
    @RequestMapping("/findbytitle")
    public Iterable<Movie> findByTitle(@RequestParam(value="title") String title) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return mongoOperation.find(query, Movie.class);
    }
    
}
