package tyut.practice.onlineDisk.model;

public class Doc {

	private int id;
	private String name;
	private String type;
	private String owner;
	private String createtime;
	private long size;
	private String directory;
	private int pid;
	private int isdir;
	private String realdirectory;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getIsdir() {
		return isdir;
	}
	public void setIsdir(int isdir) {
		this.isdir = isdir;
	}
	public String getRealdirectory() {
		return realdirectory;
	}
	public void setRealdirectory(String realdirectory) {
		this.realdirectory = realdirectory;
	}
	
	
}
