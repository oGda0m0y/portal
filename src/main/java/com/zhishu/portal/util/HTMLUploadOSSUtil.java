package com.zhishu.portal.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;

/**
 * 将文件和图片上传到OSS Bucket 中。
 * 
 */
public class HTMLUploadOSSUtil {
	/**
	 * 阿里云 Access Key ID
	 */
	protected static String accessId = "hc44qMkvgMdfP51v";

	/**
	 * 阿里云 Access Key Secret
	 */
	protected static String accessKey = "T8rPyyqzj7RSfYM8ZOgRCYiARWxH1D";

	/**
	 * OSS http 域名
	 */ // oss-cn-beijing.aliyuncs.com
		// oss-cn-beijing.aliyuncs.com
	protected static String ossHttpDomain = "html-think-data.oss-cn-beijing.aliyuncs.com";

	/**
	 * oss 上传下载接入点
	 */
	protected static String ossEndpoint = "oss-cn-beijing.aliyuncs.com";

	public static String bucketName = "html-think-data";

	protected static OSSClient client;

	static {
		client = new OSSClient(ossEndpoint, accessId, accessKey);
	}

	/**
	 * 删除指定的Bucket下的url
	 * 
	 * @param url
	 *            ，请提供要删除文件的原图地址
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static void deleteObject(String url) throws OSSException, ClientException {
		// 截取出key

		// 删除Object
		client.deleteObject(bucketName, url);
	}

	/**
	 * 上传文件
	 * 
	 * @param filename
	 *            上传文件名
	 * @param fileLength
	 * @param input
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static String uploadFile(String filename, long fileLength, InputStream input)
			throws OSSException, ClientException {

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(fileLength);
		// 可以在metadata中标记文件类型

		String key = getKey(filename);
		client.putObject(bucketName, key, input, objectMeta);
		return generateHTTPURL(bucketName) + key;
	}

	public static String uploadFile(String filename, long fileLength, byte[] bs) throws OSSException, ClientException {

		String result = null;
		if (StringUtils.isBlank(filename)) {
			return result;
		}
		if (bs == null || bs.length > 0) {
			return result;
		}
		InputStream inputStream = new java.io.ByteArrayInputStream(bs);
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(fileLength);
		// 可以在metadata中标记文件类型

		String key = getKey(filename);
		client.putObject(bucketName, key, inputStream, objectMeta);
		return generateHTTPURL(bucketName) + key;
	}

	/**
	 * 实现一个 Object:File 的上传
	 * 
	 * @param filename
	 *            上传文件名
	 * @param file
	 *            上传 文件对象
	 * @return 上传成功Object OSS地址
	 * @throws OSSException
	 * @throws ClientException
	 * @throws FileNotFoundException
	 */
	public static String uploadFile(String filename, File file) {
		try {
			// 获取指定文件的输入流
			InputStream content = new FileInputStream(file);

			// 创建上传Object的Metadata
			ObjectMetadata meta = new ObjectMetadata();
			// 必须设置ContentLength
			meta.setContentLength(file.length());

			String key = getKey(filename);
			// 上传Object.
			client.putObject(bucketName, key, content, meta);

			return generateHTTPURL(bucketName) + key;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 实现一个 Object:InputStream 的上传
	 * 
	 * @param filename
	 * @param inputStream
	 *            上传文件的inputStream的对象
	 * @return 上传成功Object OSS地址
	 * @throws OSSException
	 * @throws ClientException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static String uploadFile(String filename, InputStream inputStream)
			throws OSSException, ClientException, NumberFormatException, IOException {

		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		// meta.setContentLength(Long.parseLong(String.valueOf(inputStream.available())));

		String key = getKey(filename);
		// 上传Object.
		client.putObject(bucketName, key, inputStream, meta);

		return generateHTTPURL(bucketName) + key;
	}

	public static String uploadFile(String filename, byte[] bs) throws OSSException, ClientException, IOException {

		String result = null;
		if (StringUtils.isBlank(filename)) {
			return result;
		}
		if (bs == null || bs.length == 0) {
			return result;
		}
		InputStream inputStream = new java.io.ByteArrayInputStream(bs);
		ObjectMetadata objectMeta = new ObjectMetadata();
		// objectMeta.setContentLength(fileLength);
		objectMeta.setContentLength(inputStream.available());
		// 可以在metadata中标记文件类型

		String key = getKey(filename);
		client.putObject(bucketName, key, inputStream, objectMeta);
		return generateHTTPURL(bucketName) + key;
	}

	public static String uploadHtmlFile(String filename, byte[] bs) throws OSSException, ClientException, IOException {

		String result = null;
		if (StringUtils.isBlank(filename)) {
			return result;
		}
		if (bs == null || bs.length == 0) {
			return result;
		}
		InputStream inputStream = new java.io.ByteArrayInputStream(bs);
		ObjectMetadata objectMeta = new ObjectMetadata();
		// objectMeta.setContentLength(fileLength);
		objectMeta.setContentLength(inputStream.available());
		// 可以在metadata中标记文件类型

		client.putObject(bucketName, filename, inputStream, objectMeta);
		return generateHTTPURL(bucketName) + filename;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @return InputStream
	 * @throws OSSException
	 * @throws ClientException
	 */
	public static InputStream downloadFile(String key) throws OSSException, ClientException {

		OSSObject object = client.getObject(bucketName, key);
		return object.getObjectContent();
	}

	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	public static String downloadHtml(String key) throws OSSException, ClientException {

		OSSObject object = client.getObject(bucketName, key);
		InputStream in = object.getObjectContent();
		String ret = null;
		try {
			ret = inputStream2String(in);
		} catch (IOException e) {
			return null;
		}
		if (ret != null) {
			return ret;
		}
		return null;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static String getKey(String filename) {
		StringBuffer sb = new StringBuffer();

		sb.append(UUID.randomUUID().toString().replace("-", ""));
		sb.append(filename_ext(filename));
		return sb.toString();
	}

	/**
	 * 获取文件后缀名
	 * 
	 * @param filename
	 * @return
	 */
	public static String filename_ext(String filename) {
		String ret = "";
		String s = filename;
		int pos = s.lastIndexOf(".");
		if (pos == -1) {
			return ret;
		}
		// 获取最后一个"."的后缀
		while ((pos = s.indexOf(".")) != -1) {
			s = s.substring(pos + 1);
		}
		ret = "." + s;

		ret = ret.toLowerCase();

		return ret;
	}

	/**
	 * 生成访问url
	 * 
	 * @param bucket
	 * @return
	 */
	protected static String generateHTTPURL(String bucketName) {
		StringBuffer url = new StringBuffer();
		// url.append("http://" + bucketName + ".").append(ossHttpDomain);
		url.append("http://").append(ossHttpDomain);
		url.append("/");
		return url.toString();
	}

	/**
	 * 测试入口函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");

		String html = "<!doctype html><html lang=\"en\"> <body>asdfasdf </body></html>";
		
		

		//String uid = UploadOSSUtil.uploadHtmlFile(uuid, html.getBytes());
		// InputStream in = GetImage("http://img.99.com.cn/99/hospital/2309223671.jpg");
		// bucketName = "think-data";
		// String url =
		// UploadOSSUtil.uploadFile("/img/http://csdnimg.cn/www/images/csdnindex_logo.jpg",
		// in);
		System.out.println(uuid);
		
		InputStream in = HTMLUploadOSSUtil.downloadFile("3413dc2e11664bd2a06e6b395a95fc8b");
		System.out.println(in);
		
		
	}

	public static InputStream getStringStream(String sin) {
		if (sin != null && !sin.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sin.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static InputStream GetImage(String file) {
		try {
			URL url = new URL(file);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
			return inStream;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

}
