//https://www.codewars.com/kata/5a04863f06d5b6387c0000a5 [7kyu]
public class People{
 private int age;
 private String name;
 private String lastName;
 private String city;
 private String job;
 private String GREET="hello";
 
 public int getAge() { return age; }
 public String getName() { return name; }
 public String getLastName() { return lastName; }
 public String getCity(){ return city;}
 public String getJob() {return job;}
 
 public String greet() { return GREET + " my name is " + name;}
 
 public static People builder() { return new People(); }
 public People name(String name){  this.name = name; return this;}
 public People lastName(String lastname) { this.lastName = lastname; return this;}
 public People age(int age) { this.age = age; return this; }
 public People city(String city) { this.city = city; return this;}
 public People job(String job){ this.job = job; return this;}
 
 public People build(){ return this; }

}
