
public class AreaScore {
	private String area;
	private Double score;
	
	public AreaScore(String a, double s)
	{
		area = a;
		score = s;
	}
	
	public String getArea( ) { return area; }	
	public Double getScore() {return score;}
	
	public void setArea(String a) { area = a; }
	public void setScore(double s) {score = s;}
}