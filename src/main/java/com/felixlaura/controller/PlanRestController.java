package com.felixlaura.controller;

import com.felixlaura.entity.Plan;
import com.felixlaura.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> planCategories(){
        Map<Integer, String> planCategories = planService.getPlanCategories();
        return new ResponseEntity<>(planCategories, HttpStatus.OK);
    }

    @PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan){
        String msg = "";
        boolean savedPlan = planService.savePlan(plan);
        if(savedPlan){
            msg = "Plan saved";
        }else{
            msg ="Plan NO saved";
        }
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @GetMapping("plans")
    public ResponseEntity<List<Plan>> getAllPlans(){
        List<Plan> allPlans = planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId){
        String msg = "";
        boolean deletePlan = planService.deletePlan(planId);
        if(deletePlan){
            msg = "Plan deleted";
        }else {
            msg = "Plan NO deleted";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Integer planId){
        Plan planById = planService.getPlanById(planId);
        return new ResponseEntity<>(planById, HttpStatus.OK);
    }

    @PutMapping("/plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan, @PathVariable Integer planId){
        String msg = "";
        boolean updatePlan = planService.updatePlan(plan);

        msg = updatePlan ? "Plan Saved" : "Plan NO saved";

        return new ResponseEntity<>(msg, HttpStatus.OK);
         }

    @PutMapping("/status-change/{planId}/{status}")
    public ResponseEntity<String> planStatusChange(@PathVariable Integer planId,@PathVariable String status){
        String msg = "";
        boolean isStatusChanged = planService.planStatusChange(planId, status);

        if(isStatusChanged){
            msg = "Plan Status changed";
        } else {
            msg ="Plan Status NO changed";
        }

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

}
