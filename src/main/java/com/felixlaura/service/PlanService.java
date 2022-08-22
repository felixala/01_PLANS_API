package com.felixlaura.service;

import com.felixlaura.entity.Plan;

import java.util.List;
import java.util.Map;

public interface PlanService {

    //This method to dropdown all categories in the ui create plan
    public Map<Integer, String> getPlanCategories();

    public boolean savePlan(Plan plan);

    public List<Plan> getAllPlans();

    public Plan getPlanById(Integer planId);

    public boolean deletePlan(Integer planId);

    public boolean updatePlan(Plan plan);

    public boolean planStatusChange(Integer planId, String status);


}
