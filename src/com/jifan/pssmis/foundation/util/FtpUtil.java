package com.jifan.pssmis.foundation.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.swing.ImageIcon;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.jifan.pssmis.foundation.exception.PubShowMessage;
import com.jifan.pssmis.foundation.log.SysLogger;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * ftp上传，下载
 * 
 * @author zhangwl
 * 
 */
public class FtpUtil {

	private static String LOCAL_CHARSET = "GBK";

	// FTP协议里面，规定文件名编码为iso-8859-1
	private static String SERVER_CHARSET = "ISO-8859-1";

	private String ip = "";

	private String username = "";

	private String password = "";

	private int port = -1;

	FTPClient ftp = null;

	private static String picPath;

	private static String nodePicPath;

	private static String memberPicPath;

	/**
	 * 从配置文件获取地址，端口，用户名，密码
	 */
	public FtpUtil() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle("ftp_config");
		this.ip = bundle.getString("url");
		this.username = bundle.getString("name");
		this.password = bundle.getString("password");
		this.port = Integer.valueOf(bundle.getString("port"));
	}
	
	/**
	 * 文件内部类
	 * @author chela
	 *
	 */
	public class FileVO {
		private String fileNewName;//新文件名
		private String fileName;//原始文件名
		public FileVO(String fileName,String fileNewName){
			this.fileName=fileName;
			this.fileNewName=fileNewName;
		}
		public String getFileNewName() {
			return fileNewName;
		}
		public void setFileNewName(String fileNewName) {
			this.fileNewName = fileNewName;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	}

	public static String getRealURL(String picNname) {
		if (CommonUtil.isEmpty(picNname))
			return "";
		return SysInitUtil.getPhotosUrl() + "/" + picNname;
	}

	public static String getRealMiniURL(String picNname) {
		if (CommonUtil.isEmpty(picNname))
			return "";
		return SysInitUtil.getPhotosUrl() + "/mini/" + picNname;
	}

	public static String getRealDarkURL(String picNname) {
		if (CommonUtil.isEmpty(picNname))
			return "";
		return SysInitUtil.getPhotosUrl() + "/dark/" + picNname;
	}

	/**
	 * 转换byte数组为Image
	 * 
	 * @param bytes
	 * @return Image
	 */
	public static Image bytesToImage(byte[] bytes) {
		Image image = Toolkit.getDefaultToolkit().createImage(bytes);
		try {
			MediaTracker mt = new MediaTracker(new Label());
			mt.addImage(image, 0);
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return image;
	}

	/**
	 * 转换Image数据为byte数组
	 * 
	 * @param image
	 *            Image对象
	 * @param format
	 *            image格式字符串.如 "jpeg ", "png "
	 * @return byte数组
	 */
	public static byte[] imageToBytes(Image image, String format) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
			BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bImage.getGraphics();
		bg.drawImage(image, 0, 0, null);
		bg.dispose();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ImageIO.write(bImage, format, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
		// targetW，targetH分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		// if (flag) {
		//
		// if (sx > sy) {
		// sx = sy;
		// targetW = (int) (sx * source.getWidth());
		// } else {
		// sy = sx;
		// targetH = (int) (sy * source.getHeight());
		// }
		// }
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(targetW, targetH, type);
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param width
	 *            缩略图宽
	 * @param hight
	 *            缩略图高
	 * @param fromFileStr
	 *            源图地址
	 * @param saveToFileStr
	 *            缩略后上传的地址
	 * @param name
	 *            图片名称
	 * @throws MalformedURLException
	 * 
	 * 
	 *             public static void saveImageAsJpg(String fromFileStr, String
	 *             saveToFileStr, String name, int width, int hight) throws
	 *             Exception { FtpUtil ftp = new FtpUtil(); try { String
	 *             fileName = ""; BufferedImage srcImage; // String ex = //
	 *             fromFileStr
	 *             .substring(fromFileStr.indexOf("."),fromFileStr.length());
	 *             String imgType = "JPEG"; if
	 *             (fromFileStr.toLowerCase().endsWith(".png")) { imgType =
	 *             "PNG"; } String url = fromFileStr + "/" + name;//"http://192.168.7.168:8888/imgServer/wewe/dac200c8-9644-4fa8-8b8c-8c89c54100a1.jpg"
	 *             ;//从图片服务器上下载图片 URL httpurl = new URL(url); String[] us =
	 *             url.split("/"); fileName = us[us.length - 1]; File f = new
	 *             File(fileName); FileUtils.copyURLToFile(httpurl, f);// 写入文件流
	 * 
	 *             srcImage = ImageIO.read(f); if (width > 0 || hight > 0) {
	 *             srcImage = resize(srcImage, width, hight); }
	 *             ImageIO.write(srcImage, imgType, f); InputStream
	 *             inputStream=new FileInputStream(f);
	 *             ftp.uploadFile(saveToFileStr,name,inputStream); } catch
	 *             (Exception e) { SysLogger.forceInfo(FtpUtil.class,
	 *             e.getMessage()); SysLogger.forceInfo(FtpUtil.class,
	 *             "图片缩略失败"); }
	 * 
	 *             }
	 */
	/**
	 * 
	 * @param fromFileStr
	 * @param saveToFileStr
	 * @param name
	 * @param width
	 * @param hight
	 * @param b
	 * @throws Exception
	 */
	public static void saveImageAsJpg(String fromFileStr, String saveToFileStr, String name, int width, int hight,
			byte[] b) throws Exception {
		FtpUtil ftp = new FtpUtil();
		try {
			String imgType = "JPEG";
			if (fromFileStr.toLowerCase().endsWith(".png")) {
				imgType = "PNG";
			}
			ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流；
			BufferedImage srcImage = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可
			if (width > 0 || hight > 0) {
				srcImage = resize(srcImage, width, hight);
			}
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(srcImage, imgType, os);
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
			ftp.uploadFile(saveToFileStr, name, inputStream);
		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, "图片缩略失败" + e.getMessage());
		}
	}

	public static void saveCutImage(String fromFileStr, String saveToFileStr, String name, int x, int y, int width,
			int height, byte[] b) throws Exception {
		FtpUtil ftp = new FtpUtil();
		try {
			String imgType = "JPEG";
			if (fromFileStr.toLowerCase().endsWith(".png")) {
				imgType = "PNG";
			}
			// 先缩略
			ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流；
			BufferedImage srcImage = ImageIO.read(in); // 将in作为输入流，读取图片存入image中，而这里in可
			if (width > 0 || height > 0) {
				srcImage = resize(srcImage, width, height);
			}
			srcImage = srcImage.getSubimage(x, y, srcImage.getWidth() - 2 * x, srcImage.getHeight() - 2 * y);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(srcImage, imgType, os);
			InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
			ftp.uploadFile(saveToFileStr, name, inputStream);
		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, "图片缩略失败" + e.getLocalizedMessage());
		}
	}

	public void resize(String fromFileStr, String saveToFileStr, String name, int targetW, int targetH, byte[] b)
			throws Exception {
		FtpUtil ftp = new FtpUtil();
		// String imgType = "JPEG";
		// if (fromFileStr.toLowerCase().endsWith(".png")) {
		// imgType = "PNG";
		// }
		File floder = new File("d://jinyuanimg/minit/");
		floder.mkdirs();
		File resizedFile = new File("d://jinyuanimg/minit/" + name);
		ByteArrayInputStream in = new ByteArrayInputStream(b); // 将b作为输入流；
		BufferedImage source = ImageIO.read(in);

		ImageIcon ii = new ImageIcon(source);
		Image i = ii.getImage();
		Image resizedImage = null;

		int iWidth = i.getWidth(null);
		int iHeight = i.getHeight(null);

		if (iWidth > iHeight) {
			resizedImage = i.getScaledInstance(targetW, (targetW * iHeight) / iWidth, Image.SCALE_SMOOTH);
		} else {
			resizedImage = i.getScaledInstance((targetW * iWidth) / iHeight, targetW, Image.SCALE_SMOOTH);
		}

		// This code ensures that all the pixels in the image are loaded.
		Image temp = new ImageIcon(resizedImage).getImage();

		// Create the buffered image.
		BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null), temp.getHeight(null),
			BufferedImage.TYPE_INT_RGB);

		// Copy image to buffered image.
		Graphics g = bufferedImage.createGraphics();

		// Clear background and paint the image.
		g.setColor(Color.white);
		g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
		g.drawImage(temp, 0, 0, null);
		g.dispose();

		// Soften.
		float softenFactor = 0.05f;
		float[] softenArray = { 0, softenFactor, 0, softenFactor, 1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
		Kernel kernel = new Kernel(3, 3, softenArray);
		ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
		bufferedImage = cOp.filter(bufferedImage, null);

		// Write the jpeg to a file.
		FileOutputStream out = new FileOutputStream(resizedFile);
		// if(imgType.equals("JEPG")){
		// Encodes image as a JPEG data stream
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);

		param.setQuality(1f, true);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(bufferedImage);

		out.close();
		// }
		FileInputStream inputStream = new FileInputStream(resizedFile);
		ftp.uploadFile(saveToFileStr, name, inputStream);
		inputStream.close();
		in.close();

		deleteLocalDirectory("d://jinyuanimg/minit");
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public boolean deleteLocalDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteLocalFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteLocalDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteLocalFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 图片上传统一接口
	 * @param item 上传的文件
	 * @param picPath 上传的ftp地址
	 * @param sllength 缩略图长 如200
	 * @param slwidth 缩略图宽如200
	 * @return
	 * @throws Exception
	 */
	public FileVO picUpload(UploadedFile item,String picPath,int sllength,int slwidth) throws Exception {
		
		String fileName = item.getFileName();
		int a = fileName.lastIndexOf(".");
		// 图片后缀
		String ext = fileName.substring(a + 1);
		// 保存图片
		String id = UUID.randomUUID().toString();
		// 图片新名称
		String fileNewName = id + "." + ext;
		String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());

		if (!this.isDirExist(pathString)) {// 创建目录
			if (!this.createDir(pathString)) {
				PubShowMessage.showInfo("目录已存在");
			}
		}
		this.uploadFile(pathString, fileNewName, item.getContents());

		// 缩略图处理
		if (!this.isDirExist(pathString + "/" + "mini")) {// 创建目录
			if (!this.createDir(pathString + "/" + "mini")) {
				PubShowMessage.showInfo("目录已存在");
			}
		}
		FtpUtil.saveImageAsJpg(picPath,pathString+"/mini",fileNewName,sllength,slwidth,item.getContents());
		FileVO fileVO= new FileVO(fileName,fileNewName);
		return fileVO;
	}
	
	/**
	 * 文件上传统一接口
	 * @param item 上传的文件
	 * @param picPath 上传的ftp地址
	 * @param sllength 缩略图长 如200
	 * @param slwidth 缩略图宽如200
	 * @return
	 * @throws Exception
	 */
	public FileVO fileUpload(UploadedFile item,String picPath) throws Exception {
		
		String fileName = item.getFileName();
		int a = fileName.lastIndexOf(".");
		// 后缀
		String ext = fileName.substring(a + 1);
		// 保前缀
		String qz = fileName.substring(0, a );
		String id = qz+DateUtil.format(new Date(), "yyyyMMddHHmmss");
		// 图片新名称
		String fileNewName = id + "." + ext;
		String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());

		if (!this.isDirExist(pathString)) {// 创建目录
			if (!this.createDir(pathString)) {
				PubShowMessage.showInfo("目录已存在");
			}
		}
		this.uploadFile(pathString, fileNewName, item.getContents());

		FileVO fileVO= new FileVO(fileName,fileNewName);
		return fileVO;
	}

	public static void main(String[] args) throws Exception {

		FtpUtil ftpUtil = new FtpUtil();
		ftpUtil.openService();
		// System.out.println(ftpUtil.isDirExist("weweimg/mini"));
		FTPClient ftp2 = ftpUtil.ftp;
		InputStream is = new FileInputStream(new File("G:\\1.jpg"));
		ftp2.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpUtil.uploadFile("/test/", "哈哈.jpg", is);
		is.close();
		ftp2.disconnect();
		/**
		 * FTP远程命令列表 USER PORT RETR ALLO DELE SITE XMKD CDUP FEAT PASS PASV STOR
		 * REST CWD STAT RMD XCUP OPTS ACCT TYPE APPE RNFR XCWD HELP XRMD STOU
		 * AUTH REIN STRU SMNT RNTO LIST NOOP PWD SIZE PBSZ QUIT MODE SYST ABOR
		 * NLST MKD XPWD MDTM PROT
		 * 在服务器上执行命令,如果用sendServer来执行远程命令(不能执行本地FTP命令)的话，所有FTP命令都要加上\r\n
		 * ftpclient.sendServer("XMKD /test/bb\r\n"); //执行服务器上的FTP命令
		 * ftpclient.readServerResponse一定要在sendServer后调用
		 * nameList("/test")获取指目录下的文件列表 XMKD建立目录，当目录存在的情况下再次创建目录时报错 XRMD删除目录
		 * DELE删除文件
		 */
	}

	/**
	 * 控制文件的大小
	 * 
	 * @param file_in
	 */
	@SuppressWarnings("unused")
	private boolean fileSize(File file_in) {
		if (file_in == null || file_in.length() == 0) {
			// addActionError(getText("customer.electroFile.errors.fileUnallowed"));
			return false;
		} else {
			if (file_in.length() > (1024 * 1024 * 5)) {
				return false;
			}
		}
		return true;
	}

	public static String getPicPath() {
		String pic = cheVoid(SysInitUtil.getPhotosUrl());
		if (pic.equals("") || pic == null) {
			picPath = "";
		} else {
			picPath = pic + "/mini/";
		}
		return picPath;
	}

	public static String getNodePicPath() {
		String pic = cheVoid(SysInitUtil.getNodePhotosUrl());
		if (pic.equals("") || pic == null) {
			nodePicPath = "";
		} else {
			nodePicPath = pic + "/mini/";
		}
		return nodePicPath;
	}

	public static String getMemberPicPath() {
		String pic = cheVoid(SysInitUtil.getMemberPhotosUrl());
		if (pic.equals("") || pic == null) {
			memberPicPath = "";
		} else {
			memberPicPath = pic + "/mini/";
		}
		return memberPicPath;
	}

	@SuppressWarnings("static-access")
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	/***************************************************************************
	 * 验证 URL http://192.168.7.168:8888/imgServer/wew/wewe/ 取
	 * http://192.168.7.168:8888/imgServer/wewe/
	 * 
	 * @param url
	 *            zhangwl
	 */
	public static String cheVoid(String url) {
		if (url == null || url.equals("")) {
			return "";
		}
		String url1 = "";
		int f = numberOfStr(url, "/");
		StringBuffer str = new StringBuffer();
		if (f >= 3) {
			String[] s = url.split("/");

			int k = 4;
			for (int i = 0; i < k; i++) {
				if (i == k - 1) {
					str.append(s[i]);
				} else {
					str.append(s[i] + "/");
				}
			}

			if (f > 3) {
				url1 = url.substring(url.lastIndexOf("/") + 1, url.length());
				if (url1.equals("") || url1 == null) {
					String url2 = url.substring(0, url.length() - 1);
					url1 = url2.substring(url2.lastIndexOf("/") + 1, url2.length());
				}
			}
		}
		return url1 == "" ? str.toString() : str.toString() + "/" + url1;
	}

	/**
	 * @param k
	 *            "/" 第几次出现之前
	 * @param str
	 * @param con
	 * @returnzhangwl
	 */
	public static int numberOfStr(String str, String con) {
		str = " " + str;
		if (str.endsWith(con)) {
			return str.split(con).length;
		} else {
			return str.split(con).length - 1;
		}
	}

	/**
	 * 将byte数组转换成InputStream
	 * 
	 * @param in
	 * @return // filename:要上传的文件
	 * 
	 *         // path :上传服务器上的哪个文件夹
	 * 
	 *         // 初始表示上传失败
	 * @throws Exception
	 */
	public static InputStream byteTOInputStream(byte[] in) throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(in);
		return is;
	}

	/**
	 * 
	 * @param path
	 * @param filename
	 * @param b
	 * @return // filename:要上传的文件
	 * 
	 *         // path :上传服务器上的哪个文件夹
	 * 
	 *         // 初始表示上传失败
	 * @throws Exception
	 */
	public boolean uploadFile(String path, String filename, byte[] b) throws Exception {
		// filename:要上传的文件
		// path :上传服务器上的哪个文件夹
		// 初始表示上传失败
		boolean success = false;
		InputStream input = null;
		input = byteTOInputStream(b);
		try {
			if (!openService()) { // 打开服务
				return success;
			}
			// ftp.changeWorkingDirectory(path);// 转移到FTP服务器目录

			String path1 = new String(path.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
			// 转到指定上传目录
			ftp.changeWorkingDirectory(path1);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.appendFile(new String(filename.getBytes(LOCAL_CHARSET), LOCAL_CHARSET), input);
			input.close();
			ftp.logout();
			// 表示上传成功
			success = true;
			System.out.println("upload Success。。。。。。");

		} catch (IOException e) {
			e.printStackTrace();
			SysLogger.error(this.getClass(), e.getMessage());

		} finally {

			if (ftp.isConnected()) {

				try {

					ftp.disconnect();

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}

		}

		return success;

	}

	/**
	 * 
	 * @param path
	 * @param filename
	 * @param b
	 * @return // filename:要上传的文件
	 * 
	 *         // path :上传服务器上的哪个文件夹
	 * 
	 *         // 初始表示上传失败
	 * @throws Exception
	 */
	public boolean uploadFile(String path, String filename, InputStream input) throws Exception {

		boolean success = false;
		try {
			if (!openService()) { // 打开服务
				return success;
			}
			String path1 = new String(path.getBytes(LOCAL_CHARSET), SERVER_CHARSET);
			ftp.changeWorkingDirectory(path1);
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			success = ftp.appendFile(new String(filename.getBytes(LOCAL_CHARSET), SERVER_CHARSET), input);
			input.close();
			ftp.logout();
		} catch (IOException e) {
			SysLogger.error(this.getClass(), e.getMessage());

		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;

	}

	/**
	 * 在当前目录下创建文件夹
	 * 
	 * @param dir
	 *            所要创建的子目录
	 * @param path
	 *            需要创建目录的上1级目录
	 * @return
	 * @throws Exception
	 */
	public boolean createDir(String pathString) {
		boolean success = false;
		try {
			if (!openService()) { // 打开服务
				return success;
			}
			ftp.makeDirectory(pathString);
			ftp.changeWorkingDirectory(pathString);
			success = true;
		} catch (Exception e) {
			success = false;

		} finally {
			if (ftp.isConnected()) {
				try {

					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * 检查文件夹在当前目录下是否存在
	 * 
	 * @param dir
	 * @return
	 */
	public boolean isDirExist(String pathString) {
		boolean success = false;
		try {
			// 检查远程文件是否存在
			createDir(pathString);
			success = true;
			return success;
		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, "判断文件存在异常" + e.getMessage());
			success = true;
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}
		}
		return success;
	}

	public boolean openService() {
		boolean success = false;
		try {
			ftp = new FTPClient();
			ftp.connect(ip, port);
			int reply = ftp.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				if (ftp.login(username, password)) {
					// 解决外网无法上传的问题http://www.coderanch.com/t/207085/sockets/java/FTP-connection-Proxy
					ftp.setRemoteVerificationEnabled(false);
					if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {// 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
						LOCAL_CHARSET = "UTF-8";
					}
					ftp.setControlEncoding(LOCAL_CHARSET);
					success = true;
				} else {
					ftp.disconnect();
				}

				// FTPClientConfig conf = new
				// FTPClientConfig(FTPClientConfig.SYST_NT);
				// conf.setServerLanguageCode("zh");
				ftp.enterLocalPassiveMode();// 被动模式
			}
		} catch (Exception e) {
			success = false;
		}
		return success;
	}

	/**
	 * 
	 * @param pathname
	 * @return
	 */
	public boolean deleteFile(String pathname) {
		boolean success = true;
		try {
			if (!openService()) { // 打开服务
				return success;
			}
			success = ftp.deleteFile(pathname);
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
		} finally {

			if (ftp.isConnected()) {

				try {

					ftp.disconnect();

				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

			}

		}
		return success;
	}

	/**
	 * 上传图片至FTP服务器 author pengxl 2013-5-17 下午01:52:42
	 * 
	 * @param event
	 * @param miniPicWidth
	 *            缩略图宽度
	 * @param miniPicHeight
	 *            缩略图高度
	 * @return
	 */
	public Map<String, String> savePic2server(FileUploadEvent event, int miniPicWidth, int miniPicHeight) {
		UploadedFile uploadedFile = event.getFile();

		// 上传的图片的文件名称
		String fileName = uploadedFile.getFileName();
		// 上传图片的文件名后缀索引
		int fileExtensionIndex = fileName.lastIndexOf(".");
		// 图片后缀
		String fileExtension = fileName.substring(fileExtensionIndex + 1);
		String id = UUID.randomUUID().toString();
		String fileNewName = id + "." + fileExtension;// 图片新名称
		// 缩略图路径
		FtpUtil ftpUtil = new FtpUtil();
		// 文件信息
		Map<String, String> filemessage = new HashMap<String, String>();
		try {

			String picPath = cheVoid(SysInitUtil.getNodePhotosUrl());
			String mimiPath = picPath.substring(0, picPath.lastIndexOf("/") + 1) + "mini/";
			String picPath1 = "resources/images";
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
				.getContext();
			String filepath = servletContext.getRealPath(picPath1);

			File folder = new File(filepath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			folder = new File(mimiPath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			/*** FTP上传 ****/
			String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());// 存放图片的文件夹名称

			if (!ftpUtil.isDirExist(pathString)) {// 创建目录
				if (!ftpUtil.createDir(pathString)) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			if (!ftpUtil.isDirExist(pathString + "/" + "mini")) {// 创建目录
				if (!ftpUtil.createDir(pathString + "/" + "mini")) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			/*** FTP上传图片 ****/
			uploadFile(pathString, fileNewName, uploadedFile.getContents());
			saveImageAsJpg(picPath, pathString + "/mini", fileNewName, miniPicWidth, miniPicHeight, uploadedFile
				.getContents());

			filemessage.put("picName", fileNewName);
			filemessage.put("fileFullName", fileName);
			filemessage.put("picPath", picPath);
			filemessage.put("picExtendName", fileExtension);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return filemessage;

	}

	/**
	 * 上传并裁剪图片
	 * 
	 * @param fileName
	 * @param fileByte
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean uploadPicWithCut(String picPath, String fileName, byte[] fileByte, int x, int y, int width,
			int height) {
		boolean success = false;
		// int fileExtensionIndex = fileName.lastIndexOf(".");
		// 图片后缀
		// String fileExtension = fileName.substring(fileExtensionIndex + 1);
		// String id = UUID.randomUUID().toString();
		// String fileNewName = id + "." + fileExtension;// 图片新名称
		// 缩略图路径
		FtpUtil ftpUtil = new FtpUtil();
		// 文件信息
		try {

			// String picPath = cheVoid(SysInitUtil.getPhotosUrl());
			/*** FTP上传 ****/
			String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());// 存放图片的文件夹名称

			if (!ftpUtil.isDirExist(pathString)) {// 创建目录
				if (!ftpUtil.createDir(pathString)) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			if (!ftpUtil.isDirExist(pathString + "/" + "mini")) {// 创建目录
				if (!ftpUtil.createDir(pathString + "/" + "mini")) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			/*** FTP上传图片 ****/
			success = uploadFile(pathString, fileName, fileByte);
			if (success) {
				// saveCutImage(picPath, pathString + "/mini",
				// fileName,x,y,width, height,fileByte);
				resize(picPath, pathString + "/mini", fileName, width, height, fileByte);
			}

		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, e.getMessage());
			success = false;
		}
		return success;
	}

	/**
	 * @Title: uploadPicWithCutAndpath
	 * @Description: 上传文件
	 * @param: @param picPath
	 * @param: @param fileName
	 * @param: @param fileByte
	 * @param: @param x
	 * @param: @param y
	 * @param: @param width
	 * @param: @param height
	 * @param: @return
	 * @return: boolean
	 * @throws
	 * @author XUJUN
	 * @email 15059697@qq.com
	 * @Date 2016年1月26日 下午3:50:09
	 */
	public boolean uploadPicWithCutAndpath(String picPath, String fileName, byte[] fileByte, int targetW, int targetH) {
		boolean flag = false;
		if (!picPath.endsWith("/")) {
			picPath = picPath + "/";
		}
		ByteArrayInputStream byteArrayInputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			if (!openService()) {
				return flag;
			}
			if (!ftp.changeWorkingDirectory(picPath)) {
				return flag;
			}
			String imgType = "JPEG";
			if (fileName.toLowerCase().endsWith(".png")) {
				imgType = "PNG";
			}
			byteArrayInputStream = new ByteArrayInputStream(fileByte); // 将b作为输入流；
			BufferedImage source = ImageIO.read(byteArrayInputStream);
			BufferedImage dealImage = dealImage(source, targetW, targetH);
			byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(dealImage, imgType, byteArrayOutputStream);
			this.uploadFile(picPath, fileName, byteArrayOutputStream.toByteArray());
		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, e.getMessage());
			flag = false;
		} finally {
			try {
				if (byteArrayInputStream != null) {
					byteArrayInputStream.close();
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * @Title: dealImage
	 * @Description: 先缩放，后裁剪
	 * @param: @param source
	 * @param: @param targetWidth
	 * @param: @param targetHeight
	 * @param: @return
	 * @return: BufferedImage
	 * @throws
	 * @author XUJUN
	 * @email 15059697@qq.com
	 * @Date 2016年1月27日 下午12:38:45
	 */
	private BufferedImage dealImage(BufferedImage source, int targetWidth, int targetHeight) {
		int srcWidth = source.getWidth(); // 源图宽度
		int srcHeight = source.getHeight(); // 源图高度
		float srcRatio = (float) srcWidth / srcHeight;
		float targetRatio = (float) targetWidth / targetHeight;
		int tempWidth = srcWidth;
		int tempHeight = srcHeight;
		// 缩放
		if (srcRatio > targetRatio) {// 如果原尺寸比例大于目标尺寸比例
			tempHeight = targetHeight;
			tempWidth = (int) Math.ceil(targetHeight * srcRatio);
		} else {
			tempWidth = targetWidth;
			tempHeight = (int) Math.ceil(tempWidth / srcRatio);
		}
		Image scaledInstance = source.getScaledInstance(tempWidth, tempHeight, Image.SCALE_SMOOTH);
		BufferedImage zipImage = convertImageToBuffer(scaledInstance);
		// 裁剪
		BufferedImage subimage = zipImage.getSubimage(tempWidth - targetWidth < 0 ? 0 : (int) Math
			.ceil((tempWidth - targetWidth) / 2), tempHeight - targetHeight < 0 ? 0 : (int) Math
			.ceil((tempHeight - targetHeight) / 2), targetWidth, targetHeight);
		return subimage;
	}

	/**
	 * @Title: convertImageToBuffer
	 * @Description: Image 转换成BufferedImage
	 * @param: @param pic
	 * @param: @return
	 * @return: BufferedImage
	 * @throws
	 * @author XUJUN
	 * @email 15059697@qq.com
	 * @Date 2016年1月27日 下午12:23:27
	 */
	private BufferedImage convertImageToBuffer(Image pic) {
		BufferedImage bufferedImage = new BufferedImage(pic.getWidth(null), pic.getHeight(null),
			BufferedImage.TYPE_INT_RGB);
		Graphics g = bufferedImage.createGraphics();
		g.drawImage(pic, 0, 0, null);
		g.dispose();
		return bufferedImage;
	}

	/**
	 * 上传并裁剪图片
	 * 
	 * @param fileName
	 * @param fileByte
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean uploadPicWithCut(String fileName, byte[] fileByte, int x, int y, int width, int height) {
		SysLogger.forceDebug(FtpUtil.class, "文件大小:" + fileByte.length);
		boolean success = false;
		// int fileExtensionIndex = fileName.lastIndexOf(".");
		// 图片后缀
		// String fileExtension = fileName.substring(fileExtensionIndex + 1);
		// String id = UUID.randomUUID().toString();
		// String fileNewName = id + "." + fileExtension;// 图片新名称
		// 缩略图路径
		FtpUtil ftpUtil = new FtpUtil();
		// 文件信息
		try {

			String picPath = cheVoid(SysInitUtil.getPhotosUrl());
			/*** FTP上传 ****/
			String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());// 存放图片的文件夹名称

			if (!ftpUtil.isDirExist(pathString)) {// 创建目录
				if (!ftpUtil.createDir(pathString)) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			if (!ftpUtil.isDirExist(pathString + "/" + "mini")) {// 创建目录
				if (!ftpUtil.createDir(pathString + "/" + "mini")) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			/*** FTP上传图片 ****/
			success = uploadFile(pathString, fileName, fileByte);
			if (success) {
				// saveCutImage(picPath, pathString + "/mini",
				// fileName,x,y,width, height,fileByte);
				resize(picPath, pathString + "/mini", fileName, width, height, fileByte);
			}

		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, e.getMessage());
			success = false;
			e.printStackTrace();
		}
		return success;
	}

	public boolean uploadPic(String fileName, byte[] fileByte, int width, int height) {
		boolean success = false;
		// int fileExtensionIndex = fileName.lastIndexOf(".");
		// 图片后缀
		// String fileExtension = fileName.substring(fileExtensionIndex + 1);
		// String id = UUID.randomUUID().toString();
		// String fileNewName = id + "." + fileExtension;// 图片新名称
		// 缩略图路径
		FtpUtil ftpUtil = new FtpUtil();
		// 文件信息
		try {

			String picPath = cheVoid(SysInitUtil.getPhotosUrl());
			/*** FTP上传 ****/
			String pathString = picPath.substring(picPath.lastIndexOf("/") + 1, picPath.length());// 存放图片的文件夹名称

			if (!ftpUtil.isDirExist(pathString)) {// 创建目录
				if (!ftpUtil.createDir(pathString)) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			if (!ftpUtil.isDirExist(pathString + "/" + "mini")) {// 创建目录
				if (!ftpUtil.createDir(pathString + "/" + "mini")) {
					PubShowMessage.showInfo("目录已存在");
				}
			}
			/*** FTP上传图片 ****/
			deleteFile(pathString + "/" + fileName);
			success = uploadFile(pathString, fileName, fileByte);
			if (success) {
				deleteFile(pathString + "/mini/" + fileName);
				saveImageAsJpg(picPath, pathString + "/mini", fileName, width, height, fileByte);
			}

		} catch (Exception e) {
			SysLogger.forceInfo(FtpUtil.class, e.getMessage());
			success = false;
		}
		return success;
	}

}