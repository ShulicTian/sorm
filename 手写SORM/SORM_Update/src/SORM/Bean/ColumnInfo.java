package SORM.Bean;

/**
 * 封装表中一个字段的信息
 * @author TIan
 *
 */
public class ColumnInfo {
	private String name;//字段名
	private String dataType;//字段数据类型
	private int keyType;//字段键类型（0 普通键 1主键 2外键）

	public ColumnInfo() {
		super();
	}
	
	public ColumnInfo(String name, String dataType, int keyType) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.keyType = keyType;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public int getKeyType() {
		return keyType;
	}
	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}
	
	
}
