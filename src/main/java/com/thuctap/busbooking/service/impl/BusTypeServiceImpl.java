package com.thuctap.busbooking.service.impl;

import org.springframework.stereotype.Service;

import com.thuctap.busbooking.service.auth.BusTypeService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusTypeServiceImpl implements BusTypeService {}
