package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon)); 

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {
		
		double maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		double ystep = MAPYSIZE / (Math.abs(maxlat - minlat)); 

		return ystep;
	}
		

	public void showRouteMap(int ybase) {

		double startx = 0;
		double starty = 0;
		double ystep = ystep();
		double xstep = xstep();

		double minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		
		starty=ybase-(Math.abs(gpspoints[0].getLatitude()-minlat)*ystep); //forskjell nå * pixler per latitdue
		startx=50+(Math.abs(gpspoints[0].getLongitude()-minlon)*xstep);//forskjell nå * pixler per longitude
		
		for(int i=1; i < gpspoints.length-1; i++) {
			double lat = gpspoints[i].getLatitude();
			double lon = gpspoints[i].getLongitude();
			double endy = ybase-(Math.abs(lat-minlat)*ystep); 
			double endx = 50+(Math.abs(lon-minlon)*xstep);
			
			setColor(0,255,0);
			drawLine((int)startx,(int)starty,(int)endx,(int)endy);
			fillCircle((int)startx,(int)starty,(int)3);
			starty=endy;
			startx=endx;
			
			
		}
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0,0,0);
		setFont("Courier",12);
		
		String time = ("Total time         : " + GPSUtils.formatTime(gpscomputer.totalTime()));
		drawString(time,TEXTDISTANCE,TEXTDISTANCE);
		String distance = ("Total distance : " + GPSUtils.formatDouble(gpscomputer.totalDistance()/1000)+"km");
		drawString(distance,TEXTDISTANCE,TEXTDISTANCE*2);
		String elevation = ("Total elevation: " + GPSUtils.formatDouble(gpscomputer.totalElevation())+"m");
		drawString(elevation,TEXTDISTANCE,TEXTDISTANCE*3);
		String maxspeed = ("Max Speed      : " + GPSUtils.formatDouble(gpscomputer.maxSpeed())+"km/t");
		drawString(maxspeed,TEXTDISTANCE,TEXTDISTANCE*4);
		String avgspeed = ("Avg Speed      : " + GPSUtils.formatDouble(gpscomputer.averageSpeed())+"km/t");
		drawString(avgspeed,TEXTDISTANCE,TEXTDISTANCE*5);
		String kcal = ("Energy             : " + GPSUtils.formatDouble(gpscomputer.totalKcal(80))+"kcal");
		drawString(kcal,TEXTDISTANCE,TEXTDISTANCE*6);
	}
	

}
