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
 * 封装了生成java文件（源文件）常用的操作
 * @author TIan
 *
 */
public class JavaFileUtils {
	/**
	 * 根据字段信息生成java属性信息 如 varchar username-->private String username;以及相应的 set get方法
	 * @param column 字段信息
	 * @param convertor 类型转换器
	 * @return 返回java属性和set/get方法
	 */
	public static JavaFieldGetSet createFieldGetSetSRC(ColumnInfo column,TypeConvertor convertor){
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		String javaFieldType = convertor.databaseType2JavaType(column.getDataType());
		//生成属性
		jfgs.setFieldInfo("\tprivate "+javaFieldType+" "+column.getName()+";\n");
		//生成get源码
		StringBuilder getSrc = new StringBuilder();
		getSrc.append("\tpublic "+javaFieldType+" get"+StringUtils.firestChar2UpperCase(column.getName())+"(){\n");
		getSrc.append("\t\treturn "+column.getName()+";\n");
		getSrc.append("\t}\n");
		jfgs.setGetInfo(getSrc.toString());
		//生成set源码
		StringBuilder setSrc = new StringBuilder();
		setSrc.append("\tpublic void set"+StringUtils.firestChar2UpperCase(column.getName())+"(");
		setSrc.append(javaFieldType+" "+column.getName()+"){\n");
		setSrc.append("\t\tthis."+column.getName()+"="+column.getName()+";\n");
		setSrc.append("\t}\n");
		jfgs.setSetInfo(setSrc.toString());
		
		return jfgs;
		
	}
	/**
	 * 根据表信息生成java类的源代码
	 * @param tableinfo
	 * @param convertor
	 * @return java类的源代码
	 */
	public static String createJavaSrc(TableInfo tableinfo,TypeConvertor convertor){
		Map<String,ColumnInfo> columns = tableinfo.getColumns();
		List<JavaFieldGetSet> javaFields = new ArrayList<JavaFieldGetSet>();
		
		for(ColumnInfo c:columns.values()){
			javaFields.add(createFieldGetSetSRC(c,convertor));
		}
		
		StringBuilder src = new StringBuilder();
		//生成package
		src.append("package "+DBManager.getConf().getPersistoPackage()+";\n\n");
		//生成import
		src.append("import java.sql.*;\n");
		src.append("import java.util.*;\n\n");
		//生成类声明语句
		src.append("public class "+StringUtils.firestChar2UpperCase(tableinfo.getTname())+" {\n\n");
		//生成属性列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getFieldInfo());
		}
		src.append("\n\n");
		//生成get方法列表
		for(JavaFieldGetSet f:javaFields){
			src.append(f.getGetInfo());
		}
		//生成set方法列表
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
