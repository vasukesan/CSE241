package lab1;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class ClosestPair {
	
	public final static double INF = java.lang.Double.POSITIVE_INFINITY;
	XComparator lessThanX = new XComparator();

	/** 
	 * Given a collection of points, find the closest pair of point and the
	 * distance between them in the form "(x1, y1) (x2, y2) distance"
	 *
	 * @param pointsByX points sorted in nondecreasing order by X coordinate
	 * @param pointsByY points sorted in nondecreasing order by Y coordinate
	 * @return Result object containing the points and distance
	 */
	static Result findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[]) {
		return closestHelp(pointsByX,pointsByY,0,pointsByX.length);
	}

	static Result closestHelp(XYPoint[] pointsByX, XYPoint[] pointsByY, int beg, int end) {
		//Base cases. Each "section" has only one or two points in it. 
		int n = end-beg;
		if(n==1) {
			return new Result(pointsByX[beg], pointsByX[beg], INF);
		}
		if(n==2) {
			return new Result(pointsByX[beg], pointsByX[beg+1], pointsByX[0].dist(pointsByX[1]));
		}
		
		//cuts pointsByY recursively so that only the relevant points gets passed into combine
		XYPoint[] pointsByYLeft = new XYPoint[n/2];
		XYPoint[] pointsByYRight = new XYPoint[n-n/2];
		int j = 0;
		int k = 0;
		for(int i=0;i<pointsByY.length;i++) {
			if(pointsByY[i].isLeftOf(pointsByX[beg+n/2])) {
				pointsByYLeft[j] = pointsByY[i];
				j++;
			} else {
				pointsByYRight[k] = pointsByY[i];
				k++;
			}
		}
		
		//The recursive calls - "cuts" the array into section until there is only 1 or 2 points in each.
		//also passes through the "cut" pointsByY
		Result lMin = closestHelp(pointsByX,pointsByYLeft,beg, beg+n/2);
		Result lMin2 = closestHelp(pointsByX,pointsByYRight,beg+n/2,end); 
		
		//which result was closer?
		Result closest;
		if(lMin.dist>lMin2.dist  && lMin2.dist!=0) {
			closest = lMin2;
		} else {
			closest = lMin;
		}
		
		//Use that result to check the "borders" of the "cuts" and potentially find a new result
		//by considering the y direction.
		closest = combine(pointsByY, pointsByX[beg+n/2], closest);
		
		//make sure the points are in the right order and return the closest result.
		if(closest.p2.isLeftOf(closest.p1)) {
			return new Result(closest.p2,closest.p1,closest.dist);
		} else {
			return closest;
		}
		
	}

	static Result combine(XYPoint[] pointsByY, XYPoint midx, Result closest) {
		//collects all points distance closest.dist away from the "border" ("cut" in the middle) in order
		ArrayList<XYPoint> possibleY = new ArrayList<XYPoint>();
		for(int i=0;i<pointsByY.length;++i) {
			if(abs(pointsByY[i].x-midx.x) <= closest.dist){
				possibleY.add(pointsByY[i]);
			}
		}
		
		//of the points in possible y, are any of them closer than the result passed in? checks in order
		double minD = closest.dist;
		int k = 0;
		for (XYPoint p : possibleY) {
			k=possibleY.indexOf(p)+1;
			while (k<possibleY.size() && (possibleY.get(k).y-p.y)<minD) {
				if(p.dist(possibleY.get(k))<closest.dist  && p.num!=possibleY.get(k).num) {
					minD = p.dist(possibleY.get(k));
					closest = new Result(p,possibleY.get(k), p.dist(possibleY.get(k)));
				}
				++k;
			}
		}
		return closest;
	}
	
	static Result findClosestPairBruteForce(XYPoint[] pointsByXExpected, XYPoint[] pointsByYExpected){
		Result r = new Result(pointsByXExpected[0],pointsByXExpected[1],pointsByXExpected[0].dist(pointsByXExpected[1]));
		double dist = r.dist;
		for (int i=0;i<pointsByXExpected.length;i++) {
			for(int j=0;j<pointsByXExpected.length;j++) {
				if ((pointsByXExpected[i].dist(pointsByXExpected[j]))<dist   &&
						(pointsByXExpected[i].dist(pointsByXExpected[j])!=0)){
					dist = pointsByXExpected[i].dist(pointsByXExpected[j]);
					r = new Result(pointsByXExpected[i],pointsByXExpected[j],pointsByXExpected[i].dist(pointsByXExpected[j]));
				}
			}
		}
		return r;
	}

	static int abs(int x) {
		if (x < 0) {
			return -x;
		} else {
			return x;
		}
	}
}


