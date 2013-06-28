package de.thomasefer.fleisskappa;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class FleissKappaTest {

	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnEmptyArrayTestLong() {
		FleissKappa.calculate(new long[][] {{}});
	}
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnEmptyArrayTestInt() {
		FleissKappa.calculate(new int[][] {{}});
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnEmptyFirstLineTestLong() {
		FleissKappa.calculate(new long[][] {{},{1l}});
	}
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnEmptyFirstLineTestInt() {
		FleissKappa.calculate(new int[][] {{},{1}});
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnDifferentColumnNumbersTestLong() {
		FleissKappa.calculate(new long[][] {{1l},{2l,3l}});
	}
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnDifferentColumnNumbersTestInt() {
		FleissKappa.calculate(new int[][] {{1},{2,3}});
	}

	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnDifferentVoterNumbersTestLong() {
		FleissKappa.calculate(new long[][] {{1l,1l,0l},{0l,3l,0l}});
	}
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnDifferentVoterNumbersTestInt() {
		FleissKappa.calculate(new int[][] {{1,1,0},{0,3,0}});
	}

	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnNullTestLong() {
		FleissKappa.calculate((int[][])null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void argumentExceptionOnNullTestInt() {
		FleissKappa.calculate((int[][])null);
	}
	
	@Test
	public void wikipediaExampleTestLong() {
		long[][] longLines = {
				{0l,0l,0l,0l,14l},
				{0l,2l,6l,4l,2l},
				{0l,0l,3l,5l,6l},
				{0l,3l,9l,2l,0l},
				{2l,2l,8l,1l,1l},
				{7l,7l,0l,0l,0l},
				{3l,2l,6l,3l,0l},
				{2l,5l,3l,2l,2l},
				{6l,5l,2l,1l,0l},
				{0l,2l,2l,3l,7l},
		};
		assertTrue( "Reproduce the wikipedia example with long values",
				    Math.abs(FleissKappa.calculate(longLines)-0.210)<0.001 );
	}
	@Test
	public void wikipediaExampleTestInt() {
		int[][] intLines = {
				{0,0,0,0,14},
				{0,2,6,4,2},
				{0,0,3,5,6},
				{0,3,9,2,0},
				{2,2,8,1,1},
				{7,7,0,0,0},
				{3,2,6,3,0},
				{2,5,3,2,2},
				{6,5,2,1,0},
				{0,2,2,3,7},
		};
		assertTrue( "Reproduce the wikipedia example with int values",
				    Math.abs(FleissKappa.calculate(intLines)-0.210)<0.001 );
	}

	@Test
	public void singleRaterTestLong() {
		double k = FleissKappa.calculate(new long[][] {{1l,0l,0l},{0l,1l,0l},{0l,1l,0l},{1l,0l,0l}});
		assertThat(k, is(1.0));
	}
	@Test
	public void singleRaterTestInt() {
		double k = FleissKappa.calculate(new int[][] {{1,0,0},{0,1,0},{0,1,0},{1,0,0}});
		assertThat(k, is(1.0));
	}	
	
	@Test
	public void unanimosityTestLong() {
		double k = FleissKappa.calculate(new long[][] {{2l,0l,0l},{0l,2l,0l},{0l,2l,0l},{2l,0l,0l}});
		assertThat(k, is(1.0));
	}
	@Test
	public void unanimosityTestInt() {
		double k = FleissKappa.calculate(new int[][] {{2,0,0},{0,2,0},{0,2,0},{2,0,0}});
		assertThat(k, is(1.0));
	}

	@Test
	public void singleColumnTestLong() {
		double k = FleissKappa.calculate(new long[][] {{2l},{2l},{2l},{2l}});
		assertThat(k, is(1.0));
	}
	@Test
	public void singleColumnTestInt() {
		double k = FleissKappa.calculate(new int[][] {{2},{2},{2},{2}});
		assertThat(k, is(1.0));
	}
	
	@Test
	public void negativeKappaTestLong() {
		double k = FleissKappa.calculate(new long[][] {{1l,1l},{1l,1l},{1l,1l},{1l,1l}});
		assertThat(k, is(-1.0));
	}
	@Test
	public void negativeKappaTestInt() {
		double k = FleissKappa.calculate(new int[][] {{1,1},{1,1},{1,1},{1,1}});
		assertThat(k, is(-1.0));
	}	
}
