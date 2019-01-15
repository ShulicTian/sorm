package SORM.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import SORM.Bean.ColumnInfo;
import SORM.Bean.JavaFieldGetSet;
import SORM.Bean.TableInfo;
import SORM.Core.DBManager;
import SORM.Core.MysqlTypeConvertor;
import SORM.Core.TableContext;
import SORM.Core.TypeConvertor;

/**
 * ��װ������java�ļ���Դ�ļ������õĲ���
 * @author TIan
 *
 */
public class JavaFileUtils {
	/**
	 * �����ֶ���Ϣ����java������Ϣ �� varchar username-->private String username;�Լ���Ӧ�� set get����
	 * @param column �ֶ���Ϣ
	 * @param convertor ����ת����
	 * @return ����java���Ժ�set/get����
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor){
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		String javaFieldType = convertor.databaseType2JavaType(column.getDataType());
		//��������
		jfgs.setFieldInfo("\tprivate "+javaFieldType+" "+column.getName()+";\n");
		//����getԴ��
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firestChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\t\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		//����setԴ��
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set"+StringUtils.firestChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType+" "+column.getName()+"){\n");
		setSrc.append("\t\tthis."+column.getName()+"="+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		
		return jfgs;
		
	}
	/**
	 * ���ݱ���Ϣ����java���Դ����
	 * @param tableinfo
	 * @param convertor
	 * @return java���Դ����
	 */
	public static String createJavaSrc(TableInfo tableinfo,TypeConvertor convertor){
		Map<String,ColumnInfo> columns = tableinfo.getColumns();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		
		for(ColumnInfo c:columns.values()){
			javaFields.add(createFieldGetSetSRC(c,convertor));
		}
		
		StringBuilder src = new StringBuilder();
		//����package
		src.append("package "+DBManager.getConf().getPersistoPackage()+";\n\n");
		//����import
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		//�������������
		src.append("public class "+StringUtils.firestChar2UpperCase(tableinfo.getTname())+" {\n\n");
		//���������б�
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		//����get�����б�
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getGetInfo());
		}
		//����set�����б�
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getSetInfo());
		}
		src.append("}\n");
		return src.toString();
	}
	
	public static void createJavaPOFile(TableInfo tableinfo,TypeConvertor convertor){
		String src = createJavaSrc(tableinfo,convertor);
		
		String srcPath = DBManager.getConf().getSrcPath()+"\\";
		String persistoPackage = DBManager.getConf().getPersistoPackage().replaceAll("\\.", "\\\\");
		File f = new File(srcPath+persistoPackage);
		if(!f.exists()){
			f.mkdirs();
		}
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+StringUtils.firestChar2UpperCase(tableinfo.getTname())+".java"));
			bw.write(src);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
//		ColumnInfo ci = new ColumnInfo("username","varchar",0);
//		JavaFieldGetSet f = createFieldGetSetSRC(ci, new MysqlTypeConvertor());
//		System.out.println(f);
		Map<String,TableInfo> map = TableContext.tables;
		TableInfo t = map.get("emp");
		createJavaPOFile(t,new MysqlTypeConvertor());
	}
	
}
