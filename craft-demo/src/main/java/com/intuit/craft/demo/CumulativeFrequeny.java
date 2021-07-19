package com.intuit.craft.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * The Main class implements an application that reads lines from the standard input
 * and prints them to the standard output.
 */
public class CumulativeFrequeny {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    String line = "14 15 15 14 13 19 21 10";
 
      String[] splited = line.split(" "); 
      int[] arr = new int[splited.length];
      for(int i=0; i< splited.length;i++){
         arr[i] = Integer.parseInt(splited[i]);        
      }
      int[] res = group(arr);
      line = "";
      for(int i=0; i< res.length;i++) 
        line += res[i];
      
    System.out.println(line);
  }    
  
    
   public static int[] group(int[] nums){
      int n = nums.length;
      int k = highestPower(n);
      
      Arrays.sort(nums);
      int min = nums[0], max = nums[n-1];
      int interval = (int) Math.ceil((max-min+1)/k);
      int i=0, j=0, start, end;
      int[] fre = new int[k];
      int[] cumFre = new int[k];
      
      while(j<k && i<n){ 
    	  start = min + j*interval;
    	  end = min + j*interval + interval - 1;
    	  while(i<n && nums[i] >= start &&  nums[i] <= end) {
    		  fre[j] +=1;
              i++;
    	  }
    	  cumFre[j] +=fre[j] + (j>0? cumFre[j-1] : 0);
          j++;
      }
      
     return cumFre;
       
    }
    
    
   public static int highestPower(int num){
      int ans = 0;
      for(int i = num; i>=1; i--) {
        if((i & (i-1)) ==0 ) {
          ans =i;
          break;
        }
      }
      
      if (ans == num) return power2(num);
      else  return 1+ power2(num);
           
    } 
      
   public static int power2(int num){  
   if(num ==1) return 0;
      return 1+ power2(num/2);
    
    }
    
}