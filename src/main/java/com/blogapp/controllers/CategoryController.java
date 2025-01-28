package com.blogapp.controllers;

import com.blogapp.payloads.ApiResponseDto;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    // POST REQ -> CREATE CATEGORY -> ///TODO: ONLY ADMIN CAN CREATE A CATEGORY
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategoryHandler(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    // PUT REQ -> UPDATE CATEGORY -> ///TODO: ONLY ADMIN CAN UPDATE A CATEGORY
    @PutMapping("/update")
    public ResponseEntity<CategoryDto> updateCategoryHandler(@Valid @RequestBody CategoryDto categoryDto, @RequestParam("categoryId") String categoryId){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    // DELETE REQ -> DELETE CATEGORY -> ///TODO: ONLY ADMIN CAN DELETE A CATEGORY
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDto> deleteCategoryHandler(@RequestParam("categoryId") String categoryId){
        this.categoryService.deleteCategory(categoryId);
        ApiResponseDto apiResponseDto = new ApiResponseDto(true,String.format("Category successfully deleted with ID: %s",categoryId));
        return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
    }

    // GET REQ -> GET ALL CATEGORY -> EVERYONE CAN  ACCESS THIS
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategoryHandler(){
        List<CategoryDto> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<>(allCategories,HttpStatus.OK);
    }
    // GET REQ -> GET CATEGORY BY ID -> EVERYONE HAVE ACCESS TO THIS API
    @GetMapping("/get")
    public ResponseEntity<CategoryDto> getCategoryById(@RequestParam("categoryId") String categoryId){
        CategoryDto category = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

}
