package com.supperarrow.directory.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.supperarrow.directory.model.Article;
import com.supperarrow.directory.util.LoggerUtil;

public class DbUtils {
	final static Logger logger = LoggerUtil.getDailyLogger("DbUtils_log");

	public final static int initPoolSize = 3;
	public final static int maxPoolSize = 10;

	private DatabaseConfig dbConfig = new DatabaseConfig();
	private ConnectionPool poolConnection = null;
	private Connection connection = null;


	// init database utils
	public DbUtils() {
		String[] dbInfor = dbConfig.getConnectionProperty();
		System.out.println("__" + dbInfor[0] + "___" + dbInfor[1] + "____" + dbInfor[2]);
		// init pool connection
		try {
			poolConnection = new ConnectionPool(maxPoolSize, initPoolSize, dbInfor[0], dbInfor[1], dbInfor[2],
					"com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(dbInfor[0], dbInfor[1], dbInfor[2]);

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

	}

	public List<Article> getArticleFromDB(long cid, int start, int end) {
		List<Article> articleList = new ArrayList<>();
		logger.info("start get article : " + cid + " " + start + " " + end);
		try {

			Connection conn = connection;
			if(conn == null){
				logger.error("Can get mysql connection");
				return articleList;
			}
			
			// our SQL SELECT query.
			// if you only need a few columns, specify them by name instead of
			// using "*"
			String query = "SELECT * FROM article WHERE wid = 83 AND cid = " + cid + " order by published desc limit " + start + " , " + end + " ;";
			logger.info(query);
			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);
			
			// iterate through the java resultset
			while (rs.next()) {
				try {
				Article item = new Article();
				item.setId(rs.getInt("id"));
				item.setUrl(rs.getString("url"));
				item.setTitle(rs.getString("title"));
				item.set_abstract(rs.getString("abstract"));
				item.setContent(rs.getString("content"));
				item.setHtml_Content(rs.getString("html_content"));
				item.setCoverUrl(rs.getString("cover_url"));
				item.setPublishedTime(rs.getTimestamp("published").getTime());
				item.setCrawledTime(rs.getTimestamp("crawled").getTime());
				item.setWebsiteId(rs.getInt("wid"));
				item.setCategoryId(rs.getInt("cid"));
				
				articleList.add(item);
				System.out.println(item);
				logger.info(item);
				// print the results
				//System.out.format("%s, %s, %s, %s, %s, %s\n", id, firstName, lastName, dateCreated, isAdmin, numPoints);
				} catch (Exception e) {
					logger.error("get db error : " + e);
				}
			}
			st.close();
		} catch (Exception e) {
			logger.error("db error: " + e);
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return articleList;
	}

	public long insertArticleToDatabase(Article item) throws SQLException {
		long result = -1;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO article"
				+ "(url, title, abstract, content, html_content, cover_url, published, crawled, wid, cid) VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = getDbConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

			// dbConnection.setAutoCommit(false);
			// for(Article item : data){
			preparedStatement.setString(1, item.getUrl());
			preparedStatement.setString(2, item.getTitle());
			preparedStatement.setString(3, item.get_abstract());
			preparedStatement.setString(4, item.getContent());
			preparedStatement.setString(5, item.getHtml_Content());
			preparedStatement.setString(6, item.getCoverUrl());
			preparedStatement.setTimestamp(7, new Timestamp(item.getPublishedTime()));
			preparedStatement.setTimestamp(8, new Timestamp(item.getCrawledTime()));
			preparedStatement.setInt(9, item.getWebsiteId());
			preparedStatement.setInt(10, item.getCategoryId());

			// preparedStatement.addBatch();
			// }
			preparedStatement.executeUpdate();
			// preparedStatement.executeBatch();
			//
			// dbConnection.commit();
			ResultSet insRs = preparedStatement.getGeneratedKeys();
			if (insRs.next()) {
				result = insRs.getInt(1);
				System.out.println("Record is inserted into article table!");
			}
			insRs.close();

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return result;
	}

	public void insertListArticleToDatabase(List<Article> data) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO article"
				+ "(url, title, abstract, content, html_content, cover_url, published, crawled, wid, cid) VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?)";

		try {
			dbConnection = getDbConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			dbConnection.setAutoCommit(false);
			for (Article item : data) {
				preparedStatement.setString(1, item.getUrl());
				preparedStatement.setString(2, item.getTitle());
				preparedStatement.setString(3, item.get_abstract());
				preparedStatement.setString(4, item.getContent());
				preparedStatement.setString(5, item.getHtml_Content());
				preparedStatement.setString(6, item.getCoverUrl());
				preparedStatement.setTimestamp(7, new Timestamp(item.getPublishedTime()));
				preparedStatement.setTimestamp(8, new Timestamp(item.getCrawledTime()));
				preparedStatement.setInt(9, item.getWebsiteId());
				preparedStatement.setInt(10, item.getCategoryId());

				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();

			dbConnection.commit();

			// System.out.println("Record is inserted into article table:!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
	}

	// get connection database from pool
	public Connection getDbConnection(){
		
		if (poolConnection != null) {
			try {
				logger.info("borrow connection");
				return poolConnection.borrowConnection();
			} catch (InterruptedException | SQLException e) {
				logger.error(e);
				e.printStackTrace();
			}
		}

		return null;
	}
	
	
//	public static void main(String[] args) {
//		getArticleFromDB(1795, 0, 10);
//	}
}
