package com.pb.controller;

import javax.annotation.Resource;

import com.pb.services.accusation.AccusationService;
import com.pb.services.active.ActiveService;
import com.pb.services.applicationTable.TableService;
import com.pb.services.bike.BikeService;
import com.pb.services.projectRelated.ProjectService;
import com.pb.services.repair.RepairService;
import com.pb.services.template.TemplateService;
import com.pb.services.testService.TestService;
import com.pb.services.trip.TTripService;
import com.pb.services.user.UserService;
import com.pb.services.userRelated.InstitutionService;

public class BaseController {

    @Resource
    public InstitutionService institutionService;
    @Resource
    public TableService tableService;
    @Resource
    public TemplateService templateService;
    @Resource
    public ProjectService projectService;
    @Resource
    public TestService testService;
    @Resource
    public ActiveService activeService;
    @Resource
    public BikeService bikeService; 
    @Resource
    public TTripService tripService;
    @Resource
    public UserService userService;
    @Resource
    public AccusationService accusationService;
    @Resource
    public RepairService repairService;
    
    
}
