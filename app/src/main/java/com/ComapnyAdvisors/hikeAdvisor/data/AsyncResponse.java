package com.ComapnyAdvisors.hikeAdvisor.data;

import com.ComapnyAdvisors.hikeAdvisor.model.Park;

import java.util.List;

public interface AsyncResponse {
    void processPark(List<Park> parks);
}
