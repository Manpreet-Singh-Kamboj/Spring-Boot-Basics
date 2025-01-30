package com.blogapp.services.implementations;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.InvalidPostOwnerException;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.Category.CategoryDto;
import com.blogapp.payloads.Post.PostDto;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public PostServiceImplementation(PostRepository postRepository,CategoryRepository categoryRepository,UserRepository userRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Set<CategoryDto> postCategories = postDto.getCategories();
        Set<Category> categories = postCategories.stream().map(category->
              this.categoryRepository.findById(category.getId()).orElseThrow(()-> new ResourceNotFoundException("Category","Id",category.getId()))
        ).collect(Collectors.toSet());
        User postCreator = this.userRepository.findById(postDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",postDto.getUser().getId()));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setCategories(categories);
        post.setUser(postCreator);
        post.setAddedDate(new Date());
        postCreator.getPosts().add(post);
        categories.forEach(category -> {
            category.getPosts().add(post);
        });
        Post savedPost = this.postRepository.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public void deletePost(String postId,String userId) throws IllegalArgumentException {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        if(!user.getPosts().contains(post)){
            throw new InvalidPostOwnerException(post.getId(),user.getId());
        }
        user.getPosts().remove(post);
        Set<Category> categories = post.getCategories();
        categories.forEach(category -> {
            category.getPosts().remove(post);
        });
        this.postRepository.deleteById(post.getId());
    }

    @Override
    public PostDto updatePost(PostDto postDto, String postId,String userId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        User postCreator = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        if(!postCreator.getPosts().contains(post)){
            throw new InvalidPostOwnerException(postId,userId);
        }
        if(postDto.getTitle() != null){
            post.setTitle(postDto.getTitle());
        }
        if(postDto.getContent() != null){
            post.setContent(postDto.getContent());
        }
        if(postDto.getImageUrl() != null){
            post.setImageUrl(postDto.getImageUrl());
        }
        if(postDto.getCategories() != null && !postDto.getCategories().isEmpty()){
            Set<Category> categories = postDto.getCategories().stream().map(category-> this.categoryRepository.findById(category.getId()).orElseThrow(()-> new ResourceNotFoundException("Category","Id",category.getId()))).collect(Collectors.toSet());
            post.setCategories(categories);
        }
        Post savedPost = this.postRepository.save(post);
        return this.modelMapper.map(savedPost,PostDto.class);
    }

    @Override
    public List<PostDto> getUserPosts(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
       return user.getPosts().stream().map(post-> this.modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public List<PostDto> getPostByCategory(String categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> categoryPosts = this.postRepository.findAllByCategoryId(category.getId());
        return categoryPosts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).toList();
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = this.postRepository.findAll();
        return allPosts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).toList();
    }

}
