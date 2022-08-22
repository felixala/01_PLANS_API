package com.felixlaura.service;

import com.felixlaura.entity.PlanCategory;

import java.util.List;

public interface PlanCategoryService {

    public boolean savePlanCategory(PlanCategory planCategory);

    public PlanCategory getOnePlanCategoryById(Integer categoryId);

    public List<PlanCategory> getAllPlanCategories();

    public boolean updatePlanCategory(PlanCategory planCategory);

    public boolean planCategoryStatusChange(Integer categoryId, String status);

}
