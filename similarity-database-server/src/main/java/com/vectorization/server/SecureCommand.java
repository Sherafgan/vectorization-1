/*  
 *  Copyright (C) 2014 Robert Moss
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.vectorization.server;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.vectorization.core.SSException;
import com.vectorization.core.database.Database;
import com.vectorization.parsing.Command;

public class SecureCommand extends AbstractCommand {

	private Command delegate;

	public SecureCommand(Command delegate) {
		this.delegate = delegate;
	}

	@Override
	public String execute(Database database) {
		super.execute(database);
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			throw new SSException("User is not authenticated");
		}
		
		if(!currentUser.hasRole("user")){
			throw new SSException(currentUser + " is not authorized");
		}
		return delegate.execute(database);
	}
}