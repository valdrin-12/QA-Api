package Pojo;

public class GetCourse {


    private String url;
   private String services;
   private Courses Courses;
   private String instructor;
   private String linkedIn;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Pojo.Courses getCourses() {
        return Courses;
    }

    public void setCourses(Pojo.Courses courses) {
        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }



}
