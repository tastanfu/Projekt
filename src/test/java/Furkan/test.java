package Furkan;

import junit.framework.TestCase;

public class test extends TestCase {
	
	public void testMonat() {
		
		assertTrue(Util.istErstesHalbjahr(2));
		assertTrue(Util.istErstesHalbjahr(6));
	}

}
