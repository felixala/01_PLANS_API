package com.felixlaura.controller;

import com.felixlaura.entity.Plan;
import com.felixlaura.props.AppProperties;
import com.felixlaura.service.PlanService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import static com.felixlaura.util.Constants.*;
@RestController
public class PlanRestController {

    private PlanService planService;

    Map<String, String> message;
    public PlanRestController(PlanService planService, AppProperties appProperties) {
        this.planService = planService;
        this.message = appProperties.getMessages();
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<Integer, String>> planCategories(){
        Map<Integer, String> planCategories = planService.getPlanCategories();
        return new ResponseEntity<>(planCategories, HttpStatus.OK);
    }

    @PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan){
        String messageResponse = EMPTY_STR;
        boolean savedPlan = planService.savePlan(plan);
        if(savedPlan){
            messageResponse = message.get(PLAN_SAVE_SUCC);
        }else{
            messageResponse =message.get(PLAN_SAVE_FAIL);
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @GetMapping("plans")
    public ResponseEntity<List<Plan>> getAllPlans(){
        List<Plan> allPlans = planService.getAllPlans();
        return new ResponseEntity<>(allPlans, HttpStatus.OK);
    }

    @DeleteMapping("/plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId){

        String messageResponse = EMPTY_STR;
        boolean deletePlan = planService.deletePlan(planId);
        if(deletePlan){
            messageResponse = message.get(PLAN_DELETE_SUCC);
        }else {
            messageResponse = message.get(PLAN_DELETE_FAIL);
        }

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Integer planId){
        Plan planById = planService.getPlanById(planId);
        return new ResponseEntity<>(planById, HttpStatus.OK);
    }

    @PutMapping("/plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan){
        String messageResponse = EMPTY_STR;
        boolean updatePlan = planService.updatePlan(plan);

        messageResponse = updatePlan ? PLAN_UPDATE_SUCC : PLAN_UPDATE_FAIL;

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
         }

    @PutMapping("/status-change/{planId}/{status}")
    public ResponseEntity<String> planStatusChange(@PathVariable Integer planId,@PathVariable String status){
        String messageResponse = EMPTY_STR;
        boolean isStatusChanged = planService.planStatusChange(planId, status);

        if(isStatusChanged){
            messageResponse = message.get(PLAN_STATUS_CHANGE_SUCC);
        } else {
            messageResponse = message.get(PLAN_STATUS_CHANGE_FAIL);
        }

        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
