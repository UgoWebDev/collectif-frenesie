package com.frenesie.collectif.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.frenesie.collectif.model.Set;

@Service
public interface SetService {

    List<Set> getAllSets();

}
