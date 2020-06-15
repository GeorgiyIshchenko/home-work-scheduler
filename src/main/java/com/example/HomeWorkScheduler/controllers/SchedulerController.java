package com.example.HomeWorkScheduler.controllers;

import com.example.HomeWorkScheduler.models.Post;
import com.example.HomeWorkScheduler.models.Subject;
import com.example.HomeWorkScheduler.repos.PostRepository;
import com.example.HomeWorkScheduler.repos.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class SchedulerController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/task/add")
    public String taskAdd(Model model) {
        return "task-add";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) throws ParseException {
        Iterable<Post> iterable=postRepository.findAll();
        Iterator<Post> iterator=iterable.iterator();
        ArrayList<Post> posts=new ArrayList<>();
        while(iterator.hasNext()){
            Post post=iterator.next();
            posts.add(post);
        }
        model.addAttribute("posts",sortPosts(posts));
        return "tasks";
    }
    @GetMapping("tasks/{name}")
    public String subjects(@PathVariable(value = "name") String name,Model model) throws ParseException {
        System.out.println(name);
        Iterator<Subject> iterator=subjectRepository.findAll().iterator();
        Iterator<Post> postIterator=postRepository.findAll().iterator();
        ArrayList<Post> posts=new ArrayList<>();
        while(postIterator.hasNext()){
            Post post=postIterator.next();
            if(post.getSubjectName().equals(name)){
                posts.add(post);
            }
        }
        model.addAttribute("subjectName",name);
        model.addAttribute("posts",sortPosts(posts));
        return "subject-tasks";
    }

    @PostMapping("/task/add")
    public String home(@RequestParam String subject_name,
                       @RequestParam String body,
                       @RequestParam String date,
                       Model model){
        Iterable<Subject> iterable=subjectRepository.findAll();
        Iterator<Subject> iterator=iterable.iterator();
        ArrayList<String> subjectNames=new ArrayList<>();
        while(iterator.hasNext()){
            subjectNames.add(iterator.next().getName());
        }
        if (!subjectNames.contains(subject_name)){
            subjectRepository.save(new Subject(subject_name));
        }
        Post post=new Post(subject_name,body,date);
        postRepository.save(post);
        return "redirect:/tasks";
    }
    public ArrayList<Post> sortPosts(ArrayList<Post> posts) throws ParseException {
        //сортировка
        ArrayList<Date> dates=new ArrayList<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");
        for(Post post:posts){
            if(!dates.contains(simpleDateFormat.parse(post.getDate()))){
                dates.add(simpleDateFormat.parse(post.getDate()));
            }
        }
        Collections.sort(dates,Collections.reverseOrder());
        ArrayList<Post> postsSorted=new ArrayList<>();
        for(Date date:dates){
            for (Post post:posts){
                if (post.getDate().equals(simpleDateFormat.format(date))){
                    postsSorted.add(post);

                }
            }
        }
        return postsSorted;
    }
}
