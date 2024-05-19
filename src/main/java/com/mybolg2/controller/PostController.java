package com.mybolg2.controller;

import com.mybolg2.payload.PostDto;
import com.mybolg2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController
{
    @Autowired
    private PostService postService;
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
    @GetMapping
    public List<PostDto> getAllPosts()
    {
         List<PostDto> postDto =postService.getAllPosts();
        return postDto;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("id") long id)
    {
         PostDto dto = postService.getPostById(id);
        return  ResponseEntity.ok(dto);
    }
    //http://localhost:8080/api/posts/1
@PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("id") long id){
    //now lests take this dto & id no' give it to s.l(when ever we update id no's are mandatory without id no update canot happen)
     PostDto dto = postService.updatePost(postDto, id);

    return new ResponseEntity<>(dto,HttpStatus.OK);
}



}
