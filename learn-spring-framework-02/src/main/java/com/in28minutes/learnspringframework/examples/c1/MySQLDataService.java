package com.in28minutes.learnspringframework.examples.c1;

import org.springframework.stereotype.Repository;

//@Component
@Repository
public class MySQLDataService implements DataService {

	@Override
	public int[] retrieveData() {
		// TODO Auto-generated method stub
		return new int[] { 1, 2, 3, 4, 5 };
	}

}
