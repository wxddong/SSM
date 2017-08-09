package com.atwhere.p2p.myutil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileUtil {	
    
    /**
     * 删除文件（文件  空文件夹 非空文件夹都可以）
     * @param file
     * @return
     */
    public static boolean delete(File file){
    	if(!file.exists()){
    		return false;
    	}
    	if(file.isFile()){
    		return file.delete();
    	}else{
    		File[] files = file.listFiles();
    		for (File file1 : files) {
				delete(file1);
			}
    	}
    	return file.delete();
    }
    
    /**
     * 根据文件目录删除 （文件  空文件夹 非空文件夹都可以）
     * @param sPath
     * @return
     */
    public static boolean deleteByPath(String sPath){
    	File file = new File(sPath);
    	return delete(file);
    }
    
    /**
	 * 获取指定文件下的所有文件
	 * @param path 指定文件夹的路径
	 * @return 返回文件夹下所有文件的集合
	 */
	public static List<File> getFiles(String path){
        File root = new File(path);
        if(!root.exists()){
        	return null;
        }
        List<File> files = new ArrayList<File>();
        if(!root.isDirectory()){
            files.add(root);
        }else{
            File[] subFiles = root.listFiles();
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }    
        }
        return files;
    }
	
	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片 并返回可用的文件路径（可存入数据库的文件路径包含文件名）
	 * @param inputStream
	 * @param realPath : /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 如：surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(InputStream inputStream, String realPath) {
		OutputStream out = null;
		try {
			// 1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(inputStream);
			// 2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();
			// 3.计算目标图片的宽高值
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;
			if (sourceWidth > 100) {
				// 按比例压缩目标图片的尺寸
				targetWidth = 100;
				targetHeight = sourceHeight / (sourceWidth / 100);
			}
			// 4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth,targetHeight, BufferedImage.TYPE_INT_RGB);
			// 5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth,targetHeight, null);
			// 6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";
			// 7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath + "/" + targetFileName);
			// 8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			// 9.JPEG编码
			encoder.encode(targetImage);
			// 10.返回文件名
			return "surveyLogos/" + targetFileName;
		} catch (Exception e) {
			return null;
		} finally {
			// 10.关闭流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
     * 删除单个文件
     * @param sPath被删除文件的路径+文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
// public static boolean deleteFile(String sPath) {
//     Boolean flag = false;
//     File file = new File(sPath);
//     // 路径为文件且不为空则进行删除
//     if (file.isFile() && file.exists()) {
//         file.delete();
//         flag = true;
//     }
//     return flag;
// }
//
   /**
    * 删除目录（文件夹）以及目录下的文件
    * @param sPath 被删除目录的文件路径
    * @return 目录删除成功返回true，否则返回false
    */
// public static boolean deleteDirectory(String sPath) {
//     // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
//     if (!sPath.endsWith(File.separator)) {
//         sPath = sPath + File.separator;
//     }
//     File dirFile = new File(sPath);
//     // 如果dir对应的文件不存在，或者不是一个目录，则退出
//     if (!dirFile.exists() || !dirFile.isDirectory()) {
//         return false;
//     }
//     Boolean flag = true;
//     // 删除文件夹下的所有文件(包括子目录)
//     File[] files = dirFile.listFiles();
//     for (int i = 0; i < files.length; i++) {
//         // 删除子文件
//         if (files[i].isFile()) {
//             flag = deleteFile(files[i].getAbsolutePath());
//             if (!flag)
//                 break;
//         } // 删除子目录
//         else {
//             flag = deleteDirectory(files[i].getAbsolutePath());
//             if (!flag)
//                 break;
//         }
//     }
//     if (!flag)
//         return false;
//     // 删除当前目录
//     if (dirFile.delete()) {
//         return true;
//     } else {
//         return false;
//     }
// }
	
	/**
	 * 将查询出来的结果保存位xlsx格式的文件 最多可以写1多百万万行数据
	 * @param resultList 数据库查询出来的结果集
	 * @param rowNUm 每个sheet最多导入多少行数据 超过后自动新建一个sheet
	 */
//	public static void getExcel(List<QueryResult> resultList,int rowNum) {
//		
//		long start1 = System.currentTimeMillis();
//		int size = resultList.size();
//		int num = size/rowNum;
//		int mod = size%rowNum;
//		
//		XSSFWorkbook xssfWork;
//		SXSSFWorkbook sxssfWorkbook;
//		try {
//			xssfWork = new XSSFWorkbook();
//			sxssfWorkbook = new SXSSFWorkbook(xssfWork, 100);
//			
//			if(size==0){
//				Sheet sheet = sxssfWorkbook.createSheet("result");
//				Row row = sheet.createRow(0);
//				row.createCell(0).setCellValue("新网发货编号");
//				row.createCell(1).setCellValue("跃程商家订单号");
//				row.createCell(2).setCellValue("新网订单完成时间");
//				row.createCell(3).setCellValue("跃程订单完成时间");
//				row.createCell(4).setCellValue("公司订单状态");
//				row.createCell(5).setCellValue("跃程订单状态");
//				row.createCell(6).setCellValue("新网成本金额");
//				row.createCell(7).setCellValue("跃程销售金额");
//			}
//			
//			if(num!=0){
//				for (int i = 0; i < num; i++) {
//					Sheet sheet = sxssfWorkbook.createSheet("result"+(i+1));
//					Row row = sheet.createRow(0);
//					row.createCell(0).setCellValue("新网发货编号");
//					row.createCell(1).setCellValue("跃程商家订单号");
//					row.createCell(2).setCellValue("新网订单完成时间");
//					row.createCell(3).setCellValue("跃程订单完成时间");
//					row.createCell(4).setCellValue("公司订单状态");
//					row.createCell(5).setCellValue("跃程订单状态");
//					row.createCell(6).setCellValue("新网成本金额");
//					row.createCell(7).setCellValue("跃程销售金额");
//					
//					int m = 1;
//					
//					for (int j = i*rowNum; j < i*rowNum+rowNum; j++) {
//						
//						Row row1 = sheet.createRow(m);
//						m++;
//						
//						row1.createCell(0).setCellValue(resultList.get(j).getcOrderNum());
//						row1.createCell(1).setCellValue(resultList.get(j).getmOrderNum());
//						row1.createCell(2).setCellValue(resultList.get(j).getcTime());
//						row1.createCell(3).setCellValue(resultList.get(j).getmTime());
//						row1.createCell(4).setCellValue(resultList.get(j).getcStstus());
//						row1.createCell(5).setCellValue(resultList.get(j).getmStatus());
//						row1.createCell(6).setCellValue(resultList.get(j).getcCostAmount());
//						row1.createCell(7).setCellValue(resultList.get(j).getmSaleAmount());
//						
//					}
//				}
//			}
//		
//			if(mod!=0){
//				Sheet sheet = sxssfWorkbook.createSheet("result"+(num+1));
//				Row row = sheet.createRow(0);
//				row.createCell(0).setCellValue("新网发货编号");
//				row.createCell(1).setCellValue("跃程商家订单号");
//				row.createCell(2).setCellValue("新网订单完成时间");
//				row.createCell(3).setCellValue("跃程订单完成时间");
//				row.createCell(4).setCellValue("公司订单状态");
//				row.createCell(5).setCellValue("跃程订单状态");
//				row.createCell(6).setCellValue("新网成本金额");
//				row.createCell(7).setCellValue("跃程销售金额");
//				
//				int n = 1;
//				for (int j = resultList.size() - mod; j < resultList.size(); j++) {
//					Row row1 = sheet.createRow(n);
//					n++;
//					row1.createCell(0).setCellValue(resultList.get(j).getcOrderNum());
//					row1.createCell(1).setCellValue(resultList.get(j).getmOrderNum());
//					row1.createCell(2).setCellValue(resultList.get(j).getcTime());
//					row1.createCell(3).setCellValue(resultList.get(j).getmTime());
//					row1.createCell(4).setCellValue(resultList.get(j).getcStstus());
//					row1.createCell(5).setCellValue(resultList.get(j).getmStatus());
//					row1.createCell(6).setCellValue(resultList.get(j).getcCostAmount());
//					row1.createCell(7).setCellValue(resultList.get(j).getmSaleAmount());
//				}
//			}
//			
//			 FileOutputStream out = new FileOutputStream("E:\\workbook.xlsx");
//			 sxssfWorkbook.write(out);
//			 out.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("文件没有被找到");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		long end1 = System.currentTimeMillis();	
//		System.out.println("Excel导出完成，用时:"+(end1-start1));
//	}

    public static void main(String[] args) {
//    	File file = new File("E:/important1/文件夹3//");
    	 boolean deleteDirectory = deleteByPath("E:/important1");
    	System.out.println(deleteDirectory);
//    	System.out.println(files.size());
//    	System.out.println(deleteFile);
	}
}
