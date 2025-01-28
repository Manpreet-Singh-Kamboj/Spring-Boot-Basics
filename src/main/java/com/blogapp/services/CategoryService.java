package com.blogapp.services;

import com.blogapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    void deleteCategory(String categoryId);
    CategoryDto updateCategory(CategoryDto categoryDto,String id);
    CategoryDto getCategoryById(String categoryId);
    List<CategoryDto> getAllCategories();
}
