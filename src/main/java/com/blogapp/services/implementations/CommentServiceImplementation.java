package com.blogapp.services.implementations;

import com.blogapp.entities.Comment;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.InvalidCommentOwnerException;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.Comment.CommentDto;
import com.blogapp.payloads.Comment.CommentPostDto;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    public CommentServiceImplementation(PostRepository postRepository,UserRepository userRepository,CommentRepository commentRepository,ModelMapper modelMapper){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Post post = null;
        if(commentDto.getParentComment() == null){
            post = this.postRepository.findById(commentDto.getPost().getId()).orElseThrow(()-> new ResourceNotFoundException("Post","Id",commentDto.getPost().getId()));
        }
        
        User user = this.userRepository.findById(commentDto.getUser().getId()).orElseThrow(()-> new ResourceNotFoundException("User","Id",commentDto.getUser().getId()));
        Comment comment = this.modelMapper.map(commentDto,Comment.class);
        comment.setUser(user);
        if(commentDto.getParentComment() == null && post != null){
            comment.setPost(post);
        }
        if(commentDto.getParentComment() != null){
            Comment parentComment = this.commentRepository.findById(commentDto.getParentComment().getId()).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentDto.getParentComment().getId()));
            comment.setParentComment(parentComment);
            parentComment.setUser(parentComment.getUser());
        }
        Comment savedComment = this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public CommentDto updateComment(String commentId,String userId,String comment) {
        Comment existingComment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
        if(!existingComment.getUser().getId().equals(userId)){
            throw new InvalidCommentOwnerException(existingComment.getId(),userId);
        }
        existingComment.setComment(comment);
        Comment updatedComment = this.commentRepository.save(existingComment);
        return this.modelMapper.map(updatedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(String commentId, String userId) {
        Comment comment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if (!comment.getUser().getId().equals(userId)) {
            throw new InvalidCommentOwnerException(comment.getId(), userId);
        }
        // First Deleting the Replies of the Comment
        if (!comment.getReplies().isEmpty()) {
            // We are creating a copy of the comment replies because id we directly iterate over it and modify it the hibernate will throw and ConcurrentModificationException.
            List<Comment> replies = new ArrayList<>(comment.getReplies());
            for (Comment reply : replies) {
                // Removing this reply from the comments replies
                comment.getReplies().remove(reply);
                // Removing the reply from the DB.
                this.commentRepository.delete(reply);
            }
        }
        // If this comment is a child of any comment removing it from its parents replies.
        if (comment.getParentComment() != null) {
            comment.getParentComment().getReplies().remove(comment);
        }
        // If it's the parent only removing it from the post on which it is commented.
        else if (comment.getPost() != null) {
            comment.getPost().getComments().remove(comment);
        }
        // Finally removing this comment from the users comments
        comment.getUser().getComments().remove(comment);
        // Deleting the comment from the DB.
        this.commentRepository.delete(comment);
    }
}
