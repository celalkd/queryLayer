package com.tez.core;
import com.tez.model.*;
import com.tez.query.MongoDB;

public class App {

	public static void main(String[] args) {

            MongoDB mongodb = new MongoDB();
            System.out.println(mongodb.searchById(0));
            System.out.println(mongodb.searchByTitle("Pulp Fiction"));  
            
	}
}