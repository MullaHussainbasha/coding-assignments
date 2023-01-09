package HighPeakSoftwarePvtLtd;

import java.util.Arrays;
import java.util.Comparator;

public class Factory 
{
	static class Job
	{
	    double start, finish, profit;
	    Job(double start, double d, double profit)
	     {
	        this.start = start;
	        this.finish = d;
	        this.profit = profit;
	     }
	}
	static int latestNonConflict(Job arr[], int i)
	{
	    for (int j = i - 1; j >= 0; j--)
	    {
	        if (arr[j].finish <= arr[i - 1].start)
	            return j;
	    }
	    return -1;
	}
	static double findMaxProfitRec(Job arr[], int n)
	{
	    if (n == 1) return arr[n-1].profit;
	    double inclProf = arr[n-1].profit;
	    int i = latestNonConflict(arr, n);
	    if (i != -1)
	    inclProf += findMaxProfitRec(arr, i+1);
	    double exclProf = findMaxProfitRec(arr, n-1);
	 
	    return Math.max(inclProf, exclProf);
	}
	static double findMaxProfit(Job arr[], int n)
	{
	    Arrays.sort(arr,new Comparator<Job>(){
	       public int compare(Job j1,Job j2)
	        {
	           return  (int) (j1.finish-j2.finish);
	        }
	       });
	 
	    return findMaxProfitRec(arr, n);
	}
	public static void main(String args[])
	{
	  
		int m = 3;
	   Job arr[] = new Job[m];
	    arr[0] = new Job(9, 10.30, 100);
	    arr[1] = new Job(10, 12, 100);
	    arr[2] = new Job(11, 12, 100);
	   
	    int n =arr.length;
	    System.out.println("The optimal profit is " + findMaxProfit(arr, n));
	}

}
