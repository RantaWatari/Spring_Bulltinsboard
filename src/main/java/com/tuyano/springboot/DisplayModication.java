package com.tuyano.springboot;

import java.util.Collections;
import java.util.List;

public class DisplayModication {
    
	public static Iterable<UserData> reverseSort(Iterable<UserData> list){
		Collections.reverse((List<?>) list);
		
		// list.forEach(s -> System.out.println(s.getTime()));
		
		return list;

    }
}
