package com.my.MathJS.example;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.content.res.ColorStateList;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;
import java.io.File;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;
import java.io.BufferedOutputStream;

public class AJCode
{
	public static void SetRoundedAndRipple(View view,int LT,int RT,int RB,int LB,int Color1,int size,int Color2,int Color3){
		GradientDrawable shape = new android.graphics.drawable.GradientDrawable();
		shape.setColor(Color1);
		shape.setCornerRadii(new float[]{(float)LT,(float)LT,(float)RT,(float)RT,(float)RB,(float)RB,(float)LB,(float)LB});
		shape.setStroke(size, Color2);
		RippleDrawable ripdr = new RippleDrawable(new ColorStateList(new int[][]{new int[]{}}, new int[]{Color3}), shape, null);
		view.setBackgroundDrawable(ripdr);
	}
	
	public static void zipFile(String fromPath,String toPath){
		try {
			ZipDirMain(fromPath, toPath);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void ZipDirMain(String path1,String path2) throws IOException {
		FileOutputStream fos = new FileOutputStream(path2);
		ZipOutputStream zipOut = new ZipOutputStream(fos);
		File fileToZip = new File(path1);
		zipFile(fileToZip, fileToZip.getName(), zipOut);
		zipOut.close();
		fos.close();
	}

	private static void zipFile(File fileToZip,String fileName,ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
			}
			zipOut.closeEntry();
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		FileInputStream fis = new FileInputStream(fileToZip);
		byte[] bytes = new byte[fis.available()];
		fis.read(bytes);
		zipOut.write(bytes);
		fis.close();
		zipOut.closeEntry();
	}
	
	public static void unzipFile(String zipFile,String dscPath){
		try{
			File outdir = new File(dscPath);
			ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry entry;
			String name, dir;
			while ((entry = zin.getNextEntry()) != null){
				name = entry.getName();
				if(entry.isDirectory()){
					mkdirs(outdir, name);
					continue;
				}
				dir = dirpart(name);
				if(dir != null){
					mkdirs(outdir, dir);
				}
				extractFile(zin, outdir, name);
			}
			zin.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void extractFile(ZipInputStream in, File outdir, String name) throws IOException{
		byte[] buffer = new byte[4096];
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(outdir, name)));
		int count = -1;
		while ((count = in.read(buffer)) != -1){
			out.write(buffer, 0, count);
		}
		out.close();
	}

	public static void mkdirs(File outdir, String path){
		File d = new File(outdir, path);
		if(!d.exists()){
			d.mkdirs();
		}
	}

	public static String dirpart(String name){
		int s = name.lastIndexOf(File.separatorChar);
		return s == -1 ? null : name.substring(0, s);
	}
}
