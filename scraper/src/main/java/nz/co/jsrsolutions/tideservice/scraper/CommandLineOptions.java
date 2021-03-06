/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)CommandLineOptions.java        
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

import java.lang.String;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.log4j.Logger;


final class CommandLineOptions {

  private static final Logger logger = Logger.getLogger(CommandLineOptions.class);

  public static final String HELP = new String("help");

  public static final String COMMAND = new String("command");

  public static final String EXCHANGE = new String("exchange");

  public static final String SYMBOL = new String("symbol");
  
  public static final String SCHEDULED = new String("scheduled");

  public static final Options Options = new Options();

  static {
    
    OptionBuilder.withDescription("display help");
    Option help = OptionBuilder.create(HELP);

    OptionBuilder.withArgName("command");
    OptionBuilder.hasArg();
    OptionBuilder.withDescription("specify command to execute");
    Option command = OptionBuilder.create(COMMAND);

    OptionBuilder.withArgName("exchange");
    OptionBuilder.hasArg();
    OptionBuilder.isRequired(false);
    OptionBuilder.withDescription("specify exchange");
    Option exchange = OptionBuilder.create(EXCHANGE);

    OptionBuilder.withArgName("symbol");
    OptionBuilder.hasArg();
    OptionBuilder.isRequired(false);
    OptionBuilder.withDescription("specify symbol");
    Option symbol = OptionBuilder.create(SYMBOL);
    
    OptionBuilder.withArgName("scheduled");
    OptionBuilder.isRequired(false);
    OptionBuilder.withDescription("automatic scheduling");
    Option scheduled = OptionBuilder.create(SCHEDULED);

    logger.debug("Registering command line options:");
    logger.debug(help.toString());
    logger.debug(command.toString());
    logger.debug(exchange.toString());
    logger.debug(symbol.toString());
    logger.debug(scheduled.toString());
        
    Options.addOption(help);
    Options.addOption(command);
    Options.addOption(exchange);
    Options.addOption(symbol);
    Options.addOption(scheduled);
    

  }

  private CommandLineOptions() {

  }

}