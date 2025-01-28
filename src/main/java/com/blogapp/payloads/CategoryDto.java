package com.blogapp.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private String id;
    private String category;
    private List<PostDto> posts = new ArrayList<>();
}
