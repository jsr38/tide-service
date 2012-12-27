/* -*- mode: java; tab-width: 2; indent-tabs-mode: nil; c-basic-offset: 2 -*- */

/*
 * @(#)AuditorAwareDefaultImpl.java        
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

package nz.co.jsrsolutions.tideservice.core.util;

import nz.co.jsrsolutions.tideservice.core.domain.AbstractUser;
import nz.co.jsrsolutions.tideservice.core.domain.Operator;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareDefaultImpl implements AuditorAware<AbstractUser> {

	private ThreadLocal<Operator> operator = new ThreadLocal<Operator>();
	
	public AuditorAwareDefaultImpl() {
		Operator user = new Operator();
		user.setUserName("system");
		operator.set(user);
		
	}
	
	@Override
	public AbstractUser getCurrentAuditor() {
		return operator.get();
	}

}
