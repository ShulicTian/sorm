package SORM.Core;

public class MysqlTypeConvertor implements TypeConvertor {

	@Override
	public String databaseType2JavaType(String javaDataType) {
		
		if("varchar".equalsIgnoreCase(javaDataType)||"char".equalsIgnoreCase(javaDataType)){
			return "String";
		}else if("int".equalsIgnoreCase(javaDataType)
				||"tinyint".equalsIgnoreCase(javaDataType)
				||"smallint".equalsIgnoreCase(javaDataType)
				||"integer".equalsIgnoreCase(javaDataType)
				){
			return "Integer";
		}else if("bigint".equalsIgnoreCase(javaDataType)){
			return "Long";
		}else if("double".equalsIgnoreCase(javaDataType)||"float".equalsIgnoreCase(javaDataType)){
			return "Double";
		}else if("clob".equalsIgnoreCase(javaDataType)){
			return "java.sql.Clob";
		}else if("blob".equalsIgnoreCase(javaDataType)){
			return "java.sql.Blob";
		}else if("date".equalsIgnoreCase(javaDataType)){
			return "java.sql.Date";
		}else if("time".equalsIgnoreCase(javaDataType)){
			return "java.sql.Time";
		}else if("timestamp".equalsIgnoreCase(javaDataType)){
			return "java.sql.Timestamp";
		}
		
		return null;
	}

	@Override
	public String javaType2DatabaseType(String javaDataType) {
		
				if("String".equalsIgnoreCase(javaDataType)){
					return "varchar";
				}else if("integer".equalsIgnoreCase(javaDataType)){
					return "int";
				}else if("long".equalsIgnoreCase(javaDataType)){
					return "bigint";
				}else if("double".equalsIgnoreCase(javaDataType)){
					return "double";
				}else if("java.sql.clob".equalsIgnoreCase(javaDataType)){
					return "clob";
				}else if("java.sql.blob".equalsIgnoreCase(javaDataType)){
					return "blob";
				}else if("java.sql.date".equalsIgnoreCase(javaDataType)){
					return "date";
				}else if("java.sql.time".equalsIgnoreCase(javaDataType)){
					return "time";
				}else if("java.sql.timestamp".equalsIgnoreCase(javaDataType)){
					return "timestamp";
				}
		return null;
	}

}
