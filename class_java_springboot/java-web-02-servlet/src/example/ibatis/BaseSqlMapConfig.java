package example.ibatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BaseSqlMapConfig {

	private static final SqlMapClient sqlMapClient;

	static {
		try {
			String resource = "./config/ibatis/sql-map-config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			System.out.println("sqlMapClient : " + sqlMapClient);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ibatis config error : " + e);
		}
	}

	public static SqlMapClient getSqlMapInstance() {
		return sqlMapClient;
	}
}
