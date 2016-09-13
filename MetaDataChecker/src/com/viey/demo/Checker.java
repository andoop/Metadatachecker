package com.viey.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Checker {
	private String apkdir;
	public Checker(String apkdir){
		this.apkdir=apkdir;
	}
	
	public void check(){
		if(apkdir==null||"".equals(apkdir)){
			System.out.println("error: apkdir can not be null !!! ");
			return;
		}
		
		File file = new File(apkdir);
		if(!file.exists()){
			System.out.println("error: apkdir is not exist !!!");
			return;
		}
		
		if(!file.isDirectory()){
			System.out.println("error: apkdir is not a directory !!!");
			return;
		}
		
		File[] listFiles = file.listFiles();
		for(File f:listFiles){
			if(f.isFile()&&f.getName().endsWith(".apk")){
				runExec(f);
			}
		}
		
		genarateRp();
		
	}

	private void genarateRp() {
		File file = new File("../out");
		File file_key = new File("../check.key");
		if(!file_key.exists()){
			System.out.println("error: check.key file is not find !!!");
			return;
		}
		if(!file.exists()){
			System.out.println("error: out directory is not find !!!");
			return;
		}
		
		if(file.isFile()){
			System.out.println("error: out is a file !!!!");
			return;
		}
		
		File[] listFiles = file.listFiles();
		for(File f:listFiles){
			if(f.isDirectory()){
				File manifest_file = new File(f.getAbsolutePath(),"AndroidManifest.xml");
				if(manifest_file.exists()&&manifest_file.isFile()){
					dealManifest(f,manifest_file,file_key);
				}
			}
		}
	}

	private void dealManifest(File f, File manifest_file,File file_key) {
	
		FileInputStream fis;
		InputStreamReader reader;
		BufferedReader br=null;
		System.out.println("\n================="+f.getName()+"=================");
		try {
			fis = new FileInputStream(manifest_file);
			reader=new InputStreamReader(fis);
			br=new BufferedReader(reader);
			 String line = null;
		      while ((line = br.readLine()) != null){
		    	  checkWithKey(f,line,file_key);
		      }
		    br.close();
		    System.out.println("====================================================");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}



	private void checkWithKey(File f, String line,File file_key) {
		FileInputStream fis;
		InputStreamReader reader;
		BufferedReader br=null;
		try {
			fis = new FileInputStream(file_key);
			reader=new InputStreamReader(fis);
			br=new BufferedReader(reader);
			 String keyline = null;
		      while ((keyline = br.readLine()) != null){
		    	  if(line.contains("\""+keyline.trim()+"\"")){
		    		  System.out.println(keyline.trim()+" = "+line.split("\\\"")[3]+"\n");
		    	  }
		      }
		    br.close();
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	private void runExec(File f) {
		 String cmd = "cmd.exe /C java -jar apktool.jar d -f -s " + f.getAbsolutePath()+" -o ../out/"+f.getName().split(".apk")[0];
		 Runtime rt = Runtime.getRuntime();
		    BufferedReader br = null;
		    InputStreamReader isr = null;
		    try {
		      Process p = rt.exec(cmd);

		      isr = new InputStreamReader(p.getInputStream());
		      br = new BufferedReader(isr);
		      String msg = null;
		      while ((msg = br.readLine()) != null)
		        System.out.println(msg);
		    }
		    catch (Exception e) {
		      e.printStackTrace();
		      try
		      {
		        if (isr != null) {
		          isr.close();
		        }
		        if (br != null)
		          br.close();
		      }
		      catch (IOException ie) {
		        ie.printStackTrace();
		      }
		    }
		    finally
		    {
		      try
		      {
		        if (isr != null) {
		          isr.close();
		        }
		        if (br != null)
		          br.close();
		      }
		      catch (IOException e) {
		        e.printStackTrace();
		      }
		    }
	}

}
