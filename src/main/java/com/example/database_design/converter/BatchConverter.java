package com.example.database_design.converter;

import com.example.database_design.entity.Batch;
import com.example.database_design.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class BatchConverter implements Converter<String, Batch> {

    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Batch convert(String id) {
        System.out.println("Converting Batch");

        List<Batch> batchList = batchRepository.findAll();
        int indexTemp = Integer.parseInt(id);
        return batchList.get(indexTemp - 1);
    }
}
