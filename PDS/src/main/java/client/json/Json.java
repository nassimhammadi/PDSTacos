package main.java.client.json;


import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json<T> {

     private final Class<T> type;

     public Json(Class<T> type) {
          this.type = type;
     }

     public Class<T> getMyType() {
         return this.type;
     }
     
     
 	public  String serialize(T object){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(object);
	}
	
	
	public T deSerialize(String monJson) throws IOException{
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(monJson, this.getMyType());
	}

     
     
}
