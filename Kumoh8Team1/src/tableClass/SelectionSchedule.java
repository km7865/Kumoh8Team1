package tableClass;

public class SelectionSchedule {
	private int year;
	private int semester;
	private String program_code;
	private String start_date;
	private String end_date;
	private String content;
	
	public SelectionSchedule(int y, int s, String p, String sd, String e, String c)
	{
		year = y;
		semester = s;
		program_code = p;
		start_date = sd;
		end_date = e;
		content = c;
	}
	
	public int getYear() {return year;}
	public int getSemester() {return semester;}
	public String getProgram_code() {return program_code;}
	public String getStart_date() {return start_date;}
	public String getEnd_date() {return end_date;}
	public String getContent() {return content;}
	
	public void setYear(int y) {year = y;}
	public void setSemester(int s) {semester = s;}
	public void setProgram_code(String p) {program_code = p;}
	public void setStart_date(String sd) {start_date = sd;}
	public void setEnd_date(String e) {end_date = e;}
	public void setContent(String c) {content = c;}
}
