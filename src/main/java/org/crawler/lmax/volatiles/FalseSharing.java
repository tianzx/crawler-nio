package org.crawler.lmax.volatiles;

public class FalseSharing implements Runnable{

	public final static int NUM_THREADS = 4;
	public final static long ITERATIONS = 500L * 1000L *1000L;
	private final int arrayIndex;
	private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];
	static {
		for(int i=0;i<longs.length;i++){
			longs[i] = new VolatileLong();
		}
	}
	
	public FalseSharing(final int arrayIndex){
		this.arrayIndex = arrayIndex;
	}
	
	public static void main(String[] args) {
		final long start = System.nanoTime();
		runTest();
		System.err.println("duration = " + (System.nanoTime() - start));
	}
	private static void runTest() {
		// TODO Auto-generated method stub
		Thread[] threads = new Thread[NUM_THREADS];
		for(int i=0;i<threads.length;i++){
			threads[i]= new Thread(new FalseSharing(i));
		}
		for(Thread t:threads){
			t.start();
		}
		for(Thread t:threads){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() { 
		long i = ITERATIONS +1;
		while(0!=--i){
			longs[arrayIndex].value = i;
		}
	}
	public final static class VolatileLong{
		public volatile long value = 0L;
		public long p1,p2,p3,p4,p5,p6;
				
	}
}
