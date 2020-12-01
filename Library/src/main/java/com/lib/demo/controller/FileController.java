package com.lib.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lib.demo.bean.Book;
import com.lib.demo.constate.SysConstate;
import com.lib.demo.service.BookService;
import com.lib.demo.utils.AppFileUtils;
import com.lib.demo.utils.ExcelUtil;
import com.lib.demo.utils.RandomUtils;

@RestController
@RequestMapping("/file")
public class FileController {
		
	@Autowired
	BookService bookservice;
		@PostMapping("upload")
		public Map<String,Object> upload(@RequestParam("file") MultipartFile mf) throws IOException{
			System.out.println(mf);
			 // 文件上传的父目录
	        String parentPath = AppFileUtils.PATH;
	        // 得到当前日期作为文件夹名称
	        String dirName = RandomUtils.getCurrentDateForString();
	        // 构造文件夹对象
	        File dirFile = new File(parentPath, dirName);
	        if (!dirFile.exists()) {

	            dirFile.mkdirs();// 创建文件夹
	        }
	        // 得到文件原名
	        String oldName = mf.getOriginalFilename();
	        // 根据文件原名得到新名
	        String newName = RandomUtils.createFileNameUseTime(oldName,SysConstate.FILE_UPLOAD_TEMP);
	        File dest = new File(dirFile, newName);
	        mf.transferTo(dest);
	        Map<String,Object> map=new HashMap<>();
	        map.put("src", dirName+"/"+newName);
	        return map;
			
		}
		 /**
	     * 不下载只显示
	     */
		  @RequestMapping("downloadShowFile")
		    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) {
		        return AppFileUtils.downloadFile(response, path, "");
		    }
	    
	    
	    
	    @GetMapping("/loadimg")
	    public ResponseEntity<byte[]> loadimg(String path) {
	        //读取图片，数据库存储图片的类型为Image，接收类型为byte[]
	        
	        byte[] bytes=null;
	        HttpHeaders headers = new HttpHeaders();
	        try {
	            headers.setContentType(MediaType.IMAGE_PNG);
	            headers.setContentDispositionFormData("attachment", new String("图标".getBytes("GBK"),"ISO8859-1"));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return new ResponseEntity<byte[]>(bytes,headers, HttpStatus.OK);
	    }
	    
	    @GetMapping(value = "/exportExcel")
	    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
	        Book obj = new Book();
	        String name =(String) request.getParameter("bookname");
	        String type =(String) request.getParameter("type");
	        System.out.println("------@@@@@"+type);
	        String author =(String) request.getParameter("author");
	        String country =(String) request.getParameter("country");
	        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
	        wrapper.like(name!=null&&name!="", "name",name);
	        wrapper.like(type!=null&&type!="", "type",type);
	        wrapper.like(author!=null&&author!="", "author",author);
	        wrapper.like(country!=null&&country!="", "country",country);
	        
	        List<Book> list = bookservice.list(wrapper);
	        String[] title = {"ID", "name", "author", "total", "type", "country", "length","theme","bookdesc"};
	        String filename = "BookRecord.xls";
	        String sheetName = "sheet1";
	        String[][] content = new String[list.size()][9];
	        try {
	            for (int i = 0; i < list.size(); i++) {
	                content[i][0] = String.valueOf(list.get(i).getId());
	                content[i][1] = list.get(i).getName();
	                content[i][2] = list.get(i).getAuthor();
	                content[i][3] = String.valueOf(list.get(i).getTotal());
	                content[i][4] = list.get(i).getType();
	                content[i][5] = list.get(i).getCountry();
	                content[i][6] = list.get(i).getLength();
	                content[i][7] = list.get(i).getTheme();
	                content[i][8] = list.get(i).getBookdesc();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);
	        try {
	            // 响应到客户端
	            this.setResponseHeader(response, filename);
	            OutputStream os = response.getOutputStream();
	            wb.write(os);
	            os.flush();
	            os.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	        /**
	         * 向客户端发送响应流方法
	         *
	         * @param response
	         * @param fileName
	         */
	        public void setResponseHeader(HttpServletResponse response, String fileName) {
	            try {
	                try {
	                    fileName = new String(fileName.getBytes(), "UTF-8");
	                } catch (UnsupportedEncodingException e) {
	                    e.printStackTrace();
	                }
	                response.setContentType("application/vnd.ms-excel");
	                response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	   

}
