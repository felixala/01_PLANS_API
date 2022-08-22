package com.felixlaura.service;


import com.felixlaura.entity.Plan;
import com.felixlaura.entity.PlanCategory;
import com.felixlaura.repository.PlanCategoryRepo;
import com.felixlaura.repository.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService{

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private PlanCategoryRepo planCategoryRepo;

    @Override
    public Map<Integer, String> getPlanCategories() {

        List<PlanCategory> categories = planCategoryRepo.findAll();
        Map<Integer, String> categoryMap = new HashMap<>();
        categories.forEach(category ->
                categoryMap.put(category.getCategoryId(), category.getCategoryName()));
        return categoryMap;
    }

    @Override
    public boolean savePlan(Plan plan) {
        Plan savedPlan = planRepo.save(plan);
        return savedPlan.getPlanId() != null;
    }

    @Override
    public List<Plan> getAllPlans() {
        return planRepo.findAll();
    }

    @Override
    public Plan getPlanById(Integer planId) {
        Optional<Plan> findById = planRepo.findById(planId);
        return findById.get();
    }

    @Override
    public boolean deletePlan(Integer planId) {

        boolean status = false;
        try{
            planRepo.deleteById(planId);
            status = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean updatePlan(Plan plan) {
        Plan savedPlan = planRepo.save(plan);
        return savedPlan.getPlanId() != null;
    }

    @Override
    public boolean planStatusChange(Integer planId, String status) {
        Optional<Plan> findById = planRepo.findById(planId);
        if(findById.isPresent()){
            Plan plan = findById.get();
            plan.setActiveSw(status);
            planRepo.save(plan);
            return true;
        }
        return false;
    }
}
