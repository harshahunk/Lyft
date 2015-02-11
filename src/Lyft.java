import java.text.DecimalFormat;

/*
 * For simplicity, the program takes input as the decimal degree representation of coordinates.
 *  The inputs shall be provided in the following order
 *  Alatitude Alongitude Blatitude Blongitude Clatitude Clongitude Dlatitude Dlongitude
 */

public class Lyft {
	public static void main(String[] args){
		if(args.length == 8){
			coord A = new coord();
			coord B = new coord();
			coord C = new coord();
			coord D = new coord();
			A.setLat(Math.toRadians(Double.parseDouble(args[0])));
			A.setLon(Math.toRadians(Double.parseDouble(args[1])));
			B.setLat(Math.toRadians(Double.parseDouble(args[2])));
			B.setLon(Math.toRadians(Double.parseDouble(args[3])));
			C.setLat(Math.toRadians(Double.parseDouble(args[4])));
			C.setLon(Math.toRadians(Double.parseDouble(args[5])));
			D.setLat(Math.toRadians(Double.parseDouble(args[6])));
			D.setLon(Math.toRadians(Double.parseDouble(args[7])));
			System.out.println(minDetour(A,B,C,D));
		}
		else{
			System.out.println("Provide 8 coordinates.");
			System.exit(1);
		}
	
	}
	
	/**
	 * <b>Haversine formula</b> is used to find the distance between the co-ordinates
	 * @param A - one of the co-ordinates
	 * @param B - one of the co-ordinates
	 * @return <b>double</b> - the distance between the co-ordinates
	 */
	
	private static double calculateDistance(coord A,coord B){
		double distance;
		double difflat = Math.abs(A.getLat() - B.getLat());
		double difflon = Math.abs(A.getLon() - B.getLon());
		distance = Math.pow(Math.sin((difflat/2)),2) + 
		    Math.cos(A.getLat())*Math.cos(B.getLat())*Math.pow((Math.sin(difflon/2)), 2);
		distance = 2 * radius * Math.asin(Math.sqrt(distance));
		return distance/1.60934;
	}
	
	public static String minDetour(coord A,coord B,coord C,coord D){
		double AB,BD,CD,AC;

		AB = calculateDistance(A, B);
		AC = calculateDistance(A, C);
		BD = calculateDistance(B, D);
		CD = calculateDistance(C, D);
	
		double driver1Detour = (AC+CD+BD - AB);
		double driver2Detour = (AC+AB+BD - CD);
		if(driver1Detour > driver2Detour)
			return "Detour taken by driver 2 is minimum.Detour distance: "+new DecimalFormat("#.##").format(driver2Detour)+" miles";
		else if(driver1Detour < driver2Detour)
			return "Detour taken by driver 1 is minimum.Detour distance: "+new DecimalFormat("#.##").format(driver1Detour)+" miles";
		else
			return "Detour distance of both driver is the same: "+new DecimalFormat("#.##").format(driver2Detour)+" miles";

	}
	static final int radius = 6371;
}

/*
 * class to store co-ordinates is created 
 */
class coord{
	private double lat;
	private double lon;
	
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	
}

