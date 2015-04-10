package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileReader {
	private File file = null;
	private java.io.FileReader fr = null;
	private BufferedReader br = null;

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            文件路径
	 */
	public FileReader(String path) {
		try {
			this.file = new File(path);
			this.fr = new java.io.FileReader(file);
			this.br = new BufferedReader(fr);
		} catch (Exception e) {
			this.close();
		}

	}

	/**
	 * 关闭文件
	 */
	public void close() {
		try {
			this.br.close();
			this.fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.br = null;
		}
	}

	/**
	 * 读取一行
	 * 
	 * @param is_out_screen
	 *            是否输入到屏幕
	 * @return 一行
	 */
	public String readLine(boolean is_out_screen) {
		String str = null;
		if (this.br != null) {
			try {
				str = this.br.readLine();
				if (is_out_screen) {
					System.out.println(str == null ? "null" : str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}

	/**
	 * 读取一行
	 * 
	 * @return 一行
	 */
	public String readLine() {
		return readLine(true);
	}
}
