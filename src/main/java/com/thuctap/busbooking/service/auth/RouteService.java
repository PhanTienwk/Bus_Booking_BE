package com.thuctap.busbooking.service.auth;

import com.thuctap.busbooking.entity.BusRoute;
import com.thuctap.busbooking.entity.BusStation;
import com.thuctap.busbooking.entity.Route;

import java.util.List;

public interface RouteService {
    Route addRoute(BusRoute busRoute, BusStation busStation,int routeLocation,int status);
    List<Route> getListRoute(int busRoute);
}
