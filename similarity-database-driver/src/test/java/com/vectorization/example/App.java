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
package com.vectorization.example;

import com.vectorization.core.vectors.Vectors;
import com.vectorization.driver.VectorizationConnection;
import com.vectorization.driver.Vectorization;
import com.vectorization.driver.builders.StatementBuilders;

public class App extends Vectorization{
	
	public static void main(String[] args) {
		App application = new App();
		VectorizationConnection connection = application.getConnection();
		connection.connect();
//		Statement statement = connection.createStatement();
//		String result = statement.execute("login admin with admin");
		String result = StatementBuilders.login("admin").with("admin").execute(connection);
		System.out.println(result);
		
		String s = StatementBuilders
				.use("Test")
				.execute(connection);
		System.out.println(s);
		
		s = StatementBuilders
				.create("test")
				.withDimensionality(2)
				.execute(connection);
		System.out.println(s);
		
		s = StatementBuilders
				.list()
				.execute(connection);
		System.out.println(s);
		
		s = StatementBuilders
				.show("test")
				.execute(connection).toString();
		System.out.println(s);
		
		s = StatementBuilders
				.insert(Vectors.createVector("myvector", 0.5, 0.5))
				.into("test")
				.execute(connection);
		
		System.out.println(s);
		
		s = StatementBuilders
				.remove("myvector")
				.from("test")
				.execute(connection);
		
		System.out.println(s);
		
		s = StatementBuilders
				.find(3)
				.nearestTo("test.myvector")
				.in("test")
				.execute(connection).toString();
		System.out.println(s);
		
		s = StatementBuilders.drop("test").execute(connection);
		System.out.println(s);
		

		
		connection.close();
	}

}
