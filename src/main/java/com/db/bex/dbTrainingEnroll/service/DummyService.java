package com.db.bex.dbTrainingEnroll.service;

import com.db.bex.dbTrainingEnroll.dao.DummyRepository;
import com.db.bex.dbTrainingEnroll.dto.DummyDTOTransformer;
import com.db.bex.dbTrainingEnroll.dto.DummyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private DummyDTOTransformer dummyDTOTransformer;

    public List<DummyDto> search(){
        return dummyDTOTransformer.transform(dummyRepository.findAll());
    }
}