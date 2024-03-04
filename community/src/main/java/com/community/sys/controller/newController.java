package com.community.sys.controller;

import com.community.sys.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/chart")
@Controller
public class newController {

    @Autowired
    ICommunityServiceProjectService communityServiceProjectService;

    @Autowired
    ICommunityActivityInfoService communityActivityInfoService;

    @Autowired
    ISecurityAlarmService securityAlarmService;

    @Autowired
    IEntryLogService entryLogService;

    @Resource
    IPropertyFeePaymentService propertyFeePaymentService;


    @RequiresPermissions("chart:serviceName")
    @GetMapping("/serviceName")
    @ResponseBody
    public List<String> listService() {
        return communityServiceProjectService.selectCommunityServiceProjectNames();
    }

    @RequiresPermissions("chart:activityName")
    @GetMapping("/activityName")
    @ResponseBody
    public List<String> listActivity() {
        return communityActivityInfoService.selectCommunityActivityInfoNames();
    }

    @RequiresPermissions("chart:alarmName")
    @GetMapping("/alarmName")
    @ResponseBody
    public List<String> listAlarm() {
        return securityAlarmService.selectSecurityAlarmNames();
    }

    @RequiresPermissions("chart:EntryLogNums")
    @GetMapping("/EntryLogNums")
    @ResponseBody
    public int[] listEntryLogNums() {
        int EntryLogNumsDay = entryLogService.selectEntryLogNumsDay();
        int EntryLogNumsWeek = entryLogService.selectEntryLogNumsWeek();
        int EntryLogNumsMonth = entryLogService.selectEntryLogNumsMonth();
        return new int[]{EntryLogNumsDay, EntryLogNumsWeek, EntryLogNumsMonth};
    }

    @RequiresPermissions("chart:EntryLogChartsDay")
    @GetMapping("/EntryLogChartsDay")
    @ResponseBody
    public List<Integer> listEntryChartsDay() {
        return entryLogService.selectEntryLogChartDay();
    }

    @RequiresPermissions("chart:EntryLogChartsWeek")
    @GetMapping("/EntryLogChartsWeek")
    @ResponseBody
    public List<Integer> listEntryChartsWeek() {
        return entryLogService.selectEntryLogChartWeek();
    }

    @RequiresPermissions("chart:EntryLogChartsMonth")
    @GetMapping("/EntryLogChartsMonth")
    @ResponseBody
    public List<Integer> listEntryChartsMonth() {
        return entryLogService.selectEntryLogChartMonth();
    }

    @RequiresPermissions("chart:EntryLogAndLastMonth")
    @GetMapping("/EntryLogAndLastMonth")
    @ResponseBody
    public int[] listEntryLogAndLastMonth() {
        int currentTime = entryLogService.selectEntryLogMonth();
        int previousTime = entryLogService.selectEntryLogLastMonth();
        return new int[]{currentTime, previousTime};
    }

    @RequiresPermissions("chart:ActivityAndLastMonth")
    @GetMapping("/ActivityAndLastMonth")
    @ResponseBody
    public int[] listCommunityActivityAndLastMonth() {
        int current = communityActivityInfoService.selectCommunityActivityMonth();
        int previous = communityActivityInfoService.selectCommunityActivityLastMonth();
        return new int[]{current, previous};
    }

    @RequiresPermissions("chart:ServiceAndLastMonth")
    @GetMapping("/ServiceAndLastMonth")
    @ResponseBody
    public int listServiceAndLastMonth() {
        return communityServiceProjectService.selectCommunityServiceProjectCount();
    }

    @RequiresPermissions("chart:FeeAndLastMonth")
    @GetMapping("/FeeAndLastMonth")
    @ResponseBody
    public float listFeeAndLastMonth() {
        return propertyFeePaymentService.selectPropertyFeePaymentAvg();
    }

    @RequiresPermissions("chart:EntryLogIn")
    @GetMapping("/EntryLogIn")
    @ResponseBody
    public int listEntryLogIn() {
        return entryLogService.selectEntryLogIn();
    }

    @RequiresPermissions("chart:EntryLogOut")
    @GetMapping("/EntryLogOut")
    @ResponseBody
    public int listEntryLogOut() {
        return entryLogService.selectEntryLogOut();
    }

    @RequiresPermissions("chart:EntryLogWorker")
    @GetMapping("/EntryLogWorker")
    @ResponseBody
    public int listEntryLogWorker() {
        return entryLogService.selectEntryLogWorker();
    }

    @RequiresPermissions("chart:EntryLogTenant")
    @GetMapping("/EntryLogTenant")
    @ResponseBody
    public int listEntryLogTenant() {
        return entryLogService.selectEntryLogTenant();
    }



}
