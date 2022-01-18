package com.jmp.nightBatch.Batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class DataTransferItemProcess implements ItemProcessor {

    @Override
    public Object processItem(Object o) throws Exception {
        return o;
    }
}
