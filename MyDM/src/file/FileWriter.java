package file;

import java.io.BufferedWriter;
import java.io.File;

public class FileWriter {
	private File file = null;
	private java.io.FileWriter fw = null;
	private BufferedWriter bw = null;

	/**
	 * 构造函数
	 * 
	 * @param path
	 *            文件路径
	 */
	public FileWriter(String path) {
		try {
			this.file = new File(path);
			this.fw = new java.io.FileWriter(file, true);
			this.bw = new BufferedWriter(fw);
		} catch (Exception e) {
			this.close();
		}

	}

	/**
	 * 关闭文件
	 */
	public void close() {
		try {
			this.bw.flush();
			this.bw.close();
			this.fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.bw = null;
		}
	}

	/**
	 * 写一行
	 * 
	 * @param line
	 *            一行
	 * @param is_out_screen
	 *            是否输入到屏幕
	 */
	public void writeLine(String line, boolean is_out_screen) {
		if (bw != null) {
			try {
				bw.write(line);
				bw.newLine();
				if (is_out_screen) {
					System.out.println(line);
				}
			} catch (Exception e) {
				this.close();
			}
		}
	}

	/**
	 * 写一行
	 * 
	 * @param line
	 *            一行
	 */
	public void writeLine(String line) {
		writeLine(line, true);
	}
}
