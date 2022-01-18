package com.jmp.nightBatch.controllers;

import com.jmp.nightBatch.HibernateUtils;
import com.jmp.nightBatch.services.DataTransferService;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("batch")
public class BatchController {
    Logger logger=Logger.getLogger(BatchController.class);
    @EJB
    private DataTransferService dataTransferService;
    @GET
    public void run(){
        System.out.println("going to run Batch");
        logger.info("Going to run Data Transfer Batch.");
        dataTransferService.runDataTransferBatch();
    }
}
