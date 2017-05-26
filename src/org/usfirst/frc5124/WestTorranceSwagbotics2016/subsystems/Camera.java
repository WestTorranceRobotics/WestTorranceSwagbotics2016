package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Camera extends Subsystem {
    
	// these are arrays for the values i grab from the network table for vision
	// they are arrays becuase grip may publish multiple values for each field if it sees multiple "targets"
	double[] defaultValueConvex = {0};		// not sure what this is, i think its an index or something sorry just know I needed it for network tables
    public double[] centerX;				// x coordinate in pixels of the center of the target 			
    public double[] centerY;				// y coordinate in pixels of the center of the target  
    public double[] width;					// width in pixels of the target
    public double[] height;					// height in pixels of the target
    public double[] area;					// area in pixels^2 of the target	
    public double degrees = 0;				// the degrees the robot needs to turn to face the center of the target
    public double[] targets;				// unsure what this is
    public final int[] rangePixels = {54, 51, 47, 43, 40, 38, 35, 33, 31, 29};	//used this to estimate distance based on how big the target is in one foot intervals
    public boolean targetSighted = false;	// boolean for if the robot thinks it sees a target
  
    
    public static NetworkTable convexReport = NetworkTable.getTable("GRIP/convexHullReport");	// the network table

    public void initDefaultCommand() {
    	
    }
    
    public void getReportValues() {	// method to grab values from the newtork table
    	//try catch becuase if no target is seen, then there will be no values and you'll be accessing null values
    	try{	// try to grab the values from the network
	    	centerX = convexReport.getNumberArray("centerX", defaultValueConvex);
	        centerY = convexReport.getNumberArray("centerY", defaultValueConvex);
	        width = convexReport.getNumberArray("width", defaultValueConvex);
	        height = convexReport.getNumberArray("height", defaultValueConvex);
	        area = convexReport.getNumberArray("area", defaultValueConvex);
	        degrees = (centerX[0] - 140) * .209375;
	        targetSighted = true;
    	} catch(Exception e) {		// if theres not targets seen by the robot it wont send values, and we get an Exception 
    		targetSighted = false;	// we dont see a target
    	}
    }
    
    public double getDistance() {
    	return (-2.814655172 * width[0]) + 66.82327586;	//return estimated distance, doesnt work, dont know how i calculated this?
    }
    
    public String detectRange() {	// also for returning distance, also didnt work 
    	
    	double[] difference = new double[10];	// array for storing the differences in the width of the target to known values
    	int foot = 0;							// feet away from target 
    	double leastDifferent = 0;				// index of the least different 
    	double leastDifference = 0;				// for storing the least different vlaue
    	
    	for(int i = 0; i < 10; i++) {	// 10 loops because I have widths at 10 different distances
    		difference[i] = Math.abs(width[0] - rangePixels[i]); 	// calculate the difference between the width of the target and the observed
    	}															// width at each distance
    	
    	leastDifference = difference[0];	// make the least different value the value at the 0th index of differences
    	
    	for(int i = 1; i < 10; i++) {	//check to see which is the least different and store that index
    		if(difference[i] < leastDifference) {	// if the current difference is less than the least differnece
    			leastDifference = difference[i];	// store this difference
    			leastDifferent = i;					// store this index
    		}
    	}
    	
    	foot = (int) (leastDifferent + 5);			// add 5 for some reason i guess
													// dont know how this is supposed to give feet lol
    	
    	return (foot - 1) + "-" + foot + " feet away"; // say we are some where between (foot -1) and foot feet away ex 9-10 feet away
    	
    }
    
    public double getDegrees() {
    	return (degrees = (centerX[0] - 140) * .209375); 	// take the centerx coord, subtract the center pixel (140) and multiply by the 
    }														// ratio of degrees per pixel to get the degrees
    
    public int targetIndex() {	// method for deciding which target is the best looking target to aim at
    	
    	// this works because each data array for target data from the network is consistent between arrays for the indices
    	// ie for every array, the first target is index 0, the second is 1, third is 2, and so on
    	
    	if(centerX.length > 1) {	// if there's more than one target, the length will be above one
    		double[] lengthRatios = new double[centerX.length];		// make an array for storing aspect ratios based on how many targets there are
    		int target = 0;		// index of the target we will shoot at
    		
    		for(int i = 0; i < centerX.length; i++) {
    			lengthRatios[i] = width[i]/height[i];		// find and store the aspect ratios of each target
    		}
    		
    		for(int i = 1; i < lengthRatios.length; i++) {	// start at i = 1 becuase we dont need to check if the first target has a better ratio than itself
    			if(Math.abs(lengthRatios[i] - (5/3)) < lengthRatios[target]) {		// compare that to the actual aspect ratio of the target (5/3)
    				target = i;		// if this target has an aspect ratio closer to 5/3 than the one we think is the target, store its index
    			}					// it is now the target we think is best
    		}
    		
    		return target;			// return the index of the target we think is best to shoot at
    	} else {
    		return 0;				// return 0 if there is 1 or 0 targets
    	}
    }
    
}

