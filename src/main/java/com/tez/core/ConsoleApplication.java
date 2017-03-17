package com.tez.core;
import com.tez.model.*;
import com.tez.repo.Mongo;
import java.util.ArrayList;

public class ConsoleApplication {

	public static void main(String[] args) {

//          ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new ClassPathResource("SpringConfig.xml").getPath());
//          MovieRepository mongoRepo = context.getBean(MovieRepository.class);
            Mongo  mongo= new Mongo();
            
            
            ArrayList<String> starring = new ArrayList<String>();
            starring.add("Morgan Freeman");
            starring.add("Tim Robbins");
            
            ArrayList<String> genre = new ArrayList<String>();
            genre.add("Crime");
            genre.add("Drama");

            Iterable<Movie> movieList = mongo.findByTags("Frank Darabont", 1990, 2000, starring, genre, 8.0);
            
            for(Object o : movieList){
                System.out.println(o);
            }
            
	}
}
