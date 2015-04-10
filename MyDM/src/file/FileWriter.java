package file;

import java.io.BufferedWriter;
import java.io.File;

public class FileWriter {
	private File file = null;
	private java.io.FileWriter fw = null;
	private BufferedWriter bw = null;

	/**
	 * ���캯��
	 * 
	 * @param path
	 *            �ļ�·��
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
	 * �ر��ļ�
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
	 * дһ��
	 * 
	 * @param line
	 *            һ��
	 * @param is_out_screen
	 *            �Ƿ����뵽��Ļ
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
	 * дһ��
	 * 
	 * @param line
	 *            һ��
	 */
	public void writeLine(String line) {
		writeLine(line, true);
	}
}
