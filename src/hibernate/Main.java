package hibernate;

import hibernate.entitys.Channel;

import java.util.Date;
import java.util.UUID;

import org.hibernate.CacheMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class Main {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate/cfg/hbn_proxool.cfg.xml");
		
		SessionFactory sf = cfg.buildSessionFactory();
		
		Session s = sf.openSession();

		s.setCacheMode(CacheMode.IGNORE);
		
		Transaction tx = s.beginTransaction();
		
		for (int i = 0; i < 100000; i++) {
			Channel chl = new Channel();
			chl.setName(UUID.randomUUID().toString());
			chl.setOrder(i);
			chl.setCreateBy((long)(Math.random() * i * 1000));
			chl.setCreateDate(new Date());
			chl.setLastModifiedBy((long)(Math.random() * i * 1000));
			chl.setLastModifiedDate(new Date());
			
			s.save(chl);
			
			if (i % 500 == 0) {
				s.flush();
				s.clear();
			}
		}
		
		tx.commit();
		s.close();
		sf.close();
	}
}
