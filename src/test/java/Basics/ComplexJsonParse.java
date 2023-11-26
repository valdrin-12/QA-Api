package Basics;

import Payload.Payload;
import com.fasterxml.jackson.annotation.JsonAlias;

import io.restassured.path.json.JsonPath;

import java.sql.SQLOutput;

public class ComplexJsonParse {
    public static void main(String[] args) {
//add commeent
//print number of courses
        JsonPath js=new JsonPath(Payload.CoursePrice());
        int count= js.getInt("courses.size()");
        System.out.println(count); // 4
        //Print Purchase amount
      int totalAmount=  js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount); // 1162
        //Print first course
        String titleFirstCourse=js.get("courses[2].title");
        System.out.println(titleFirstCourse); // Selenium Python
//Print all titles and prices
        for (int i=0;i<count;i++){
          String courseTitles=js.get("courses["+i+"].title");
          System.out.println(js.get("courses["+i+"].price").toString());
          System.out.println(courseTitles);
        }
        //Print number of copies sold by RPA
        System.out.println("Print number of copies sold by RPA\n");

        for (int i=0;i<count;i++){
            String courseTitles=js.get("courses["+i+"].title");
            if (courseTitles.equalsIgnoreCase("RPA")){
                int copies=js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;
            }
        }
    }
}