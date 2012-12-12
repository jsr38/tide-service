/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)CommandFactory.java        
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

package nz.co.jsrsolutions.tideservice.scraper.command;

import java.util.concurrent.ExecutorService;

import org.apache.commons.chain.Command;

public final class CommandFactory {

  private static final String UPDATEALLPORTS_KEY = new String(
      "updateallsymbols");

  public static Command create(String type, ExecutorService executorService)
      throws CommandException {

    if (type.compareTo(UPDATEALLPORTS_KEY) == 0) {

      return new UpdateAllPortsCommand(executorService);

    } else {

      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("Unknown command [ ");
      stringBuffer.append(type);
      stringBuffer.append(" ] ");
      throw new CommandException(stringBuffer.toString());

    }

  }

}