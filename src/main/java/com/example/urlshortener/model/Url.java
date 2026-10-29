package com.example.urlshortener.model;

import jakarta.persistence.*;

@Entity
@Table(name = "urls")
//描述用户属性
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    private Long id;

    @Column(unique = true)
    private String shortCode;

    @Column(nullable = false)
    private String originalUrl;

    public Url(){} //无参构造器，只负责创建对象（必须的
   // 框架需要。Spring 把 JSON 转成对象时，会先 new User()建空对象，再用 setter 逐个填值

    //有参构造器，new
        public Url(String originalUrl){
            this.originalUrl = originalUrl;
        }

        //getter & setter:字段是private的，靠它们来读和改
        public Long getId(){
            return id;
        }
        public void setId(Long id){
            this.id = id;//值赋给字段,字段和值同名，为了区分
        }
        public String getShortCode(){
            return shortCode;
        }
        public void setShortCode(String shortCode){
            this.shortCode = shortCode;
        }
        public String getOriginalUrl(){return originalUrl;}
        public void setOriginalUrl(String email){
            this.originalUrl = originalUrl;
        }
}