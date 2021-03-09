package by.gourianova.binocularvision.dao.impl;

import by.gourianova.binocularvision.bean.News;
import by.gourianova.binocularvision.dao.DAOException;
import by.gourianova.binocularvision.dao.NewsDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLNewsDAO implements NewsDAO {
	public static final String DB_URL = "jdbc:h2:E:\\Program Files\\Web\\db\\exchange";
	static {
		MYSQLDriverLoader.getInstance();
	}

	@Override
	public List<News> all() throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<News> news = null;
		try {
			con =DriverManager.getConnection(DB_URL);
			//con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/news_management?useSSL=false&serverTimezone=UTC",
			//		"root", "778899");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/useSSL=false&serverTimezone=UTC",
					"root", "778899");
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM news");
			
			news = new ArrayList<News>();
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String brief = rs.getString("brief");
				News n = new News(id, title, brief);
				
				news.add(n);
				
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				//if (con!=null)
					con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return news;
	}

}
