package com.blogapp.services.implementations;

import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.InvalidPostOwnerException;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.payloads.PostDto;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
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

    // DELETE REQ -> DELETE USER POST -> ///TODO: ONLY POST OWNER AND AUTHORIZED USER CAN DELETE THE POST
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
    public PostDto updatePost(PostDto postDto) {
        return null;
    }

    // GET REQ -> GET USER POST -> ///TODO: NEED TO IMPLEMENT ONLY AUTHENTICATED USERS ACCESS THIS API.
    @Override
    public List<PostDto> getUserPosts(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
       return user.getPosts().stream().map(post-> this.modelMapper.map(post,PostDto.class)).toList();
    }

    // GET REQ -> GET POST BY CATEGORY
    @Override
    public List<PostDto> getPostByCategory(String categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        List<Post> categoryPosts = this.postRepository.findAllByCategoryId(category.getId());
        return categoryPosts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).toList();
    }

}
