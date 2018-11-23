package com.qloudbiz.core.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qloudfin.qloudbus.security.discovery.SecurityContext;
import com.qloudfin.sagas.config.QloudSagaDBConfig;

import io.advantageous.boon.json.JsonFactory;

public class DbInit {

	private final static Logger logger = LoggerFactory.getLogger(DbInit.class);

	private final static String USER_NAME = "username";

	private final static String PASSWORD = "password";

	private static int validTimeOut = 0;

	private static QloudSagaDBConfig dbConfig;

	public DbInit() {
		// TODO Auto-generated constructor stub
	}

	private static Properties getBbConfig() {
		String application = System.getProperty("QLOUD_APPLICATION_CONFIG");
		// boolean securityUserd = Boolean.parseBoolean( System.getProperty(
		// "QLOUD_SECURITY_USED" ) );
		Map<String, Object> map = new HashMap<>();
		if (application != null) {
			map = JsonFactory.fromJson(application, Map.class);
			dbConfig = QloudSagaDBConfig.readConfig(JsonFactory.toJson(map.get("db")));
			logger.info("got app config {}", dbConfig);
		} else
			dbConfig = QloudSagaDBConfig.readConfig();

		// get username and password from security service
		if (dbConfig.getEngineName() != null && dbConfig.getEngineRole() != null) {
			Map<String, String> userMap = new HashMap<String, String>();
			try {
				userMap = JsonFactory.fromJson(SecurityContext.getSecurityDiscovery()
						.getTokenByRole(dbConfig.getEngineName(), dbConfig.getEngineRole()), Map.class);
			} catch (Throwable t) {
				logger.error("database security error is {} ", t);
				throw new RuntimeException("get database username and password error");
			}
			dbConfig.setUsername(userMap.get(USER_NAME));
			dbConfig.setPassword(userMap.get(PASSWORD));
		}

		logger.debug("db config is {} \n", JsonFactory.toJson(map.get("db")));
		validTimeOut = dbConfig.getValidTimeout();

		Properties p = new Properties();
		p.setProperty("user", dbConfig.getUsername());
		p.setProperty("password", dbConfig.getPassword());
		StringBuilder databaseUrl = new StringBuilder("jdbc:mariadb://");

		databaseUrl.append(dbConfig.getIp()).append(":").append(dbConfig.getPort()).append("/")
				.append(dbConfig.getDbname()).append("?maxPoolSize=" + dbConfig.getMaxPoolSize())
				.append("&autoReconnect=true");
		// .append( "?pinGlobalTxToPhysicalConnection=true&maxPoolSize=10" );
		// .append( "&connectTimeout=" + info.getConnectTimeout() );

		p.setProperty("url", databaseUrl.toString());

		return p;
	}

	public static void init() {
		Properties props = getBbConfig();
		Flyway flyway = Flyway.configure()
				.dataSource(props.getProperty("url"), props.getProperty("user"), props.getProperty("password")).load();
		flyway.migrate();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
