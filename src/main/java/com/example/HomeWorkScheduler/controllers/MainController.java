package com.example.HomeWorkScheduler.controllers;

import com.example.HomeWorkScheduler.models.Card;
import com.example.HomeWorkScheduler.models.Post;
import com.example.HomeWorkScheduler.models.Subject;
import com.example.HomeWorkScheduler.repos.PostRepository;
import com.example.HomeWorkScheduler.repos.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

@Controller
public class MainController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/")
    public String home(Model model) throws ParseException {
        Iterator<Subject> iterator=subjectRepository.findAll().iterator();
        ArrayList<Card> cards=new ArrayList<>();
        while(iterator.hasNext()){
            Card card=cardConstructor(iterator.next().getName());
            cards.add(card);
        }
        //сортировка
        ArrayList<String> subjects=new ArrayList<>();
        for(Card card:cards){subjects.add(card.getName());}
        Collections.sort(subjects);
        ArrayList<Card> cardSorted=new ArrayList<>();
        for(String subject:subjects){
            for (Card card:cards){
                if (card.getName().equals(subject)){
                    cardSorted.add(card);
                    break;
                }
            }
        }
        model.addAttribute("cards",cardSorted);
        return "home";
    }
    public Card cardConstructor(String subjectName){
        Iterator<Post> iterator=postRepository.findAll().iterator();
        String lastTask=null;
        String date=null;
        String id=null;
        while(iterator.hasNext()){
            Post post=iterator.next();
            if (post.getSubjectName().equals(subjectName)){
                lastTask=post.getBody();
                date=post.getDate();
                id=String.valueOf(post.getId()-1);
            }
        }
        return new Card(subjectName,lastTask,date,id);
    }
}
