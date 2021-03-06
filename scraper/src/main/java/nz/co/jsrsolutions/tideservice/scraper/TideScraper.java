/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)DataScraper3.java        
 *
 * Copyright (c) 2012 JSR Solutions Limited
 * 4 Viridian Lane, Auckland, 0632.  New Zealand
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of JSR
 * Solutions Limited. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with JSR Solutions Limited.
 */

package nz.co.jsrsolutions.tideservice.scraper;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.context.support.GenericXmlApplicationContext;


final class TideScraper {

  private static final transient Logger logger = Logger.getLogger(TideScraper.class);

  private static final String SPRING_CONFIG = new String("META-INF/spring/scraper-context-*.xml");
  
  private static final String SCHEDULER_BEAN_ID = new String("scheduler");

  private static final String CONTROLLER_BEAN_ID = new String("controller");

  private TideScraper() {

  }

  public static void main(String[] args) {
    
    logger.info("Starting application [ tide scraper ] ..."); 

    GenericXmlApplicationContext context = null;

    try {

      CommandLineParser parser = new GnuParser();

      CommandLine commandLine = parser.parse(CommandLineOptions.Options, args);

      if (commandLine.getOptions().length > 0 && !commandLine.hasOption(CommandLineOptions.HELP)) {

        context = new GenericXmlApplicationContext();
     
        //ConfigurableEnvironment env = ctx.getEnvironment();
        
        //env.setActiveProfiles("test1");
        
        context.load(SPRING_CONFIG);
        
        context.refresh();

        context.registerShutdownHook();

        if (commandLine.hasOption(CommandLineOptions.SCHEDULED)) {
          
          Scheduler scheduler = context.getBean(SCHEDULER_BEAN_ID, Scheduler.class);
          scheduler.start();
       
          Object lock = new Object();
          synchronized (lock) {
              lock.wait();  
          }

        }
        else {
          TideScraperController controller = context.getBean(CONTROLLER_BEAN_ID,
              TideScraperController.class);
          controller.executeCommandLine(commandLine);
        }
        
      }
      else {

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ts", CommandLineOptions.Options);

      }


    }
    catch (TideScraperException tse) {
      logger.error("Failed to execute command", tse); 
    }
    catch(ParseException pe) {

      logger.error("Failed to parse command line", pe); 

    }
    catch(Exception e) {

      logger.error("Failed to execute command", e);

    }
    finally {
      if (context != null) {
        context.close();
      }
    }

    logger.info("Exiting application [ tide scraper ] ...");

  }


}

