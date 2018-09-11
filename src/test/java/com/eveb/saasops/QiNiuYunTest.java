package com.eveb.saasops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eveb.saasops.common.utils.FastDFSUtil;
import com.eveb.saasops.modules.common.service.QiNiuYunUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QiNiuYunTest {
	@Autowired
	private QiNiuYunUtil qiNiuYunUtil;
	@Autowired
	private FastDFSUtil fastDFSUtil;

	// 查询七牛云上所有的文件名
	@Test
	public void test002() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<String> list = qiNiuYunUtil.bucketFileList();
		for(String str:list) {
			System.out.println(str);
		}
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// 删除对应的文件
	@Test
	public void test003() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.deleteFile("FmTG8HZNI5gBU8Aqpoudz-0w6ruw.png");
		qiNiuYunUtil.deleteFile("FhAUqPq3FZjRSDYZKK4TOKfnac3T.png");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// 通过字节数组与文件名的形式上传
	@Test
	public void test004() throws Exception {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		File file = new File("F:\\111.png");
		InputStream in = new FileInputStream(file);
		byte[] data = qiNiuYunUtil.readInputStreamAsByteArray(in);
		String fileName = file.getName();
		String uploadFile = qiNiuYunUtil.uploadFile(data, fileName);
		System.out.println(uploadFile);// FmTG8HZNI5gBU8Aqpoudz-0w6ruw.png
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");

	}

	// 通过本地文件上传,传入文件路径
	@Test
	public void test005() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String uploadFile = qiNiuYunUtil.uploadFile("C:\\Users\\Tony\\Desktop\\final\\3.jpg");
		System.out.println(uploadFile);
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// 通过本地文件上传，传入文件夹路径
	@Test
	public void test0005() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String path="C:\\Users\\Tony\\Desktop\\final";
		File file = new File(path);
		String[] filesName = file.list();
		for (String fileName : filesName) {
			String uploadFile = qiNiuYunUtil.uploadFile(path+"\\"+fileName);
			System.out.println(uploadFile);
		}
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// 通过文件名下载文件
	@Test
	public void test006() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.downLoadFile("FlHhseBeqAIG-JbeDUbEvrNTRokp", "D:\\");
		System.out.println("OK");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	@Test
	public void test007() {
		try {
			String url = "jdbc:mysql//192.168.5.30:8066";
			String username = "root";
			String password = "myCat_dev";
			String table = "tableName";
			String column1 = "id";
			String column2 = "picPcPath";
			
			File file = new File("");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, password);
			String sql = "select * from " + table;
			FileWriter fw = new FileWriter(file);
			BufferedWriter bfw = new BufferedWriter(fw);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt(column1);
				String picPath = resultSet.getString("column2");
				InputStream inputStream = fastDFSUtil.downloadFile("", picPath);
				byte[] data = qiNiuYunUtil.readInputStreamAsByteArray(inputStream);
				String uploadFile = qiNiuYunUtil.uploadFile(data, picPath);
				bfw.write("update " + table + " set " + column2 + "=" + uploadFile + " where " + column1 + "=" + id
						+ ";");
			}
			bfw.close();
		} catch (Exception e) {

		}
	}
	/*++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
	//opr_adv
	@Test
	public void replace001() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "opr_adv", "id",
				"picPcPath", "F:\\000\\opr_adv_picPcPath.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	@Test
	public void replace002() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "opr_adv", "id",
				"picMbPath", "F:\\000\\opr_adv_picMbPath.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	//opr_act_activity
	@Test
	public void replace003() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "opr_act_activity", "id",
				"pcLogoUrl", "F:\\000\\opr_act_activity_pcLogoUrl.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	@Test
	public void replace004() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "opr_act_activity", "id",
				"mbLogoUrl", "F:\\000\\opr_act_activity_mbLogoUrl.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	//set_basic_set_sys_setting
	//单独手动替换
	
	
	//t_opr_adv
	@Test
	public void replace005() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_opr_adv", "id",
				"picPcPath", "F:\\000\\t_opr_adv_picPcPath.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	public void replace006() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_opr_adv", "id",
				"picMbPath", "F:\\000\\t_opr_adv_picMbPath.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	//t_op_pay
	public void replace009() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_op_pay", "id",
				"mBankLog", "F:\\000\\t_op_pay_mBankLog.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	
	//t_game_logo
	@Test
	public void replace012() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_game_logo", "id",
				"picUrl", "F:\\000\\t_game_logo_picUrl.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	@Test
	public void replace013() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_game_logo", "id",
				"mbPicUrl", "F:\\000\\t_game_logo_mbPicUrl.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	//t_gm_game
	@Test
	public void replace014() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_gm_game", "id",
				"logo", "F:\\000\\t_gm_game_logo.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	@Test
	public void replace015() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_gm_game", "id",
				"logo2", "F:\\000\\t_gm_game_logo2.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
	//t_bs_bank
	@Test
	public void test0016() {
		System.out.println("begin>>>>>>>>>>>>>>>>>>>>>>>>>>");
		qiNiuYunUtil.replaceTablePath("jdbc:mysql://202.61.86.162:8066/saasops_a002", "root", "Dashujushiyindanmie", "t_bs_bank", "id",
				"bankLog", "F:\\000\\t_bs_bank_bankLog.txt");
		System.out.println("end<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

}
