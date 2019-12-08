package tableClass;

public class Dormitory_cost {
	private static final long serialVersionUID = 5L;
	private String kind_code;
	private int mng_cost1;
	private int mng_cost2;
	private int mng_cost3;
	private int mng_cost4;
	private int fd_food_cost1;
	private int fd_food_cost2;
	private int fd_food_cost3;
	private int fd_food_cost4;
	private int sd_food_cost1;
	private int sd_food_cost2;
	private int sd_food_cost3;
	private int sd_food_cost4;
	
	public Dormitory_cost(String k, int mc1, int mc2, int mc3, int mc4,
			int ffc1, int ffc2, int ffc3, int ffc4, int sfc1, int sfc2, int sfc3, int sfc4)
	{
		kind_code = k;
		mng_cost1 = mc1;
		mng_cost2 = mc2;
		mng_cost3 = mc3;
		mng_cost4 = mc4;
		fd_food_cost1 = ffc1;
		fd_food_cost2 = ffc2;
		fd_food_cost3 = ffc3;
		fd_food_cost4 = ffc4;
		sd_food_cost1 = sfc1;
		sd_food_cost2 = sfc2;
		sd_food_cost3 = sfc3;
		sd_food_cost4 = sfc4;
	}
	
	public String getKind_code() {return kind_code;}
	public int getMng_cost1() {return mng_cost1;}
	public int getMng_cost2() {return mng_cost2;}
	public int getMng_cost3() {return mng_cost3;}
	public int getMng_cost4() {return mng_cost4;}
	public int getFd_food_cost1() {return fd_food_cost1;}
	public int getFd_food_cost2() {return fd_food_cost2;}
	public int getFd_food_cost3() {return fd_food_cost3;}
	public int getFd_food_cost4() {return fd_food_cost4;}
	public int getSd_food_cost1() {return sd_food_cost1;}
	public int getSd_food_cost2() {return sd_food_cost2;}
	public int getSd_food_cost3() {return sd_food_cost3;}
	public int getSd_food_cost4() {return sd_food_cost4;}
	
	public void setKind_code(String k) {kind_code = k;}
	public void setMng_cost1(int mc1) {mng_cost1 = mc1;}
	public void setMng_cost2(int mc2) {mng_cost1 = mc2;}
	public void setMng_cost3(int mc3) {mng_cost1 = mc3;}
	public void setMng_cost4(int mc4) {mng_cost1 = mc4;}
	public void setFd_food_cost1(int ffc1) {fd_food_cost1 = ffc1;}
	public void setFd_food_cost2(int ffc2) {fd_food_cost2 = ffc2;}
	public void setFd_food_cost3(int ffc3) {fd_food_cost3 = ffc3;}
	public void setFd_food_cost4(int ffc4) {fd_food_cost4 = ffc4;}
	public void setSd_food_cost1(int sfc1) {sd_food_cost1 = sfc1;}
	public void setSd_food_cost2(int sfc2) {sd_food_cost2 = sfc2;}
	public void setSd_food_cost3(int sfc3) {sd_food_cost3 = sfc3;}
	public void setSd_food_cost4(int sfc4) {sd_food_cost4 = sfc4;}
}
