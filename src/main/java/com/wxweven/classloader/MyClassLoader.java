package com.wxweven.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader {

	private String classDir;

	public MyClassLoader() {}
	
	public MyClassLoader(String classDir) {
		this.classDir = classDir;
	}
	
	public static void main(String[] args) {
		
		if(args.length != 2) {
			System.err.println("Error!! parameters need to two");
		} 
		
		String srcPath = args[0];//源文件的完整路径，包括目录和文件名
		String destDir = args[1];//目标文件的目录名
		
		File srcFile = new File(srcPath);
		File destFile = null;
		
		//为了让程序兼容性更好，做个判断，用户输入目录名时，可以以"/"结尾，也可以不以"/"结尾
		if(destDir.endsWith("/"))
			destFile = new File(destDir + srcFile.getName());
		else
			destFile = new File(destDir + "/" + srcFile.getName());
		
		System.out.println("source file is:" + srcFile.getPath());
		System.out.println("dest file is:" + destFile.getPath());
		
		try {
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			cypher(fis, fos);//加密类文件
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 逐个字节的加密类二进制代码
	 * @param is 输入流
	 * @param os 输出流
	 * @throws IOException
	 */
	private static void cypher(InputStream is, OutputStream os) throws IOException {
		int b = -1;
		while((b = is.read()) != -1){
			os.write(b ^ 0xff);
		}
	}

	/**
	 * 自定义的类加载器
	 * 覆盖父类额findClass(),通过自定义的方法来加载类的字节码到内存
	 */
	@SuppressWarnings("deprecation")
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
//		定义类字节码文件
		String classFileName = classDir + "/" + className.substring(className.lastIndexOf('.') + 1) + ".class";
		System.out.println("the user defined classFileName is:" + classFileName );
		
//		定义一个ByteArrayOutputStream，用于存放临时的输出的字节
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		定义文件输入流，用于读取类文件的字节码
		FileInputStream fis = null;
		
		
		try {
			fis = new FileInputStream(classFileName);
			cypher(fis, baos);//解密类字节码文件
			//将解密输出后的类字节码存到数组中
			byte[] bytes = baos.toByteArray();
			
			//将数组中保存的类字节码，当作类的定义返回
			return defineClass(bytes, 0, bytes.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
