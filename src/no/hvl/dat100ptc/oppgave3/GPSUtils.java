package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max; 
		
		max = da[0];
		
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		
		return max;
	}

	public static double findMin(double[] da) {

		double min;
		min =da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		

	return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] tablatitude = new double [gpspoints.length];
		for (int i=0; i < tablatitude.length; i++) {
			tablatitude[i] = gpspoints[i].getLatitude();
		}
		
		return tablatitude;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] tablongitude = new double [gpspoints.length];
		for (int i=0; i < tablongitude.length; i++) {
			tablongitude[i] = gpspoints[i].getLongitude();
		}
		
		return tablongitude;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		// get verdi
		
		latitude1=gpspoint1.getLatitude();
		latitude2=gpspoint2.getLatitude();
		longitude1=gpspoint1.getLongitude();
		longitude2=gpspoint2.getLongitude();
		
		
		//til radianer
		latitude1=latitude1*Math.PI/180;
		latitude2=latitude2*Math.PI/180;
		longitude1=longitude1*Math.PI/180;
		longitude2=longitude2*Math.PI/180;
		
		//delta
		double deltalatitude=latitude2-latitude1;
		double deltalongitude=longitude2-longitude1;
		//a/c/d verdier
		double a = (Math.pow(Math.sin(deltalatitude/2), 2))+Math.cos(latitude1)*Math.cos(latitude2)*(Math.sin(deltalongitude/2)*Math.sin(deltalongitude/2));
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		
		d = R*c;
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		// get tid	

		int sec1=gpspoint1.getTime();
		int sec2=gpspoint2.getTime();
		
		double d = distance(gpspoint1, gpspoint2);
		

		
		double ms = d/(sec2-sec1);
		speed = (ms*3600)/1000;
		
		return speed;

	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		// TODO - START
		String hr=;
		String min=;
		String sec=;
		timestr = ("  "+hr+TIMESEP+min+TIMESEP+sec);
		return timestr;
	}
	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;

		// TODO - START

		throw new UnsupportedOperationException(TODO.method());

		// TODO - SLUTT
		
	}
}
