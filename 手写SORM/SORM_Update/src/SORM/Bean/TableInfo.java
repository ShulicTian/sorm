package SORM.Bean;

import java.util.List;
import java.util.Map;

/**
 * �洢��ṹ����Ϣ
 * @author TIan
 *
 */
public class TableInfo {
	private String tname;//����
	private Map<String,ColumnInfo> columns;//�����ֶε���Ϣ
	private ColumnInfo onlyPriKey;//Ψһ����
	private List<ColumnInfo> prikeys;//�������� �ڴ˴���
	
	public List<ColumnInfo> getPrikeys() {
		return prikeys;
	}

	public void setPrikeys(List<ColumnInfo> prikeys) {
		this.prikeys = prikeys;
	}

	public TableInfo() {
		super();
	}
	
	public TableInfo(String tname, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
		super();
		this.tname = tname;
		this.columns = columns;
		this.onlyPriKey = onlyPriKey;
	}
	
	public TableInfo(String tableName, List<ColumnInfo> arrayList, Map<String, ColumnInfo> columns) {
		super();
		this.tname = tableName;
		this.prikeys = arrayList;
		this.columns = columns;
	}

	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}
	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}
	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}
	
}
