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
	 * ���캯��
	 * 
	 * @param path
	 *            �ļ�·��
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
	 * �ر��ļ�
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
	 * ��ȡһ��
	 * 
	 * @param is_out_screen
	 *            �Ƿ����뵽��Ļ
	 * @return һ��
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
	 * ��ȡһ��
	 * 
	 * @return һ��
	 */
	public String readLine() {
		return readLine(true);
	}
}
