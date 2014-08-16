package ibatis;

import ibatis.entitys.Channel;
import ibatis.mappers.ChannelMapper;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main {
	private static final String configLocation = "ibatis/cfg/MapperConfig.xml";
	private static final ThreadLocal<SqlSession> tSession = new ThreadLocal<SqlSession>();
	private static SqlSessionFactory sessionBuilder;
	
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(configLocation);
			sessionBuilder = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		SqlSession session = null;
		try {
			session = getSession();
			List<Channel> allChls = session.getMapper(ChannelMapper.class).selAllChls();
			
			
			Channel parent;
			Set<Channel> childs;
			for (Channel chl : allChls) {
				parent = chl.getParent();
				childs = chl.getChilds();
				
				System.out.println(chl.getName() + ": PARENT -> " + (parent == null ? null : parent.getName()));
				if (childs != null && childs.size() > 0) {
					for (Channel c : childs) {
						System.out.println(chl.getName() + ": CHILD -> " + c.getName());
					}
				} else {
					System.out.println(chl.getName() + ": CHILD -> NULL");
				}
			}
			
		} finally {
			closeSession();
		}
	}
	
	public static SqlSession getSession() {
		SqlSession session = tSession.get();
		
		if (session == null) {
			session = sessionBuilder.openSession();
			tSession.set(session);
		}
		
		return session;
	}
	
	public static void commit() {
		getSession().commit();
	}
	
	public static void rollback() {
		getSession().rollback();
	}
	
	public static void clearCache() {
		getSession().clearCache();
	}
	
	public static void closeSession() {
		SqlSession session = tSession.get();
		
		tSession.set(null);
		
		if (session != null) {
			session.close();
		}
	}
}
