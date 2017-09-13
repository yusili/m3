package com.maven.s;

public class Person {
  private String name;
  private Integer id;
  private Integer age;

  public Person(String name, Integer id, Integer age) {
    this.name = name;
    this.id = id;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
