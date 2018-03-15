package tyut.practice.onlineDisk.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tyut.practice.onlineDisk.dao.DocDao;
import tyut.practice.onlineDisk.model.Doc;

public class DocService {
	private DocDao dao = new DocDao();
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	/**
	 * 根据传入的用户(owner),路径(dir)删除文件夹
	 * @param  String owner,String dir
	 * @return boolean
	 */
	public boolean deleteDir(String owner, String dir) {
		try{
			List<Doc> docs = dao.getAllDocsUnderDir(owner, dir);
			
			for(Doc doc : docs){
				if(doc.getIsdir() == 1){
					dao.deleteDocByDir(owner, doc.getDirectory());
					continue;
				}
				String realdir = doc.getRealdirectory();
				dao.deleteDocByDir(owner, doc.getDirectory());
				
				java.io.File f = new java.io.File(realdir);
				if(f.isFile() && f.exists()) {  
			        f.delete();  
			    }
			}
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 根据传入的用户(owner),路径(dir)返回文件列表
	 * @param  String owner,String dir
	 * @return List<Doc>
	 */
	public List<Doc> getDocs(String dir, String owner) {
		List<Doc> docs = dao.getDocUnderDir(owner, dir);
		return docs;
	}
	/**
	 * 根据传入的用户(owner),路径(dir)删除文件
	 * @param  String owner String dir
	 * @return boolean
	 */
	public boolean deleteDoc(String owner, String dir) {
		// TODO Auto-generated method stub
		Doc doc = dao.getDocByOwnerAndDirc(owner, dir);
		String realdir = doc.getRealdirectory();
		
		java.io.File f = new java.io.File(realdir);
		if(f.isFile() && f.exists()) {  
	        f.delete();  
	    }
		return dao.deleteDocByDir(owner, dir);
	}
	/**
	 * 根据传入的用户(owner),id(id)返回文件对象
	 * @param  String owner,String dir
	 * @return boolean
	 */
	public Doc getDocById(int id, String owner) {
		// TODO Auto-generated method stub
		return dao.getDocById(owner, id);
	}
	/**
	 * 根据传入的用户(owner),路径(dir)，文件名(name),文件的真实路径(realpath),文件的大小(long size)
	 * @param  String owner
	 * @param  String dir, String name, String realpath, long size
	 * @return boolean
	 */
	public boolean createDoc(String owner, String dir, String name, String realpath, long size) {
		// TODO Auto-generated method stub
		if(dao.getDocByOwnerAndDirc(owner, dir.equals("")?name:dir + "/" + name) != null){
			return false;
		}
		
		int pid = -1;
		if(!dir.equals("")){
			pid = dao.getDocByOwnerAndDirc(owner, dir).getId();
		}
		
		Doc doc = new Doc();
		doc.setDirectory(dir.equals("")?name:dir + "/" + name);
		doc.setCreatetime(df.format(new Date()));
		doc.setName(name);
		doc.setPid(pid);
		doc.setIsdir(0);
		doc.setOwner(owner);
		doc.setSize(size/1000);
		doc.setRealdirectory(realpath);
		dao.addDoc(doc);
		return true;
	}
	/**
	 * 根据传入的父目录(pdir),用户(owner),文件夹名(name)创建文件夹
	 * @param  String owner,String dir
	 * @return boolean
	 */
	public boolean createDir(String pdir, String owner, String name) {
		// TODO Auto-generated method stub
		try{
			if(dao.getDocByOwnerAndDirc(owner, pdir.equals("")?name:pdir + "/" + name) != null){
				return false;
			}
			Doc pf = null;
			if(!pdir.equals("")){
				pf = dao.getDocByOwnerAndDirc(owner, pdir);
			}
			
			Doc doc = new Doc();
			doc.setCreatetime(df.format(new Date()));
			if(pf == null){
				doc.setDirectory(name);
				doc.setRealdirectory(name);
				doc.setPid(-1);
			} else{
				doc.setDirectory(pf.getDirectory() + "/" + name);
				doc.setRealdirectory(pf.getDirectory() + "/" + name);
				doc.setPid(pf.getId());
			}
			doc.setIsdir(1);
			doc.setName(name);
			doc.setOwner(owner);
			doc.setSize(0);
			dao.addDoc(doc);
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
