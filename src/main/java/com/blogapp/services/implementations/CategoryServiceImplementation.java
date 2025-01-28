package com.blogapp.services.implementations;

import com.blogapp.entities.Category;
import com.blogapp.exceptions.CategoryAlreadyExistsException;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.repository.CategoryRepository;
import com.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        if(this.categoryRepository.existsByCategory(categoryDto.getCategory())){
            throw new CategoryAlreadyExistsException(categoryDto.getCategory());
        }
        Category category = this.modelMapper.map(categoryDto,Category.class);
        Category savedCategory = this.categoryRepository.save(category);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        if(categoryDto.getCategory() != null){
            category.setCategory(categoryDto.getCategory());
        }
        this.categoryRepository.save(category);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        this.categoryRepository.deleteById(categoryId);
    }
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = this.categoryRepository.findAll();
        return allCategories.stream().map(category-> this.modelMapper.map(category,CategoryDto.class)).toList();
    }
    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Id",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

}
